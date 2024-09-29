package com.ism.views;

import java.util.Scanner;
import com.ism.core.ViewImpl;
import com.ism.data.entities.Client;
import com.ism.data.entities.User;
import com.ism.data.enums.UserRole;
import com.ism.data.services.list.ClientService;
import com.ism.data.services.list.UserService;

public class UserView extends ViewImpl<User>  {

    private ClientService clientService;
    private UserService userService;
    private ClientView clientView;

    public UserView(Scanner scanner, ClientService clientService, UserService userService, ClientView clientView) {
        super(scanner);
        this.clientService = clientService;
        this.userService = userService;
        this.clientView = clientView;
    }

    @Override
    public User create() {
        User user=new User();
        System.out.println("1-pour un client existant");
        System.out.println("2-pour un nouveau client ");
        if (choiceToContinue()==1) {
            System.out.println("veiller choisir le client");
            Client cl=clientView.askClient();
            if (cl.getUser()==null) {
                user=createUser();
                cl.setUser(user);
                user.setClient(cl);
                user.isUserEtat(); 
                return user;
            }else{
                System.out.println("ce client a deja un compte");
                return null;
            }   
        } else {
            Client cl= clientView.create();
            clientService.save(cl);
            user=createUser();
            cl.setUser(user);
            user.setClient(cl);
            user.isUserEtat();
            return user;
        }
    }

    private UserRole saisieRoleCompte() {
        int choix;
        do {
            System.out.println("veuillez selectionner le type d'étudiant");
            for (UserRole roleCompte : UserRole.values()) {
                System.out.println((roleCompte.ordinal() + 1) + "-" + roleCompte.name());
            }
            choix = scanner.nextInt();
        } while (choix <= 0 || choix > UserRole.values().length);
        return UserRole.values()[choix - 1];
    }
    
    public void listerParRole(){
        System.out.println(userService.getBy(choiceUserRole()));
        
    }

    private UserRole choiceUserRole() {
        int choix;
        do {
            System.out.println("veuillez selectionner le role du user");
            for (UserRole userRole : UserRole.values()) {
                System.out.println((userRole.ordinal() + 1) + "-" + userRole.name());
            }
            choix = scanner.nextInt();
        } while (choix <= 0 || choix > UserRole.values().length);
        return UserRole.values()[choix - 1];
    }

    private User createUser(){
        User user =new User();
        // user.setUserEtat(UserEtat.Actif);
        scanner.nextLine();
        System.out.println("entrer l'email ");
        user.setEmail(scanner.nextLine());
        System.out.println("entrer le login");
        user.setLogin(scanner.nextLine());
        System.out.println("entrer le password ");
        user.setPassword(scanner.nextLine()); 
        System.out.println("voulez vous lui ajouter un role");
            if (askToContinue()==1) {
                user.setUserRole(saisieRoleCompte());
            } 
        return user;
    }
}
