package com.ism.data.repository.interfaces;

import com.ism.core.Repository.Repository;
import com.ism.data.entities.Article;
import com.ism.data.enums.EtatArticle;

public interface ArticleRepositoryI extends Repository<Article>{

    Article selectById(int id) ;
    Article selectBy(EtatArticle etat) ;

}
