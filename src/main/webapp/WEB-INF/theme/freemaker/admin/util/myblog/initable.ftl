<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><@spring.message "admin_updateset"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <link href='${contextPath}liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css' rel="stylesheet" type="text/css" />
    <link href='${contextPath}liger/lib/ligerUI/skins/Gray2014/css/all.css' rel="stylesheet" />
    <script src='${contextPath}liger/lib/jquery/jquery-1.5.2.min.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/ligerui.all.js'></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerForm.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerComboBox.js'></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js'></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerRadioList.js'></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerListBox.js'></script>
 

    <script type="text/javascript">
        $(function ()
        {
            $("#formupdateinit").ligerForm();
        }); 
    </script>
    <style type="text/css">
            body
            {
                padding-left:10px;
                font-size:13px;
            }
            h1
            {
                font-size:20px;
                font-family:Verdana;
            }
            h4
            {
                font-size:16px;
                margin-top:25px;
                margin-bottom:10px;
            }

            .description
            {
                padding-bottom:30px;
                font-family:Verdana;
            }
            .description h3
            {
                color:#CC0000;
                font-size:16px;
                margin:0 30px 10px 0px;
                padding:45px 0 8px; 
                border-bottom:solid 1px #888;
            }
        td {
            padding: 5px;
        }

    </style>

</head>

<body style="padding:10px">
 
     <h1><@spring.message "admin_updateset"/></h1>      
    <div id="formupdateinit" >
        <table>
            
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_webname"/></label>
                </td>
                <td>
                	<input id="web_title" name="web_title" value="${setinit['web_title']}" lab="<@spring.message 'admin_webname'/>" class="ui-textbox" theme="simple"/>
                </td>
                <td>
                	<@spring.message "admin_blogname"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_des"/></label>
                </td>
                <td>
                	<input id="web_des" name="web_des" value="${setinit['web_title']}" lab="<@spring.message 'admin_web_des'/>" class="ui-textbox" theme="simple"/>
                </td>
                <td>
                	<@spring.message "admin_web_dess"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_installurl"/></label>
                </td>
                <td>
                	<input id="web_installurl" name="web_installurl" value="${setinit['web_installurl'] }" lab="<@spring.message 'admin_web_installurl'/>" class="ui-textbox" theme="simple" />
                </td>
                <td>
                	<@spring.message "admin_web_installurls"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_url"/></label>
                </td>
                <td>
                	<input id="web_url" name="web_url" value="${setinit['web_url'] }" lab="<@spring.message 'admin_web_url'/>" class="ui-textbox" theme="simple" />
                </td>
                <td>
                	<@spring.message "admin_web_urls"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"></label><@spring.message "admin_web_openre"/></label>
                </td>
                <td>
                	<#if "openreg"== setinit['web_openre']>
                		<input id="web_openre" name="web_openre" value="openreg" lab="<@spring.message 'admin_web_openre'/>" class="ui-checkbox" theme="simple" checked="checked" />
                	<#else>
                		<input id="web_openre" name="web_openre" value="noopen" lab="<@spring.message 'admin_web_openre'/>" class="ui-checkbox" theme="simple" />
                	</#if>
                </td>
                <td>
                	<@spring.message "admin_web_openres"/>
                </td>
            </tr>
            <tr>
            	<!-- 定义用户组 -->
            	<#assign freeweekug = [
						['普通用户','0'],
						['投稿用户','1'],
						['网站编辑','3'],
						['网站管理','10']
					] >
                <td>
                    <label for="textbox1"><@spring.message "admin_web_newusergroup"/></label>
                </td>
                <td>
                	<select name="web_newusergroup" validate="{required:true}">
                		<#list freeweekug as feug>
                			<#if feug[1]== setinit['web_newusergroup']>
                				<option value="${feug[1]}" selected="selected">${feug[0]}</option>
		                	<#else>
		                		<option value="${feug[1]}">${feug[0]}</option>
			                </#if>		
		               </#list>
                	</select>
                </td>
                <td>
                	<@spring.message "admin_web_newusergroups"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_menuopsition"/></label>
                </td>
                <td>
                	<input id="web_menuopsition" name="web_menuopsition" value='${setinit["web_menuopsition"] }' lab="<@spring.message 'admin_web_menuopsition'/>" class="ui-textbox" theme="simple" />
                </td>
                <td>
                	<@spring.message "admin_web_menuopsitions"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_index_tuijie"/></label>
                </td>
                <td>
                	<select name="index_tuijie" id="index_tuijie" ltype="select">
	                		<#list categorylist.keySet() as key>
								<#if key==setinit["index_tuijie"]>
									<option value="${key}" selected="selected">${categorylist[key]}</option>									
								<#else>
									<option value="${key}">${categorylist[key]}</option>	
								</#if>								
							</#list>
						</select>
                </td>
                <td>
                	<@spring.message "admin_index_tuijies"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_notice_size"/></label>
                </td>
                <td>
                	<input id="notice_size" name="notice_size" value='${setinit["notice_size"] }' lab="<@spring.message 'admin_notice_size'/>" class="ui-textbox" theme="simple" />
                </td>
                <td>
                	<@spring.message "admin_index_tuijies"/>
                </td>
            </tr>
            <tr>
            <!-- 定义时间 -->
            	<#assign freeweeds = [
					['Y年n月j日','2016年12月8日'],
					['Y-m-d','2016-12-08'],
					['m/d/Y','12/08/2016'],
					['d/m/Y','08/12/2016']
				] >
                <td>
                    <label for="textbox1"><@spring.message "admin_datef"/></label>
                </td>
                <td>
                	<table id="datef">
                		<#list freeweeds as fed>
		                	<#if fed[0]== setinit['datef']>
								<tr><td><input type="radio" value="${fed[0]}" name="datef" checked/></td><td>${fed[1]}</td></tr>
							<#else>
								<tr><td><input type="radio" value="${fed[0]}" name="datef" /></td><td>${fed[1]}</td></tr>
							</#if>
						</#list>
				    </table>
                </td>
                <td>
                	<@spring.message "admin_datefs"/>
                </td>
            </tr>
            <tr>
            <!-- 定义时间 -->
            	<#assign freeweekts = [
						['ag:i','下午1:57'],
						['g:i A','1:57 下午'],
						['H:i','13:57']
					] >
                <td>
                    <label for="textbox1"><@spring.message "admin_timef"/></label>
                </td>
                <td>
					<table id="timef">
	                	<#list freeweekts as fet>
		                	<#if fet[0]== setinit['timef']>
		                		<tr><td><input type="radio" value="${fet[0]}" name="timef" checked/></td><td>${fet[1]}</td></tr>
				            <#else>
				               	<tr><td><input type="radio" value="${fet[0]}" name="timef" /></td><td>${fet[1]}</td></tr>
					        </#if>			
				        </#list>
				    </table>
                </td>
                <td>
                	<@spring.message "admin_timefs"/>
                </td>
            </tr>
            	<!-- 定义星期 -->
            	<#assign freeweeks = [
						['星期一','1'],
						['星期二','2'],
						['星期三','3'],
						['星期四','4'],
						['星期五','5'],
						['星期六','6'],
						['星期日','7']
					] >
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_startweek"/></label>
                </td>
                <td>
                	<select id="web_startweek" name="web_startweek" autocomplete="off" validate="{required:true}">
                		<#list freeweeks as fes>
                				<#if fes[1]== setinit['web_startweek']>
		                			<option value="${fes[1]}" selected="selected">${fes[0]}</option>
		                			<#else>
		                			<option value="${fes[1]}">${fes[0]}</option>
			                	</#if>
										
		               </#list>
                	</select>
                </td>
                <td>
                	<@spring.message "admin_web_startweeks"/>
                </td>
            </tr>
            
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_brandtext"/></label>
                </td>
                <td>
                	<textarea cols="100" rows="2" class="l-textarea" id="web_brandtext" name="web_brandtext" style="width:400px">${setinit['web_brandtext'] }</textarea>
                </td>
                <td>
                	<@spring.message "admin_web_brandtexts"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_indexkey"/></label>
                </td>
                <td>
						<textarea cols="100" rows="4" class="l-textarea" id="web_indexkey" name="web_indexkey" style="width:400px">${setinit['web_indexkey'] }</textarea>
                </td>
                <td>
                	<@spring.message "admin_web_indexkeys"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_indexdes"/></label>
                </td>
                <td>
                	<textarea cols="100" rows="4" class="l-textarea" id="web_indexdes" name="web_indexdes" style="width:400px">${setinit['web_indexdes'] }</textarea>
                </td>
                <td>
                	<@spring.message "admin_web_indexdess"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_custop"/></label>
                </td>
                <td>
                	<textarea cols="100" rows="6" class="l-textarea" id="web_custop" name="web_custop" style="width:400px">${setinit['web_custop'] }</textarea>
                </td>
                <td>
                	<@spring.message "admin_web_custops"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_cusbut"/></label>
                </td>
                <td>
                	<textarea cols="100" rows="6" class="l-textarea" id="web_cusbut" name="web_cusbut" style="width:400px">${setinit['web_cusbut'] }</textarea>
                </td>
                <td>
                	<@spring.message "admin_web_cusbuts"/>
                </td>
            </tr>
            
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_icp"/></label>
                </td>
                <td>
                	<input id="web_icp" name="web_icp" value="${setinit['web_icp'] }" lab="<@spring.message 'admin_web_icp'/>" class="ui-textbox" theme="simple" />
                </td>
                <td>
                	<@spring.message "admin_web_icps"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textbox1"><@spring.message "admin_web_language"/></label>
                </td>
                <td>
                	<select name="web_language" validate="{required:true}">
		                <option value="cn" selected="selected">简体中文</option>
		                <option value="en">English</option>
                	</select>
                </td>
                <td>
                	<@spring.message "admin_web_languages"/>
                </td>
            </tr>
            <tr>
                <td colSpan=2> 
                    <input value="<@spring.message 'admin_button_submit'/>" type="button" onclick="getData()" />
                    <input value="<@spring.message 'admin_button_csubmit'/>" type="button" onclick="submitForm()" />
                </td>
            </tr>
        </table>
    </div>

 <script type="text/javascript">
     function getData()
     {
    	 //var form = new liger.get("formupdateinit");
         //var data1 = form.getData();
         //alert(JSON.stringify(data1));
         var data = submitForm1();
         $.ajax({
        	 contentType:"application/x-www-form-urlencoded",
             url:"update/upinitable.html",
             async:true,
             type:"POST",
             dataType:"text",
             loading:'正在更新中...',
             data:{"data":data},  // 设置主键对应。
             success:function (data1) 
             {
            	 if(data1 == "upok"){
            		 alert("更新成功");
            	 }else{
            		 alert("更新失败");
            	 }
                 
             }
         });
     }
     function submitForm1()
     {

         var data = {};
         $("input,select,textarea").each(function ()
         {
             var name = $(this).attr("name");
             if (name && name.indexOf('ligerui') == -1)
             {
             	if(name=="datef" || name=="timef")
                	data[name] = $("input[name='"+name+"']:checked").val();
                else if(name=="web_openre" ){
                	if ($("#web_openre").attr('checked')) {
    					data[name] = "openreg";
					}else{
						data[name] = "noopen";
					}
                }else
                	data[name] = this.value;
                 
             }
         });
         return JSON.stringify(data);

     }
     
     function submitForm()
     {

         var data = {};
         $("input,select,textarea").each(function ()
         {
             var name = $(this).attr("name");
             if (name && name.indexOf('ligerui') == -1)
             {
                if(name=="datef" || name=="timef")
                	data[name] = $("input[name='"+name+"']:checked").val();
                else if(name=="web_openre" ){
                	if ($("#web_openre").attr('checked')) {
    					data[name] = "noopen";
					}else{
						data[name] = "openreg";
					}
                }
                else
                	data[name] = this.value;
             }
         });
         alert(JSON.stringify(data));

     }
    </script>

</body>
</html>
