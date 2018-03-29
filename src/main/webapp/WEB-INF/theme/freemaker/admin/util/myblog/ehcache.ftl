<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href='${contextPath}liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css' rel="stylesheet" type="text/css" />
    <link href='${contextPath}liger/lib/ligerUI/skins/ligerui-icons.css' rel="stylesheet" type="text/css" />
    <link href='${contextPath}liger/lib/ligerUI/skins/Gray/css/all.css' rel="stylesheet" type="text/css" />
    <script src='${contextPath}liger/lib/jquery/jquery-1.9.0.min.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/core/base.js' type="text/javascript"></script> 
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerForm.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerButton.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerDateEditor.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerComboBox.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerCheckBox.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerTextBox.js' type="text/javascript"></script> 
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerTip.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/jquery-validation/jquery.validate.min.js' type="text/javascript"></script> 
    <script src='${contextPath}liger/lib/jquery-validation/jquery.metadata.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/jquery-validation/messages_cn.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerSpinner.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerGrid.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerToolBar.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerResizable.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerSpinner.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerTextBox.js' type="text/javascript"></script>
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerDrag.js' type="text/javascript"></script> 
    <script src='${contextPath}liger/lib/ligerUI/js/plugins/ligerDialog.js' type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerTab.js"></script>
    <script type="text/javascript">
    	var getpng;
    	var manager, g;
        function itemclick(item)
        {
        	var rows=manager.getSelectedRows();
        	if(item.text == "删除选定缓存"){
        		if(rows.length == 0){
        			alert("请选择删除的缓存");
        		}else{
        			var deleteid="";
        			for(var i=0;i<rows.length;i++){
        				deleteid = deleteid+rows[i].ehcachekey+","
        			}
        			deleteid= deleteid.substring(0,deleteid.length-1);  			
        			deleteSelect(deleteid);
        		}
        	}else{
        		deleteAll();
        	}
        }
        $(function ()
        {
            window['g'] = manager =
            $("#maingrid").ligerGrid({
                height:'100%',
                columns: [
                { display: '缓存name', name: 'ehcachename', align: 'left',width: 240 },
                { display: '缓存 key', name: 'ehcachekey', align: 'left',width: 360 }
                ],
                minWidth:"1",
                dataAction:'server',
                //data:CustomersData,  
                url:"ehcachedata.html?method=getUserPaging&privilege=-1&nowehcache=${nowehcache}",
                usePager:true,
                pageSize:16 ,
                rownumbers:true,
                toolbar: { items: [
                { text: '管理当前缓存==》${nowehcache}'},
                { text: '删除选定缓存', click: itemclick, img: '${contextPath}liger/lib/ligerUI/skins/icons/delete.gif' },
                { text: '清空当前缓存', click: itemclick, img: '${contextPath}liger/lib/ligerUI/skins/icons/delete.gif' },
                ]
                }
            });
            $("#pageloading").hide();
            });
             

		$("#pageloading").hide();
            
		$.metadata.setType("attr", "validate");

        function deleteSelect(deleteid)
        {
            $.ajax({
            	url:"update/delselectehcache.do",
            	async:true,
            	dataType:"text",
            	type:"post",
            	loading:"正在保存在服务器......",
            	data:{"ehcachekey":deleteid,"ehcachename":"${nowehcache}"},
				success:function(data){
					if($.trim(data) == "cleanok"){
						alert("删除成功");
						f_reload();
					}else{
						alert("删除失败");
					}
					
				},
        		error:function(){
        			alert("操作失败");
        		}
            });
        }
        function deleteAll()
        {
            $.ajax({
            	url:"update/delselectehcache.do",
            	async:true,
            	dataType:"text",
            	type:"post",
            	loading:"正在保存在服务器......",
            	data:{"ehcachename":"${nowehcache}"},
				success:function(data){
					if($.trim(data) == "cleanok"){
						alert("删除成功");
						f_reload();
					}else{
						alert("删除失败");
					}
					
				},
        		error:function(){
        			alert("操作失败");
        		}
            });
        }
        
        //刷新
    	function f_reload() {
    		manager.loadData();			//动态刷新，弹窗和父页面刷新  即弹窗不消失刷新
    		//location.reload();		//刷新整个页面  即弹窗刷新消失 
    	}
        
        //下拉框
        //选择菜单项
        function clickee(){
        	var options=$("#m_selectmenu option:selected");
	        parent.f_addTab(options.text(), options.text(), "ehcache.html?nowehcache="+options.val()); 
        }      
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div id="selectmenu" style="width:420px; height:50px; margin:3px;">
	<select id="m_selectmenu" name="m_selectmenu" validate="{required:true}">
		<#if ehcachename??>
			<#list ehcachename as ehcache>
				<#if key??  || mapmenus[key]?? || 'class' == key>
				<#else>
					<option value="${ehcache}">${ehcache}</option>	
				</#if>
			</#list>
		<#else>
		</#if>
	</select>
	<a href="javascript:clickee()" class="l-button" style="width:100px">操作</a>	
</div>
<div class="l-loading" style="display:block" id="pageloading"></div>

 
 <div class="l-clear"></div>

    <div id="maingrid"></div>  
  <div style="display:none;">
</div>
 
</body>
</html>
