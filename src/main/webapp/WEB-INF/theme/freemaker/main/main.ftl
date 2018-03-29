<section class="container">
  <div class="content-wrap">
    <div class="content">
      <div class="jumbotron">
        <h1>欢迎访问java爱好者</h1>
        <p>在这里可以看到前端技术，后端程序，网站内容管理系统等文章，还有我的程序人生！</p>
      </div>
      
      <!-- 幻灯片 -->
      <#assign a=0>
      <#assign a1=0>
   <#if bdgg??>
		<div id="focusslide" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
        	<#list bdgg.keySet() as key>
        		<li data-target="#focusslide" data-slide-to="${a}" class="<#if a == 0>active</#if>"></li>
          	<#assign a=a+1>							
			</#list>
        </ol>
        <div class="carousel-inner" role="listbox">
        		<#list bdgg.keySet() as key>	
    				<div class="item <#if a1 == 0>active</#if>"> <a href="${bdgg[key]['picurl']!'#'}" target="_blank"><img src="${contextPath}upload/images/images/${bdgg[key]['url']!}" alt="${bdgg[key]['title']}" class="img-responsive"></a> 
            		<!--<div class="carousel-caption"> </div>--> 
          		</div>
          		<#assign a1=2>							
				</#list>
        </div>
        <a class="left carousel-control" href="#focusslide" role="button" data-slide="prev" rel="nofollow"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">上一个</span> </a> <a class="right carousel-control" href="#focusslide" role="button" data-slide="next" rel="nofollow"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">下一个</span> </a> 
       </div>
	</#if>
      

      <article class="excerpt-minic excerpt-minic-index">
        <h2><span class="red">【今日推荐】</span><a href="${contextPath}archives/${tuijie.arurl}" title="${tuijie.artitle}">${tuijie.arname}</a></h2>
        <p class="note">${tuijie.excerpt}</p>
      </article>
      <div class="title">
        <h3>最新发布</h3>
        <div class="more">
        	<#list latestmenu as maplatest>
				<#if maplatest['sxattri']==2>
					<a href="${contextPath}${maplatest['cdposit']}" title=${maplatest['cdtitle']}>${maplatest['mename']}</a>
				<#else>
					<a href="${contextPath}fl/${maplatest['slname']}.html" title=${maplatest['cdtitle']}>${maplatest['mename']}</a>
				</#if>
        	</#list>
        </div>
      </div>
      <#assign showarimglist='no' />
      <#assign x=1 />
      	<#if showarimglist=='no'>
      		<#list article as listac>
	    		<article class="excerpt excerpt-${x} excerpt-text">
			        <header><a class="cat" href="${contextPath}fl/${listac.slug}.html">${listac.category}<i></i></a>
			        	<h2><a href="${contextPath}archives/${listac.arurl}" title="${listac.artitle}">${listac.arname}</a></h2>
			        </header>
			        <p class="meta">
			        	<time class="time"><i class="glyphicon glyphicon-time"></i> ${listac.ardate}</time>
			        	<span class="views"><i class="glyphicon glyphicon-eye-open"></i> 共${listac.views}人围观</span> <a class="comment" href="${contextPath}"><i class="glyphicon glyphicon-comment"></i> ${article.commentcount}个不明物体</a></p>
			    	<p class="note">${listac.excerpt}</p>
			    </article>
	      		<#assign x=x+1 />
	        </#list>
		<#else>
      		<#list article as listac>
	    		<article class="excerpt excerpt-${x}"><a class="focus" href="${contextPath}" title=""><img class="thumb" data-original="upload/images/excerpt.jpg" src="upload/images/excerpt.jpg" alt=""></a>
			        <header><a class="cat" href="${contextPath}fl/${listac.slug}.html">${listac.category}<i></i></a>
			        	<h2><a href="${contextPath}archives/${listac.arurl}" title="${listac.artitle}">${listac.arname}</a></h2>
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
			<#if page.number == 1>
				<li class="prev-page"></li>
			<#else>
				<li class="prev-page"><a href="?page=${contextPath}page/${page?number-1}.html">上一页</a></li>
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
					<#assign pagebegin=pagebegin+1 />
				<#else>
					<li class="active"><span>1</span></li>
					<li><span>...</span></li>
				</#if>
			<#else>
				<#if pagebegin == 1>
					<li><a href="${contextPath}page/1">1</a></li>
					<#assign pagebegin=pagebegin+1 />
				<#else>
					<li><a href="${contextPath}page/1">1</a></li>
					<li><span>...</span></li>
				</#if>
			</#if>

			<#list pagebegin?number..pageend?number as k>					
				<#if page==k>
					<li class="active"><span>${k}</span></li>
					<#else>
						<li><a href="${contextPath}page/${k}.html">${k}</a></li>
				</#if>

			</#list>
			<#if page?number == pages>
				<li class="next-page"></li>
			<#else>
				<li><span>...</span></li>
				<li class="next-page"><a href="${contextPath}page/${page?number+1}.html">下一页</a></li>
			</#if>
	          <li><span>共 ${pages}页</span></li>
        	</ul>
		</nav>
	</div>
</div>
<#include "model.ftl"/>
</section>