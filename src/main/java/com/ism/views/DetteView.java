package com.ism.views;

import java.util.Scanner;
import com.ism.core.ViewImpl;
import com.ism.data.entities.Article;
import com.ism.data.entities.Client;
import com.ism.data.entities.Details;
import com.ism.data.entities.Dette;
import com.ism.data.enums.TypeDette;
import com.ism.data.services.list.ClientService;
import com.ism.data.services.list.DetteService;


public class DetteView extends ViewImpl<Dette>{

    private DetailsView detailsView ;
    private ClientService clientService;
    private DetteService detteService;
    
    public DetteView(Scanner scanner, DetailsView detailsView, ClientService clientService,
            DetteService detteService) {
        super(scanner);
        this.detailsView = detailsView;
        this.clientService = clientService;
        this.detteService = detteService;
    }


    @Override
    public Dette create() {
        Dette dette =new Dette();
        scanner.nextLine();
        Client cl = askClient();
        Details details= detailsView.create();
            dette.setTypeDette(TypeDette.nonSolde);
            details.setDette(dette);
            cl.setListeDette(dette); 
            dette.setClient(cl);          
            dette.setListeDetails(details); 
            Article art=details.getArticle();
            dette.setMontant(details.getQteDette()*art.getPrix());
            art.setQteStock(art.getQteStock()-details.getQteDette());
            dette.setMontantVerse(0);
            dette.setMontantRestant(dette.getMontant()-dette.getMontantVerse());
        return dette ;
    }


    private Client askClient(){
        System.out.println(clientService.show());
        System.out.println("veiller choisir le client");
        Client cl= clientService.getById(scanner.nextInt());
            if (cl!=null) {
                return cl;
            } else {
                System.out.println("le client nexiste pas");
                return null;
            }
    }
    public void listerDetteClient(){
        Client cl=askClient();
        System.out.println(cl.getListeDette());
       
    }

    public Dette archiverDetteSolde(){
        Client cl=askClient();
        if (cl!=null) {
            Dette dette=askdetteSolde();
            if (dette.isArchiver()) {
                System.out.println("la dette est deja archiver");  
            }else{
                dette.setArchiver(true);
                System.out.println("archiver avec succes");
            } 
            return dette; 
        } else {
            return null;
        } 
    }

    private Dette askdetteSolde(){
        System.out.println(detteService.getBy(TypeDette.solde));
        System.out.println("indiquer la dette");
        Dette dette= detteService.getById(scanner.nextInt());
            if (dette!=null) {
                return dette;
            } else {
                System.out.println("le dette nexiste pas");
                return null;
            }
    }
}
