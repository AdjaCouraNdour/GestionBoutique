package com.ism.data.repository.list;

import com.ism.core.Repository.RepositoryImpl;
import com.ism.data.entities.Article;
import com.ism.data.enums.EtatArticle;
import com.ism.data.repository.interfaces.ArticleRepositoryI;

public class ArticleRepositoryList extends RepositoryImpl<Article> implements ArticleRepositoryI {

    @Override
    public Article selectById(int id) {
        return
        list.stream()
        .filter(article -> article.getId()==id)
        .findFirst()
        .orElse(null);
    }

    @Override
    public Article selectBy(EtatArticle etat) {
        return
        list.stream()
        .filter(article -> article.getEtatArticle()==etat)
        .findFirst()
        .orElse(null);
    }
    
}
