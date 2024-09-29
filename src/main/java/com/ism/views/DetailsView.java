package com.ism.views;

import java.util.Scanner;
import com.ism.core.ViewImpl;
import com.ism.data.entities.Article;
import com.ism.data.entities.Details;
import com.ism.data.services.list.ArticleService;

public class DetailsView extends ViewImpl<Details> {
    
    private ArticleService artService;
    
    public DetailsView(Scanner scanner, ArticleService artService) {
        super(scanner);
        this.artService = artService;
    }

   

    @Override
    public Details create() {
        Details details =new Details();
        scanner.nextLine();
        Article art=askArticle();
        details.setArticle(art);
        int qte;
        do {
            System.out.println("entrer une qtedette pour larticle");
            qte=scanner.nextInt();
        } while ( qte<=0 || qte > art.getQteStock());
        details.setQteDette(qte);

        return details;
    }

 

    private Article askArticle(){
        System.out.println(artService.show());
        System.out.println("veiller choisir larticle");
        Article art=artService.getById(scanner.nextInt());
            if (art!=null) {
                return art;
            } else {
                System.out.println("le article nexiste pas");
                return null;
            }
    }
    
}
