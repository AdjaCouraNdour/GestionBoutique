package com.ism.views;

import java.util.Scanner;

import com.ism.core.ViewImpl;
import com.ism.data.entities.Client;
import com.ism.data.services.list.ClientService;

public class ClientView extends ViewImpl<Client> {

    private ClientService clientService;


    public ClientView(Scanner scanner, ClientService clientService) {
        super(scanner);
        this.clientService = clientService;
    }


    @Override
    public Client create() {
        Client cl=new Client();
        scanner.nextLine();
        System.out.println("entre le surnom du client");
        cl.setSurname(scanner.nextLine());
        
        String num;
        do {
            System.out.println("entre le numero du client");
            num=scanner.nextLine();
        } while (clientService.getByNumero(num)!=null);
        cl.setTelephone(num); 

        System.out.println("entre l'address du client");
        cl.setAddress(scanner.nextLine());
        return cl;
    }


    public Client askClient() {

        System.out.println(clientService.show());
        System.out.println("veiller choisir le client");
        Client cl= clientService.getById(scanner.nextInt());
            if (cl!=null) {
               return cl;
            } else {
                System.out.println("ce client nexiste pas");
            return null;
            }
    }
   
}
