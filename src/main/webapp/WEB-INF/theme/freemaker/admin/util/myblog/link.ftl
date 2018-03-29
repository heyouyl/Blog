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
                title : '友情链接管理',
                columns: [
                { display: 'urlid', name: 'linkurlid', width: 60, type: 'int',frozen:true },
                { display: '链接url', name: 'linkurl',width: 120, editor: { type: 'text' }, align: 'left'},
                { display: '链接名称', name: 'linkname',width: 120, editor: { type: 'text' }, align: 'left'},
                { display: '是否显示', name: 'linkvisible',width: 120, editor: { type: 'text' }, align: 'left'},
                { display: '链接说明', name: 'linknote',width: 240, editor: { type: 'text' }, align: 'left'},
                { display: '链接等级', name: 'linkrating',width: 60,editor: { type: 'text' }, align: 'left'}
                ],
                enabledEdit: true, isScroll: false, checkbox:true,rownumbers:true,
                dataAction:'server',
                url:'linkegoryall.do',
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
            if (e.column.name == "linkurl")
            {
               if (e.value == "") {
               	alert("友情链接URL不能为空");
                	return false;
                	}
            }
          if (e.column.name == "linkname")
            {
               if (e.value == "") {
               	alert("友情链接名称不能为空");
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
        	 //与服务器交互，判断是否有相同的友情链接
            if (e.column.name == "linkurl")
            {
            	var d = e.record;
            		//获得所有的数据
            	var row1 = manager.getData();
            	var rcount = 0;
            	for(var i=0;i<row1.length;i++){
            			if(row1[i].linkurl==e.value){
            				rcount++;
            				}
            		}
            	if(rcount >1){
            		alert('存在相同的数据 '+e.value+' ，请修改！');
               	g.updateCell('linkurl', qianvalue, d);
           			return false;
            		}

            }
            if (e.column.name == "linkname")
            {
            	var d = e.record;
            		//获得所有的数据
            	var row1 = manager.getData();
            	var rcount = 0;
            	for(var i=0;i<row1.length;i++){
            			if(row1[i].linkname==e.value){
            				rcount++;
            				}
            		}
            	if(rcount >1){
            		alert('存在相同的数据 '+e.value+' ，请修改！');
               	g.updateCell('linkname', qianvalue, d);
           			return false;
            		}

            }

        }
        function deleteRow()
        { 
            var rows=manager.getSelectedRows();
            var linkids="";
            var count = 0;
            var count1 = 0;
            if (rows.length <= 0) {
            	alert('请选择删除行');
            	return; 
            }else{
            	for(var i=0;i<rows.length;i++){
                	if(rows[i].__status != "add"){
                    	count = count + 1;
                		linkids=linkids+rows[i].linkurlid+",";
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
                    	url:"update/dellinks.do",
                    	async:true,
                    	type:"POST",
                    	dataType:"html",
                    	loading:"正在删除......",
                    	data:{"linkids":linkids.substr(0,linkids.length-1)},
        				success:function(data2){
        					if($.trim(data2) == "dellinkok"){
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
                linkurlid: -1,			//红包ID添加时无需填写
                linkurl: 'linkurl',
                linkname: 'linkname',
                linkvisible:'Y',
                linknote:'',
                linkrating:'0'
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
            				if(row[i].linkurl=='linkurl' || row[i].linkname=='linkname'){
            						alert("增加项存在默认值，请修改");
            						return;
            					}
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
                    url:"update/uplinks.do",
                    async:true,
                    type:"POST",
                    dataType:"html",
                    loading:'正在更新中...',
                    data:{"datajsons":JSON.stringify(row)},  // 设置主键对应。
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
 
<a class="l-button" style="width:120px;float:left; margin-left:10px;" onclick="deleteRow()">删除友情链接</a>

<a class="l-button" style="width:100px;float:left; margin-left:10px;" onclick="addNewRow()">新增友情链接</a>

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