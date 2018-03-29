<div class="user-main">
	<form>
	  	<ul class="user-meta">
	  		<li><label>入门时间</label>
				${userlog['userdate']}
	  		</li>
	  		<li><label>用户名</label>
				<input type="input" class="form-control" disabled="" value="${userlog['username']}">
	  		</li>
	  		<li><label>邮箱</label>
				<input type="email" class="form-control" disabled="" value="${userlog['useremail']}">
	  		</li>
	  		<li><label>昵称</label>
				<input type="input" class="form-control" name="displayname" value="${userlog['displayname']}">
	  		</li>
	  		<li><label>网址</label>
				<input type="input" class="form-control" name="blogurl" value="${userlog['blogurl']}">
	  		</li>
	  		<li><label>QQ</label>
				<input type="input" class="form-control" name="user_qq" value="${userlog['user_qq']}">
	  		</li>
	  		<li><label>微信号</label>
				<input type="input" class="form-control" name="user_weixin" value="${userlog['user_weixin']}">
	  		</li>
	  		<li><label>微博地址</label>
				<input type="input" class="form-control" name="user_weibo" value="${userlog['user_weibo']}">
	  		</li>
	  		<li>
				<input type="button" evt="info.submit" url="update/info.do" class="btn btn-primary" name="submit" value="确认修改资料">
				<input type="hidden" name="action" value="info.edit">
	  		</li>
	  	</ul>
	</form>
</div>