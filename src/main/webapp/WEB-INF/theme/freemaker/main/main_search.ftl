<section class="container">
  <div class="content-wrap">
    <div class="content">
      <div class="title">
        <h3>搜索：${searchs}</h3>
      </div>
		<#assign showarimglist='no' />
      <#assign x=1 />
      	<#if showarimglist=='no'>
      		<#list article as listac>
	    		<article class="excerpt excerpt-${x} excerpt-text">
			        <header>
			        		<h2><a target="_blank" href="${contextPath}archives/${listac.arurl}" title="${listac.artitle}">${listac.arname}</a></h2>
			        </header>
			        <p class="meta">
			        	<time class="time"><i class="glyphicon glyphicon-time"></i> ${listac.ardate}</time>
			        	<span class="views"><i class="glyphicon glyphicon-eye-open"></i> 共${listac.views}人围观</span> <a class="comment" href="${contextPath}article.html#comment"><i class="glyphicon glyphicon-comment"></i> ${article.commentcount}个不明物体</a></p>
			    	<p class="note">${listac.excerpt}</p>
			    </article>
	      		<#assign x=x+1 />
	        </#list>
		<#else>
      		<#list article as listac>
	    		<article class="excerpt excerpt-${x}"><a class="focus" href="${contextPath}" title=""><img class="thumb" data-original="upload/images/excerpt.jpg" src="upload/images/excerpt.jpg" alt=""></a>
			        <header>
			        		<h2><a target="_blank" href="${contextPath}archives/${listac.arurl}" title="${listac.artitle}">${listac.arname}</a></h2>
			        </header>
			        <p class="meta">
			        	<time class="time"><i class="glyphicon glyphicon-time"></i> ${listac.ardate}</time>
			        	<span class="views"><i class="glyphicon glyphicon-eye-open"></i> 共${listac.views}人围观</span> <a class="comment" href="${contextPath}"><i class="glyphicon glyphicon-comment"></i> ${article.commentcount}个不明物体</a></p>
			    	<p class="note">${listac.excerpt}</p>
			    </article>
	      		<#assign x=x+1 />
	        </#list>
      	</#if>

		<#assign pages="${((arcount?number)/(posts_per_page?number))?ceiling}" /> 
		<nav class="pagination" style="display: none;">
			<ul>
			<li class="prev-page"></li>
			<#if page == 1>
				<li class="prev-page"></li>
			<#else>
				<li class="prev-page"><a href="${contextPath}page/${page?number-1}.html?s=${searchs}">上一页</a></li>
			</#if>

			<#if page?number-3 gt 2>
				<#assign pagebegin=page?number-3 />
			<#else>
				<#assign pagebegin=1 />
			</#if>

			<#if page?number+3 gt pages?number>
				<#assign pageend=pages />
			<#else>
				<#assign pageend=page?number+3 />
			</#if>

			<#if page==1>
				<#if pagebegin == 1>
					<li class="active"><span>1</span></li>
					<#if pages?number != 1>
						<#assign pagebegin=pagebegin+1 />
					</#if>
				<#else>
					<li class="active"><span>1</span></li>
					<li><span>...</span></li>
				</#if>
			<#else>
				<#if pagebegin == 1>
					<li><a href="${contextPath}page/1.html?s=${searchs}">1</a></li>
					<#if pages?number != 1>
						<#assign pagebegin=pagebegin+1 />
					</#if>
				<#else>
					<li><a href="${contextPath}page/1.html?s=${searchs}">1</a></li>
					<li><span>...</span></li>
				</#if>
			</#if>

			<#list pagebegin?number..pageend?number as k>
				<#if pages!=1>		
					<#if page==k>
						<li class="active"><span>${k}</span></li>
					<#else>
						<li><a href="${contextPath}page/${k}.html?s=${searchs}">${k}</a></li>
					</#if>
				</#if>
			</#list>
			<#if page?number == pages>
				<li class="next-page"></li>
			<#else>
				<li><span>...</span></li>
				<li class="next-page"><a href="${contextPath}page/${page?number+1}.html?s=${searchs}">下一页</a></li>
			</#if>
	          <li><span>共 ${pages}页</span></li>
        	</ul>
		</nav>
    </div>
  </div>
<#include "model.ftl"/>
</section>