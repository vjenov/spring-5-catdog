package com.catdog.web.brd;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catdog.web.cmm.IConsumer;
import com.catdog.web.utl.Printer;

@RestController
@RequestMapping("/articles")

public class ArticleCtrl {
	@Autowired Map<String, Object> map;
	@Autowired Article article;
	@Autowired ArticleMapper articleMapper;
	@Autowired Printer printer;
	private static final Logger Logger = LoggerFactory.getLogger(ArticleCtrl.class);
	@PostMapping("/")
	public Map<?,?> write(@RequestBody Article param){
		printer.accept("글쓰기 컨트롤러 진입");
		map.clear();
		param.setBoardtype("게시판");
		map.put("msg", "success");
		IConsumer<Article> c = o -> articleMapper.insertArticle(param);
		c.accept(param);
		printer.accept(map.get("msg"));
		return map;
	}
	@GetMapping("/{artseq}")
	public Article read(@PathVariable @RequestBody Article param){
		return null;
	}
	
	@PutMapping("/{artseq}")
	public Article edit(@PathVariable @RequestBody Article param){
		return param;
	}
	@DeleteMapping("/{artseq}")
	public Map<?,?> delete(@PathVariable @RequestBody Article param){
		return map;
	}
}
