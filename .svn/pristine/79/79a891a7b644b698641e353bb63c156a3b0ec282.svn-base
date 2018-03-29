<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>垃圾评论</title><link href="${contextPath}liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
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
                //title : 'gg',
                columns: [
				{ display: '评论id', name: 'comid', width: 80,hide: true},
                { display: '文章id', name: 'compostid', width: 140, type: 'text',frozen:true },
                { display: '评论者', name: 'author',width: 100,editor: { type: 'int' },frozen:true},
                { display: '评论ip', name: 'comip',width: 100,editor: { type: 'int' },frozen:true},
                { display: '评论时间', name: 'comdate', width: 140, type: 'date',frozen:true,render:function(rowdata, index, value){
                	var d=new Date(value);
                	var year=d.getYear()+1900;
                	var month=d.getMonth()+1;
                	var date = d.getDate();
                	var hour=d.getHours();
                	var minute=d.getMinutes();
                	var second=d.getSeconds();
                	return   year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;  
                }},
                { display: '评论内容', name: 'comcontent', width: 300,editor: { type: 'text' }, align: 'left',frozen:true}
                ],
                
                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                },
                enabledEdit: true, isScroll: false, checkbox:true,rownumbers:true,
                dataAction:'server',
                url:"commensdata.do?iden=spam&method=getUserPaging&privilege=-1",
                usePager:true,
				pageSize:10,
                width: "100%"
            });
        }
          //提交修改评论状态
      function getSelected(data){
        	var rows = manager.getSelecteds();
        	var len = rows.length;
        	var comid="";
        	if(len == 0){
        		alert("没有需要处理的评论");
        		return;
        	}else{
        		for(var i=0;i<len;i++){
        			comid=comid+rows[i].comid+",";
        		}
        	}
			comid = comid.substr(0,comid.length-1);
        	$.ajax({
        		url:"update/upcomment.do",
        		async:true,
        		type:"post",
        		dataType:"text",
        		data:{'iden':data,'comids':comid},
        		success:function(data){
        			f_reload();
        			alert("更新成功");
        		},
        		error:function(){
        			f_reload();
        			alert("更新失败");
        		}
        	});
        }
        //获得所有的订单信息
        function getData()
        { 
            var data = manager.getData();
            alert(JSON.stringify(data));
        }
      	//刷新
    	function f_reload() {
    		location.reload();		//刷新整个页面  即弹窗刷新消失 
    	}
    </script>
</head>
<body  style="padding:10px">
 
<a class="l-button" style="width:120px" onclick="getSelected('-3')">直接删除</a>
<a class="l-button" style="width:120px" onclick="getSelected('-1')">放进回收站</a>
<a class="l-button" style="width:120px" onclick="getSelected('-0')">放进带审核</a>

 <div class="l-clear"></div>
    <div id="maingrid" style="margin-top:20px"></div> <br />
       <br />
   <a class="l-button" style="width:120px" onclick="getData()">所有订单信息</a>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
</body>
</html>