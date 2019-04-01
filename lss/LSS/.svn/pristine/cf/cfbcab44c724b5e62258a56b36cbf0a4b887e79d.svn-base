/**
* 基础工具脚本（基于jQuery 1.9.0 主框架）
* 作者：Jack(xiangu@foxmail.com)
*/
$(function () {
    /*初始化项目根路径*/
    Common.BasePath = Common.getBasePath();

    /*初始化扩展插件*/
    $(".rule-single-checkbox").ruleSingleCheckbox(); //复选框单选
    $(".rule-multi-checkbox").ruleMultiCheckbox(); //复选框多选
    $(".rule-single-select").ruleSingleSelect(); // 多项单选
    $(".rule-multi-radio").ruleMultiRadio(); //多项单选
    $(".rule-multi-porp").ruleMultiPorp(); //多项选择PORP
});

/**
 * 为元素注册展开与折叠切换的方法
 * @param {} targetId 
 * @returns {} 
 */
function f_toggle(targetId) {
    var target = document.getElementById(targetId);
    if (target) {
        $(target).slideToggle();
        $(this).find(".icon").toggleClass("fa-chevron-up").toggleClass("fa-chevron-down");
    }
}

/**
 * 基于 bootstrap 的 button dropdown 打造自定义select
 * @param {} ele 
 * @returns {} 
 */
function bootDropdown(ele) {
    this.dropdown = ele;
    this.placeholder = this.dropdown.find(".placeholder");
    this.options = this.dropdown.find("ul.dropdown-menu > li");
    this.val = "";
    this.index = -1;//默认为-1;
    this.initEvents();
}
bootDropdown.prototype = {
    initEvents: function () {
        var obj = this;
        //这个方法可以不写，因为点击事件被Bootstrap本身就捕获了，显示下面下拉列表
        obj.dropdown.on("click", function (event) {
            $(this).toggleClass("active");
        });

        //点击下拉列表的选项
        obj.options.on("click", function () {
            var opt = $(this);
            opt.siblings().removeClass("selected");
            opt.addClass("selected");
            obj.text = opt.find("a").text();
            obj.val = opt.data("value");
            obj.index = opt.index();
            obj.placeholder.text(obj.text);
        });
    },
    getText: function () {
        return this.text;
    },
    getValue: function () {
        return this.val;
    },
    getIndex: function () {
        return this.index;
    }
}

/**
 * 基于artdialog插件可以自动关闭的提示
 * @param {} msg 提示消息
 * @param {} url 跳转路由
 * @param {} callback 回调事件
 * @returns {} 
 */
function artDialog(msg, url, callback) {
    var d = top.dialog({ title: "该提示将在5秒钟内自动关闭...", content: msg }).show();
    setTimeout(function () {
        d.close().remove();
    }, 5500);
    if (arguments.length >= 2 && url) {
        if (url === "back") {
            window.history.back(-1);
        } else if (url != "") {
            window.location.href = url;
        }
    }
    //执行回调函数
    if (arguments.length === 3) {
        callback();
    }
}

/**
 * 先检测 checkbox 的选中情况，而后执行回调函数
 * @param {} okCallback 回调函数
 * @param {} objmsg 确认提示的消息
 * @returns {} 
 */
function onCheckPostBack(okCallback, objmsg) {
    var checkedCount = $("input[type='checkbox'].checkall:checked").size();
    if (checkedCount < 1) {
        top.dialog({
            skin: "art-alert",
            title: "提示",
            content: "对不起，请选中您要操作的记录！",
            okValue: "确定",
            ok: function () { }
        }).showModal();
        return false;
    }
    var msg = "删除记录后不可恢复，您确定吗？";
    if (arguments.length == 2) {
        msg = objmsg;
    }
    top.dialog({
        skin: "art-alert",
        title: "提示",
        content: msg,
        okValue: "确定",
        ok: okCallback || function () {},
        cancelValue: "取消",
        cancel: function () { }
    }).showModal();

    return false;
}

/**
 * 无需检测 checkbox 的选中情况，执行回调函数
 * @param {} okCallback 回调函数
 * @param {} objmsg 确认提示的消息
 * @returns {} 
 */
function onPostBack(okCallback, objmsg) {
    var msg = "删除记录后不可恢复，您确定吗？";
    if (arguments.length == 2) {
        msg = objmsg;
    }
    top.dialog({
        skin: "art-alert",
        title: "提示",
        content: msg,
        okValue: "确定",
        ok: okCallback || function () { },
        cancelValue: "取消",
        cancel: function () { }
    }).showModal();

    return false;
}
/*-- 以上基于artdialog插件 --*/

/*===========================工具类函数============================*/
/**
 * 判断浏览器环境是PC端还是手机端
 * @returns {} 
 */
function getUserAgent() {
    if ((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
        return 1; //手机端（移动端）
    } else {
        return 0; //PC端    
    }
}

/**
 * 全选或取消函数
 * @param {} chkobj 全选元素
 * @returns {} 
 */
function checkAll(chkobj) {
    var textObj = $(chkobj).children("span");
    var checkbox = $("input[type='checkbox'].checkall");
    var rows = $("tr.rule-row-check");
    if (textObj.text().indexOf("全选") > -1) {
        textObj.text("取消");
        checkbox.prop("checked", true);
        rows.addClass("success"); //标识行的激活状态
    } else {
        textObj.text("全选");
        checkbox.prop("checked", false);
        rows.removeClass("success"); //标识行的激活状态
    }
}

/**
 * 阻止冒泡方法
 * @param {} evt 
 * @returns {} 
 */
function stopPro(evt) {
    var e = evt || window.event;
    //returnValue如果设置了该属性，它的值比事件句柄的返回值优先级高。把这个属性设置为 fasle， 
    //可以取消发生事件的源元素的默认动作。 
    //window.event?e.returnValue = false:e.preventDefault(); 
    window.event ? e.cancelBubble = true : e.stopPropagation();
}

/**
 * 只允许输入数字
 * @param {} e 
 * @returns {} 
 */
function checkIsNumber(e) {
    var keynum = window.event ? e.keyCode : e.which;
    if ((48 <= keynum && keynum <= 57) || keynum == 8) {
        return true;
    } else {
        return false;
    }
}

/**
 * 只允许输入小数
 * @param {} obj 
 * @param {} e 
 * @returns {} 
 */
function checkIsFloat(obj, e) {
    var isOk = false;
    var key = window.event ? e.keyCode : e.which;
    if ((key > 95 && key < 106) || //小键盘上的0到9  
        (key > 47 && key < 60) || //大键盘上的0到9  
        (key == 110 && obj.value.indexOf(".") < 0) || //小键盘上的.而且以前没有输入.  
        (key == 190 && obj.value.indexOf(".") < 0) || //大键盘上的.而且以前没有输入.  
        key == 8 || key == 9 || key == 46 || key == 37 || key == 39) {
        isOk = true;
    } else {
        if (window.event) { //IE
            e.returnValue = false; //event.returnValue=false 效果相同.    
        } else { //Firefox 
            e.preventDefault();
        }
    }
    return isOk;
}

/**
 * 检测值只能为整数
 * @param {} value 
 * @returns {} 
 */
function checkIsInt(value) {
    var isOk = false;
    if (isNaN(value) || !(/^\d+$/.test(value))) {
        //window.Common.tips({ type: 2, content: "请输入正确格式的数值，只允许输入整数!" });
        isOk = false;
    } else {
        isOk = true;
    }
    return isOk;
}

/**
 * 检测值只能为非负整数
 * @param {} value 
 * @returns {} 
 */
function checkIsPositiveInt(value) {
    var isOk = false;
    if (isNaN(value) || value < 0 || !(/^\d+$/.test(value))) {
        //window.Common.tips({ type: 2, content: "请输入正确格式的数值，只允许输入非负整数!" });
        isOk = false;
    } else {
        isOk = true;
    }
    return isOk;
}

/**
 * 检查值是否为邮箱格式
 * @param {} value 值
 * @returns {} true||false
 */
function checkIsEmail(value) {
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    return reg.test(value);
}

/**
 * 校验身份证号码的方法：身份证号码可以是15位或者是18位，其中最后一位可以是X，其它全是数字。
 * @param {} value 
 * @returns {} 
 */
function checkIsCnId(value) {
    var reg = /^(\d{14}|\d{17})(\d|[xX])$/;
    return reg.test(value);
}

/**
 * 检查短信字数
 * @param {} obj 
 * @param {} txtId 
 * @returns {} 
 */
function checkTxtLength(obj, txtId) {
    var txtCount = $(obj).val().length;
    if (txtCount < 1) {
        return false;
    }
    var smsLength = Math.ceil(txtCount / 62);
    $("#" + txtId).html("您已输入<b>" + txtCount + "</b>个字符，将以<b>" + smsLength + "</b>条短信扣取费用。");
    return true;
}

/**
 * 四舍五入函数
 * @param {} dight 
 * @param {} how 
 * @returns {} 
 */
function computeForDight(dight, how) {
    dight = Math.round(dight * Math.pow(10, how)) / Math.pow(10, how);
    return dight;
}

/**
 * 写入 Cookie
 * @param {} objName 
 * @param {} objValue 
 * @param {} objHours 
 * @param {} path 
 * @returns {} 
 */
function addCookie(objName, objValue, objHours, path) {
    var str = [objName, "=", escape(objValue)];
    if (objHours > 0) { //为0时不设定过期时间，浏览器关闭时cookie自动消失
        var date = new Date();
        var ms = objHours * 3600 * 1000;
        date.setTime(date.getTime() + ms);
        str.push("; expires=");
        str.push(date.toGMTString());
    }
    if (path) {
        str.push("; path=");
        str.push(path);
    }
    document.cookie = str.join("");
}

/**
 * 读取 Cookie
 * @param {} objName 
 * @returns {} 
 */
function getCookie(objName) { //获取指定名称的cookie的值
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if (temp[0] == objName) return unescape(temp[1]);
    }
    return "";
}

/**
 * 原生 JavaScript 方法判断 Element 是否含有指定的 Css ClassName
 * @param {} element 目标元素
 * @param {} className 样式类名
 * @returns {} 
 */
function hasClass(element, className) {
    var reg = new RegExp("(\\s|^)" + className + "(\\s|$)");
    return element.className.match(reg);
}

/**
 * 原生 JavaScript 方法为 Element 添加指定的 Css ClassName
 * @param {} element 目标元素
 * @param {} className 样式类名
 * @returns {} 
 */
function addClass(element, className) {
    if (!this.hasClass(element, className)) {
        element.className += " " + className;
    }
}

/*
 * 原生 JavaScript 方法为 Element 移除指定的 Css ClassName
 * @param {} element 目标元素
 * @param {} className 样式类名
 * @returns {} 
 */
function removeClass(element, className) {
    if (hasClass(element, className)) {
        var reg = new RegExp("(\\s|^)" + className + "(\\s|$)");
        element.className = element.className.replace(reg, " ");
    }
}

/*
* js 获取 url 中的参数，方法一的重载方法：正则分析法，获取指定 url 中的参数
* @param url: url 路径 
* @param name: 参数名 
*/
function getQueryString(url, name) {
    var reg = new RegExp("(^|&|\\?)" + name + "=([^&]*)(&|$)", "i");
    var r = url.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/**
 * jQuery 分隔 Url 的 param 方法（返回 JSON 对象）
 * 示例：search = "abc=foo&def=%5Basf%5D&xyz=5&foo=b%3Dar";
 * 返回：Object {abc: "foo", def: "[asf]", xyz: "5", foo: "b=ar"}
 */
function getUrlParam(search) {
    return search ? JSON.parse('{"' + search.replace(/&/g, '","').replace(/=/g, '":"') + '"}', function (key, value) { return key === "" ? value : decodeURIComponent(value) }) : {}
}

/// <summary>
/// 删除最后结尾的指定字符后的字符
/// </summary>
function removeLastChar(str, strchar){
    if (str) {
        var lastIndex = str.lastIndexOf(strchar);
        if (lastIndex >= 0 && lastIndex == str.length - 1) {
            return str.substring(0, lastIndex);
        }
    }
    return str;
}

/**
 * 去掉所有的html标记
 * @param {} str 
 * @returns {} 
 */
function killHtml(str) {
    return str.replace(/<[^>]+>/g, "");
}

/**
 * 兼容 FF 的加入收藏夹方法和设为首页
 * @param {} sUrl 收藏链接地址
 * @param {} sTitle 设置收藏标题
 * @returns {} 
 */
function AddFavorite(sUrl, sTitle) {
    try {
        window.external.addFavorite(sUrl, sTitle);
    } catch (e) {
        try {
            window.sidebar.addPanel(sTitle, sUrl, "");
        } catch (e) {
            alert("加入收藏失败，您所使用的浏览器无法完成此操作。\r\n请尝试使用Ctrl+D进行添加，或手动加入收藏夹！");
        }
    }
}

/**
 * 兼容 FF 的设为首页方法
 * @param {} obj 
 * @param {} url 
 * @returns {} 
 */
function SetHome(obj, url) {
    try {
        obj.style.behavior = "url(#default#homepage)";
        obj.setHomePage(url);
    } catch (e) {
        if (window.netscape) {
            try {
                window.netscape.security.PrivilegeManager
                        .enablePrivilege("UniversalXPConnect");
            } catch (e) {
                alert("此操作不被浏览器允许！\n请在浏览器地址栏输入\"about:config\"并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
            }
            var prefs = window.Components.classes["@mozilla.org/preferences-service;1"].getService(window.Components.interfaces.nsIPrefBranch);
            prefs.setCharPref("browser.startup.homepage", url);
        }
        alert("抱歉，此操作不被浏览器允许，\r\n您需要手动将【" + url + "】设置为首页！");
    }
}

/**
 * 保存到桌面
 * @param {} sUrl 
 * @param {} sName 
 * @returns {} 
 */
function AddToDesktop(sUrl, sName) {
    try {
        var wshShell = new ActiveXObject("WScript.Shell");
        var oUrlLink = wshShell.CreateShortcut(wshShell.SpecialFolders("Desktop") + "\\" + sName + ".url");
        oUrlLink.TargetPath = sUrl;
        oUrlLink.Save();
    }
    catch (e) {
        alert("当前浏览器安全级别不允许操作！");
    }
}

/**
 * JavaScript 压缩图片：等比例修正图片尺寸
 * @param imgD: 目标图片对象
 * @param fixWidth: 指定修正后的最大宽值
 * @param fixHeight: 指定修正后的最大高值
 */
function DrawImage(imgD, fitWidth, fitHeight) {
    var image = new Image();
    image.src = imgD.src;
    image.width = imgD.width;
    image.height = imgD.height;
    if (image.width > 0 && image.height > 0) {
        if (image.width > fitWidth) {
            imgD.width = fitWidth;
            imgD.height = (image.height * fitWidth) / image.width;
            if (imgD.height > fitHeight) {
                imgD.height = fitHeight;
                imgD.width = (image.width * fitHeight) / image.height;
            }
        } else if (image.height > fitHeight) {
            imgD.height = fitHeight;
            imgD.width = (image.width * fitHeight) / image.height;
            if (image.width > fitWidth) {
                imgD.width = fitWidth;
                imgD.height = (image.height * fitWidth) / image.width;
            }
        } else {
            imgD.width = image.width;
            imgD.height = image.height;
        }
    }
}

/**
 * javascript中没有像 trim 的函数，我们就可以利用这个表达式来实现，如下：
 * @returns {} 
 */
String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * JS 中的 string.format()
 * //两种调用方式
 * var template1="我是{0}，今年{1}了";
 * var template2="我是{name}，今年{age}了";
 * var result1=template1.format("loogn",22);
 * var result2=template2.format({name:"loogn",age:22});
 //两个结果都是"我是loogn，今年22了"
 * @param {} args 
 * @returns {} 
 */
String.prototype.format = function (args) {
    var result = this;
    if (arguments.length > 0) {
        var reg;
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if (args.hasOwnProperty(key)) {
                    if (args[key] != undefined) {
                        reg = new RegExp("({" + key + "})", "g");
                        result = result.replace(reg, args[key]);
                    }
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}

/**
 * 仿 C# 构造 JavaScript 的 StringBuilder 原型，替换 string += "xx"，使字符串拼接效率提升
 * @returns {} 
 */
function stringBuilder() {
    this.tmp = new Array();
}
stringBuilder.prototype.Append = function (value) {
    this.tmp.push(value);
    return this;
}
stringBuilder.prototype.Clear = function () {
    tmp.length = 1;
}
stringBuilder.prototype.toString = function () {
    return this.tmp.join("");
}

/**
 * 表单值域清空
 * @param {} formId 
 * @returns {} 
 */
function clearForm(formId) {
    var formObj = document.getElementById(formId);
    if (formObj == undefined) {
        return false;
    }
    var elements = formObj.elements;
    for (var i = 0; i < elements.length; i++) {
        var ele = elements[i];
        if (ele.type == "text" || ele.type == "password" || ele.type == "textarea" || ele.type == "search" || ele.type == "number" || ele.type == "email") {
            ele.value = "";
        }
        else if (ele.type == "radio" || ele.type == "checkbox") {
            ele.checked = false;
        }
        else if (ele.type == "select-one") {
            ele.options[0].selected = true;
        }
        else if (ele.type == "select-multiple") {
            for (var j = 0; j < ele.options.length; j++) {
                ele.options[j].selected = false;
            }
        }
        else if (ele.type == "file") {
            //formObj.elements[i].select();  
            //document.selection.clear();             
            //for IE, Opera, Safari, Chrome  
            var file = ele;
            if (file.outerHTML) {
                file.outerHTML = file.outerHTML;
            } else {
                file.value = "";  // FF(包括3.5)  
            }
        }
    }
    return true;
}

/**
 * 表单数据是否有修改过
 * @param {} form 
 * @returns {} 
 */
function formIsModified(form) {
    for (var i = 0; i < form.elements.length; i++) {
        var element = form.elements[i];
        var type = element.type;
        if (type == "checkbox" || type == "radio") {
            if (element.checked != element.defaultChecked) {
                return true;
            }
        }
        else if (type == "hidden" || type == "password" || type == "text" || type == "textarea") {
            if (element.value != element.defaultValue) {
                return true;
            }
        }
        else if (type == "select-one" || type == "select-multiple") {
            for (var j = 0; j < element.options.length; j++) {
                if (element.options[j].selected != element.options[j].defaultSelected) {
                    return true;
                }
            }
        }
    }
    return false;
}

/**
 * 声明一个通用对象载体
 */
var Common = Common || { BasePath: "" };

/**
 * 获取项目根路径
 * @returns {} 
 */
Common.getBasePath = function () {
    var basePathObj = $("input#myBasePath");
    return basePathObj ? basePathObj.val() : "";
}

/**
* 基于自定义样式与脚本的弹窗提示的工具函数
* @param options: {type, content, ...}
*/
Common.tips = function (options) { return new Common.Tips(options); }
Common.Tips = function (options) {
    var defaults = {
        renderTo: "body",
        type: 0,
        autoClose: true,
        removeOthers: true,
        time: undefined,
        top: 50,
        onClose: null,
        onShow: null,
        redirectUrl: ""
    }
    this.options = $.extend({}, defaults, options);
    this._init();
    !Common.Tips._collection ? Common.Tips._collection = [this] : Common.Tips._collection.push(this);
}
Common.Tips.removeAll = function () {
    try {
        for (var i = Common.Tips._collection.length - 1; i >= 0; i--) {
            Common.Tips._collection[i].remove();
        }
    } catch (e) {
    }
}
Common.Tips.prototype = {
    _init: function () {
        var self = this, opts = this.options, time;
        if (opts.redirectUrl) {
            window.location.href = opts.redirectUrl;
        }
        if (opts.removeOthers) {
            Common.Tips.removeAll();
        }
        this._create();
        this.closeBtn.on("click", function () {
            self.remove();
        });
        if (opts.autoClose) {
            time = opts.time || opts.type > 1 ? 8000 : 6000;
            window.setTimeout(function () {
                self.remove();
            }, time);
        }
    },
    _create: function () {
        var opts = this.options;
        this.obj = $('<div class="ui-toastr clearfix alert alert-dismissible" role="alert"><i class="icon" aria-hidden="true"></i><a class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true" title="关闭 | Close">&times;</span></a></div>');
        this.closeBtn = this.obj.find(".close");
        var iconObj = this.obj.find(".icon");
        this.obj.append(["<div class='toastr-message'>", opts.content, "</div>"].join(""));
        switch (opts.type) {
            default:
            case 0:
                this.obj.addClass("alert-info");
                iconObj.addClass("fa fa-info-circle");
                break;
            case 1:
                this.obj.addClass("alert-success");
                iconObj.addClass("fa fa-check");
                break;
            case 2:
                this.obj.addClass("alert-warning");
                iconObj.addClass("fa fa-exclamation-circle");
                break;
            case 3:
                this.obj.addClass("alert-danger");
                iconObj.addClass("fa fa-warning");
                break;
        }
        this.obj.appendTo("body").hide();
        this._setPos();
        if (opts.onShow) {
            opts.onShow();
        }
    },
    _setPos: function () {
        var self = this, opts = this.options;
        if (opts.width) {
            this.obj.css("width", opts.width);
        }
        //var h = this.obj.outerHeight(), winH = $(window).height();
        var scrollTop = $(window).scrollTop();
        //var top = parseInt(opts.top) ? (parseInt(opts.top) + scrollTop) : (winH > h ? scrollTop+(winH - h)/2 : scrollTop);
        var top = parseFloat(opts.top) + scrollTop;
        this.obj.css({
            position: "absolute",
            left: "50%",
            top: top - 50,
            zIndex: "899",
            marginLeft: -self.obj.outerWidth() / 2
        }).animate({"top": top}, 500);

        window.setTimeout(function () {
            self.obj.show().css({
                marginLeft: -self.obj.outerWidth() / 2
            });
        }, 150);
    },
    remove: function () {
        var opts = this.options;
        this.obj.animate({"top": 0}, 500);
        this.obj.fadeOut(1000, function () {            
            $(this).remove();
            if (opts.onClose) {
                opts.onClose();
            }
        });
    }
};


/**
 * 扩展jQuery插件：处理表单数据序列化为 json 格式
 * @returns {} 
 */
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || "");
        } else {
            o[this.name] = this.value || "";
        }
    });
    return o;
};


/**
 * 扩展jQuery插件：复选框
 * @returns {} 
 */
$.fn.ruleSingleCheckbox = function () {
    var singleCheckbox = function (parentObj) {
        /*查找复选框*/
        var checkObj = parentObj.children('input:checkbox').eq(0);
        parentObj.children().hide();
        /*添加元素及样式*/
        var newObj = $('<a href="javascript:;"><i class="off">否</i><i class="on">是</i></a>').prependTo(parentObj);
        parentObj.addClass("single-checkbox");
        /*判断是否选中*/
        if (checkObj.prop("checked") == true) {
            newObj.addClass("selected");
        }
        /*检查控件是否启用*/
        if (checkObj.prop("disabled") == true) {
            newObj.css("cursor", "default");
            return;
        }
        /*绑定事件*/
        newObj.click(function () {
            if ($(this).hasClass("selected")) {
                $(this).removeClass("selected");
            } else {
                $(this).addClass("selected");
            }
            checkObj.trigger("click"); /*触发对应的checkbox的click事件*/
        });
        /*绑定反监听事件*/
        checkObj.on('click', function () {
            if ($(this).prop("checked") == true && !newObj.hasClass("selected")) {
                alert();
                newObj.addClass("selected");
            } else if ($(this).prop("checked") == false && newObj.hasClass("selected")) {
                newObj.removeClass("selected");
            }
        });
    };
    return $(this).each(function () {
        singleCheckbox($(this));
    });
};

/**
 * 扩展jQuery插件：多项复选框
 * @returns {} 
 */
$.fn.ruleMultiCheckbox = function () {
    var multiCheckbox = function (parentObj) {
        parentObj.addClass("multi-checkbox"); /*添加样式*/
        parentObj.children().hide(); /*隐藏内容*/
        var divObj = $('<div class="boxwrap"></div>').prependTo(parentObj); /*前插入一个DIV*/
        parentObj.find(":checkbox").each(function () {
            var indexNum = parentObj.find(":checkbox").index(this); /*当前索引*/
            var value = $(this).val();
            var newObj = $('<a href="javascript:void(0);" data-value="' + value + '">' + parentObj.find("label").eq(indexNum).text() + "</a>").appendTo(divObj); /*查找对应Label创建选项*/
            if ($(this).prop("checked") === true) {
                newObj.addClass("selected"); /*默认选中*/
            }
            /*检查控件是否启用*/
            if ($(this).prop("disabled") === true) {
                newObj.css("cursor", "default");
                return;
            }
            /*绑定事件*/
            $(newObj).click(function () {
                if ($(this).hasClass("selected")) {
                    $(this).removeClass("selected");
                    /*parentObj.find(':checkbox').eq(indexNum).prop("checked",false);*/
                } else {
                    $(this).addClass("selected");
                    /*parentObj.find(':checkbox').eq(indexNum).prop("checked",true);*/
                }
                parentObj.find(":checkbox").eq(indexNum).trigger("click"); /*触发对应的checkbox的click事件*/
                /*alert(parentObj.find(':checkbox').eq(indexNum).prop("checked"));*/
            });
        });
    };
    return $(this).each(function () {
        multiCheckbox($(this));
    });
}

/**
 * 扩展jQuery插件：多项选项PROP
 * @returns {} 
 */
$.fn.ruleMultiPorp = function () {
    var multiPorp = function (parentObj) {
        parentObj.addClass("multi-porp"); /*添加样式*/
        parentObj.children().hide(); /*隐藏内容*/
        var divObj = $("<ul></ul>").prependTo(parentObj); /*前插入一个DIV*/
        parentObj.find(":checkbox").each(function () {
            var indexNum = parentObj.find(":checkbox").index(this); /*当前索引*/
            var value = $(this).val();
            var liObj = $("<li></li>").appendTo(divObj);
            var newObj = $('<a href="javascript:void(0);" data-value="' + value + '">' + parentObj.find("label").eq(indexNum).text() + "</a><i></i>").appendTo(liObj); /*查找对应Label创建选项*/
            if ($(this).prop("checked") === true) {
                liObj.addClass("selected"); /*默认选中*/
            }
            /*检查控件是否启用*/
            if ($(this).prop("disabled") === true) {
                newObj.css("cursor", "default");
                return;
            }
            /*绑定事件*/
            $(newObj).click(function () {
                if ($(this).parent().hasClass("disabled")) {
                    return false;
                } else {
                    if ($(this).parent().hasClass("selected")) {
                        $(this).parent().removeClass("selected");
                    } else {
                        $(this).parent().addClass("selected");
                    }
                    parentObj.find(":checkbox").eq(indexNum).trigger("click"); /*触发对应的checkbox的click事件*/
                    /*alert(parentObj.find(':checkbox').eq(indexNum).prop("checked"));*/
                }
                return false;
            });
        });
    };
    return $(this).each(function () {
        multiPorp($(this));
    });
}

/**
 * 扩展jQuery插件：多项单选
 * @returns {} 
 */
$.fn.ruleMultiRadio = function () {
    var multiRadio = function (parentObj) {
        parentObj.addClass("multi-radio"); /*添加样式*/
        parentObj.children().hide(); /*隐藏内容*/
        var divObj = $('<div class="boxwrap"></div>').prependTo(parentObj); /*前插入一个DIV*/
        parentObj.find('input[type="radio"]').each(function () {
            var indexNum = parentObj.find('input[type="radio"]').index(this); /*当前索引*/
            var value = $(this).val();
            var newObj = $('<a href="javascript: void(0);" data-value="' + value + '">' + parentObj.find("label").eq(indexNum).text() + "</a>").appendTo(divObj); /*查找对应Label创建选项*/
            if ($(this).prop("checked") == true) {
                newObj.addClass("selected"); /*默认选中*/
            }
            /*检查控件是否启用*/
            if ($(this).prop("disabled") == true) {
                newObj.css("cursor", "default");
                return;
            }
            /*绑定事件*/
            $(newObj).click(function () {
                $(this).siblings().removeClass("selected");
                $(this).addClass("selected");
                parentObj.find('input[type="radio"]').prop("checked", false);
                parentObj.find('input[type="radio"]').eq(indexNum).prop("checked", true);
                parentObj.find('input[type="radio"]').eq(indexNum).trigger("click"); /*触发对应的radio的click事件*/
                /*alert(parentObj.find('input[type="radio"]').eq(indexNum).prop("checked"));*/
            });
        });
    };
    return $(this).each(function () {
        multiRadio($(this));
    });
}

/**
 * 扩展jQuery插件：单选下拉框
 * @returns {} 
 */
$.fn.ruleSingleSelect = function () {
    var singleSelect = function (parentObj) {
        parentObj.addClass("single-select"); //添加样式
        var children = parentObj.children();
        children.each(function () {
            if ($(this).is(".Validform_checktip")) {
                return;
            } else {
                $(this).hide(); //隐藏内容
            }
        });
        var divObj = $('<div class="boxwrap"></div>').prependTo(parentObj); //前插入一个DIV
        //创建元素
        var titObj = $('<a class="select-tit" href="javascript:void(0);"><span></span><i></i></a>').appendTo(divObj);
        var itemObj = $('<div class="select-items"><ul></ul></div>').appendTo(divObj);
        var arrowObj = $('<i class="arrow"></i>').appendTo(divObj);
        var selectObj = parentObj.find("select").eq(0); //取得select对象
        selectObj.hide();

        //遍历option选项
        selectObj.find("option").each(function () {
            var indexNum = selectObj.find("option").index(this); //当前索引
            var liObj = $("<li>" + $(this).text() + "</li>").appendTo(itemObj.find("ul")); //创建LI
            if ($(this).prop("selected") === true) {
                liObj.addClass("selected");
                titObj.find("span").text($(this).text());
            }
            //检查控件是否启用
            if ($(this).prop("disabled") === true) {
                liObj.css("cursor", "default");
                return;
            }
            //绑定事件
            liObj.click(function () {
                $(this).siblings().removeClass("selected");
                $(this).addClass("selected"); //添加选中样式
                selectObj.find("option").prop("selected", false);
                selectObj.find("option").eq(indexNum).prop("selected", true); //赋值给对应的option
                titObj.find("span").text($(this).text()); //赋值选中值
                arrowObj.hide();
                itemObj.hide(); //隐藏下拉框
                selectObj.trigger("change"); //触发select的onchange事件
                //alert(selectObj.find("option:selected").text());
            });
        });
        //设置样式
        //titObj.css({ "width": titObj.innerWidth(), "overflow": "hidden" });
        //itemObj.children("ul").css({ "max-height": $(document).height() - titObj.offset().top - 62 });

        //检查控件是否启用
        if (selectObj.prop("disabled") === true) {
            titObj.css("cursor", "default");
            return;
        }
        //绑定单击事件
        titObj.click(function (e) {
            e.stopPropagation();
            if (itemObj.is(":hidden")) {
                //隐藏其它的下位框菜单
                $(".single-select .select-items").hide();
                $(".single-select .arrow").hide();
                //位于其它无素的上面
                arrowObj.css("z-index", "1000");
                itemObj.css("z-index", "1000");
                //显示下拉框
                arrowObj.show();
                itemObj.show();
            } else {
                //位于其它无素的上面
                arrowObj.css("z-index", "");
                itemObj.css("z-index", "");
                //隐藏下拉框
                arrowObj.hide();
                itemObj.hide();
            }
        });
        //绑定页面点击事件
        $(document).click(function () {
            selectObj.trigger("blur"); //触发select的onblure事件
            arrowObj.hide();
            itemObj.hide(); //隐藏下拉框
        });
    };
    return $(this).each(function () {
        singleSelect($(this));
    });
}

/*-- endregion 扩展插件 --*/

/*
* 基于 jQuery 的倒计时间信息输出方法
* 示例:
* window.countDown({ selector: "#countDown1", time: "2016/12/20 13:59:59", format: "还剩 {dd}天{HH}:{mm}:{ss}", callback: function () { $("#countDown1").html("结束啦"); } });
*/
function countDown(options) {
    var mOptions = {
        selector: "",
        time: new Date(),   //倒计时间点
        format: "{dd} {HH}:{mm}:{ss}",  //格式，大写HH表示24小时格式
        callback: function(){}
    };

    $.extend(mOptions, options);

    var endTime = new Date(mOptions.time).getTime(), //获取倒计时间点，月份是实际月份-1
    currentTime = new Date().getTime(),
	sysSecond = (endTime - currentTime) / 1000;     //时间差

    if (mOptions.selector && $(mOptions.selector)) {
        var timer = setInterval(function() {
            if (sysSecond > 0) {
                sysSecond -= 1; //时间差减1个计时单位
                var days = Math.floor((sysSecond / 3600) / 24);
                var hours = Math.floor((sysSecond / 3600) % 24);
                var minutes = Math.floor((sysSecond / 60) % 60);
                var seconds = Math.floor(sysSecond % 60);

                var result = mOptions.format.replace("{HH}", hours < 10 ? "0" + hours : hours)
                    .replace("{mm}", minutes < 10 ? "0" + minutes : minutes)
                    .replace("{ss}", seconds < 10 ? "0" + seconds : seconds);
                if (days > 1) {
                    result = result.replace("{dd}", days );
                } else {
                    result = result.replace("{dd}", "").replace("天", "");
                }
                $(mOptions.selector).html(result);

            } else {
                clearInterval(timer); //清除timer
                mOptions.callback(); //执行回调函数
            }
        }, 1000);
    }
}

/**
 * 将数字转化为人民币大写形式
 * @param {} currencyDigits 
 * @returns {} 
 */
function convertCurrency(currencyDigits) {
    // Constants:
    var maximumNumber = 99999999999.99;
    // Predefine the radix characters and currency symbols for output:
    var cnZero = "零";
    var cnOne = "壹";
    var cnTwo = "贰";
    var cnThree = "叁";
    var cnFour = "肆";
    var cnFive = "伍";
    var cnSix = "陆";
    var cnSeven = "柒";
    var cnEight = "捌";
    var cnNine = "玖";
    var cnTen = "拾";
    var cnHundred = "佰";
    var cnThousand = "仟";
    var cnTenThousand = "万";
    var cnHundredMillion = "亿";
    var cnSymbol = "人民币";
    var cnDollar = "元";
    var cnTenCent = "角";
    var cnCent = "分";
    var cnInteger = "整";

    // Variables:
    var integral; // Represent integral part of digit number.
    var decimal; // Represent decimal part of digit number.
    var outputCharacters; // The output result.
    var parts;
    var digits, radices, bigRadices, decimals;
    var zeroCount;
    var i, p, d;
    var quotient, modulus;

    // Validate input string:
    currencyDigits = currencyDigits.toString();
    if (currencyDigits == "") {
        alert("Empty input!");
        return "";
    }
    if (currencyDigits.match(/[^,.\d]/) != null) {
        alert("Invalid characters in the input string!");
        return "";
    }
    if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
        alert("Illegal format of digit number!");
        return "";
    }

    // Normalize the format of input digits:
    currencyDigits = currencyDigits.replace(/,/g, ""); // Remove comma delimiters.
    currencyDigits = currencyDigits.replace(/^0+/, ""); // Trim zeros at the beginning.
    // Assert the number is not greater than the maximum number.
    if (Number(currencyDigits) > maximumNumber) {
        alert("Too large a number to convert!");
        return "";
    }

    // Separate integral and decimal parts before processing coversion:
    parts = currencyDigits.split(".");
    if (parts.length > 1) {
        integral = parts[0];
        decimal = parts[1];
        // Cut down redundant decimal digits that are after the second.
        decimal = decimal.substr(0, 2);
    }
    else {
        integral = parts[0];
        decimal = "";
    }
    // Prepare the characters corresponding to the digits:
    digits = new Array(cnZero, cnOne, cnTwo, cnThree, cnFour, cnFive, cnSix, cnSeven, cnEight,

    cnNine);
    radices = new Array("", cnTen, cnHundred, cnThousand);
    bigRadices = new Array("", cnTenThousand, cnHundredMillion);
    decimals = new Array(cnTenCent, cnCent);
    // Start processing:
    outputCharacters = "";
    // Process integral part if it is larger than 0:
    if (Number(integral) > 0) {
        zeroCount = 0;
        for (i = 0; i < integral.length; i++) {
            p = integral.length - i - 1;
            d = integral.substr(i, 1);
            quotient = p / 4;
            modulus = p % 4;
            if (d == "0") {
                zeroCount++;
            }
            else {
                if (zeroCount > 0) {
                    outputCharacters += digits[0];
                }
                zeroCount = 0;
                outputCharacters += digits[Number(d)] + radices[modulus];
            }
            if (modulus == 0 && zeroCount < 4) {
                outputCharacters += bigRadices[quotient];
            }
        }
        outputCharacters += cnDollar;
    }
    // Process decimal part if there is:
    if (decimal != "") {
        for (i = 0; i < decimal.length; i++) {
            d = decimal.substr(i, 1);
            if (d != "0") {
                outputCharacters += digits[Number(d)] + decimals;
            }
        }
    }
    // Confirm and return the final output string:
    if (outputCharacters == "") {
        outputCharacters = cnZero + cnDollar;
    }
    if (decimal == "") {
        outputCharacters += cnInteger;
    }
    outputCharacters = cnSymbol + outputCharacters;
    return outputCharacters;
}

/**
 * 根据IP查询地理位置，调用接口：http://api.map.baidu.com/location/ip?ak=&ip=
 * @param {} ipAddress 
 * @param {} successFunction 
 * @returns {} 
 */
function f_getIpGeoInfo(ipAddress, successFunction) {
    if (!successFunction) {
        successFunction = function (data) { };
    }
    var apiUrl = "http://api.map.baidu.com/location/ip?ak=BCe01f2a03336e188a8199d159b81f65&ip=";
    $.ajax({
        type: "GET",
        url: apiUrl + ipAddress,
        dataType: "jsonp",
        jsonp: "callback",  //传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
        jsonpCallback: "ipGeoHandler",  //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据/*将得到的数据提取出来*/
        success: successFunction,
        error: function (msg, testStatus) {
            return false;
        }
    });
}

/**
 * 显示来访用户的IP，调用接口：http://ip.chinaz.com/getip.aspx
 * @param {} successFunction 
 * @returns {} 
 */
function f_getIp(successFunction) {
    if (!successFunction) {
        successFunction = function (data) { };
    }
    var apiUrl = "http://ip.chinaz.com/getip.aspx";
    $.ajax({
        type: "GET",
        url: apiUrl,
        dataType: "jsonp",
        jsonp: "callback",  //传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
        jsonpCallback: "ipHandler", //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据/*将得到的数据提取出来*/
        success: successFunction,
        error: function (msg, testStatus) {
            return false;
        }
    });
}

