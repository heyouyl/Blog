/**
 * 自定义函数
 */
var pagination = ".content_zheli";		//显示分页
var triggerPageThreshold = 1;			//显示分页时，此数值为1

function getUrlParam(name){
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
//获得cookie  在script.js中有函数getCookie
function getCookies(cookie_name)
{
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookie_name);   //索引的长度
  
    // 如果找到了索引，就代表cookie存在，
    // 反之，就说明不存在。
    if (cookie_pos != -1)
    {
        // 把cookie_pos放在值的开始，只要给值加1即可。
        cookie_pos += cookie_name.length + 1;      //这里容易出问题，所以请大家参考的时候自己好好研究一下
        var cookie_end = allcookies.indexOf(";", cookie_pos);
  
        if (cookie_end == -1)
        {
            cookie_end = allcookies.length;
        }
  
        var value = unescape(allcookies.substring(cookie_pos, cookie_end));         //这里就可以得到你想要的cookie的值了。。。
    }
    return value;
}
//如果这个cookie是一个字符串，需要具体获得   a=2&b=3   获得a的值
function getCookey(cookie_name,cookie_key){
	var ck = getCookie(cookie_name);
	ck=ck.substring(1,ck.length-1);
	var cks = ck.split('&');
	for(var i=0;i<cks.length;i++){
		var vcks = cks[i].split('=');
		if(vcks[0]==cookie_key){
			return vcks[1];
		}
	}
}

//将form表单转化成Javascript object
$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//form验证的正则表达式
function is_name(str) {
    return /^[A-Za-z]+\w{4,8}$/.test(str)
}
function is_url(str) {
    return /^((http|https)\:\/\/)([a-z0-9-]{1,}.)?[a-z0-9-]{2,}.([a-z0-9-]{1,}.)?[a-z0-9]{2,}$/.test(str)
}
function is_qq(str) {
    return /^[1-9]\d{4,13}$/.test(str)
}
function is_mail(str) {
    return /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/.test(str)
}
var regname = /^[A-Za-z]+\w{3,8}$/; // 用户名必须第一个是数字，长度为3到8位
var regemail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; // email

//分页
function paging(max, current, plink, step) {
	var show = 2
	if( !step ) step = 10
    if ( max <= step ) return;
    max = Math.ceil(max/step)
    var html = '<div class="pagination user-pagination"><ul>'

    if ( !current ) current = 1
    current = Number(current)
    if ( current > show + 1 ) html += '<li><a href="'+plink+'1">1</a></li>'
    if ( current > show + 2 ) html += '<li><span>...</span></li>'
    for( i = current - show; i <= current + show; i++ ) { 
        if ( i > 0 && i <= max ){
        	html += (i == current) ? '<li class="active"><span>'+i+'</span></li>' : '<li><a href="'+plink+i+'">'+i+'</a></li>'
        }
    }

    if ( current < max - show - 1 ) html += '<li><span>...</span></li>'
    if ( current < max - show ) html += '<li><a href="'+plink+max+'">'+max+'</a></li>'

    html += '<li><span>共'+max+'页</span></li>'
    html += '</ul></div>'
    return html
}

//注册验证,暂不用，用html5
function registerForm(){
	var username = $("#user_login").val();
	var usermail = $("#user_email").val();
	if(!regname.test(username)){
		$("#login .register").html("用户名限制在4-8字内,第一个为字母");
		return false;
	}else if($("#user_email").val()==""){
		$("#login .register").html("用户名邮箱错误不能为空");
		return false;
	}else if(!regemail.test(usermail)){
		$("#login .register").html("邮箱格式不对哦");
		return false;
	}else{
		$("#login .register").html("稍等，正在注册");
		return true;
	}
}
//找回密码验证
function lostpassForm(){
	var lostpass=$("#lostpass").val();
	if(lostpass==""){
		$("#login .message").html("用户名或邮箱不能为空");
		return false;
	}else{
		var iden ="";
		if(is_mail(lostpass)){
			iden = "email";
		}else{
			iden = "username";
		}
		$.ajax({   
			type:"POST",
			url:"lostpass.do",  
			data:{"iden":iden,"lostpass":lostpass},
			dataType:"json",
			cache:false, //不缓存此页面  
			success:function(data){
				if(data.msg=="lostok"){
					$("#login .message").html("请查询邮件以获得修改密码连接（"+data.iden+")");
				}else{
					$("#login .message").html("找回密码失败，没有这个用户吗？");
				}
			}
		});
		return false;
	}
}

function lostpasswordForm(){
	var newpass = $("#newpass").val();
	var newpass1 = $("#newpass1").val();
	if(newpass!=newpass1){
		$("#login .message").html("两次密码输入不一致");
		return false;
	}else if(newpass.length<8){
		$("#login .message").html("密码长度至少为8");
		return false;
	}
	else{
		$("#login .message").html("稍等，正在操作");
		return true;
	}
}
function replycomment(parentid,reauthor){
	alert("回复给["+reauthor+"]");
	$("#replycommentid").val(parentid);
}
function isphone(blogadid){
	 var system = {  
            win: false,  
            mac: false,  
            xll: false,  
            ipad: false  
        };  
        //检测平台   
        var p = navigator.platform;  
        system.win = p.indexOf("Win") == 0;  
        system.mac = p.indexOf("Mac") == 0;  
        system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);  
        system.ipad = (navigator.userAgent.match(/iPad/i) != null) ? true : false;  
        //跳转语句，如果是手机访问就自动跳转到wap.baidu.com页面   
        if (system.win || system.mac || system.xll || system.ipad) {
        		//获得对应的pc代码 
        } else {  
        		//获得对应的手机代码
//            var ua = navigator.userAgent.toLowerCase();    
//            if(ua.match(/MicroMessenger/i)=="micromessenger") {    
//                alert("微信浏览器");  
//            } else {    
//                alert("手机");  
//            	}    
            //window.location.href = "http://www.jdpatro.com/3g/";  
        }  
}
$(function(){
	var bass = $(".blogadshow");
	var len = bass.length;
	for(var i=0;i<len;i++){
		bass.eq(i).html(isphone(bass.eq(i).text()));
	}
	$('#registerform input').focus(function (){
		$(this).attr("placeholder","");
	})
	$('#registerform input').blur(function (){
		if($(this).val()==""){
			if($(this).attr("name")=="user_login"){
				$(this).attr("placeholder","例如:asdf");
			}
			if($(this).attr("name")=="user_email"){
				$(this).attr("placeholder","例如:abc@example.abc");
			}
		}
	})
})