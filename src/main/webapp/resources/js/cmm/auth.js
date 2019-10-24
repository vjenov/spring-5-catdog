"use strict";
var auth = auth || {}
auth = (()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.'
    let _, js, auth_vue_js, brd_vue_js
    let init = ()=>{
        _ = $.ctx()
        js = $.js()
        auth_vue_js = js+'/vue/auth_vue.js'
        brd_vue_js = js+'/vue/brd_vue.js'
    }
    function onCreate(){
        init()
        $.getScript(auth_vue_js).done(()=>{
        	setContentView()
    		$('#a_go_join').click(e=>{
         		e.preventDefault()
         		$('head').html(auth_vue.join_head())
		        $('body').html(auth_vue.join_body())
		        $('<button>',{
		            text : '회원가입',
		            href : '#',
		            click : e=>{
		            	e.preventDefault()
		            	if(!existId())
		            		join()
	            		else
	            			alert('아이디 중복')
		            }
		        })
		        .addClass('btn btn-primary btn-lg btn-block')
		        .appendTo('#btn_join')
    		})
        }).fail(()=>{alert(WHEN_ERR)})
    }
    function setContentView(){
    	 login()
    }
    let existId =()=>{
	    		let data = {uid : $('#uid').val()}
	    		$.ajax({
	    			url : _+'/users/'+$('#uid').val()+'/existId',
	    			type : 'GET',
	    			contentType : 'application/json',
	    			success : d=>{
	    				if(d.uid=='')
	    				alert('AJAX 성공 아이디: '+d.uid)
	    				else
	    				alert('중복')
	    			},
	    			error : e=>{
	    				alert('AJAX 실패')
	    			}
	    		})
	    	}
    
    let join =()=>{
    	$('head').html(auth_vue.join_head())
        $('body').html(auth_vue.join_body())
        $('<button>',{
            text : 'Continue to checkout',
            href : '#',
            click : e=>{
            	e.preventDefault();
            	let data = {uid : $('#uid').val(), pwd : $('#pwd').val(), pname : $('#pname').val()}
            	alert('전송아이디: '+data.uid)
                $.ajax({
			    	url : _+'/users/',
			    	type : 'POST',
			    	dataType : 'json',
			    	data : JSON.stringify(data),
			    	contentType : 'application/json',
			    	success : d => {
			    		alert('AJAX 성공 : '+d.msg)
			    		if(d.msg === 'Success')
			    			login()
			    		else
			    			alert('회원가입 실패')
			    	},
			    	error : e => {
			    		alert('AJAX 실패')
			    	}
            	})
                
            }
        })
        .addClass('btn btn-primary btn-lg btn-block')
        .appendTo('#btn_join')
    }
    let login =()=>{
    	let x = {css: $.css(), img: $.img()}
    	$('head').html(auth_vue.login_head(x))
        $('body').addClass('text-center')
        .html(auth_vue.login_body(x))
        $('<button>',{
        	type : "submit",
        	text : "Sign in",
        	click : e => {
        		e.preventDefault()
        		let data = {uid : $('#uid').val(), pwd : $('#pwd').val(), pname : $('#pname').val()}
            	alert('전송아이디: '+data.uid)
                $.ajax({
			    	url : _+'/users/uid',
			    	type : 'POST',
			    	dataType : 'json',
			    	data : JSON.stringify(data),
			    	contentType : 'application/json',
			    	success : d => {
			    		alert(d.pname + '님 환영합니다.')
			    		board()
			    	},
			    	error : e => {
			    		alert('AJAX 실패')
			    	}
            	})
        	}
        })
        .addClass("btn btn-lg btn-primary btn-block")
        .appendTo('#btn_login')
    }
    let board =()=>{
//    	$('h1').html(auth_vue.my_page(d))
    	  $.getScript(brd_vue_js).done(()=>{
    		  let x = {css: $.css(), img: $.img()}
    	    	$('head').html(brd_vue.brd_head(x))
    	    	$('body').html(brd_vue.brd_body(x))
    	  })	  
    }
    return {onCreate, join, login, existId, board}
})();