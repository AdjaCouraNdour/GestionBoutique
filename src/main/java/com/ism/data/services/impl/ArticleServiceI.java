package com.ism.data.services.impl;

import com.ism.core.Service;
import com.ism.data.entities.Article;
import com.ism.data.enums.EtatArticle;

public interface ArticleServiceI extends Service<Article>{
    Article getById(int id) ;
    Article getBy(EtatArticle etat);
}
