<div class="title" id="comment">
	<h3>评论 <small>抢沙发</small></h3>
</div>
<!--div id="respond">
	<div class="comment-signarea">
		<h3 class="text-muted">评论前必须登录！</h3>
		<p> <a href="javascript:;" class="btn btn-primary login" rel="nofollow">立即登录</a> &nbsp; <a href="javascript:;" class="btn btn-default register" rel="nofollow">注册</a> </p>
		<h3 class="text-muted">当前文章禁止评论</h3>
	</div>
</div -->
<div id="respond">
	<form action="" method="post" id="comment-form">
		<div class="comment">
			<div class="comment-title"><img class="avatar" src="${contextPath}upload/images/icon/icon.png" alt="" /></div>
			<#if null==userlog>
	        	<input class="ipt" type="text" name="author" id="author" value="" tabindex="2" placeholder="昵称">
				<span class="text-muted">昵称 (必填)</span>
				<input class="ipt" type="text" name="email" id="email" value="" tabindex="3" placeholder="邮箱">
				<span class="text-muted">邮箱 (必填)</span>
				<input type="hidden" value="0" id="userid" name="userid" />
	      	<#else>
	      		<input type="hidden" name="author" id="author" value="${userlog['username']}" />
	      		<input type="hidden" name="email" id="email" value="${userlog['useremail']}" />
					<input type="hidden" value="${userlog['userid']}" id="userid" name="userid" />
	      </#if>
	     		<input type="hidden" value="0" id="replycommentid" name="replycommentid" />
			
			<div class="comment-box">
				<textarea placeholder="您的评论可以一针见血" name="comment" id="comment-textarea" cols="100%" rows="3" tabindex="1" ></textarea>
				<div class="comment-ctrl"> <span class="emotion"><img src="${contextPath}upload/images/face/5.png" width="20" height="20" alt="" />表情</span>
					<div class="comment-prompt"> <i class="fa fa-spin fa-circle-o-notch"></i> <span class="comment-prompt-text"></span> </div>
						<input type="hidden" value="${arid}" class="articleid" />
	                <button type="submit" name="comment-submit" id="comment-submit" tabindex="5" articleid="1">评论</button>
					</div>
				</div>
			</div>
	</form>
</div>
<div id="postcomments">
	<ol class="commentlist">
		<#assign cx=1>
		<#list comments as commentmap>
			<li class="comment-content comment-content-pos"><span class="comment-f">#${cx}</span>
				<div class="comment-avatar"><img class="avatar" src="${contextPath}upload/images/icon/icon.png" alt="" /></div>
				<div class="comment-main">
	            <p>来自<span class="address">${commentmap['commentauthor']}</span>
	            	<#if pcommentparentauthor??>
	            			的评论
	            	<#else>
	            			回复给<span class="address">${commentmap['pcommentparentauthor']}</span>的评论
	            	</#if>
	            	<span class="time">(${commentmap['commentdate']})</span>
	            		<!-- 需要评论id 以及当前用户，表示当前用户回复给这个评论 -->
	            	<a class="comment-reply-link" href="javascript:;" onclick="return replycomment('${commentmap["commentid"]}','${commentmap["commentauthor"]}')" aria-label="回复给${commentmap['commentauthor']}">
	        				回复
	        			</a>
	        			<br />
		      			${commentmap['commentcontent']}</p>
	        	</div>
			</li>
			<#assign cx=cx+1>
		</#list>
	</ol>
	<!-- div class="quotes"><span class="disabled">首页</span><span class="disabled">上一页</span><a class="current">1</a><a href="">2</a><span class="disabled">下一页</span><span class="disabled">尾页</span></div -->
</div>