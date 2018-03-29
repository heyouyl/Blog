<div class="user-main">
	<form>
	  	<ul class="user-meta">
	  		<li><label>原密码</label>
				<input type="password" class="form-control" name="passwordold">
	  		</li>
	  		<li><label>新密码</label>
				<input type="password" class="form-control" name="password">
	  		</li>
	  		<li><label>重复新密码</label>
				<input type="password" class="form-control" name="password2">
	  		</li>
	  		<li>
				<input type="button" evt="password.submit" url="update/password.do" class="btn btn-primary" name="submit" value="确认修改密码">
				<input type="hidden" name="action" value="password.edit">
	  		</li>
	  	</ul>
	</form>
</div>