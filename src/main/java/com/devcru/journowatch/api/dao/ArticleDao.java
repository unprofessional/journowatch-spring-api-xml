package com.devcru.journowatch.api.dao;

import java.util.LinkedList;

import com.devcru.journowatch.api.objects.Article;

public interface ArticleDao {
	
	public boolean createArticle(Article article);
	
	public Article getArticle(Article article);
	
	public boolean updateArticle(Article article);
	
	public boolean deleteArticle(Article article);
	
	public LinkedList<Article> getAllArticles();

}
