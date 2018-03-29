<!DOCTYPE html>
<!--[if IE 8]>
	<html xmlns="http://www.w3.org/1999/xhtml" class="ie8" lang="zh-CN">
<![endif]-->
<!--[if !(IE 8) ]><!-->
	<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><@spring.message "common_name"/> &lsaquo; <@spring.message "common_login"/></title>
<link rel='dns-prefetch' href='//s.w.org' />
<link rel='stylesheet' href='../res/css/load-styles.css' type='text/css' media='all' />
<meta name='robots' content='noindex,follow' />
<script src="${contextPath}res/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	addLoadEvent = function(func){if(typeof jQuery!="undefined")jQuery(document).ready(func);else if(typeof wpOnload!='function'){wpOnload=func;}else{var oldonload=wpOnload;wpOnload=function(){oldonload();func();}}};
	function s(id,pos){g(id).left=pos+'px';}
	function g(id){return document.getElementById(id).style;}
	function shake(id,a,d){c=a.shift();s(id,c);if(a.length>0){setTimeout(function(){shake(id,a,d);},d);}else{try{g(id).position='static';wp_attempt_focus();}catch(e){}}}
	addLoadEvent(function(){ var p=new Array(15,30,15,0,-15,-30,-15,0);p=p.concat(p.concat(p));var i=document.forms[0].id;g(i).position='relative';shake(i,p,20);});
</script>
<script src="${contextPath}res/js/myl/public.js"></script>
<style type="text/css">#maxthon-1eec22d4-0232-4212-8283-6f2ac8f967-iframe{display:block!important;position:absolute!important;visibility:visible!important;z-index:2147483647!important;border-style:none!important;opacity:1!important;margin:0!important;padding:0!important;box-shadow:0 0 5px rgba(0,0,0,.3)!important;border:1px solid #b3b3b3!important}</style>
</head>
<body class="login login-action-login wp-core-ui  locale-zh-cn">
	<div id="login">
	<h1><a href="http://www.028888.net/" title="<@spring.message "common_name"/>" tabindex="-1"><@spring.message "common_name"/></a></h1>
	<#if iden=="nulluser">
		<div id="login_error">	<strong><@spring.message "common_error"/></strong>：
			<@spring.message "common_nullname"/>
			<br>
		</div>
	<#elseif iden=="nullpass">
		<div id="login_error">	<strong><@spring.message "common_error"/></strong>：
			<@spring.message "common_nullpass"/>
			<br>
		</div>		
	<#elseif iden=="errorpass">
		<div id="login_error">	<strong><@spring.message "common_error"/></strong>：
			<@spring.message "common_errorpass"/>
			<br>
		</div>			
	<#elseif iden=="erroruser">
		<div id="login_error">	<strong><@spring.message "common_error"/></strong>：
			<@spring.message "common_erroruser"/>
			<br>
		</div>	
	<#elseif iden=="errorother">
		<div id="login_error">	<strong><@spring.message "common_error"/></strong>：
			<@spring.message "common_errorother"/>
			<br>
		</div>
	<#elseif iden=="logoutok">
		<div id="login_error">	<strong><@spring.message "common_error"/></strong>：
			<@spring.message "common_logoutok"/>
			<br>
		</div>
	<#elseif iden=="resetpassok">
		<div id="login_error">	<strong><@spring.message "common_login"/></strong>：
			<@spring.message "common_resetpassok"/>
			<br>
		</div>	
	<#else>
	
	</#if>

	<#if action=="login">
		<form name="loginform" id="loginform" action="clogin.do" method="post">
			<p>
				<label for="user_login"><@spring.message "common_loginname1"/><br />
				<input type="text" name="log" id="user_login" class="input" value="" size="20" /></label>
			</p>
			<p>
				<label for="user_pass"><@spring.message "common_loginpass"/><br />
				<input type="password" name="pwd" id="user_pass" class="input" value="" size="20" /></label>
			</p>
				<p class="forgetmenot"><label for="rememberme"><input name="rememberme" type="checkbox" id="rememberme" value="forever"  /> <@spring.message "common_relogininfo"/></label></p>
			<p class="submit">
				<input type="submit" name="wp-submit" id="wp-submit" class="button button-primary button-large" value="<@spring.message "common_login"/>" />
				<input type="hidden" name="redirect_to" value="http://www.028888.net/wp-admin/" />
				<input type="hidden" name="testcookie" value="1" />
			</p>
		</form>
		<p id="nav">
			<a href="login.html?action=register"><@spring.message "common_register"/></a> | 	<a href="login.html?action=lostpassword"><@spring.message "common_lostpassword"/></a>
		</p>
	<#elseif action=="register">
		<p class="message register"><#if message_register??>${message_register}<#else>在这个站点注册</#if></p>
		<form name="registerform" id="registerform" action="cregister.do" method="post" onsubmit="return registerForm();">
			<p>
				<label for="user_login">用户名<br>
				<input type="text" pattern="^[a-zA-Z]{1}[_a-z0-9-]{3,11}$" name="user_login" placeholder="例如:asdf" id="user_login" class="input" value="" size="20" required></label>
			</p>
			<p>
				<label for="user_email">电子邮件<br>
				<input type="email" pattern="^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$" name="user_email" placeholder="例如:abc@example.abc" id="user_email" class="input" value="" size="25" required></label>
			</p>
				<p id="reg_passmail">注册确认信将会被寄给您。</p>
			<br class="clear">
			<input type="hidden" name="redirect_to" value="login">
			<p class="submit"><input type="submit" name="wp-submit" id="wp-submit" class="button button-primary button-large" value="注册"></p>
		</form>
		<p id="nav">
			<a href="login.html"><@spring.message "common_login"/></a> | 	<a href="login.html?action=lostpassword"><@spring.message "common_lostpassword"/></a>
		</p>
	<#elseif action=="lostpassword">
		<#if lostpass=='yes1'>
			<p class="message">请输入新密码</p>
	
			<form action="resetpass.do" method="post" onsubmit="return lostpasswordForm();">
				<p>
					<label for="user_pass">新的密码<br />
					<input type="text" name="newpass" id="newpass" class="input" value="" size="20" /></label>
				</p>
				<p>
					<label for="user_pass">再输一次<br />
					<input type="password" name="newpass1" id="newpass1" class="input" value="" size="20" /></label>
				</p>
				<input type="hidden" name="lostpassuid" value="${lostpassuid}">
				<p class="submit"><input type="submit" name="wp-submit" id="wp-submit" class="button button-primary button-large" value="修改密码"></p>
			  	
			</form>	
		<#else>
			<p class="message">
				<#if lostpass=='no'>
					<b>输入用户名或密码找回密码</b>
				<#elseif lostpass=='yes-1'>
					<b>非法的url</b>
				<#elseif lostpass=='yes0'>
					<b>已过期的url</b>
				<#else>
					请输入您的用户名或电子邮箱地址。您会收到一封包含创建新密码链接的电子邮件。
				</#if>
			</p>
	
			<form name="lostpasswordform" id="lostpasswordform" action="lostpass.do" method="post" onsubmit="return lostpassForm();">
				<p>
					<label for="user_login">用户名或电子邮件地址<br>
					<input type="text" name="lostpass" id="lostpass" class="input" value="" size="20"></label>
				</p>
					<input type="hidden" name="redirect_to" value="login">
				<p class="submit"><input type="submit" name="wp-submit" id="wp-submit" class="button button-primary button-large" value="获取新密码"></p>
			</form>
			<p id="nav">
				<a href="login.html"><@spring.message "common_login"/> | 	<a href="login.html?action=register"><@spring.message "common_register"/></a></a>
			</p>	
		</#if>
	<#else>
	</#if>

<#if iden != null>
	<script type="text/javascript">
		function wp_attempt_focus(){
			setTimeout( function(){ try{
			d = document.getElementById('user_login');
			d.focus();
			d.select();
			} catch(e){}
			}, 200);
		}
		
		wp_attempt_focus();
		if(typeof wpOnload=='function')wpOnload();
	</script>
</#if>

<p id="backtoblog"><a href="http://www.028888.net/">&larr; Back to <@spring.message "common_name"/></a></p>

</div>


	<div class="clear"></div>
</body>
</html>
