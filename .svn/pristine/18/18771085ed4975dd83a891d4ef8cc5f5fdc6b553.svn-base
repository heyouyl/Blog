<#include "header.ftl" />
<#include "main/top.ftl" />
<div class="container-user">
	<div class="userside">
		<div class="usermenus">
			<ul class="usermenu cateline">
				<#list topmenu as mapmenus>
					<#if mapmenus['sxattri']==2>
						<li><a href="${contextPath}${mapmenus['cdposit']}" title=${mapmenus['cdtitle']}>${mapmenus['mename']}</a></li>
					<#else>
						<li><a href="${contextPath}fl/${mapmenus['slname']}.html" title=${mapmenus['cdtitle']}>${mapmenus['mename']}</a></li>
					</#if>
	        	</#list>
			</ul>
		</div>
	</div>

	<div class="content">
		<ul class="plinks">
			<li id="linkcat-113">
				<h1>所有的分类</h1>
				<ul class="xoxo blogroll">
            	<#list categorylist.keySet() as key>
            			<#if key != 'uncategorized'>
            				<li><a href="http://www.028888.net/fl/${key}.html" title="View all posts in ${key}">${categorylist[key]}<@spring.message "common_categories_text"/></a></li>							
							</#if>
					</#list>
					<!-- li><a href="http://www.028888.net/fl/axis2.html" title="View all posts in AXIS2">AXIS2&nbsp;&nbsp;7篇文章</a></li>
					<li><a href="http://www.028888.net/fl/dwr.html" title="View all posts in DWR">DWR&nbsp;&nbsp;6篇文章</a></li>
					<li><a href="http://www.028888.net/fl/freemarker.html" title="View all posts in FreeMarker">FreeMarker&nbsp;&nbsp;11篇文章</a></li>
					<li><a href="http://www.028888.net/fl/hadoop" title="View all posts in hadoop">hadoop&nbsp;&nbsp;27篇文章</a></li>
					<li><a href="http://www.028888.net/fl/javabasics" title="View all posts in java基础">java基础&nbsp;&nbsp;95篇文章</a></li>
					<li><a href="http://www.028888.net/fl/jspstudy.html" title="View all posts in jsp学习">jsp学习&nbsp;&nbsp;67篇文章</a></li>
					<li><a href="http://www.028888.net/fl/linuxbasics.html" title="View all posts in linux基础">linux基础&nbsp;&nbsp;40篇文章</a></li>
					<li><a href="http://www.028888.net/fl/maven.html" title="View all posts in Maven">Maven&nbsp;&nbsp;20篇文章</a></li>
					<li><a href="http://www.028888.net/fl/oracle-2.html" title="View all posts in Oracle">Oracle&nbsp;&nbsp;12篇文章</a></li>
					<li><a href="http://www.028888.net/fl/springmvc.html" title="View all posts in SpringMVC">SpringMVC&nbsp;&nbsp;54篇文章</a></li>
					<li><a href="http://www.028888.net/fl/sshstudy.html" title="View all posts in SSH框架学习">SSH框架学习&nbsp;&nbsp;86篇文章</a></li>
					<li><a href="http://www.028888.net/fl/swing.html" title="View all posts in Swing">Swing&nbsp;&nbsp;9篇文章</a></li>
					<li><a href="http://www.028888.net/fl/webfe.html" title="View all posts in web前端">web前端&nbsp;&nbsp;35篇文章</a></li>
					<li><a href="http://www.028888.net/fl/wordpress.html" title="View all posts in wordpress">wordpress&nbsp;&nbsp;20篇文章</a></li>
					<li><a href="http://www.028888.net/fl/linuxserver.html" title="View all posts in 服务器">服务器&nbsp;&nbsp;24篇文章</a></li>
					<li><a href="http://www.028888.net/fl/webnet.html" title="View all posts in 网站网络">网站网络&nbsp;&nbsp;9篇文章</a></li>
					<li><a href="http://www.028888.net/fl/activitistudy.html" title="View all posts in 网站网络">Activiti&nbsp;&nbsp;10篇文章</a></li>
					<li><a href="http://www.028888.net/fl/liferaystudy.html" title="View all posts in 资源下载">Liferay&nbsp;&nbsp;9篇文章</a></li>
					<li><a href="http://www.028888.net/fl/kettlestudy.html" title="View all posts in 资源下载">Kettle&nbsp;&nbsp;18篇文章</a></li>
					<li><a href="http://www.028888.net/fl/zyxz.html" title="View all posts in 资源下载">资源下载&nbsp;&nbsp;18篇文章</a></li -->
				</ul>
			</li>
		</ul>	
	</div>
</div>
<#include "footer.ftl" />