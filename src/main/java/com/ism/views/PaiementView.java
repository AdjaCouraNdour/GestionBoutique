package com.ism.views;

import java.util.Scanner;

import com.ism.core.ViewImpl;
import com.ism.data.entities.Client;
import com.ism.data.entities.Dette;
import com.ism.data.entities.Paiement;
import com.ism.data.services.list.ClientService;
import com.ism.data.services.list.DetteService;


public class PaiementView extends ViewImpl<Paiement> {

    private ClientService clientService;
    private DetteService detteService;

    public PaiementView(Scanner scanner, ClientService clientService,
            DetteService detteService) {
        super(scanner);
        this.clientService = clientService;
        this.detteService = detteService;
    }

    @Override
    public Paiement create() {
        Paiement pmt=new Paiement();
        System.out.println(clientService.show());
        System.out.println("entrer le client");
        Client cl= clientService.getById(scanner.nextInt());
        if (cl!=null) {
            System.out.println(detteService.show());
            System.out.println("entrer le la dette");
            Dette dette= detteService.getById(scanner.nextInt());
            if (dette!=null) {
                int m;
                do {
                    System.out.println("entrer le montant  paiyer");
                    m=scanner.nextInt();
                } while ( m<=0 || m > dette.getMontantRestant());
                dette.setMontantVerse(dette.getMontantVerse()+m);
                pmt.setMontant(m);
                // dette.setMontantRestant(dette.getMontantRestant()-pmt.getMontant());
                dette.setListePaiement(pmt);
                pmt.setDette(dette);
                
            return pmt;
            } else {
                System.out.println("cette dette n'existe pas "); 
                return null;
            }
        } else {
            System.out.println("ce client n'existe pas ");
            return null;
        }
    
    }
    
}
