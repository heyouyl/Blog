<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>所有评论</title>
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
        function f_initGrid()
        { 
            g = manager = $("#maingrid").ligerGrid({
                //toolbar: {},
                //title : 'gg',
                columns: [
					 { display: '评论id', name: 'comid', width: 80,hide: true},
					 { display: '文章id', name: 'compostid', width: 80,hide: true},
					 { display: '文章名称', name: 'arname', width: 80,hide: true},
                { display: '评论地址', name: 'arurl', width: 140, type: 'text',frozen:true ,render:function(rowdata, index, value){
                	return   "<a href='../archives/"+rowdata.arurl+"' targer='_black'>"+rowdata.arname+"</a>";  
                }},
                { display: '评论者', name: 'author',width: 100,frozen:true},
                { display: '评论ip', name: 'comip',width: 100,frozen:true},
                { display: '父评论者', name: 'comParentUser',width: 100,frozen:true},
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
                { display: '评论内容', name: 'comcontent', width: 300, align: 'left',frozen:true},
                { display: '审核状态', name: 'comstatus', width: 60,frozen:true,render:function(rowdata, index, value){
                	if(value == "0"){
                		return "代审核";
                	}else if(value == "1"){
                		return "已审核";
                	}else if(value == "spam"){
                		return "垃圾评论";
                	}else{
                		return "回收站";
                		}
                	}}
                ],
                
                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                },
                enabledEdit: false, isScroll: false, checkbox:false,rownumbers:true,
                dataAction:'server',
                url:"commensdata.do?iden=all&method=getUserPaging&privilege=-1",
                usePager:true,
				pageSize:10,
                width: "100%"
            });
        }
        //获得所有的订单信息
        function getData()
        { 
            var data = manager.getData();
            alert(JSON.stringify(data));
        }
    </script>
</head>
<body  style="padding:10px">

 <div class="l-clear"></div>
    <div id="maingrid" style="margin-top:20px"></div> <br />
       <br />
   <a class="l-button" style="width:120px" onclick="getData()">所有订单信息(json测试)</a>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
</body>
</html>