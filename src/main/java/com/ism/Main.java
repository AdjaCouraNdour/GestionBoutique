package com.ism;

import java.util.Scanner;
import com.ism.core.Factory.FactoryRepo;
import com.ism.core.Factory.FactoryService;
import com.ism.views.ArticleView;
import com.ism.views.ClientView;
import com.ism.views.DetailsView;
import com.ism.views.DetteView;
import com.ism.views.PaiementView;
import com.ism.views.UserView;

public class Main {
        private static Scanner scanner =new Scanner(System.in);
    public static void main(String[] args) {

        FactoryRepo factory =new FactoryRepo( null, null, null);
        FactoryService factoryService =new FactoryService(null, null, null, null, null, factory);

        DetailsView detailsView =new DetailsView(scanner,factoryService.getInstanceArticleService());
        DetteView detteView =new DetteView(scanner,detailsView,factoryService.getInstanceClientService(),factoryService.getInstanceDetteService());
        ClientView clientView =new ClientView(scanner,factoryService.getInstanceClientService() );
        UserView userView =new UserView(scanner,factoryService.getInstanceClientService(),factoryService.getInstanceUserService(),clientView);
        ArticleView articleView= new ArticleView(scanner,factoryService.getInstanceArticleService());
        PaiementView paiementView =new PaiementView(scanner,factoryService.getInstanceClientService(),factoryService.getInstanceDetteService());


    int choix;
    do {
        choix=menu();
        switch (choix) {
            case 1->{
                factoryService.getInstanceClientService().save(clientView.create());
            }
            case 2->{
                clientView.afficher(factoryService.getInstanceClientService().show());
             }   
            case 3->{
                factoryService.getInstanceUserService().save(userView.create());
            }
            case 4->{
                userView.afficher(factoryService.getInstanceUserService().show());
            }
            case 5->{
                userView.listerParRole();
            }
            case 6->{
                factoryService.getInstanceArticleService().save(articleView.create());
            }
            case 7->{
                articleView.afficher(factoryService.getInstanceArticleService().show());
            }
            case 8->{
                articleView.listerArticleDispo();
            }
            case 9->{
                articleView.mettreAJour();
            }
            case 10->{
                factoryService.getInstanceDetteService().save(detteView.create());
            }
            case 11->{
                detteView.listerDetteClient();
            }
            case 12->{
                factoryService.getInstancePaiementService().save(paiementView.create());
            }
            case 13->{
                detteView.archiverDetteSolde();
            }
            case 14->{
             System.out.println("bizzzzzzoooou !!! <3");
            }
        }
        
    } while (choix!=15);
    
}

public static int menu(){
    System.out.println("1-Créer un client");
    System.out.println("2-Lister les clients");
    System.out.println("3-Creer compte utulisateur pour un client");
    System.out.println("4-Lister les comptes utulisateur ");
    System.out.println("5-Lister les comptes utulisateur par role");
    System.out.println("6-creer un article");
    System.out.println("7-lister les articles");
    System.out.println("8-lister les articles disponibles");
    System.out.println("9-mettre a jour la quantite stock d'un article");
    System.out.println("10-faire une dette");
    System.out.println("11-Lister les dettes d'un client");
    System.out.println("12-faire un paiement");
    System.out.println("13-archiver les dettes soldes");
    System.out.println("14-Quitter");
    return scanner.nextInt();
}
}