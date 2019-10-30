package com.catdog.web.brd;

import java.util.List;
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
import com.catdog.web.cmm.IFunction;
import com.catdog.web.cmm.ISupplier;
import com.catdog.web.usr.UserCtrl;
import com.catdog.web.utl.Printer;

@RestController
@RequestMapping("/articles")

public class ArticleCtrl {
	private static final Logger Logger = LoggerFactory.getLogger(ArticleCtrl.class);
	@Autowired Map<String, Object> map;
	@Autowired Article article;
	@Autowired ArticleMapper articleMapper;
	@Autowired Printer printer;
	@Autowired List<Article> list;
	@PostMapping("/")
	public Map<?,?> write(@RequestBody Article param){
		printer.accept("글쓰기 컨트롤러 진입");
		map.clear();
		param.setBoardtype("게시판");
		map.put("msg", "success");
		IConsumer<Article> c = o -> articleMapper.insertArticle(param);
		c.accept(param);
		printer.accept(map.get("msg"));
		ISupplier<String> s = () -> articleMapper.countArticle();
		map.put("count", s.get());
		return map;
	}
	@GetMapping("/{articleseq}")
	public Article read(@PathVariable @RequestBody Article param){
		return null;
	}
	
	@PutMapping("/{articleseq}")
	public Article editArticle(@PathVariable String articleseq, @RequestBody Article param){
		printer.accept("edit 진입");
		list.clear();
		IConsumer<Article> c = o-> articleMapper.editArticle(o);
		c.accept(param);
		IFunction<Article, Article> f = t-> articleMapper.getArticle(t);
		printer.accept("글목록 :::" + f.apply(param));
		return f.apply(param);
	}
	@DeleteMapping("/{articleseq}")
	public Map<?,?> deleteArticle(@PathVariable String articleseq, @RequestBody Article param){
		printer.accept("delete도착");
		IConsumer<Article> c = o-> articleMapper.deleteArticle(o);
		map.put("msg", "삭제완료");
		c.accept(param);
		return map;
	}
	@GetMapping("/count")
	public Map<?,?> countNum() {
		ISupplier<String> s = () -> articleMapper.countArticle();
		printer.accept("매퍼값" + s.get());
		map.clear();
		map.put("count", s.get());
		return map;
	}
	@GetMapping("/")
	public List<Article> list() {
		list.clear();
		ISupplier<List<Article>> s =()-> articleMapper.selectAll();
		printer.accept("전체글목록\n"+s.get());
		return s.get();
	}
}
