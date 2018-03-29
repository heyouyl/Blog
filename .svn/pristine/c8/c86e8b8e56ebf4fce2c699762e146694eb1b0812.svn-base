<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>
	<#if slnameiden=="index">
		<@spring.message "common_name"/>-${headtitle}
	<#elseif slnameiden=="articleiden">
		${article.artitle}-<@spring.message "common_name"/>
	<#elseif slnameiden=="useradmin">
		<@spring.message "user_admin_title"/>-<@spring.message "common_name"/>
	<#elseif slnameiden=="category">
		${slnameidenname}-<@spring.message "common_name"/>
	<#elseif slnameiden=="categories">
		<@spring.message "common_categories"/>-<@spring.message "common_name"/>
	<#else>
		${article.get(0).category}-<@spring.message "common_name"/>
	</#if>
</title>
<link rel="stylesheet" type="text/css" href="${contextPath}res/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${contextPath}res/css/nprogress.css">
<link rel="stylesheet" type="text/css" href="${contextPath}res/css/style.css">
<#if categoryiden=="useradmin" || categoryiden=="categories">
	<link rel="stylesheet" type="text/css" href="${contextPath}res/css/user.css">
</#if>
<link rel="stylesheet" type="text/css" href="${contextPath}res/css/font-awesome.min.css">
<link rel="apple-touch-icon-precomposed" href="${contextPath}upload/images/icon/icon.png">
<link rel="shortcut icon" href="${contextPath}upload/images/icon/favicon.ico">
<meta name="keywords" content="${keywords}">
<meta name="description" content="${description}">
${web_custop}
<script src="${contextPath}res/js/jquery-2.1.4.min.js"></script>
<script src="${contextPath}res/js/myl/public.js"></script>

<script src="${contextPath}res/js/nprogress.js"></script>
<script src="${contextPath}res/js/jquery.lazyload.min.js"></script>
<script src="${contextPath}res/js/jquery.qqFace.js"></script>
<!--[if gte IE 9]>
  <script src="${contextPath}res/js/jquery-1.11.1.min.js" type="text/javascript"></script>
  <script src="${contextPath}res/js/html5shiv.min.js" type="text/javascript"></script>
  <script src="${contextPath}res/js/respond.min.js" type="text/javascript"></script>
  <script src="${contextPath}res/js/selectivizr-min.js" type="text/javascript"></script>
<![endif]-->
<!--[if lt IE 9]>
  <script>window.location.href='upgrade-browser.html';</script>
<![endif]-->
</head>