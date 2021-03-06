package com.catdog.web.brd;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper {
	public void insertArticle(Article param);
	public Article getArticle(Article param);
	public void editArticle(Article param);
	public void deleteArticle(Article param);
	public String countArticle();
	public List<Article> selectAll();
}
