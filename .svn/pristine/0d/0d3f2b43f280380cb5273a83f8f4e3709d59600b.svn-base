<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加文章</title>
	<link href="${contextPath}liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="${contextPath}liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="${contextPath}liger/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
    <script src="${contextPath}liger/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
     <script src="${contextPath}liger/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="${contextPath}liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="${contextPath}liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="${contextPath}liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8" src="${contextPath}ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${contextPath}ueditor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="${contextPath}ueditor/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript">
        var eee;
        $(function ()
        {
            $.metadata.setType("attr", "validate");
            var v = $("form").validate({
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
                    var result = $.parseJSON(xhr.responseText);
					alert(result);
					alert(123);
                    
                },
                submitHandler: function ()
                {
//                    $("form .l-text,.l-textarea").ligerHideTip();
//                    var serialStr = $("#form1").serialize();//序列化表单 获得数据
//                    var urlStr = 'update/addar'; //请求的url 
//                    $.ajax({  
//                        type:'post',  
//                        url:urlStr,  
//                        data:serialStr,  
//                        success:function(redata){
//                            if  (redata == "1") {  
//                                alert( '操作成功!');  
//                            }else{  
//                                alert('失败提示', '操作失败!'); 
//                            }  
//                        }  
//                    });  
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function ()
            {
                alert(v.element($("#txtName")));
                alert(123);
            });
        });  
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>

</head>

<body style="padding:10px">
	<#if updataar['arid']??>
		<form name="form1" method="post" action="${contextPath}admin/update/addeditarticle.do" id="form1">
	<#else>
    	<form name="form1" method="post" action="${contextPath}admin/update/addarticle.do" id="form1">
    </#if>
<div>${adfefd}
</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
            <tr>
                <td align="right" class="l-table-edit-td">文章标题:</td>
                <td align="left" class="l-table-edit-td"><input name="txtName" type="text" id="txtName" ltype="text" value="${updataar['artitle']}" validate="{required:true,minlength:3,maxlength:100}" /></td>
                <td align="left"><input name="arid" type="hidden" id="arid" ltype="text" value="${updataar['arid']}"/>
                <input name="userid" type="hidden" id="userid" ltype="text" value="${userlog['userid']}"/></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">文章内容:</td>
                <td align="left" class="l-table-edit-td"> 
                <textarea cols="100" rows="4" class="l-textarea" id="addtext" name="addtext" style="width:400px" validate="{required:true}" >${updataar['content']}</textarea>
                </td><td align="left"></td>
            </tr>                 
            <tr>
                <td align="right" class="l-table-edit-td">发布日期:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="txtDate" type="text" id="txtDate" value="${updataar['ardate']}" ltype="date" />
                </td><td align="left">留空表示当前日期(${updataar['ardate']})</td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">浏览次数:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="arcount" type="text" id="arcount" ligerui="{type:'int'}" value="${updataar['views']}" class="required" validate="{digits:true,min:0,max:100}" />
                </td><td align="left">输入默认的浏览次数，默认为0</td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">文章分类:</td>
                <td align="left" class="l-table-edit-td">
                	<select name="addarfl" id="addarfl" ltype="select">
	                	<#list categorylist?keys as key>
								<#if categorylist.get(key)??>
									<#if key==updataar['categorys']>
										<option value="${key}" selected="selected">${categorylist.get(key)}</option>
									<#else>
										<option value="${key}">${categorylist.get(key)}</option>
									</#if>
								</#if>
							</#list>
					</select>
                </td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">文章标签:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="artags" type="text" id="artags" value="${updataar['tags']}" ltype='text' class="required" validate="{length:3}" />
                </td><td align="left">多个标签请用英文逗号（,）分开</td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">关键字:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="arkeywords" type="text" id="arkeywords" value="${updataar['keywords']}" ltype='text' class="required" validate="{length:3}" />
                </td><td align="left">多个关键字请用英文（,）分开</td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">描述:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="ardescription" type="text" id="ardescription" value="${updataar['description']}" ltype='text' class="required" validate="{length:3}" />
                </td><td align="left">文章的描述</td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">直达链接:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="arlink" type="text" id="arlink" value="${updataar['link']}" ltype='text' class="required" validate="{lenght:3}" />
                </td><td align="left">文章底部，标签上面显示的链接</td>
            </tr>
        </table>

 <br />
<input type="submit" value="立即发布" id="Button1" class="l-button l-button-submit" /> 
<input type="button" value="保存草稿" class="l-button l-button-test"/>
    </form>
    <div style="display:none">
    <!--  数据统计代码 --></div>

    
</body>
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用ue就能拿到相关的实例
	var ue = new UE.ui.Editor({
		initialFrameWidth:900,
		initialFrameHeight:200,
		toolbars: [[
					'fullscreen', 'source', '|', 'undo', 'redo', '|',
					'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
					'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
					'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
					'directionalityltr', 'directionalityrtl', 'indent', '|',
					'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
					'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
					'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
					'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
					'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
					'print', 'preview', 'searchreplace', 'drafts', 'help'
		        ]]	
	});
    ue.render("addtext");

    function isFocus(e){
        alert(ue.isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        ue.blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        ue.execCommand('insertHtml', value)
    }
    function createEditor() {
    	enableBtn();
        UE.getEditor('object');
    }
    function getAllHtml() {
        alert(ue.getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(ue.getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(ue.getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        ue.setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        ue.setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        ue.setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = ue.selection.getRange();
        range.select();
        var txt = ue.selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(ue.getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(ue.hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        ue.focus();
    }
    function deleteEditor() {
        disableBtn();
        ue.destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(ue.execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        ue.execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
</script>


</html>