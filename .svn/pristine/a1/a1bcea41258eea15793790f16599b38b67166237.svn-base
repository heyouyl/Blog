<div class="widget widget-tabs">
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#notice" aria-controls="notice" role="tab" data-toggle="tab">网站公告</a></li>
		<li role="presentation"><a href="#centre" aria-controls="centre" role="tab" data-toggle="tab">会员中心</a></li>
		<li role="presentation"><a href="#contact" aria-controls="contact" role="tab" data-toggle="tab">联系站长</a></li>
	</ul>
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane notice active" id="notice">
			<ul>
				<#list noticeList as notice>
					<li>
						<time datetime="${notice['ardate']}">${notice['ardate1']}</time>
	            	<a href="${contextPath}archives/${notice['arurl']}" title="${notice['artitle']}" target="_blank">${notice['arname']}</a>
	           	</li>
				</#list>
         </ul>
		</div>
		<div role="tabpanel" class="tab-pane centre" id="centre">
			<#if null==userlog>
				<h4>需要登录才能进入会员中心</h4>
	            <p> <a data-toggle="modal" data-target="#loginModal" class="btn btn-primary">立即登录</a> <!-- a href="${contextPath}user/login.html?action=register" data-toggle="modal" data-target="#registerModal" class="register" rel="nofollow" --><a href="${contextPath}user/login.html?action=register" class="register" rel="nofollow">现在注册</a> </p>
          	<#else>
          		<li class="item item-02 active">
					<dl>
						<dt><img src="http://gravatar.duoshuo.com/avatar/9db32c2b82d74c2e9aa3f4c7f95805af?s=50&amp;d=http%3A%2F%2Fwww.028888.net%2Fwp-content%2Fthemes%2Fdux%2Fimg%2Favatar-default.png&amp;r=g" width="50" height="50" alt="" class="avatar avatar-50 wp-user-avatar wp-user-avatar-50 photo avatar-default"></dt>
						<dd>${userlog["displayname"]}<span class="text-muted">${userlog["useremail"]}</span></dd>
					</dl>
					<ul>
						<li><a href="${contextPath}user/admin/index.html?useriden=all">我的文章</a></li>
						<li><a href="${contextPath}user/admin/index.html?useriden=comments">我的评论</a></li>
						<li><a href="${contextPath}user/admin/index.html?useriden=info">修改资料</a></li>
						<li><a href="${contextPath}user/admin/index.html?useriden=password">修改密码</a></li>
					</ul>
				</li>
			</#if>
		</div>
		<div role="tabpanel" class="tab-pane contact" id="contact">
			<h2>Email:<br />
			<a href="mailto:${userlog['useremail']}" data-toggle="tooltip" data-placement="bottom" title="${userlog['useremail']}">${userlog['useremail']}</a></h2>
		</div>
	</div>
</div>