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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClientController {


    public ClientController(){

    }
        public final ClientService clientService = new ClientService();
        public  final ComposantServices mainoeuvreServices=new ComposantServices();
        public final ProjetServices projetServices= new ProjetServices();
        public static Projet projet=new Projet();
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
            boolean addProject = InputValidator.getBooleanInput("Voulez-vous ajouter un projet pour ce client? (1: YES, 2: Non) :");

            if (addProject) {
           Projet p1=  inputProjet(p);
                if(p1!=null){
               LoggerMessage.info("Projet registration successful.");
               createCpmposant(p1);
               calculerCoutTotal(p1);
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
    //Reche client add Client
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
//create Composant
    public void createCpmposant(Projet p) {
       if( InputValidator.askYesNoQuestion("Voulez-vous ajouter un Composant (yes - non)?")){
           mainOeuvre(p);
           matriEuax(p);
       }else{
          LoggerMessage.warn("Ajout refusé.");

       }

    }


//Composant
    public  void mainOeuvre(Projet p){
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------- Ajout de la main-d'œuvre ---------------- " + CostumColor.RESET);
        String continueInput;
        do {
            Mainœuvre mainœuvre = new Mainœuvre();
            String name = InputValidator.getStringInput("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste): ");
            mainœuvre.setNom(name);
            mainœuvre.setTypeComposant("MAINOEUVRE");
            mainœuvre.setProjet(p);

            double tauxHoraire = InputValidator.getDoubleInput("Entrez le taux horaire de cette main-d'œuvre (€/h): ");
            mainœuvre.setTauxHoraire(tauxHoraire);

            double heuresTravail = InputValidator.getDoubleInput("Entrez le nombre d'heures travaillées: ");
            mainœuvre.setHeuresTravail(heuresTravail);

            double productivite = InputValidator.getDouble("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité): ");
            mainœuvre.setProductiviteOuvrier(productivite);

            mainœuvres.add(mainœuvre);
            p.ajouterMainOuvrier(mainœuvre);
            System.out.println("Mainoeuvre ajouté avec succès !\n");

            continueInput = InputValidator.getYesNoInput("Voulez-vous ajouter un autre? (yes/no): ");

        } while (continueInput.equalsIgnoreCase("yes"));

        mainœuvres.forEach(mainœuvre -> mainoeuvreServices.createMainoeuvre(mainœuvre));

    }
    public void matriEuax(Projet p){
        String continueInput;
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------- Ajout de la matériau ---------------- " + CostumColor.RESET);

        do {
            Materiaux materiaux = new Materiaux();

            String name = InputValidator.getStringInput("Entrez le nom du matériau: ");
            materiaux.setNom(name);
            materiaux.setTypeComposant("MATERIAUX");
            materiaux.setProjet(p);

            double quantité = InputValidator.getDoubleInput("Entrez la quantité de ce matériau (en litres): ");
            materiaux.setQuantite(quantité);

            double unitaire = InputValidator.getDoubleInput("Entrez le coût unitaire de ce matériau (€/litre) : ");
            materiaux.setCoutUnitaire(unitaire);

            double transport = InputValidator.getDoubleInput("Entrez le coût de transport de ce matériau (€): ");
            materiaux.setCoutTransport(transport);
            double qualite= InputValidator.getDouble("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : 1.0:");
             materiaux.setCoefficientQualite(qualite);
            materiauxes.add(materiaux);
            p.ajouterMatriaux(materiaux);
            System.out.println("Materiaux ajouté avec succès !\n");

            continueInput = InputValidator.getYesNoInput("Voulez-vous ajouter un autre? (yes/no): ");

        } while (continueInput.equalsIgnoreCase("yes"));

        materiauxes.forEach(materiaux -> mainoeuvreServices.createMatrieaux(materiaux));
    }


    //Calculer
    public void calculerCoutTotal(Projet p) {

        System.out.println(p);
        double tauxTVA = 0;
        double margeBeneficiaire = 0;
        boolean appliquerTVA = InputValidator.getYesNoInput("Souhaitez-vous appliquer une TVA au projet ? (yes/no) : ").equalsIgnoreCase("yes");

        if (appliquerTVA) {
            tauxTVA = InputValidator.getDoubleInput("Entrez le pourcentage de TVA (%) : ") / 100.0;
        }

        boolean appliquerMargeB = InputValidator.getYesNoInput("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (yes/no) : ").equalsIgnoreCase("yes");

        if (appliquerMargeB) {
            margeBeneficiaire = InputValidator.getDoubleInput("Entrez le pourcentage de marge bénéficiaire (%) : ") / 100.0;
        }

        // Calculer le coût total des composants
        double coutTotalMateriaux = p.calculerTotalMatriaux();
        double coutTotalMainOeuvre = p.calculerTotalMainOeuvre();

        // Calculer avant TVA
        double totalAvantTVA = coutTotalMateriaux + coutTotalMainOeuvre;

        // Calculer avec TVA
        double totalAvecTVA = totalAvantTVA;
        if (appliquerTVA) {
            totalAvecTVA += totalAvantTVA * tauxTVA;
        }

        //final total
        double coutFinal = totalAvecTVA;
        if (appliquerMargeB) {
            coutFinal += coutFinal * margeBeneficiaire;
        }

        // Afficher les résultats
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ Résultat du Calcul ---------------- " + CostumColor.RESET);
        System.out.println(CostumColor.PURPLE_BOLD_BRIGHT+"Nom du projet ->  " + p.getNomProjet() + CostumColor.RESET);
        System.out.println("Surface  ->  "+CostumColor.PURPLE_BOLD_BRIGHT  +  p.getSurface() + CostumColor.RESET);
        System.out.println(CostumColor.PURPLE_BOLD_BRIGHT +  "Client :"+ CostumColor.RESET);
        System.out.println("Nom du  Client -> "+CostumColor.PURPLE_BOLD_BRIGHT+  p.getClient().getNom()+ CostumColor.RESET);
        System.out.println("telephone du Client -> "+CostumColor.PURPLE_BOLD_BRIGHT+  p.getClient().getTelephone()+ CostumColor.RESET);
        System.out.println("Adresse du Client -> "+CostumColor.PURPLE_BOLD_BRIGHT+  p.getClient().getAdrresse()+ CostumColor.RESET);

        p.afficherDetailsMateriaux();
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", coutTotalMateriaux);
        if (appliquerTVA) {
            System.out.printf("**Coût total des matériaux avec TVA : %.2f €**\n",p.calculerTotalMateriauxAvecTVA(tauxTVA));

        }
        p.afficherDetailsOuvriers();
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", coutTotalMainOeuvre);

        if (appliquerTVA) {
            System.out.printf("**Coût total de la main-d'œuvre Avec TVA : %.2f €**\n",  p.calculerTotalMainOeuvreAvecTVA(tauxTVA));

        }
        System.out.printf("**Coût total avant marge : %.2f €**\n", totalAvantTVA);
        double marge = 0;
        if (appliquerMargeB) {
             marge = coutFinal - totalAvecTVA;
            System.out.printf("**Marge bénéficiaire (%.0f%%) : %.2f €**\n", margeBeneficiaire * 100, marge);
        }

        System.out.printf("**Coût total final du projet : %.2f €**\n", coutFinal);
    }



}


