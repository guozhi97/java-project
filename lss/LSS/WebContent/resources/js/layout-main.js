/**
 * layout-main js document：主要存放后台的一些公共处理逻辑、方法等
 */
/* layput main document ready */
$(function() {
	/* 设置面包屑路径 */
	f_setLocation();
	f_onLinkRight();
});

function f_setLocation() {
	var locationObj = document.getElementById("main-location");
	if (locationObj) {
		var navGroupObj = $("#sidebar-nav .list-group.selected",
				top.parent.document), routeObj = navGroupObj
				.find("ul>li>a.selected");
		if (routeObj.length > 0) {
			/* 获取当前导航超链接对象 */
			var first = routeObj.first(), warpObj = routeObj
					.parents(".list-wrap");
			var target = routeObj.parent().parent().parent();
			var location = new Array();
			/* 填充路径导航：父节点 */
			location.push(warpObj.find(">h2").text());
			location.push(target.find(">a").first().text());
			/* 填充路径导航：当前 */
			location.push("<a href='" + routeObj.attr("href") + "'>"
					+ routeObj.text() + "</a>");
			locationObj.innerHTML = location
					.join("<span class='bread-slash'>/</span>");
		}
	}
}

function f_onLinkRight() {
	$("body a[data-target='right']").on("click", function(e) {
		var location = $(this).prop("href");
		if (top.frameRight) {
			if (e && e.preventDefault) {
				e.preventDefault();
			} else {
				window.event.returnValue = false;
			}
			if (top.linkMenuTree) {
				top.linkMenuTree(true, location);
			}
		}
	});
}

/**
 * 保存排序数字处理
 * 
 * @param {}
 *            modelId 实体主键标识
 * @param {}
 *            sortCodeEleId 排序值输入的文本框 Id 属性
 * @param {}
 *            specifyUrl 指定的接口地址
 * @returns {}
 */
function f_onSaveSort(modelId, sortCodeEleId, specifyUrl) {
	if (!modelId || !sortCodeEleId) {
		top.Common.tips({
			type : 2,
			content : "缺少参数，不合法请求。"
		});
		return;
	} else {
		var element = document.getElementById(sortCodeEleId);
		if (element) {
			if (element.value == element.defaultValue) {
				top.Common.tips({
					type : 2,
					content : "排序数字未修改，将不会提交保存。"
				});
				return;
			} else {
				if (top.showLoadingMain) {
					top.showLoadingMain();
				}

				/* 设置接口地址，有一个默认值；如果客户端指定了接口地址，重新设置接口地址 */
				var apiUrl = "_EditSortCode";
				if (Common.ControllerPath) {
					apiUrl = [ Common.ControllerPath, apiUrl ].join("/");
				}
				if (arguments.length >= 3 && specifyUrl) {
					apiUrl = specifyUrl;
				}

				$.ajax({
					type : "POST",
					url : apiUrl,
					dataType : "json",
					data : {
						id : modelId,
						sortCode : element.value
					},
					success : function(data, textStatus) {
						/*
						 * commenTip 是服务端返回的消息载体，这里调用了 Common.tips()
						 * 脚本方法来显示消息交互的效果
						 */
						var commonTip = data.commonTip;
						if (commonTip && commonTip.key == "CommonTips") {
							top.Common.tips(commonTip);
						}
						/* 如果有搜索功能，这里将重新请求列表数据 */
						var btnSearch = $("#btnSearch");
						if (btnSearch) {
							btnSearch.trigger("click");
						}
						if (top.hideLoadingMain) {
							top.hideLoadingMain();
						}
					},
					error : function(data, testStatus) {
						var commonTip = data.commonTip;
						if (commonTip && commonTip.key == "CommonTips") {
							top.Common.tips(commonTip);
						} else {
							top.Common.tips({
								type : 3,
								content : data.responseText
							});
						}
						if (top.hideLoadingMain) {
							top.hideLoadingMain();
						}
					}
				});
			}
		}
	}
}

/**
 * 设置或取消属性的方法
 * 
 * @param {}
 *            modelId 实体主键标识
 * @param {}
 *            attrName 属性名称
 * @param {}
 *            attrValue 更新的属性值
 * @param {}
 *            specifyUrl 指定的接口地址
 * @returns {}
 */
function f_onSaveAttr(modelId, attrName, attrValue, specifyUrl) {
	if (!modelId || !attrName) {
		top.Common.tips({
			type : 2,
			content : "缺少参数，将不会提交保存"
		});
		return;
	} else {
		/* 设置接口地址，有一个默认值；如果客户端指定了接口地址，重新设置接口地址 */
		var apiUrl = "_EditMoreAttributes";
		if (Common.ControllerPath) {
			apiUrl = [ Common.ControllerPath, apiUrl ].join("/");
		}
		if (arguments.length >= 4 && specifyUrl) {
			if (Common.ControllerPath) {
				apiUrl = [ Common.ControllerPath, apiUrl ].join("/");
			}
			apiUrl = specifyUrl;
		}

		showLoadingMain();
		$.ajax({
			type : "POST",
			url : apiUrl,
			dataType : "json",
			data : {
				id : modelId,
				attributeName : attrName,
				attributeValue : attrValue
			},
			success : function(data, textStatus) {
				/* commenTip 是服务端返回的消息载体，这里调用了 Common.tips() 脚本方法来显示消息交互的效果 */
				var commonTip = data.commonTip;
				if (commonTip && commonTip.key == "CommonTips") {
					top.Common.tips(commonTip);
				}
				/* 如果有搜索功能，这里将重新请求列表数据 */
				var btnSearch = $("#btnSearch");
				if (btnSearch) {
					btnSearch.trigger("click");
				}
				if (top.hideLoadingMain) {
					top.hideLoadingMain();
				}
			},
			error : function(data, testStatus) {
				var commonTip = data.commonTip;
				if (commonTip && commonTip.key == "CommonTips") {
					top.Common.tips(commonTip);
				} else {
					top.Common.tips({
						type : 3,
						content : data.responseText
					});
				}
				if (top.hideLoadingMain) {
					top.hideLoadingMain();
				}
			}
		});
	}
}



/**
 * 列表页中的单项与批量删除处理
 * 
 * @param {}
 *            oneId 如果是单项删除，指定单项删除的 Id
 * @param {}
 *            specifyUrl 指定的接口地址
 * @returns {}
 */
function f_onDelOne(oneId, specifyUrl) {
	if (top.onPostBack) {
		top.onPostBack(function() {
			f_onDel([ oneId ], specifyUrl);
		});
	}
}

/**
 * 列表页中的单项与批量删除处理
 * 
 * @param {}
 *            oneId 如果是单项删除，指定单项删除的 Id
 * @param {}
 *            specifyUrl 指定的接口地址
 * @returns {}
 */
function f_onDel(ids, specifyUrl) {
	top.showLoadingMain();
	if (ids) {
		/* 设置接口地址，有一个默认值；如果客户端指定了接口地址，重新设置接口地址 */
		var apiUrl = "../../admin/json_deleteOk.js";
		if (Common.ControllerPath) {
			apiUrl = [ Common.ControllerPath, apiUrl ].join("/");
		}
		if (arguments.length >= 2 && specifyUrl) {
			apiUrl = specifyUrl;
		}
		$.ajax({
			type : "GET",
			url : apiUrl,
			dataType : "json",
			data : {
				ids : ids.join(",")
			},
			success : function(data, textStatus) {
				/* commenTip 是服务端返回的消息载体，这里调用了 Common.tips() 脚本方法来显示消息交互的效果 */
				var commonTip = data.commonTip;
				if (commonTip && commonTip.key == "CommonTips") {
					top.Common.tips(commonTip);
				}
				/* 如果有搜索功能，这里将重新请求列表数据 */
				var btnSearch = $("#btnSearch");
				if (btnSearch) {
					btnSearch.trigger("click");
				}
				if (top.hideLoadingMain) {
					top.hideLoadingMain();
				}
			},
			error : function(data, testStatus) {
				var commonTip = data.commonTip; // 服务端返回的消息载体
				if (commonTip && commonTip.key == "CommonTips") {
					top.Common.tips(commonTip);
				} else {
					top.Common.tips({
						type : 3,
						content : data.responseText
					});
				}
				if (top.hideLoadingMain) {
					top.hideLoadingMain();
				}
			}
		});
	}
}
