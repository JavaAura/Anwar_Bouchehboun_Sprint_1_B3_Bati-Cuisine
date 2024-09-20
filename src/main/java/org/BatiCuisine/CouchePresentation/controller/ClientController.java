package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CoucheMetier.Enum.EtatProjet;
import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.coucheServices.ClientService;
import org.BatiCuisine.coucheUtilitaire.InputValidator;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.util.List;
import java.util.Optional;


public class ClientController {


    public ClientController(){

    }
        public final ClientService clientService = new ClientService();

    public static Projet projet=new Projet();

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
    public Client createClient() {
        String nom = InputValidator.getStringInput("Entre Nom Client :");
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
//check Client for add projet
    public void checkOptionalClient(Client client){
        Optional<Client> clientOpt = Optional.ofNullable(client);

        clientOpt.ifPresentOrElse(p -> {
            boolean addProject = InputValidator.getBooleanInput("Voulez-vous ajouter un projet pour ce client? (1: Oui, 2: Non) :");

            if (addProject) {
             inputProjet(p);
                LoggerMessage.info("Projet registration successful.");


            } else {
                LoggerMessage.info("Aucun projet ajouté pour le client.");
            }
        }, () -> LoggerMessage.error("Aucun Client"));

    }
//create Project and client
    public  void  crateProject(){
        Client c= createClient();
        checkOptionalClient(c);


    }
    //Reche client add Client
    public void createProjectRecherche(){
        String nom =InputValidator.getStringInput("Entre NOM RECHERCHE :");
       Client client = new Client();
       client.setNom(nom);
      Client cl=  findClient(client);
      checkOptionalClient(cl);




    }
    public void menuClient(){
        System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"|Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?  : " + CostumColor.RESET);
        System.out.println("|Appuyez sur 1 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Ajouter un nouveau client    "      + CostumColor.RESET);
        System.out.println("|Appuyez sur 2 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Chercher un client existant  "      + CostumColor.RESET);
        System.out.println("|Appuyez sur 3 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"| _____Quitter__________"+ CostumColor.RESET);
        System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
        System.out.println("Choix => : " +CostumColor.PURPLE_BOLD_BRIGHT + " CHOIX "+ CostumColor.RESET);
    }





    //create Projet
    public Projet inputProjet(Client client){
        System.out.println(CostumColor.PURPLE_BOLD_BRIGHT +" Create Projet  "      + CostumColor.RESET);
     String nomProjet=InputValidator.getStringInput("Entrez le nom du projet:");
      double sourface= InputValidator.getDoubleInput("Entrez la surface de la cuisine (en m²) : :");
        projet.setNomProjet(nomProjet);
        projet.setSurface(sourface);
        projet.setEtatProjet(EtatProjet.ENCOURS);
        projet.setClient(client);
        clientService.createProjet(projet);
       return  projet;
    }

}


