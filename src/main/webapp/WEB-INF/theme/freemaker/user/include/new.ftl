<div class="user-main">
	<form>
		 <ul class="user-meta user-postnew">
		  	<li><label>标题</label>
				<input type="text" class="form-control" name="post_title" placeholder="文章标题">
		  	</li>
		  	<li><label>内容</label>
				<textarea name="post_content" class="form-control" rows="12" placeholder="文章内容"></textarea>
		  	</li>
		  	<li><label>来源链接</label>
				<input type="text" class="form-control" name="post_url" placeholder="文章来源链接">
		  	</li>
		  	<li>
		  		<br>
				<input type="button" evt="postnew.submit" url="update/pending.do" class="btn btn-primary" name="submit" value="提交审核">
				<input type="button" evt="postnew.submit" url="update/draft.do" class="btn btn-primary" name="submit" value="提交草稿">
				<input type="hidden" name="userid" value="${userlog['userid']}">
		  	</li>
		  </ul>
	</form>
</div>
