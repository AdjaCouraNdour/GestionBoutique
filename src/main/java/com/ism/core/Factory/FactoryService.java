package com.ism.core.Factory;

import com.ism.data.services.list.ArticleService;
import com.ism.data.services.list.ClientService;
import com.ism.data.services.list.DetteService;
import com.ism.data.services.list.PaiementService;
import com.ism.data.services.list.UserService;

public class FactoryService {

     private ClientService clientService = null;
     private UserService userService  = null;
     private ArticleService articleService = null;
     private DetteService detteService  = null;
     private PaiementService paiementService = null;
     FactoryRepo factory = new FactoryRepo( null, null, null);
   
    public FactoryService(ClientService clientService, UserService userService,
            ArticleService articleService, DetteService detteService,
            PaiementService paiementService, FactoryRepo factory) {
        this.clientService = clientService;
        this.userService = userService;
        this.articleService = articleService;
        this.detteService = detteService;
        this.paiementService = paiementService;
        this.factory = factory;
    }

    public ClientService getInstanceClientService(){
        if (clientService==null) {
            return clientService=new ClientService(factory.getInstanceRepoClient());
        }
        return clientService;
    }

    public UserService getInstanceUserService(){
        if (userService==null) {
            return userService=new UserService(factory.getInstanceRepoUser());
        }
        return userService;
    }

    public ArticleService getInstanceArticleService(){
        if (articleService==null) {
            return articleService=new ArticleService(factory.getInstanceRepoArticle());
        }
        return articleService;
    }
    public DetteService getInstanceDetteService(){
        if (detteService==null) {
            return detteService=new DetteService(factory.getInstanceRepoDette());
        }
        return detteService;
    }
    public PaiementService getInstancePaiementService(){
        if (paiementService==null) {
            return paiementService=new PaiementService(factory.getInstanceRepoPaiement());
        }
        return paiementService;
    }
   
   
}
