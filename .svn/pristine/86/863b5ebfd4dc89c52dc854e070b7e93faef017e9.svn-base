<section class="container">
  <div class="content-wrap">
    <div class="content">
      <header class="article-header">
        <h1 class="article-title"><a href="${contextPath}archives/${article.arurl}" title="${article.artitle}">${article.arname}</a></h1>
        <div class="article-meta">
        	<span class="item article-meta-time">
         	<time class="time" data-toggle="tooltip" data-placement="bottom" title="${article.ardate}"><i class="glyphicon glyphicon-time"></i> ${article.ardate}</time>
         </span>
         <!-- span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom" title="来源：第一PHP社区"><i class="glyphicon glyphicon-globe"></i> 第一PHP社区</span-->
         <span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom">
				<i class="glyphicon glyphicon-list"></i>          		
			<#list article['categorys'] as fenlei>
         		<a href="${contextPath}fl/${fenlei['slug']}.html" title="${fenlei['category']}">${fenlei['category']}</a>
         	</#list>
         </span>
         <span class="item article-meta-views" data-toggle="tooltip" data-placement="bottom" title="查看：${article.views?number+1}">
         	<i class="glyphicon glyphicon-eye-open"></i> 共${article.views?number+1}人围观
         </span>
         <span class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom" title="评论：0">
         	<i class="glyphicon glyphicon-comment"></i> ${article.commentcount}个不明物体
         </span>
         </div>
      </header>
      <article class="article-content">
      <#if article.arname != '支持博主'>
      	${guangbd['archives_1']}
      </#if>

        ${article.content}
        <br />
        	<a href='2016_06_1176.html'>如果您觉得本文章对您有用，也为了更好的运营博客，您可以点击这里捐助</a>
        <p class="article-copyright hidden-xs">未经允许不得转载：<a href="/">【java爱好者】博客</a> » <a href="${contextPath}archives/${article.arurl}">${article.artitle}</a></p>
      </article>
      <div class="article-tags">标签：
	      <#list article['tags'] as biaoqian>
	      	<a href="${contextPath}bq/${biaoqian['slug']}.html" rel="tag">${biaoqian['category']}</a>
	      </#list>
	   <#if article.artype=='post'>
	   <div class="blogadshow"></div>
	   	${guangbd['archives_2']}
		</#if>

		</div>
		<!--#include "model/widget_arbut.ftl"/ -->
		<#if article.arname!='支持博主'>
			<#include "model/widget_comment.ftl"/>
		</#if>
    </div>
  </div>
<#include "model.ftl"/>
</section>