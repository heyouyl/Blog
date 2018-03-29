<div class="user-main">
	<ul class="user-commentlist">
		<#list usercomment as ucomment>
			<li>
				<time>${ucomment['commentdate']}</time>
				<p class="note">【${ucomment['commentiden']}】${ucomment['commentcontent']}</p>
				<p class="text-muted">文章：<a target="_blank" href="${contextPath}archives/${ucomment['posturl']}#comment-${ucomment['commentid']}">${ucomment['posttitle']}</a></p>
			</li>
		</#list>
	<script>
		//max：总数据，current：当前页，plink：连接，step：每页数据
		$(".user-commentlist").append(paging(${arcount}, ${page},"dsfsd", ${posts_per_page}));
	</script>
</div>