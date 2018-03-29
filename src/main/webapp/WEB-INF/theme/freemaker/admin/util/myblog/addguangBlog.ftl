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
     	  var gbdtypes = [{ bdtypeid: 0, text: '手机版本' }, { bdtypeid: 1, text: '电脑版本'}];  
     	  var gbdtis= [{ gbdtisid: 0, text: '不显示' }, { gbdtisid: 1, text: '显示'}];     
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
                { display: '广告编号', name: 'guangbdid', width: 60, type: 'int',frozen:true },
                { display: '广告位置', name: 'guangbdlocal',width: 120, editor: { type: 'text' }, align: 'left'},
                { display: '广告说明', name: 'guangbddes',width: 120, editor: { type: 'text' }, align: 'left'},
                { display: '广告类型', name: 'guangbdtype',width: 120, type: 'int',editor: { type: 'select', data: gbdtypes, valueField: 'bdtypeid'}, align: 'left',render: function (item)
                    	{
                        if (parseInt(item.guangbdtype) == 1) return '电脑版本';
                        return '手机版本';
                    	}
                    },
                { display: '广告内容', name: 'guangbdcontext',width: 360, editor: { type: 'text' }, align: 'left'},
                { display: '是否显示', name: 'guangbdis',width: 60,type: 'int',editor: { type: 'select', data: gbdtis, valueField: 'gbdtisid'}, align: 'left',render: function (item)
                    	{
                        if (parseInt(item.guangbdis) == 1) return '显示';
                        return '不显示';
                    	}}
                ],
                enabledEdit: true, isScroll: false, checkbox:true,rownumbers:true,
                dataAction:'server',
                url:'guangBlogDatas.do',
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
            if (e.column.name == "guangbdlocal")
            {
               if (e.value == "") {
               	alert("广告位置必填");
                	return false;
                	}
            }
          if (e.column.name == "guangbddes")
            {
               if (e.value == "") {
               	alert("广告说明必填");
                	return false;
                	}
            }
          if (e.column.name == "guangbdtype")
            {
               if (e.value == "") {
               	alert("广告类型必填");
                	return false;
                	}
            }
          if (e.column.name == "guangbdcontext")
            {
               if (e.value == "") {
               	alert("广告内容必填");
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
			//因数据可以重复，所以无编辑后事件
        }
        function deleteRow()
        { 
            var rows=manager.getSelectedRows();
            var gbdids="";
            var count = 0;
            var count1 = 0;
            if (rows.length <= 0) {
            	alert('请选择删除行');
            	return; 
            }else{
            	for(var i=0;i<rows.length;i++){
                	if(rows[i].__status != "add"){
                    	count = count + 1;
                		gbdids=gbdids+rows[i].guangbdid+",";
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
                    	url:"update/delguangbds.do",
                    	async:true,
                    	type:"POST",
                    	dataType:"html",
                    	loading:"正在删除......",
                    	data:{"gbdids":gbdids.substr(0,gbdids.length-1)},
        				success:function(data2){
        					if($.trim(data2) == "delok"){
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
                guangbdid: -1,			//红包ID添加时无需填写
                guangbdlocal: '广告位置',
                guangbddes: '广告说明',
                guangbdtype:'广告类型',
                guangbdcontext:'广告内容',
                guangbdis:'1'
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
            				if(row[i].guangbdlocal=='广告位置' || row[i].guangbddes=='广告说明' || row[i].guangbdtype=='广告类型' || row[i].guangbdcontext=='广告内容'){
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
                    url:"update/upguangbd.do",
                    async:true,
                    type:"POST",
                    dataType:"html",
                    loading:'正在更新中...',
                    data:{"datajsons":JSON.stringify(row)},  // 设置主键对应。
                    success:function (data2) 
                    {
                    	if($.trim(data2) == "saveorupok"){
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
 
<a class="l-button" style="width:120px;float:left; margin-left:10px;" onclick="deleteRow()">删除广告位</a>

<a class="l-button" style="width:100px;float:left; margin-left:10px;" onclick="addNewRow()">新增广告位</a>

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