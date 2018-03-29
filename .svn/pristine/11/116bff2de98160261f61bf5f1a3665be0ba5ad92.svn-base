<body class="user-select">
	<header class="header">
	  <nav class="navbar navbar-default" id="navbar">
	    <div class="container">
	      <div class="header-topbar hidden-xs link-border">
	        <ul class="site-nav topmenu">
	        	<#list topmenu as mapmenus>
					<#if mapmenus['sxattri']==2>
						<li><a href="${contextPath}${mapmenus['cdposit']}" title=${mapmenus['cdtitle']}>${mapmenus['mename']}</a></li>
					<#else>
						<li><a href="${contextPath}fl/${mapmenus['slname']}.html" title=${mapmenus['cdtitle']}>${mapmenus['mename']}</a></li>
					</#if>
	        	</#list>
	          <!-- li><a href="tags.html">标签云</a></li>
	          <li><a href="readers.html" rel="nofollow">读者墙</a></li>
	          <li><a href="links.html" rel="nofollow">友情链接</a></li -->
	          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" rel="nofollow">关注本站 <span class="caret"></span></a>
	            <ul class="dropdown-menu header-topbar-dropdown-menu">
	              <li><a data-toggle="modal" data-target="#WeChat" rel="nofollow"><i class="fa fa-weixin"></i> 微信</a></li>
	              <li><a href="#" rel="nofollow"><i class="fa fa-weibo"></i> 微博</a></li>
	              <li><a data-toggle="modal" data-target="#areDeveloping" rel="nofollow"><i class="fa fa-rss"></i> RSS</a></li>
	            </ul>
	          </li>
	        </ul>
	        <#if null==userlog>
	        	<a data-toggle="modal" data-target="#loginModal" class="login" rel="nofollow">Hi,请登录</a>&nbsp;&nbsp;<!-- a data-toggle="modal" data-target="#registerModal" class="register" rel="nofollow" --><a href="${contextPath}user/login.html?action=register">我要注册</a>&nbsp;&nbsp;<a href="${contextPath}user/login.html?action=lostpassword" rel="nofollow">找回密码</a> 
	      	<#else>
			Hi, ${userlog["displayname"]}&nbsp;&nbsp;<a href="${contextPath}user/admin/index.html">进入会员中心</a><#if "10"==userlogin>&nbsp;&nbsp;<a target="_blank" href="${contextPath}admin/index.html">后台管理</a></#if>
	      	</#if>
	      </div>
	      <div class="navbar-header">
	        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-navbar" aria-expanded="false"> <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
	        <h1 class="logo hvr-bounce-in"><a href="${contextPath}" title=""><img src="${contextPath}upload/images/logo.png" alt=""></a></h1>
	        <div class="brand">${brand}</div>
	      </div>
	      <div class="collapse navbar-collapse" id="header-navbar">
	        <ul class="nav navbar-nav navbar-right">
	        	<#list topnav as mapnavs>
					<#if mapnavs['sxattri']==2>
							<#if slnameiden == mapnavs['slname']>
								<li class="hidden-index active"><a data-cont="${mapnavs['cdtitle']}" href="${contextPath}${mapnavs['cdposit']}" title="${mapnavs['cdtitle']}">${mapnavs['mename']}</a></li>
							<#else>
								<li><a href="${contextPath}${mapnavs['cdposit']}" title="${mapnavs['cdtitle']}">${mapnavs['mename']}</a></li>
							</#if>
					<#else>
						<#if slnameiden == mapnavs['slname']>
							<li class="hidden-index active"><a data-cont="${mapnavs['cdtitle']}" href="${contextPath}fl/${mapnavs['slname']}.html" title="${mapnavs['cdtitle']}">${mapnavs['mename']}</a></li>
						<#else>
							<li><a data-cont="${mapnavs['cdtitle']}" href="${contextPath}fl/${mapnavs['slname']}.html" title=${mapnavs['cdtitle']}>${mapnavs['mename']}</a></li>
						</#if>
					</#if>
	        	</#list>
	        </ul>
	        <form class="navbar-form visible-xs" action="${contextPath}" method="get">
	          <div class="input-group">
	            <input type="text" name="s" class="form-control" placeholder="请输入关键字" maxlength="20" autocomplete="off">
	            <span class="input-group-btn">
	            <button class="btn btn-default btn-search" type="submit">搜索</button>
	            </span> </div>
	        </form>
	      </div>
	    </div>
	  </nav>
	</header>