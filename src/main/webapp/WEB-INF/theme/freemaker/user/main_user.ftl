<section class="container">
	<div class="container-user">
		<div class="userside">
			<div class="usertitle">
				<img src="http://gravatar.duoshuo.com/avatar/9db32c2b82d74c2e9aa3f4c7f95805af?s=50&#038;d=http%3A%2F%2Fwww.028888.net%2Fwp-content%2Fthemes%2Fdux%2Fimg%2Favatar-default.png&#038;r=g" width="50" height="50" alt="" class="avatar avatar-50 wp-user-avatar wp-user-avatar-50 photo avatar-default" />				<h2>java</h2>
				<input type="hidden" value="${userlog['userid']}" id="userid" name="userid" />
			</div>
			<div class="usermenus">	
				<ul class="usermenu">
					<li class="usermenu-post-new"><a href="?useriden=new">发布文章</a></li>
					<li class="usermenu-posts"><a href="?useriden=all">我的文章</a></li>
					<li class="usermenu-comments"><a href="?useriden=comments">我的评论</a></li>
					<li class="usermenu-info"><a href="?useriden=info">修改资料</a></li>
					<li class="usermenu-password"><a href="?useriden=password">修改密码</a></li>
					<li class="usermenu-signout"><a href="../loginout.html">退出</a></li>
				</ul>
			</div>
		</div>
		<div class="content" id="contentframe">
			<#if useriden=="new">
				<#include "user/include/new.ftl" />
				<script>
					$('.usermenus li').removeClass('active');
					$('.usermenu-post-new').addClass('active');
				</script>
			<#elseif useriden=="all">
				<#include "user/include/all.ftl" />
				<script>
					$('.usermenus li').removeClass('active');
					$('.usermenu-posts').addClass('active');
					//$('.user-postmenu a:eq(0)').addClass('active');
				</script>
			<#elseif useriden=="comments">
				<#include "user/include/comments.ftl" />
				<script>
					$('.usermenus li').removeClass('active');
					$('.usermenu-comments').addClass('active');
				</script>
			<#elseif useriden=="info">
				<#include "user/include/info.ftl" />
				<script>
					$('.usermenus li').removeClass('active');
					$('.usermenu-info').addClass('active');
				</script>
			<#elseif useriden=="password">
				<#include "user/include/password.ftl" />
				<script>
					$('.usermenus li').removeClass('active');
					$('.usermenu-password').addClass('active');
				</script>
			<#else>
				<#include "user/include/error.ftl" />
			</#if>
			<div class="user-tips"></div>
		</div>
			</div>
</section>