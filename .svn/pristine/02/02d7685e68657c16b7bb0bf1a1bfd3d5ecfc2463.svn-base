<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
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
        $(f_initGrid);
        var manager, g;
        var updata;
        // $.post("uphbinfo!getRedbao.action",function(data){
        	// alert(data);
			// updata = data;
		// });
        
        function f_initGrid()
        { 
            g = manager = $("#maingrid").ligerGrid({
                //toolbar: {},
                title : '分类管理',
                columns: [
                { display: '分类编号', name: 'flid', width: 60, type: 'int',frozen:true },
                { display: '分类名称', name: 'flname',width: 120, editor: { type: 'text' }, align: 'left'},
                { display: '分类缩略名', name: 'flslug',width: 120, editor: { type: 'text' }, align: 'left'},
                { display: '分类说明', name: 'fldes',width: 240, editor: { type: 'text' }, align: 'left'},
                { display: '文章数据', name: 'flcount',width: 60, type:'int',frozen:true }
                ],
                
                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                    if(rowdata.flname == "未分类"){
                    	alert("抱歉！默认分类禁止操作");
                    	manager.unselect(rowindex);
                    	return false;
                    }
                    if(rowdata.flname == "默认分类"){
                    	alert("抱歉！请更新分类名称");
                    	manager.unselect(rowindex);
                    	return false;
                    }
                    if(rowdata.flslug == "public"){
                    	alert("抱歉！请更新分类别名");
                    	manager.unselect(rowindex);
                    	return false;
                    }
                },
                enabledEdit: true, isScroll: false, checkbox:true,rownumbers:true,
                dataAction:'server',
                url:"categoryall.do",
                usePager:false,
                onBeforeSubmitEdit: f_onBeforeSubmitEdit,
                onAfterEdit:f_onAfterEdit,
                onBeforeEdit: f_onBeforeEdit,
				pageSize:20,
                width: "100%"
            });   
        }
        
        function f_onBeforeSubmitEdit(e)
        { 
            if (e.column.name == "flname")
            {
               if (e.value == "") {
               	alert("分类名称不能为空");
                	return false;
                	}
            }
          if (e.column.name == "flslug")
            {
               if (e.value == "") {
               	alert("分类缩略名不能为空");
                	return false;
                	}
            }
            return true;
        }
        
        
        var qianvalue;
        function f_onBeforeEdit(e){
        	//获得编辑器的数据，每编辑一个单元格就保存原始数据
        	qianvalue = e.value;
        }
        //编辑后事件 
        function f_onAfterEdit(e)
        {
        	 //与服务器交互，判断是否有相同的缩略名
            if (e.column.name == "flslug")
            {
            	var d = e.record;
            		//获得所有的数据
            	var row1 = manager.getData();
            	var rcount = 0;
            	for(var i=0;i<row1.length;i++){
            			if(row1[i].flslug==e.value){
            				rcount++;
            				}
            		}
            	if(rcount >1){
            		alert('存在相同的数据 '+e.value+' ，请修改！');
               	g.updateCell('flslug', qianvalue, d);
           			return false;
            		}

            }

        }
        function deleteRow()
        { 
            var rows=manager.getSelectedRows();
            var flids="";
            var count = 0;
            var count1 = 0;
            if (rows.length <= 0) {
            	alert('请选择删除行');
            	return; 
            }else{
            	for(var i=0;i<rows.length;i++){
                	if(rows[i].__status != "add"){
                    	count = count + 1;
                		flids=flids+rows[i].flid+",";
                	}else{
                		count1 = count1 + 1;
                	}
                }
            	if(count == 0 || count1 == rows.length){
            		manager.deleteSelectedRow();
					alert("删除成功");
					return;
            	}else{
            		$.ajax({
                    	url:"update/delfl.do",
                    	async:true,
                    	type:"POST",
                    	dataType:"html",
                    	loading:"正在删除......",
                    	data:{"flids":flids.substr(0,flids.length-1)},
        				success:function(data2){
        					if($.trim(data2) == "deltermsok"){
        						manager.deleteSelectedRow();
            					alert("删除成功"+$.trim(data2));
        					}else{

        						alert("删除失败"+$.trim(data2));
        						f_reload()
        					}
        				}
                    });
            	}	
            }
            
        }
        
        var newrowid = 100;
        function addNewRow()
        {
            var row = manager.getSelectedRow();
            //参数1:rowdata(非必填)
            //参数2:插入的位置 Row Data 
            //参数3:之前或者之后(非必填)
            manager.addRow({ 
                flid: -1,			//红包ID添加时无需填写
                flname: '默认分类',
                flslug: 'public',
                fldes:'',
                flcount:'0'
            }, row, document.getElementById("chkbefore").checked);
        } 
        function getSelected()
        { 
            var row = manager.getSelectedRows();
            //var data1 = manager.getData();
            if (row.length <= 0) {
            	alert('请选择提交的数据行');
            	return; 
            }else{
            	var dd = 0;
            	for(var i=0;i<row.length;i++){
            		if(row[i].__status == "add" || row[i].__status == "update"){
            			dd = dd+1;
            			break;
            		}
            	}
            	if(dd == 0){
            		alert("没有需要保存或更新的项");
            		return;
            	}
            	//这里要判断添加的数据是否符合要求
            	//var data = manager.getAdded(); 这是获取的新增家属
            	$.ajax({
                    url:"update/upterms.do?taxonomy=category",
                    async:true,
                    type:"POST",
                    dataType:"html",
                    loading:'正在更新中...',
                    data:{"gfl":JSON.stringify(row)},  // 设置主键对应。
                    success:function (data2) 
                    {
                    	if($.trim(data2) == "saveupok"){
                    		alert("成功"+$.trim(data2));
                    		f_reload()    
                    	}else{
                    		alert("失败"+$.trim(data2));
                    		f_reload() 
                    	}
                    	         
                    },
                    error:function(cuowu){
                    	alert("错误 ");
                    }
                });
            }       
        }
        function getData()
        { 
            var data = manager.getData();
            alert(JSON.stringify(data));
        } 
        
      	//刷新
    	function f_reload() {
    		//manager.loadData();			//动态刷新，弹窗和父页面刷新  即弹窗不消失刷新
    		location.reload();		//刷新整个页面  即弹窗刷新消失 
    	}
    </script>
</head>
<body  style="padding:10px">
 
<a class="l-button" style="width:120px;float:left; margin-left:10px;" onclick="deleteRow()">删除分类</a>

<a class="l-button" style="width:100px;float:left; margin-left:10px;" onclick="addNewRow()">新增分类</a> 

<div> 

是否之前：
<input type="checkbox" id="chkbefore" />
</div>

 <div class="l-clear"></div>
    <div id="maingrid" style="margin-top:20px"></div> <br />
       <br />
   <a class="l-button" style="width:120px" onclick="getSelected()">提交        更改 </a>
   <a class="l-button" style="width:120px" onclick="getData()">获得所有分类</a>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
</body>
</html>