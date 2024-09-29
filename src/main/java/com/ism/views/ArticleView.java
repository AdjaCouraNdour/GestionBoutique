package com.ism.views;

import java.util.Scanner;
import com.ism.core.ViewImpl;
import com.ism.data.entities.Article;
import com.ism.data.enums.EtatArticle;
import com.ism.data.services.list.ArticleService;

public class ArticleView extends ViewImpl<Article> {

    private ArticleService artService;

    public ArticleView(Scanner scanner, ArticleService artService) {
        super(scanner);
        this.artService = artService;
    }

    @Override
    public Article create() {
        Article art =new Article();
        scanner.nextLine();
        System.out.println("veiller entre la reference de l'article");
        art.setReference(scanner.nextLine());
        System.out.println("veiller entre le libelle de l'article");
        art.setLibelle(scanner.nextLine());
        System.out.println("veiller entre le prix de l'article");
        art.setPrix(scanner.nextInt());
        double qte;
        do {
            System.out.println("entrer une qte pour larticle");
            qte=scanner.nextInt();
        } while (qte<0);
        art.setQteStock(qte);
        if (art.getQteStock()==0) {
            art.setEtatArticle(EtatArticle.Indisponible);
        } else {
            art.setEtatArticle(EtatArticle.Disponible);
        }
       return art;
    }
    
    public void listerArticleDispo(){
        System.out.println(artService.getBy(EtatArticle.Disponible));
    }

    public Article mettreAJour(){
        System.out.println(artService.show());
        System.out.println("choisissez l'article");
        Article art=artService.getById(scanner.nextInt());
        if (art!=null) {
            System.out.println("---MIS A JOUR---");
            System.out.println("1/le libelle ");
            System.out.println("2/la quantite");
            System.out.println("3/abandonner ");
            int choix=scanner.nextInt();
            switch (choix) {
                case 1->{
                    scanner.nextLine();
                    System.out.println("entrer un nouveau libelle pour larticle");
                    art.setLibelle(scanner.nextLine());
                }
                case 2->{
                    int qte;
                    do {
                        System.out.println("entrer une nouvelle qte pour larticle");
                        qte=scanner.nextInt();
                    } while (qte<0);
                    art.setQteStock(qte);
                }
                case 3->{
                    return null;               
                }
            }
        }
        return art;
    }
 // private EtatArticle choiceEtatArticle() {
    //     int choix;
    //     do {
    //         System.out.println("veuillez selectionner le role du user");
    //         for (EtatArticle etatArticle : EtatArticle.values()) {
    //             System.out.println((etatArticle.ordinal() + 1) + "-" + etatArticle.name());
    //         }
    //         choix = scanner.nextInt();
    //     } while (choix <= 0 || choix > EtatArticle.values().length);
    //     return EtatArticle.values()[choix - 1];
    // }

   

}
