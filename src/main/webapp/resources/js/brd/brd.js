"use strict"
var brd = brd||{}
brd = (()=>{
	let _, js, brd_vue_js, $uid
	let init =()=>{
		 _ = $.ctx()
        js = $.js()
        brd_vue_js = js+'/vue/brd_vue.js'
        $uid = $.uid()
	}
	let onCreate =()=>{
		init()
		$.getScript(brd_vue_js, ()=>{
			setContentView()
			navigation()
		})  
	}
	let setContentView=()=>{
		$('head').html(brd_vue.brd_head({css: $.css(), img: $.img()}))
        $('body').addClass('text-center')
        .html(brd_vue.brd_body({ctx: '/web',css: $.css(), img: $.img()}))
        $('#recent_updates .media').remove()
        $('#recent_updates .d-block').remove()
        $('#recent_updates').append('<h1>등록된 글이 없습니다.</h1>')
	}
/*	+' <input type="reset" class="btn btn-danger" style="float:right;width:100px;margin-right:10px" value="CANCEL"/>'
	+'<input name="write" type="submit" class="btn btn-primary" style="float:right;width:100px;margin-right:10px" value="SUBMIT"/>'*/
	let write=()=>{
		alert('글쓰기클릭')
		$('#recent_updates').html(brd_vue.brd_write())
		$('#write_form input[name="writer"]').val($uid)
		$('#suggestions').remove()
		$('<input>', {
			style:"float:right;width:100px;margin-right:10px",
			value:"CANCEL"
		})
		.addClass('btn btn-danger')
		.appendTo($('#write_form'))
		.click(()=>{
		})
		$('<input>', {
			type:"submit",
			style:"float:right;width:100px;margin-right:10px",
			value:"전송"
		})
		.addClass('btn btn-primary')
		.appendTo($('#write_form'))
		.click(e=>{
     		e.preventDefault()
			let json = {
					uid: $('#write_form input[name="writer"]').val(),
					title: $('#write_form input[name="title"]').val(),
					content: $('#write_form textarea[name="content"]').val()
			}
			alert('ID : ' +json.uid)
			alert('제목 : ' +json.title)
			alert('내용 : ' +json.content)
			$.ajax({
				url:_+'/articles/',
				type:'POST',
				data:JSON.stringify(json),
				dataType:'json',
				contentType:'application/json',
				success:d=>{
					alert('ajax 메세지'+d.msg)
					setContentView()
					navigation()
				},
				fail:e=>{
					alert('AJAX실패')
				}
			})
		})
	}
	let navigation=()=>{
		 $('<a>',{
	        	href : '#',
	        	click : e=>{
		        	e.preventDefault()
		        	write()
		        },
		        text : '글쓰기'
	        })
	        .addClass('nav-link')
	        .appendTo('#go_write')
	}
	return {onCreate}
})()