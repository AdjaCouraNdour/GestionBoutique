package com.ism.data.services.list;

import java.util.List;

import com.ism.data.entities.Article;
import com.ism.data.enums.EtatArticle;
import com.ism.data.repository.interfaces.ArticleRepositoryI;
import com.ism.data.services.impl.ArticleServiceI;

public class ArticleService implements ArticleServiceI {

    ArticleRepositoryI repo;

    public ArticleService(ArticleRepositoryI repo) {
        this.repo = repo;
    }

    @Override
    public Article getById(int id) {
        return
        repo.selectAll().stream()
        .filter(Article -> Article.getId()==id)
        .findFirst()
        .orElse(null);  
    }

    @Override
    public Article getBy(EtatArticle etat) {
        return
        repo.selectAll().stream()
        .filter(article->article.getEtatArticle().compareTo(etat)==0)
        .findAny()
        .orElse(null);
        
    }

    @Override
    public boolean save(Article object) {
        return repo.insert(object);
    }

    @Override
    public List<Article> show() {
        return repo.selectAll();
    }
    


    
    
}
