<footer class="footer">
  <div class="container">
  	<div class="flinks">
		<strong>友情链接</strong>
		<ul class="xoxo blogroll">
		<#list listlinks as link>
	         <li><a href="${link.linkurl}" title="${link.linkname}" target="${link.linktarget}">${link.linkname}</a></li>
		</#list>
		</ul>

	</div>
    <p>${web_cusbut}</p>
  </div>
  <div id="gotop"><a class="gotop"></a></div>
</footer>
<!--微信二维码模态框-->
<div class="modal fade user-select" id="WeChat" tabindex="-1" role="dialog" aria-labelledby="WeChatModalLabel">
  <div class="modal-dialog" role="document" style="margin-top:120px;max-width:280px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="WeChatModalLabel" style="cursor:default;">微信关注”myjavafans“ </h4>
      </div>
      <div class="modal-body" style="text-align:center"> <img src="${contextPath}upload/images/wxzzh200.png" alt="" style="cursor:pointer"/> </div>
    </div>
  </div>
</div>
<!--该功能正在日以继夜的开发中-->
<div class="modal fade user-select" id="areDeveloping" tabindex="-1" role="dialog" aria-labelledby="areDevelopingModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="areDevelopingModalLabel" style="cursor:default;">该功能正在日以继夜的开发中…</h4>
      </div>
      <div class="modal-body"> <img src="${contextPath}upload/images/baoman/baoman_01.gif" alt="深思熟虑" />
        <p style="padding:15px 15px 15px 100px; position:absolute; top:15px; cursor:default;">很抱歉，程序猿正在日以继夜的开发此功能，本程序将会在以后的版本中持续完善！</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">朕已阅</button>
      </div>
    </div>
  </div>
</div>
<!--登录注册模态框-->
<div class="modal fade user-select" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form action="${contextPath}user/clogin.do" method="post">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="loginModalLabel">登录</h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="loginModalUserNmae">用户名</label>
            <input type="text" class="form-control" name="log" id="loginModalUserNmae" placeholder="请输入用户名" autofocus maxlength="15" autocomplete="off" required>
          </div>
          <div class="form-group">
            <label for="loginModalUserPwd">密码</label>
            <input type="password" class="form-control" name="pwd" id="loginModalUserPwd" placeholder="请输入密码" maxlength="18" autocomplete="off" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          <button type="submit" class="btn btn-primary signsubmit-loader">登录</button>
        </div>
      </form>
    </div>
  </div>
</div>
<div class="modal fade user-select" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form action="${contextPath}user/cregister.do" method="post">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="loginModalLabel">注册</h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="loginModalUserNmae">用户名</label>
            <input type="text" class="form-control" name="log" id="loginModalUserNmae" placeholder="请输入用户名" autofocus maxlength="15" autocomplete="off" required>
          </div>
          <div class="form-group">
            <label for="loginModalUserEmail">邮箱</label>
            <input type="email" class="form-control" name="pwd" id="loginModalUserPwd" placeholder="请输入邮箱" maxlength="18" autocomplete="off" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary btn-block signsubmit-loader">快速注册</button>
        </div>
      </form>
    </div>
  </div>
</div>
<!--右键菜单列表-->
<div id="rightClickMenu">
  <ul class="list-group rightClickMenuList">
    <li class="list-group-item disabled">欢迎访问【java爱好者】博客</li>
    <li class="list-group-item"><span>URL：</span>www.028888.net</li>
  </ul>
</div>
<script src="${contextPath}res/js/bootstrap.min.js"></script> 
<script src="${contextPath}res/js/jquery.ias.js"></script> 
<script src="${contextPath}res/js/scripts.js"></script>
<#if slnameiden=="useradmin">
	<script src="${contextPath}res/js/loader.js"></script>
	<script src="${contextPath}res/js/lazyload.js"></script>
	<script src="${contextPath}res/js/myl/user.js"></script>
	<script src="${contextPath}res/js/myl/libs/router.min.js"></script>
	<script src="${contextPath}res/js/myl/libs/jsrender.min.js"></script>
</#if>
<#if slnameiden=="articleiden">
	<script src="${contextPath}res/js/myl/plugin.js"></script>
	<script>
		$(".article-content").html(parseShortcode($(".article-content").html()));
	</script>
</#if>
${guangbd['index_1']}
</body>
</html>