<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员管理</title>
    <link href="${contextPath}liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="${contextPath}liger/lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/json2.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script type="text/javascript">
        var sexData = [{ sex: 'm', text: '男' }, { sex: 'w', text: '女'}];        
        $(f_initGrid);
        var manager, g;
        var newtext;
        var updata;
        // $.post("uphbinfo!getRedbao.action",function(data){
        	// alert(data);
			// updata = data;
		// });
        
        function f_initGrid()
        { 
        	var groupselect;
        	//参数false表示同步
        	$.ajax({
                url:"showsetgroup",
                async:false,
               	dataType:"json",
    			success:function(data){
    				groupselect =  data;
    			}
			});
            g = manager = $("#maingrid").ligerGrid({
                //toolbar: {},
                //title : 'gg',
                columns: [
				{ display: 'id', hide:true, name: 'userid', width: 1, type: 'text'},
                { display: '用户姓名', name: 'username', width: "80", type: 'text'},
                { display: '用户组', name: 'my028888_user_level', width: "80",editor: { type: 'int'},type: 'int'},
                { display: '注册时间', name: 'redate',width: "140",type: 'int',render:function(rowdata, index, value){
                	var d=new Date(value);
                	var year=d.getYear()+1900;
                	var month=d.getMonth()+1;
                	var date = d.getDate();
                	var hour=d.getHours();
                	var minute=d.getMinutes();
                	var second=d.getSeconds();
                	return   year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;
                	//return   year+"-"+month+"-"+date;
                }},
                { display: '用户邮件', name: 'useremail', width: "150",type: 'text'},
                { display: '用户昵称', name: 'displayname',width: "80",editor: { type: 'text'},type: 'text'},
                { display: '网址', name: 'blogurl', width: "100",editor: { type: 'text'},type: 'text'},
                { display: 'QQ号码', name: 'my028888_user_qq', width: "80",editor: { type: 'text'},type: 'text'},
                { display: '微信号', name: 'my028888_user_weixin', width: "80",editor: { type: 'text'},type: 'text'},
                { display: '微薄地址', name: 'my028888_user_weibo', width: "100",editor: { type: 'text'},type: 'text'}
                ],
                
                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                    if(parseInt(rowdata.usergroup) == 10){
                    	alert("不可直接操作管理员账户");
                    	manager.unselect(rowindex);
                    	
                    }
                },
                enabledEdit: true, isScroll: false, checkbox:true,rownumbers:true,
                dataAction:'server',
                url:"manageruserdata.do?method=getUserPaging&privilege=-1",
                usePager:true,
                onBeforeSubmitEdit: f_onBeforeSubmitEdit,
                onAfterEdit:f_onAfterEdit,
                onBeforeEdit: f_onBeforeEdit,
				pageSize:10,
                width: "100%"
            });   
        }
        
        function f_onBeforeSubmitEdit(e)
        { 
            if (e.column.name == "HBmoney")
            {
                if (e.value < 10 || e.value > 500) {
                	alert("红包金额在10到500之间");
                	return false;
                }
            }
            if(e.column.name == "HByaoqiu"){
            	if(e.value <= 10){
            		alert("最低订单必须大于10");
                	return false;
            	}
            }
            if(e.column.name == "usecount"){
            	if(e.value < 1){
            		alert("最低使用1张");
                	return false;
            	}
            }
            if(e.column.name == "HBcounts"){
            	if(e.value < 1){
            		alert("红包总数至少1张");
                	return false;
            	}
            }
          
            return true;
        }
        
        
        var qianvalue;
        function f_onBeforeEdit(e){
        	//获得编辑器的数据，这里获取编辑前的数据
        	qianvalue = e.value;
        }
        //编辑后事件 
        function f_onAfterEdit(e)
        {
        	
        	if (e.column.name == "usergroup"){
        		var d = e.record;
            	g.updateCell('usergroup', newtext, d);
        	}
        	
        	 //与服务器交互，判断是否有相同的用户名
            if (e.column.name == "username")
            {
            	var d = e.record;
            	getbooleanusermail.userAndemail("username",e.value, getHBboolean);
            	function getHBboolean(getboolean){
            		if(getboolean){
            			alert("已存该用户，请重新修改");
                        g.updateCell('HBmoney', qianvalue, d);
            			return false;
            		}
            	}
            }
        	//需要判断邮件是否有相同的 
            if (e.column.name == "email")
            {
            	var d = e.record;
            	getbooleanusermail.userAndemail("email",e.value,getHBboolean1);
            	function getHBboolean1(getboolean){
            		if(getboolean){
            			alert("已存在的邮件，不可修改");
                        g.updateCell('HBmoney', qianvalue, d);
            			return false;
            		}
            	}
            }
            if (e.column.name == "userpass")
            {
            	if(e.column.value<8 || typeof(e.column.value) == "undefined"){
            		alert("用户密码最小长度为8");
            		return false;
            	}
            }

        }
        
        function getSelected(iden)
        {
        	//因为没有新增数据，只有更新，所以这里可直接获得更新的数据
        	var rows=manager.getSelectedRows();
        	var userdatas = "";
            for(var i=0;i<rows.length;i++){
            	if(rows[i].__status == "update"){
            		userdatas = userdatas + JSON.stringify(rows[i])+",";
            	}
            }
            if(userdatas.length != 0){
            	userdatas = "["+userdatas.substr(0,userdatas.length-1)+"]";
            	$.ajax({
            		async:true,
            		url:"update/upuser.do",
            		type:"post",
            		dataType:"text",
            		data:{"datajsons":userdatas,"iden":iden},
            		success:function(data){
            			if($.trim(data)=="upuserok"){
            				alert("更新用户成功"+$.trim(data));
            			}else{
            				alert("更新用户信息失败"+$.trim(data));
            			}
            		},
            		error:function(){
            			alert("错误");
            		}
            	});
            }
        }
        function getData()
        { 
            var data = manager.getData();
            alert(JSON.stringify(data));
        } 
    </script>
</head>
<body  style="padding:10px">
<a class="l-button" style="width:120px;float:left; margin-left:10px;" onclick="getSelected(1)">修改用户资料</a>
<a class="l-button" style="width:120px;float:left; margin-left:10px;" onclick="getSelected(-1)">删除用户</a>

<div> 

是否之前：
<input type="checkbox" id="chkbefore" />
</div>

 <div class="l-clear"></div>
    <div id="maingrid" style="margin-top:20px"></div> <br />
       <br />
   <a class="l-button" style="width:120px" onclick="getSelected()">更新用户信息</a>
   <a class="l-button" style="width:120px" onclick="getData()">获取所有红包</a>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
</body>
</html>
