<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<c:set var="path" value="<%=basePath %>"/>
<!DOCTYPE html>
<html>
<head>
    <title>Sorry，微信服务器验证错误，请确认您通过微信客户端登陆！</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${path }/resources/css/main.css"/>
    <style type="text/css">
        .mod-notfound
        {
            border-right: #e1e1e1 1px solid;
            border-top: #e1e1e1 1px solid;
            margin-top: 10px;
            background: #fff;
            border-left: #e1e1e1 1px solid;
            border-bottom: #e1e1e1 1px solid;
            text-align: center;
            border-radius: 10px;
        }
        
        .mod-notfound ul
        {
            margin: 0 ;
            padding: 0;
            list-style-position: inside;
            text-align: left;
            text-indent: 1em;
            }
        
        .mod-notfound li
        {
            height: 24px;
            line-height: 24px;
            }
            
        .mod-notfound li a
        {
            color: steelblue;
            }
    </style>
</head>
<body>
    <div class="mod-page-body">
        <div class="mod-page-main wordwrap clearfix">
            <div class="x-page-container">
                <div class="mod-notfound grid-98">
                    <img alt="404 NOT FOUND" class="img-notfound" src="${path }/resources/images/notfound.gif" style="width: 520px;height: 320px;" />
                    <p style="color:#e80;font-size: 24px; line-height: 70px">
                        -.-&nbsp;抱歉！微信服务器验证错误.
                    </p>
                    <div style="margin: 0 auto;width: 500px; color: #999;">
                        <div style="text-align: left;">可能的原因：</div>
                        <ul>
                        	<c:set var="error" value='<%=request.getAttribute("exception") %>' ></c:set>
                        	<c:if test="${not empty error }">
                            	<li>${error }</li>
                        	</c:if>
                            <li>1.您此次访问不是通过微信公众号进行的；</li>                     
                            <li>2.微信端访问出现异常，未正常获取您的个人记录信息！</li>
                        </ul>                        
                        <div style="margin: 20px 0 0 0;text-align: left;">您可以：</div>
                        <ul>
                            <li>1.微信搜索并关注"求索人"(uu_seeker)微信公众号，通过左下角的快递服务菜单按钮进行操作</li> 
                            <li/>                    
                            <li>2.如果您确实是通过微信菜单进入又没有正常数据，请关闭此页面，重试一次！</li>
                        </ul>
                        <div>
                    </div>&nbsp;</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>