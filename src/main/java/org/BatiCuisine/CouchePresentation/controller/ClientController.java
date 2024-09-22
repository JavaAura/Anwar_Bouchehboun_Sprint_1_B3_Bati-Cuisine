package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Materiaux;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CoucheMetier.Enum.EtatProjet;
import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.coucheServices.ClientService;
import org.BatiCuisine.coucheServices.ComposantServices;
import org.BatiCuisine.coucheServices.ProjetServices;
import org.BatiCuisine.coucheUtilitaire.InputValidator;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClientController {


    public ClientController(){

    }
        public final ClientService clientService = new ClientService();
        public  final ComposantServices mainoeuvreServices=new ComposantServices();
        public final ProjetController projetController= new ProjetController();
        public static  ComposantController composantController=new ComposantController();
        public static Projet p1=new Projet();
         List<Mainœuvre> mainœuvres = new ArrayList<>();
         List<Materiaux> materiauxes= new ArrayList<>();

    //MenuStart
    public void menuStart(){
        do {
            menuClient();
            int choix=InputValidator.getIntInput("Entre choix : ");
            switch (choix){
                case 1:
                    crateProject();
                    break;
                case 2:
                    createProjectRecherche();
                    break;
                case 3:  System.out.println(CostumColor.PURPLE_BOLD_BRIGHT + "-----_____Exit Menu_______------" + CostumColor.RESET);
                    return;
                default:
                    System.out.println(CostumColor.RED_BOLD_BRIGHT + "Invalid choice");
                    break;

            }
        }while (true);
    }
    //create Client
    public Client createClient() {
        String nom = InputValidator.getStringInput("Entre Nom Client :");
       if (checkClient(nom)){
           System.out.println("Daja create  client");
           createProjectRecherche();
           return null;
       }
        String adresse = InputValidator.getStringInput("Entre Adresse : ");
        String tele = InputValidator.getIntInputNombre("Entre Telephone +212|0 :");
        boolean isProfessional = InputValidator.getBooleanInput("Entre isProfessional (1-true,2-false) :");
        Client client =new Client();
        client.setNom(nom);
        client.setAdrresse(adresse);
        client.setEstProfessionnel(isProfessional);
        client.setTelephone(tele);

        LoggerMessage.info("Client registration successful.");
     return    clientService.createClient(client);

    }

    //findClient
    public Client findClient(Client client){
        List<Client> clients = clientService.Client();
        Optional<Client> opClient = clients.stream()
                .filter(t -> t.getNom().equals(client.getNom()))
                .findFirst();
        if (opClient.isPresent()) {
            Client foundClient = opClient.get();
            System.out.println(foundClient);
            return foundClient;
        } else {
            LoggerMessage.error("Aucun Client");
            return null;
        }

    }

    // check Client daja create
    public boolean  checkClient(String  client){
        List<Client>clients=    clientService.Client();
    Optional<Client> opClient= clients.stream().filter(c->c.getNom().equals(client)).findFirst();
        return opClient.isPresent();    }

//check Client for add projet avec composant et calculer
    public void checkOptionalClient(Client client){
        Optional<Client> clientOpt = Optional.ofNullable(client);

        clientOpt.ifPresentOrElse(p -> {
            boolean addProject = InputValidator.getBooleanInput("Voulez-vous ajouter un projet pour ce client? (1: YES, 2: Non) :");

            if (addProject) {
      p1=  projetController.inputProjet(p);
                if(p1!=null){
                    LoggerMessage.info("Projet registration successful.");
                 composantController.createCpmposant(p1);
                 projetController.calculerCoutTotal(p1);
               }else{
                    LoggerMessage.info("Projet Aucun.");
                     menuStart();
                }

            } else {
                LoggerMessage.info("Aucun projet ajouté pour le client.");
            }
        }, () -> LoggerMessage.error("Aucun Client"));

    }


//create Project and client
    public  void  crateProject(){
        System.out.println(CostumColor.PURPLE_BOLD_BRIGHT +" Ajouter Client  :  "      + CostumColor.RESET);
        Client c= createClient();
        checkOptionalClient(c);
    }


    //Recherche  client et ajouter  Client
    public void createProjectRecherche(){
        System.out.println(CostumColor.PURPLE_BOLD_BRIGHT +" Recherche Client   "      + CostumColor.RESET);
        String nom =InputValidator.getStringInput("Entre NOM RECHERCHE :");
       Client client = new Client();
       client.setNom(nom);
      Client cl=  findClient(client);
      if(cl!=null){
          checkOptionalClient(cl);
      }else {
          crateProject();
      }

    }


    //Menu
    public void menuClient(){
        System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"|Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?  : " + CostumColor.RESET);
        System.out.println("|Appuyez sur 1 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Ajouter un nouveau client    "      + CostumColor.RESET);
        System.out.println("|Appuyez sur 2 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Chercher un client existant  "      + CostumColor.RESET);
        System.out.println("|Appuyez sur 3 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"| _____Quitter__________"+ CostumColor.RESET);
        System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
        System.out.println("Choix => : " +CostumColor.PURPLE_BOLD_BRIGHT + " CHOIX "+ CostumColor.RESET);
    }



}


