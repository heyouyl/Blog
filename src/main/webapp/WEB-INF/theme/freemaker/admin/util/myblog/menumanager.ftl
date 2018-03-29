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
        	if(item.text == "增加"){
        		
				$.ligerDialog.open({ target: $("#savebox"),width:"400",title:"新增项目",show:false,isHidden:true,modal:true,id:"add" });
        		
        	}else{
        		if(rows <= 0){
        			alert("请选择删除的行");
        		}else{  			
        			deleteRow();
        		}
        	}
        }
        $(function ()
        {
            window['g'] = manager =
            $("#maingrid").ligerGrid({
                height:'100%',
                columns: [
                { display: '菜单属性', name: 'sxattri', align: 'left',width: 60 },
                { display: '菜单位置', name: 'cdposi', align: 'left',width: 120 },
                { display: '菜单url', name: 'cdposit', align: 'left',width: 120 },
                { display: '菜单名称', name: 'mename', align: 'left', width: 120 },
                { display: '缩略名称', name: 'slname', align: 'left',width: 120 },
                { display: '菜单title', name: 'cdtitle', align: 'left',width: 120 },
                { display: '项目排序', name: 'cdorder', align: 'left',width: 60 }
                ],
                minWidth:"1",
                dataAction:'server',
                //data:CustomersData,  
                url:"menusdata.do?method=getUserPaging&privilege=-1&menusname=${nowmenus}",
                usePager:true,
                pageSize:10 ,
                rownumbers:true,
                toolbar: { items: [
                { text: '管理当前菜单==》${nowmenus}'},
                { text: '增加', click: itemclick, icon: 'add' },
                { line: true },
                { text: '删除', click: itemclick, img: '${contextPath}liger/lib/ligerUI/skins/icons/delete.gif' }
                ]
                }
            });
             

            $("#pageloading").hide();
            
            $.metadata.setType("attr", "validate");
            var v = $("form").validate({
            	//调试模式，只验证，不能提交，false为关闭
                debug: false,
                errorPlacement: function (lable, element)
                {
                    if (element.hasClass("l-textarea"))
                    {
                        element.ligerTip({ content: lable.html(), target: element[0] }); 
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
                    }
                    else
                    {
                        lable.appendTo(element.parents("td:first").next("td"));
                    }
                },
                success: function (lable)
                {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function (form)
                {
                	var data = {};
                	var datajson;
                	$("#savebox input,#savebox select").each(function(){
                		var name = $(this).attr("name");
                		if(name && name.indexOf('ligerui') == -1){
                			data[name] = this.value;
                		}
                	});
                    datajson = JSON.stringify(data);
                    //var formData = new FormData($("#form1")[0]);

                    if(datajson.length > 0){
                    	$.ajax({
                    		contentType:"application/x-www-form-urlencoded",
                           	url:"update/upmenus.do?nowmenus=${nowmenus}",
                           	async:true,
                           	dataType:"text",
                           	type:"post",
                           	loading:"正在保存在服务器......",
                           	data:{"saverows":datajson},
                           	//data:formData,
                           	//contentType:false,
                			//processData:false,
                			success:function(data){
                				if($.trim(data) == "saveok"){
                					alert("保存成功");
                					f_reload();
                				}else if($.trim(data) == "updateok"){
                    				alert("更新成功");
                    				f_reload();
                				}else{
                					alert("更新失败");
                				}
                				$('#reset').click();
                				//关闭弹窗 可以直接$.ligerDialog.close();
                				//$.ligerDialog.close({ target: $("#savebox") });
                				$.ligerDialog.hide();
                				//移除遮罩层
                				//$(".l-dialog,.l-window-mask").remove();
                				
                			},
                        	error:function(){
                        		alert("操作失败");
                        	}
                           });
					}else{
						alert("错误，表单数据怎么会为空呢");
					}
                }
            });
        });

        function deleteRow()
        {
        	manager.deleteRange(manager.getSelectedRows());
       		//删除的数据
       		var deljson = JSON.stringify(manager.getData());
            $.ajax({
            	url:"update/delmenus.do?nowmenus=${nowmenus}",
            	async:true,
            	dataType:"text",
            	type:"post",
            	loading:"正在保存在服务器......",
            	//data:{"saverows":datajson},
            	data:{"saverows":deljson},
				success:function(data){
					if($.trim(data) == "deleteok"){
						alert("删除成功");
					}else{
						alert("删除失败");
						f_reload();
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
	        parent.f_addTab(options.text(), options.text(), "menumanager.html?nowmenus="+options.val()); 
        }      
          
		$(function(){
			$("#sxattri").bind("change",function(){ 
				if($(this).val()=="1"){
					//显示下拉框，隐藏input
					$("#cdposi").show();
					$("#cdposit").hide();
				}else{
					$("#cdposi").hide();
					$("#cdposit").show();
				}
  			}); 
		})
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
<div id="selectmenu" style="width:250px; height:50px; margin:3px;">
	<select id="m_selectmenu" name="m_selectmenu" validate="{required:true}">
		<#list mapmenus.keySet() as key>
			<option value="${key}">${mapmenus[key]}</option>
		</#list>
	</select>
	<a href="javascript:clickee()" class="l-button" style="width:100px">操作</a>	
</div>
<div class="l-loading" style="display:block" id="pageloading"></div>
 <a class="l-button" style="width:120px;float:left; margin-left:10px; display:none;" onclick="deleteRow()">删除选择的行</a>

 
 <div class="l-clear"></div>

    <div id="maingrid"></div>  
  <div style="display:none;">
</div>
<div id="savebox" class="savebox" style="width:400px; height:400px; margin:3px; display:none;">
	    <form name="form1" id="form1">
	    <div>
		</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >  
            <tr>
                <td align="right" class="l-table-edit-td">菜单属性:</td>
                <td align="left" class="l-table-edit-td">
	                <select name="sxattri" id="sxattri" ltype="select">
						<option value="1">文章分类</option>
						<option value="2">自定义URL</option>
					</select>
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">菜单位置:</td>
                <td align="left" class="l-table-edit-td">
                	<select name="cdposi" id="cdposi" ltype="select">
	                	<#list categorylist.keySet() as key>
							<option value="${key}">${categorylist[key]}</option>
						</#list>
					</select>
					<input type="text"  style="width:200px;display:none" id="cdposit" name="cdposit" /> 
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">菜单名称:</td>
                <td align="left" class="l-table-edit-td">
                     <input type="text" id="mename" name="mename" validate="{required:true,minlength:3,maxlength:20}"/>
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">缩略名称:</td>
                <td align="left" class="l-table-edit-td">
                     <input type="text" id="slname" name="slname" validate="{required:true,minlength:3,maxlength:20}"/>
                </td><td align="left"></td>
            </tr> 
            <tr>
                <td align="right" class="l-table-edit-td">菜单title:</td>
                <td align="left" class="l-table-edit-td">
                	<input type="text"  style="width:200px" id="cdtitle" name="cdtitle" validate="{required:true,minlength:3,maxlength:20}" /> 
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">菜单排序:</td>
                <td align="left" class="l-table-edit-td">                		
					<input type="text" id="cdorder" name="cdorder" ltype='spinner' ligerui="{type:'int'}" class="required" validate="{digits:true,min:0,max:100}"/>
				</td><td align="left"></td>
            </tr>
        </table>
 <br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
		<input type="reset" value="重置" id="reset" class="l-button l-button-reset"/>
    </form>
</div> 
 
</body>
</html>
