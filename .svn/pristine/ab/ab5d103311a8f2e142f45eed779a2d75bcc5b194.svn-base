<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title> 
    <link href="${contextPath}liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="${contextPath}liger/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
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
		function f_addTabItem(url, text)
        {
            var tabid = "ligerui1" + new Date().toDateString();
            parent.f_addTab("12sd", text, url);
        }

        var arstatuss = [
        	{ arstatus: "pending", text: '待审' }, 
        	{ arstatus: "draft", text: '草稿'}, 
        	{ arstatus: "auto-draft", text: '自动保存的草稿'}, 
        	{ arstatus: "inherit", text: '修订版本'}, 
        	{ arstatus: "trash", text: '回收站'}, 
        	{ arstatus: "publish", text: '已发布'}, 
        	{ arstatus: "future", text: '定时'}, 
        	{ arstatus: "private", text: '私有'}
        	];
        var comstatuss = [
        	{ commentstatus: "open", text: '已开启' }, 
        	{ commentstatus: "closed", text: '已关闭'}
        	];
        $(f_initGrid);
        var manager, g;
        function f_initGrid()
        {
            g = manager = $("#maingrid").ligerGrid({
                columns: [
                { display: '文章id', name: 'arid', width: 50, type: 'int', frozen: true },
                { display: '文章名', name: 'arname',width: 380,
                    editor: { type: 'text' }
                },
                { display: '用户id', name: 'aruid',width: 50,type: 'int', frozen: true
                },
                { display: '文章状态', name: 'arstatus',width: 80,editor: { type: 'select', data: arstatuss, valueField: 'arstatus' },
                	render: function (item)
                    {
                    	for(var i=0;i<arstatuss.length;i++ ){
                    		if(arstatuss[i].arstatus == item.arstatus){
                    			return arstatuss[i].text;
                    		}
                    	}
                        return '错误';
                    }
                },
                { display: '评论状态', name: 'commentstatus',width: 80,editor: { type: 'select', data: comstatuss, valueField: 'commentstatus'},
                	render: function (item)
                    {
                    	for(var i=0;i<comstatuss.length;i++ ){
                    		if(comstatuss[i].commentstatus == item.commentstatus){
                    			return comstatuss[i].text;
                    		}
                    	}
                        return '错误';
                    }
                },
                { display: '文章密码', name: 'pass',width: 100,
                    editor: { type: 'password' }
                },
                { display: '发布时间', name: 'ardate',width: 100,
                    editor: { type: 'date' }
                },
                { display: '操作', isSort: false, width: 150, render: function (rowdata, rowindex, value)
                {
                    var h = "";
                    if (!rowdata._editing)
                    {
                        h += "<a href='javascript:beginEdit(" + rowindex + ")'>快速修改</a> ";
                        h += "<a href='javascript:deleteRow(" + rowindex + ")'>删除</a> "; 
                        h += "<a href='javascript:editRow(" + rowindex + ")'>编辑</a> "; 
                    }
                    else
                    {
                        h += "<a href='javascript:endEdit(" + rowindex + ")'>提交</a> ";
                        h += "<a href='javascript:cancelEdit(" + rowindex + ")'>取消</a> "; 
                    }
                    return h;
                }
                }
                ],
                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                },
                enabledEdit: true,clickToEdit:false, isScroll: false,
                type:"post",
                url: "articledata.do?method=getUserPaging&privilege=-1&poststatus=all",
                pageSize:16 ,
                width: '100%'
            });   
        }
        function beginEdit(rowid) {
            manager.beginEdit(rowid);
        }
        function cancelEdit(rowid) {
            manager.cancelEdit(rowid);
        }
		function editRow(rowid) {
			var arid = manager.getRow(rowid).arid;
			var tname = "编辑id-"+arid;
			if (confirm('进入新的tab页编辑')){
				f_addTabItem('addarticle.html?arid='+arid,tname);
			}
        }
        function endEdit(rowid)
        {
        	manager.endEdit(rowid);
	        $.ajax({
	           	url:"update/uparticle.do",
	            async:true,
	            dataType:"text",
	            type:"post",
	            loading:"正在保存在服务器......",
	            data:manager.getRow(rowid),
				success:function(data){
					if($.trim(data) == "uparok"){
						alert("更新成功");
					}else{
						alert("更新失败");
					}
					
				},
	        	error:function(){
	        		alert("操作失败");
	        	}
	        });           
        }

        function deleteRow(rowid)
        {
            if (confirm('确定删除?'))
            {
            	var arid  = manager.getRow(rowid).arid; 
	            $.ajax({
	            	url:"update/delpostid.do",
	            	async:true,
	            	dataType:"text",
	            	type:"post",
	            	loading:"正在保存在服务器......",
	            	data:{"poid":arid},
					success:function(data){
						if($.trim(data) == "delarok"){
							alert("删除成功");
							manager.deleteRow(rowid);
						}else{
							alert("删除失败");
						}
						
					},
	        		error:function(){
	        			alert("操作失败");
	        		}
	            });
                
            }
        }
        var newrowid = 100;
        function addNewRow()
        {
            manager.addEditRow();
        } 
         
        function getSelected()
        { 
            var row = manager.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
        }
        function getData()
        { 
            var data = manager.getData();
            alert(JSON.stringify(data));
        } 
    </script>
</head>
<body  style="padding:10px">  
 <div class="l-clear"></div>
 <a class="l-button" style="width:120px" onclick="getSelected()">批量删除选中的文章</a>
    <div id="maingrid" style="margin-top:20px"></div> <br />
       <br />
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
</body>
</html>
