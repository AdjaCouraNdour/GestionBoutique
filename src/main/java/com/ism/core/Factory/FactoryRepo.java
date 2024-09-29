package com.ism.core.Factory;

import com.ism.data.repository.bd.ClientRepositoryBD;
import com.ism.data.repository.bd.UserRepositoryBD;
import com.ism.data.repository.interfaces.ArticleRepositoryI;
import com.ism.data.repository.interfaces.ClientRepositoryI;
import com.ism.data.repository.interfaces.DetteRepositoryI;
import com.ism.data.repository.interfaces.PaiementRepositoryI;
import com.ism.data.repository.interfaces.UserRepositoryI;
import com.ism.data.repository.list.ArticleRepositoryList;
import com.ism.data.repository.list.DetteRepositoryList;
import com.ism.data.repository.list.PaiementRepositoryList;

public class FactoryRepo {
    
    private static ClientRepositoryI clientRepository = null;
    private static UserRepositoryI userRepository=null;
    private ArticleRepositoryI articleRepository=null;
    private PaiementRepositoryI paiementRepository=null;
    private DetteRepositoryI detteRepository=null;

    public FactoryRepo(ArticleRepositoryI articleRepository,
			PaiementRepositoryI paiementRepository, DetteRepositoryI detteRepository) {
		this.articleRepository = articleRepository;
		this.paiementRepository = paiementRepository;
		this.detteRepository = detteRepository;
	}
	public ClientRepositoryI getInstanceRepoClient(){
        if (clientRepository==null) {
            return clientRepository = new ClientRepositoryBD(getInstanceRepoUser());
        }
        return clientRepository;

    }
    public UserRepositoryI getInstanceRepoUser(){
        if (userRepository==null) {
            return userRepository=new UserRepositoryBD();
        }
        ((UserRepositoryBD) userRepository).setClientRepository(getInstanceRepoClient());
        return userRepository;
    }   

    public ArticleRepositoryI getInstanceRepoArticle(){
        if (articleRepository==null) {
            return articleRepository=new ArticleRepositoryList();
        }
        return articleRepository;
    }  
    public DetteRepositoryI getInstanceRepoDette(){
        if (detteRepository==null) {
            return detteRepository=new DetteRepositoryList();
        }
        return detteRepository;
    }   
    public PaiementRepositoryI getInstanceRepoPaiement(){
        if (paiementRepository==null) {
            return paiementRepository=new PaiementRepositoryList();
        }
        return paiementRepository;
    }     
   
}
