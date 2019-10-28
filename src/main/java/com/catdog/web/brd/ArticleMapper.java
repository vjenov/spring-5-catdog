package com.catdog.web.brd;

import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper {
	public void insertArticle(Article param);
	public Article read(Article param);
	public Article edit(Article param);
	public Article delete(Article param);
}
