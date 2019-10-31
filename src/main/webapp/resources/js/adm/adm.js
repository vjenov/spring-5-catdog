"use strict"
var adm = adm || {}
adm = (()=>{
	let _, js, css, img, navi_vue_js, auth_js
	let init=()=>{
		_ = $.ctx()
		js = $.js()
		css = $.css()
		img = $.img()
		navi_vue_js = js + '/vue/navi_vue.js'
		auth_js = js + 'cmm/auth.js'
	}
	let onCreate=()=>{
		init()
		$.when(
				$.getScript(navi_vue_js)
		).done(()=>{
			setContentView()
		}).fail(()=>{
			alert('ajax에러')
		})
	}
	let setContentView=()=>{
		$('body').empty()
		$(navi_vue.navi())
		.appendTo('body')
		$('<table id="tab"><tr></tr></table>')
		.css({border: '1px solid black', width: '80%', height: '90%', margin: '0 auto'})
		.appendTo('body')
		$.each([{id : "left", width : "20%"},
			{id : "right", width : "80%"}],(i,j)=>{
			$('<td id="'+j.id+'"></td>')
			.css({border: '1px solid black', width: j.width, 'vertical-align':'top'})
			.appendTo('#tab tr')
		})
		$.each([{txt:'웹크롤링', name : 'web_crawl'},
				{txt:'고객관리', name : 'cust_mgmt'},
				{txt:'상품등록', name : 'item_reg'},
				{txt:'상품조회', name : 'item_srch'},
				{txt:'상품수정', name : 'item_mod'},
				{txt:'상품삭제', name : 'item_del'}],
				(i,j)=>{
					$('<div name="'+ j.name +'">'+j.txt+'</div>')
					.css({border: '2px solid black', margin: 'auto 0', 'line-height': '50px'})
					.appendTo('#left')
					.hover(function(){
						$(this).addClass('active')
						$(this).siblings().removeClass('active')
						switch($(this).attr('name')){
						case 'web_crawl':
							
							break;
						case 'cust_mgmt':
							
							break;
						case 'item_reg':
							
							break;
						case 'item_srch':
							
							break;
						case 'item_mod':
							
							break;
						case 'item_del':
							
							break;
						}
					})
		})
		webCrawl()
	}
	let custManager=()=>{
		alert('고객관리')
	}
	let webCrawl=()=>{
		$('<form action="/action_page.php">'+
				'  <select id = "name" size="4" multiple>'+
				'  </select>'+
				'  <br><br>'+
				'  <input type="submit">'+
				'</form>').appendTo('#right')
		$( '<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">'+
	        '<button id="search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>')
	        .appendTo('#right')
		$.each([{name : '집', url : '집'},
			{name : '에', url : '에'},
			{name : '가', url : '가'},
			{name : '고', url : '고'},
			{name : '싶', url : '싶'},
			{name : '다', url : '다'}],
			(i,j)=>{
				$('<option value="">'+j.name+'</option>').appendTo('#name')
		}
	)}
	return {onCreate}
})()