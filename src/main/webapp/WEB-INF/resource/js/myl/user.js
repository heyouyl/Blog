var _tipstimer;
var 
_iframe = $('#contentframe'),
_main = $('.user-main'),
_homepage = 'comments',
userpost_ajax_url="posts/all.do",
userid=$("#userid").val(),
datefirst = false,
cache_postmenu = null,
cache_userdata = null,
cache_orderdata = null,
cache_coupondata = null,

rp_post = /^#post\//,
rp_comment = /^#comment/,
rp_like = /^#like/,
_msg = {
	// 1-2位：类型；3-4位：01-69指客户端操作提示，71-99指服务端操作提示
	1101: '该栏目下暂无数据！',
	1079: '服务器异常，请稍候再试！',

	1201: '暂无文章！',
	1301: '暂无评论！'
};

//发布文章提示信息
function tips(str){
	if( !str ) return false
	_tipstimer && clearTimeout(_tipstimer)
	$('.user-tips').html(str).animate({
		top: 0
	}, 220)
	_tipstimer = setTimeout(function(){
	$('.user-tips').animate({
		top: -30
	}, 220)
	}, 5000)
}
//等待
function loading(el, msg){
	if( !msg ){
		msg = '<i class="fa fa-spinner fa-spin" style="position:relative;top:1px;margin-right:5px;"></i> 数据加载中'
	}
	el.html('<div class="user-loading">'+msg+'</div>')
}
function thumb_lazyload(){
	require(['../../res/js/jquery.lazyload.min'], function(){
		$('.user-main .thumb').lazyload({
	        data_attribute: 'src',
	        placeholder: jsui.uri + '/img/thumbnail.png',
	        threshold: 400
	    });
    });
}
//导航状态
function menuactive(name){
	$('.usermenus li').removeClass('active')
	$('.usermenu-'+name).addClass('active')
}

//异步数据
function get_postdata(status, paged, callback){
	menuactive('posts')
	$('.user-postmenu a').removeClass()

	loading( _main )

	var datas = {
		status: status,
		paged: paged,
		userid: userid
	}

	if( !cache_postmenu ){
		datas.first = true;
		datefirst = datas.first;
	}

	$.ajax({
		url: userpost_ajax_url,
		type: 'POST',
		dataType: 'json',
		data: datas,
		success: function(data, textStatus, xhr) {
			//console.log( data )

			if( !cache_postmenu && data.menus ){
				cache_postmenu = data.menus
			}

			if( (cache_postmenu || (!cache_postmenu && data.menus)) && !$('.user-postmenu').length ){
				_main.before( '<div class="user-postmenu"></div>' )
				$('.user-postmenu').html(
					$('#temp-postmenu').render( cache_postmenu || data.menus )
				)
			}

			if( data.items ){
				_main.html('<ul class="user-postlist"></ul>');
				$('.user-postlist').html(
						function(){
							var htmls="";
							var datas = data.items;
							for(var i=0;i<datas.length;i++){
								htmls=htmls+"<li><img data-src='"+datas[i].thumb+"' class='thumb'><h2><a target='_blank' href='"+datas[i].link+"'>"+datas[i].title+"</a></h2><p class='note'>"+datas[i].desc+"</p><p class='text-muted'>"+datas[i].time+" &nbsp;&nbsp; 分类："+datas[i].cat+" &nbsp;&nbsp; 阅读("+datas[i].views+") &nbsp;&nbsp; 评论("+datas[i].comment+") &nbsp;&nbsp; 赞("+datas[i].like+")</p></li>";
							}
							return htmls;
						}
					//$('#temp-postitem').render( data.items )
				).after( paging(data.max, paged, '#posts/'+status+'/') );
				thumb_lazyload();
			}else{
				loading(_main, _msg['1201']);
			}

			callback && callback()
		},
		error: function(xhr, textStatus, errorThrown) {
			loading(_main, _msg['1079'])
		}
	});
}

(function($) {
//	if(!datefirst){
//		get_postdata('all', 1);
//	}
	//右侧导航
	$('.usermenu').on('click', function(e){
		e = e || window.event;
	    var target = e.target || e.srcElement;
	    var _ta = $(target);
	    if( _ta.parent().attr('class') ){
	        _ta = $(_ta.parent()[0]);
	    }else if( _ta.parent().parent().attr('class') ){
	        _ta = $(_ta.parent().parent()[0]);
	    }
	    var type = _ta.attr('class');

	    if( !type || _ta.hasClass('disabled') ) return
	})
	//我的文章 头部
	$('.user-postmenu').on('click', function(e){
		e = e || window.event;
	    var target = e.target || e.srcElement;
	    var _ta = $(target);
	    if( _ta.parent().attr('href') ){
	        _ta = $(_ta.parent()[0])
	    }else if( _ta.parent().parent().attr('href') ){
	        _ta = $(_ta.parent().parent()[0])
	    }
	    var type = _ta.attr('href')

	    if( !type || _ta.hasClass('disabled') ) return
	    switch( type ){
	    	case '#posts/all': 
				get_postdata('all', 1);
				$('.user-postmenu a:eq(0)').addClass('active');
				break;
	    	case '#posts/publish':
				get_postdata('publish', 1)
				$('.user-postmenu a:eq(1)').addClass('active')
				break;
	    	case '#posts/future': 
				get_postdata('future', 1)
				$('.user-postmenu a:eq(2)').addClass('active')
				break;
	    	case '#posts/pending': 
				get_postdata('pending', 1)
				$('.user-postmenu a:eq(3)').addClass('active')
				break;
	    	case '#posts/draft':
				get_postdata('draft', 1)
				$('.user-postmenu a:eq(4)').addClass('active')
				break;
	    	case '#posts/trash':
				get_postdata('trash', 1)
				$('.user-postmenu a:eq(5)').addClass('active')
				break;
	    }
	})
	
	//提交按钮
	$('.container-user').on('click', function(e){
	    e = e || window.event;
	    var target = e.target || e.srcElement
	    var _ta = $(target)

	    if( _ta.parent().attr('evt') ){
	        _ta = $(_ta.parent()[0])
	    }else if( _ta.parent().parent().attr('evt') ){
	        _ta = $(_ta.parent().parent()[0])
	    }

	    var type = _ta.attr('evt')

	    if( !type || _ta.hasClass('disabled') ) return 

	    switch( type ){
	    	
	    	case 'postnew.submit':
	    		var form = _ta.parent().parent().parent();
	    		var inputs = form.serializeObject();
	            
	    		var title   =  $.trim(inputs.post_title);
	    		var url     =  $.trim(inputs.post_url);
	    		var content =  $.trim(inputs.post_content);
	    	
	    		var ajax_url = _ta.attr('url');
	    		if ( !title || title.length > 50 ) {
	    			tips('标题不能为空，且小于50个字符');  
	    			return
	    		}

		        if ( !content || content.length > 10000 || content.length < 10 ) {
		            tips('文章内容不能为空，且介于10-10000字之间');  
		            return
		        }

		        if ( !url && url.length > 200 ) {
		            tips('来源链接不能大于200个字符');  
		            return
		        }

	    		$.ajax({  
	                type: 'POST',  
	                url:  ajax_url,  
	                data: inputs,  
	                dataType: 'text',
	                success: function(data){  

	                	tips(data)

	                    form.find('.form-control').val('')

	                    location.hash = 'posts/draft'
	                }  
	            });  

	    		break;

	        case 'password.submit':
	        	var form = _ta.parent().parent().parent();
	            var inputs = form.serializeObject();
	            inputs.userid = userid
	            var ajax_url = _ta.attr('url')
	            if( !inputs.action ){
	                return
	            }

	        	if( !$.trim(inputs.passwordold) ){
                    tips('请输入原密码');
                    return
                }

                if( !inputs.password || inputs.password.length < 6 ){
                    tips('新密码不能为空且至少6位');
                    return
                }

                if( inputs.password !== inputs.password2 ){
                    tips('两次密码输入不一致');
                    return
                }

                if( inputs.passwordold === inputs.password ){
                    tips('新密码和原密码不能相同');
                    return
                }

	        	$.ajax({  
	                type: 'POST',  
	                url:  ajax_url,  
	                data: inputs,  
	                dataType: 'text',
	                success: function(data){  
	                    if( data=='updatepasserror'){
	                        tips("密码修改失败，愿密码错误？")
	                        return
	                    }

	                    tips('修改成功！下次登录请使用新密码！'+data)

	                    $('input:password').val('')
	                }  
	            });  

	            break;

	        case 'info.submit':
	            var form = _ta.parent().parent().parent()
	            var inputs = form.serializeObject()
	            inputs.userid = userid
	            var ajax_url = _ta.attr('url')
	            if( !inputs.action ){
	            	tips('请勿重复提交')
	                return
	            }
	      
	            
                if( !/.{2,20}$/.test(inputs.displayname) ){
                    tips('昵称限制在2-20字内')
                    alert(123)
                    return
                }

                /*if( !inputs.email ){
                    tips('邮箱不能为空')
                    return
                }

                if( !is_mail(inputs.email) ){
                    tips('邮箱格式错误')
                    return
                }
*/
                if( inputs.blogurl && (!is_url(inputs.blogurl) || inputs.blogurl.length>100) ){
                    tips('网址格式错误')
                    return
                }

                if( inputs.user_qq && !is_qq(inputs.user_qq) ){
                    tips('QQ格式错误')
                    return
                }

                if( inputs.user_weixin && inputs.user_weixin.length>30 ){
                    tips('微信字数过长，限制在30字内')
                    return
                }

                if( inputs.user_weibo && (!is_url(inputs.user_weibo) || inputs.user_weibo.length>100) ){
                    tips('微博格式错误')
                    return
                }

	            $.ajax({  
	                type: 'POST',  
	                url:  ajax_url,  
	                data: inputs,  
	                dataType: 'text',
	                success: function(data){  
	                	if(data=="updateok"){
	                		tips('修改成功！'+data)

		                    cache_userdata = null
	                	}else{
	                		tips('修改失败！')
	                	}
	                    
	                }  
	            });  

	            break;


	    }
	})
})(jQuery);