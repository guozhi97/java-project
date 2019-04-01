/* 全部变量 */
var areaPath = "admin", controllerPath = "Home";
var areaNaviKey = "my_navi_cookie";
var preloader = $("#preloader"), mainMenu = $("#main-nav"), popMenu = $("#pop-menu"), logOnCheckedObj = $("input#logOnChecked"), logonAlert = $("#logonAlert");
var frameRight = window.frames["right"];

$(function () {
    /*初始化根路径*/
    var basePath = Common.BasePath;    
    var myAreaObj = $("input#myAreaName");
    var myControllerObj = $("input#myController");
    if (myAreaObj) {
        areaPath = [basePath, myAreaObj.val()].join("");
    } if (myControllerObj) {
        controllerPath = [areaPath, myControllerObj.val()].join("");
    }
    Common.AreaPath = areaPath;
    Common.ControllerPath = controllerPath;

    /*加载管理目录的菜单资源*/
    var sideNavUri = [areaPath, "/sidebar.jsp"].join("");
    //var logonAlertUri = [areaPath, "/json_latestLogOn.js"].join("");
    loadMenuTree(sideNavUri, true);
//    loadLogonAlert(logonAlertUri);

    /* 基于 Bootstrap Js 组件的可关闭的警告框*/
    window.setTimeout(function() { logonAlert.fadeOut(1000); }, 10000);
    
    //主页面响应式
    mainPageResize(); 

    //页面尺寸改变时
    $(window).on("resize", function () {
        //延迟执行,防止多次触发
        setTimeout(function () {
            mainPageResize(); //主页面响应式
        }, 100);
    });
});

/**
 * 最近安全登录信息
 * @param {} apiUrl 
 * @returns {} 
 */
function loadLogonAlert(apiUrl) {
    if (apiUrl) {
        $.ajax({
            type: "GET",
            url: apiUrl,
            dataType: "json",
            success: function (data, textStatus) {
                /*将得到的数据交互到页面中*/
                var result = data || { rows: [], logOnChecked: 0 }
                logOnCheckedObj.val(result.logOnChecked);
                if (result.rows && result.rows.length > 0) {
                    var first = result.rows[0] || { ClientIp: "::1", ClientLocation: "" };
                    var htmlFirst = [first.ClientIp];
                    if (first.ClientLocation) {
                        htmlFirst.push(first.ClientLocation);
                    }
                    $("#logonCurrentIp").text(htmlFirst.join(", "));
                    if (result.rows.length > 1) {
                        var second = result.rows[1] || { ClientIp: "::1", ClientLocation: "", CreateTime: "" };
                        var htmlSecond = [second.ClientIp];
                        if (second.ClientLocation) {
                            htmlSecond.push(second.ClientLocation);
                        }
                        $("#logonLatestIp").text(htmlSecond.join(", "));
                        $("#logonAlertLatestIp").show();
                        if (second.CreateTime) {
                            $("#shield").addClass("fa-3x");
                            $("#logonLatestTime").text(second.CreateTime);
                            $("#logonAlertLatestTime").show();
                        }
                    }
                }
                f_onLogonAlert();
            },
            error: function (msg, testStatus) {
                return false;
            }
        });
    }
}

/*显示与否安全登录信息*/
function f_onLogonAlert() {
    var logOnCheckedOnce = parseInt(logOnCheckedObj.val());
    if (logOnCheckedOnce == 0) {
        logonAlert.addClass("active");
    } else {
        logonAlert.remove();
    }
}

/*页面重置*/
function mainPageResize() {
    var docWidth = $(window).width();
    if (docWidth >= 768) {
        $("body").removeClass("lay-mini");
        mainMenu.show();
    } else {
        $("body").addClass("lay-mini");
        mainMenu.hide();
    }
}

/*切换按钮显示事件*/
function toggleMainMenu() {
    $("body").toggleClass("lay-mini");
    if (!$("body").hasClass("lay-mini") && $(window).width() > 768) {
        mainMenu.show();
    } else {
        mainMenu.hide();
    }
}

/**
 * 显示布局页 Loading 块
 * @returns {} 
 */
function showLoadingMain(text) {
    var statusObj = preloader.children(".status");
    preloader.show()
    if(text){
        statusObj.text(text);
    }
    statusObj.show();
}

/**
 * 隐藏布局页 Loading 状态条
 * @returns {} 
 */
function hideLoadingMain() {
    preloader.children(".status").delay(300).fadeOut();
    preloader.delay(500).fadeOut("slow");
}

/*
 * 加载管理首页左边导航菜单
 */
function loadMenuTree(apiUrl, islink) {
    /* apiUrl 带上时间戳 */
    apiUrl = [apiUrl, "?time=", Math.random()].join("");

    /*判断是否跳转链接*/
    var flagIsLink = false;
    if (arguments.length == 2 && islink) {
        flagIsLink = true;
    }
    /*初始化导航菜单，发送AJAX请求*/
    $.ajax({
        type: "GET",
        url: apiUrl,
        dataType: "html",
        success: function(data, textStatus) {
            /*将得到的数据插件到页面中*/
            $("#sidebar-nav").html(data);
            $("#pop-menu .list-box").html(data);
            /*初始化导航菜单*/
            initMenuTree(flagIsLink);
            initPopMenuTree();
        },
        error: function(data, testStatus) {
            //$("#sidebar-nav").html(JSON.stringify(msg));
            var msg = ['<div style="padding: 10px; color: #9f9f9f;">'];
            if (data.status == 403) {
                msg.push('<strong>403 Forbidden.</strong> <br/>请重新<a href="');
                msg.push(areaPath);
                msg.push('/Account/Logout">【登录】</a>');
            } else {
                msg.push("网络响应异常，您可以尝试<a href='javascript: window.location.reload();'>重新加载</a>页面.");
            }
            msg.push("</div>");
            $("#sidebar-nav").html(msg.join(""));
        }
    });
}

/*
 * 初始化导航菜单
 */
function initMenuTree(islink) {
    var navObj = $("#main-nav");
    var navGroupObj = $("#sidebar-nav .list-group");
    //先清空 main-nav 中菜单内容
    navObj.html("");
    navGroupObj.each(function (i) {
        //h1
        var h1 = $(this).children("h1"), h2 = $(this).find(".list-wrap > h2");        

        //添加菜单导航
        var navHtml = $("<li></li>").appendTo(navObj);
        navHtml.append(["<a><span>", h1.attr("title"), "</span></a>"].join(""));

        //默认选中第一项
        if (i == 0) {
            $(this).show();
            navHtml.addClass("selected");
        }
        //为菜单添加事件
        navHtml.click(function () {
            navObj.children("li").removeClass("selected");
            $(this).addClass("selected");
            navGroupObj.hide();
            navGroupObj.eq(navObj.children("li").index($(this))).show();
        });

        if (typeof (h2.attr("icon")) != "undefined") {
            h2.html(['<i class="fa fa-fw ', h2.attr("icon") ,'" aria-hidden="true"></i>', h2.text()].join(""));
        } 
        //首先隐藏所有的UL
        $(this).find("ul").hide();
        //绑定树菜单事件.开始
        $(this).find("ul").each(function (j) { //遍历所有的UL
            //遍历UL第一层LI
            $(this).children("li").each(function () {
                var liObj = $(this);
                var liLinkObj = liObj.children("a");
                var spanObj = liLinkObj.children("span");
                //判断是否有子菜单和设置距左距离
                var parentIconLenght = liObj.parent().parent().children("a").children(".icon").length; //父节点的左距离
                //设置左距离
                var lastIconObj = new Object();
                for (var n = 0; n <= parentIconLenght; n++) { //注意<=
                    lastIconObj = $('<i class="icon"></i>').insertBefore(spanObj); //插入到span前面
                }

                //如果有下级菜单
                if (liObj.children("ul").length > 0) {
                    liLinkObj.removeAttr("href"); //删除链接，防止跳转
                    liLinkObj.append('<i class="expandable fa fa-plus" title="展开"></i>'); //最后插件一个+-
                    //如果a有自定义图标则将图标插入，否则使用默认的样式
                    if (typeof (liLinkObj.attr("icon")) != "undefined") {
                        lastIconObj.addClass(liLinkObj.attr("icon"));
                    } else {
                        lastIconObj.addClass("fa fa-folder-o");
                    }
                    //隐藏下级的UL
                    liObj.children("ul").hide();
                    //绑定单击事件
                    liLinkObj.on("click", function () {
                        //如果菜单已展开则闭合
                        var iconObj = $(this).children(".icon");
                        var expandableObj = $(this).children(".expandable");
                        if (expandableObj.hasClass("fa-minus")) {
                            //设置自身的右图标为+号
                            expandableObj.removeClass("fa-minus").addClass("fa-plus").attr("title", "展开");
                            //隐藏自身父节点的UL子菜单
                            $(this).siblings("ul").slideUp(300);
                            if (iconObj.hasClass("fa-folder-open-o")) {
                                //设置文件夹图标折叠
                                iconObj.removeClass("fa-folder-open-o").addClass("fa-folder-o");
                            }
                        } else {
                            //搜索所有同级LI且有子菜单的右图标为+号及隐藏子菜单
                            $(this).parent().siblings().each(function () {
                                if ($(this).children("ul").length > 0) {
                                    //设置自身的右图标为+号
                                    $(this).children("a").children(".expandable").removeClass("fa-minus").addClass("fa-plus").attr("title", "展开");
                                    var mIconObj = $(this).children("a").children(".icon");
                                    if(mIconObj.hasClass("fa-folder-open-o")){
                                        mIconObj.removeClass("fa-folder-open-o").addClass("fa-folder-o");
                                    }
                                    //隐藏自身子菜单
                                    $(this).children("ul").slideUp(300);
                                }
                            });
                            //设置自身的右图标为-号
                            expandableObj.removeClass("fa-plus").addClass("fa-minus").attr("title", "收起");
                            if(iconObj.hasClass("fa-folder-o")){
                                iconObj.removeClass("fa-folder-o").addClass("fa-folder-open-o");
                            }
                            //显示自身父节点的UL子菜单
                            $(this).siblings("ul").slideDown(300);
                        }
                    });

                } else {
                    //超链接相对路径转绝对路径
                    var a = document.createElement('A');
                    a.href = liLinkObj.attr("href"); 
                    //console.log(a.href);
                    liLinkObj.attr("href", a.href);

                    if(frameRight){
                        liLinkObj.attr("target", "right");
                    }
                    //如果a有自定义图标则将图标插入，否则使用默认的样式
                    if (typeof (liLinkObj.attr("icon")) != "undefined") {
                        lastIconObj.addClass(liLinkObj.attr("icon"));
                    } else if (typeof (liLinkObj.attr("href")) == "undefined" || liLinkObj.attr("href").length < 2) { //如果没有链接
                        liLinkObj.removeAttr("href");
                        lastIconObj.addClass("fa fa-folder-o");
                    } else {                        
                        lastIconObj.addClass("fa fa-file-text-o");
                    }
                    if (typeof (liLinkObj.attr("href")) != "undefined") {
                        //绑定单击事件
                        liLinkObj.on("click", function () {
                            //删除所有的选中样式
                            navGroupObj.find("ul li a").removeClass("selected");
                            //删除所有的list-group选中样式
                            navGroupObj.removeClass("selected");
                            //删除所有的main-nav选中样式
                            navObj.children("a").removeClass("selected");
                            //自身添加样式
                            $(this).addClass("selected");
                            //设置父list-group选中样式
                            $(this).parents(".list-group").addClass("selected");
                            //设置父main-nav选中样式
                            navObj.children("a").eq(navGroupObj.index($(this).parents(".list-group"))).addClass("selected");
                            //隐藏所有的list-group
                            navGroupObj.hide();
                            //显示自己的父list-group
                            $(this).parents(".list-group").show();
                            //保存到cookie
                            if (typeof ($(this).attr("navid")) != "undefined") {
                                addCookie(areaNaviKey, $(this).attr("navid"), 240, areaPath);
                            }
                        });
                    }
                }
            });
            //显示第一个UL
            if (j == 0) {
                $(this).show();
                //展开第一个菜单
                var first = $(this).children("li").first(), firstChildrenObj = first.children("ul"), firstLinkObj = first.children("a");                
                if (firstChildrenObj.length > 0) {
                    var mIconObj = firstLinkObj.children(".icon");
                    firstLinkObj.children(".expandable").removeClass("fa-plus").addClass("fa-minus").attr("title", "收起");
                    if(mIconObj.hasClass("fa-folder-o")){
                        mIconObj.removeClass("fa-folder-o").addClass("fa-folder-open-o");
                    }
                    firstChildrenObj.show();
                }
            }
        });
        //绑定树菜单事件.结束
    });
    //定位或跳转到相应的菜单    
    linkMenuTree(islink);
}

/**
 * 定位或跳转到相应的菜单
 * @param {} islink 
 * @param {} navid 
 * @returns {} 
 */
function linkMenuTree(islink, navid) {
    var navMainObj = $("#main-nav"), navGroupObj = $("#sidebar-nav .list-group"), routeObj;       
    var myNavId = getCookie(areaNaviKey);
    if (arguments.length == 2) {
        myNavId = navid;
    }
    //routeObj = navGroupObj.find('a[navid="' + navid + '"]');
    var maps = navGroupObj.find("a").map(function(){
        var self = $(this);
        if(self.attr("navid") == myNavId || myNavId.indexOf(self.attr("href")) > -1 ){
            return self;
        }
    }).get();

    /*如果存在, routeObj 则定位到对应的导航*/
    if(maps && maps.length > 0){
        routeObj = maps[0];

        /*删除所有的list-group选中样式*/
        navGroupObj.removeClass("selected");

        /*删除所有的链接选中的样式*/
        navGroupObj.find("ul li a").removeClass("selected");

        /*删除所有的main-nav选中样式*/
        navMainObj.children("li").removeClass("selected");

        /*自身添加样式*/
        routeObj.addClass("selected");

        /*设置父list-group选中样式*/
        var group = routeObj.parents(".list-group").addClass("selected");

        /*设置 main-nav 中对应块选中样式*/
        navMainObj.children("li").eq(navGroupObj.index(group)).addClass("selected");

        /*隐藏所有的list-group*/
        navGroupObj.hide();

        /*显示自己的父list-group*/
        group.show();
        
        /*遍历所有的LI父节点*/
        routeObj.parents("li").each(function () {
            /*搜索所有同级 li 元素*/
            $(this).siblings().each(function () {
                if ($(this).children("ul").length > 0) {
                    /*设置自身的右图标为+号*/
                    var mLinkObj = $(this).children("a"), mIconObj = mLinkObj.children(".icon");
                    mLinkObj.children(".expandable").removeClass("fa-minus").addClass("fa-plus").attr("title", "展开");
                    if(mIconObj.hasClass("fa-folder-open-o")){
                        mIconObj.removeClass("fa-folder-open-o").addClass("fa-folder-o");
                    }
                    /*隐藏自身子菜单*/
                    $(this).children("ul").hide();
                }
            });
            /*设置自身的右图标为-号*/
            if ($(this).children("ul").length > 0) {
                var mLinkObj = $(this).children("a"), mIconObj = mLinkObj.children(".icon");                
                mLinkObj.children(".expandable").removeClass("fa-plus").addClass("fa fa-minus").attr("title", "收起");
                if(mIconObj.hasClass("fa-folder-o")){
                    mIconObj.children(".icon").removeClass("fa-folder-o").addClass("fa-folder-open-o");
                }
            }
            /*显示自身的 UL 元素*/
            $(this).children("ul").show();
        });
        /*检查是否需要保存到 cookie */
        if (arguments.length == 2) {
            addCookie(areaNaviKey, navid, 240, areaPath);
        }
        //检查是否需要跳转链接
        if (islink == true && routeObj.attr("href") != "" && routeObj.attr("href") != "#") {
            if(frameRight){
                frameRight.location.href = routeObj.attr("href");
            }else{
                window.location.href = routeObj.attr("href");
            }
        }
    } else if (arguments.length == 2) {
        /*删除所有的选中样式*/
        navGroupObj.find("ul li a").removeClass("selected");
        /*置空cookie*/
        addCookie(areaNaviKey, "", 240, areaPath);
    }
}

//初始化快捷导航菜单
function initPopMenuTree() {
    //遍历及加载事件
    $(".pop-box .list-box li", popMenu).each(function () {
        var linkObj = $(this).children("a");
        linkObj.removeAttr("href");
        if ($(this).children("ul").length > 0) { //如果无下级菜单
            linkObj.addClass("nolink");
        } else {
            linkObj.addClass("link");
            linkObj.on("click", function () {
                linkMenuTree(true, linkObj.attr("navid")); //加载函数
            });
        }
    });
    //设置快捷菜单容器的大小
    popMenuTreeResize();
}

//设置快捷菜单容器的大小
function popMenuTreeResize() {
    //计算容器的宽度
    var group = $(".list-box .list-group", popMenu);
    var groupWidth = group.outerWidth();
    var divWidth = group.length * groupWidth;
    var winWidth = $(window).width();
    if (divWidth > winWidth) {
        var groupCount = Math.floor(winWidth / groupWidth);
        if (groupCount > 0) {
            groupWidth = groupWidth * groupCount;
        }
    } else {
        groupWidth = divWidth;
    }
    $(".pop-box", popMenu).width(groupWidth);
    //只有显示的时候才能设置高度
    if (popMenu.hasClass("active")) {
        //setPopMenuHeight();
    }
}

//设置快捷菜单的高度
function setPopMenuHeight() {
    //计算容器的高度
    var group = $(".list-box .list-group", popMenu);
    var divHeight = $(window).height() * 0.6;
    var groupHeight = 0;
    group.each(function () {
        if ($(this).height() > groupHeight) {
            groupHeight = $(this).height();
        }
    });
    if (divHeight > groupHeight) {
        divHeight = groupHeight;
    }
    group.height(groupHeight);
    $(".pop-box", popMenu).height(divHeight);
}

//快捷菜单的显示与隐藏
function togglePopMenu() {
    if (popMenu.hasClass("active")) {
        popMenu.removeClass("active");
        $(".list-box", popMenu).getNiceScroll().remove();
    } else {
        popMenu.addClass("active");
        //只有显示的时候才能设置高度
        setPopMenuHeight(); //设置高度
        //设置导航滚动条
        $(".list-box", popMenu).niceScroll({ touchbehavior: false, cursorcolor: "#c0c0c0", cursoropacitymax: 0.6, cursorwidth: 5, autohidemode: false });
    }
}
