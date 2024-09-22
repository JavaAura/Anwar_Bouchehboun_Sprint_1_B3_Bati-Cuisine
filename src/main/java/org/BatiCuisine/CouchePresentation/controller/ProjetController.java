package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Projet;

import org.BatiCuisine.CoucheMetier.Enum.EtatProjet;
import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.coucheServices.ProjetServices;
import org.BatiCuisine.coucheUtilitaire.InputValidator;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.util.HashMap;

public class ProjetController {
    public ProjetController(){

    }

    public final ProjetServices projetServices = new ProjetServices();
    public final  ComposantController composantController=new ComposantController();
    HashMap<String, Projet> projetHashMap=new HashMap<>();


    public void getProjet() {
        projetHashMap = projetServices.Projet();
        if (projetHashMap.isEmpty()) {
            LoggerMessage.info("Projet Vide");
        } else {
            System.out.printf("%-10s| %-20s | %-30s | %-30s | %-15s%n",
                    "ID", "Projet", "Surface", "Etat Projet", "Client");
            System.out.print("-----------------------------------------------------------------------------------------------------------------\n");
            projetHashMap.values()
                    .forEach(Projet::Affiche);
        }
    }
    public void getByNameProjet(String name) {
        projetHashMap = projetServices.Projet();
        if (projetHashMap.isEmpty()) {
            LoggerMessage.info("Projet Vide");
        } else {
            System.out.printf("%-10s| %-20s | %-30s | %-30s | %-15s%n",
                    "ID", "Projet", "Surface", "Etat Projet", "Client");
            System.out.print("-----------------------------------------------------------------------------------------------------------------\n");
            projetHashMap.values().stream().filter(t->t.getNomProjet().equals(name))
                    .forEach(Projet::Affiche);
        }
    }

  public Projet findByName(String projet){
     return projetServices.finAll(projet);

    }


    public Projet inputProjet(Client client){
        System.out.println(CostumColor.PURPLE_BOLD_BRIGHT +" Create Projet  "      + CostumColor.RESET);
        String nomProjet=InputValidator.getStringInput("Entrez le nom du projet:");
        double sourface= InputValidator.getDoubleInput("Entrez la surface de la cuisine (en m²) : :");
       Projet projet=new Projet();
        projet.setNomProjet(nomProjet);
        projet.setSurface(sourface);
        projet.setEtatProjet(EtatProjet.ENCOURS);
        projet.setClient(client);
        projetServices.createProjet(projet);
        return  projet;
    }


    public void calculerCoutTotal(Projet p) {
        double tauxTVA = 0;
        double total =0;
        double margeBeneficiaire = 0;
        boolean appliquerTVA = InputValidator.getYesNoInput("Souhaitez-vous appliquer une TVA au projet ? (yes/no) : ").equalsIgnoreCase("yes");

        if (appliquerTVA) {
            tauxTVA = InputValidator.getDoubleInput("Entrez le pourcentage de TVA (%) : ") / 100.0;
            composantController.updateTva(tauxTVA,p.getId());
        }

        boolean appliquerMargeB = InputValidator.getYesNoInput("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (yes/no) : ").equalsIgnoreCase("yes");

        if (appliquerMargeB) {
            margeBeneficiaire = InputValidator.getDoubleInput("Entrez le pourcentage de marge bénéficiaire (%) : ") / 100.0;
            p.setMargeBeneficiaire(margeBeneficiaire);
        }

        // Calculer avant TVA  = Composant M + Maindoeuvre

        double totalAvantTVA = p.calculerTotalMatriaux() + p.calculerTotalMainOeuvre();

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

        // Afficher les résultats //
        detailsProjet(p);
        System.out.println(CostumColor.RED_BOLD_BRIGHT+"--- Détail des Coûts ---"+ CostumColor.RESET);
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ matériaux ---------------- " + CostumColor.RESET);
        p.afficherDetailsMateriaux();
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", p.calculerTotalMatriaux());
        if (appliquerTVA) {
            System.out.printf("**Coût total des matériaux avec  (%.0f%%) TVA : %.2f €**\n",tauxTVA*100,p.calculerTotalMateriauxAvecTVA(tauxTVA));

        }
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ main-d'œuvre ---------------- " + CostumColor.RESET);

        p.afficherDetailsOuvriers();
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", p.calculerTotalMainOeuvre());

        if (appliquerTVA) {
            System.out.printf("**Coût total de la main-d'œuvre Avec TVA : %.2f €**\n",  p.calculerTotalMainOeuvreAvecTVA(tauxTVA));

        }
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ Total_marge :  ---------------- " + CostumColor.RESET);

        System.out.printf("**Coût total avant marge : %.2f €**\n", totalAvantTVA);
        if (appliquerMargeB) {
            double  marge = coutFinal - totalAvecTVA;
            System.out.printf("**Marge bénéficiaire (%.0f%%) : %.2f €**\n", margeBeneficiaire * 100, marge);
        }
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ Total Final :  ---------------- " + CostumColor.RESET);

        boolean EsProfessionnel=p.getClient().isEstProfessionnel();

        if(EsProfessionnel){
            double nombre= 10;
            double discountAmount = coutFinal * (nombre / 100);
            System.out.printf("**Coût total final du projet : %.2f €**\n", coutFinal);
            total=coutFinal-discountAmount;
            System.out.printf("**Coût total final du projet discount Cliet Porfessionnel  (%.0f%%) : %.2f €**\n",nombre, coutFinal-discountAmount);
        } else {
            total=coutFinal;
            System.out.printf("**Coût total final du projet : %.2f €**\n", total);

        }
        // update champ for table Projet
        p.setCoutTotal(total);
        projetServices.updateProjet(p);

    }


    public void detailsProjet(Projet p){
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ Résultat du Calcul ---------------- " + CostumColor.RESET);
        System.out.println(CostumColor.PURPLE_BOLD_BRIGHT+"Nom du projet ->  " + p.getNomProjet() + CostumColor.RESET);
        System.out.println("Surface  ->  "+CostumColor.PURPLE_BOLD_BRIGHT  +  p.getSurface() + CostumColor.RESET);
        System.out.println(CostumColor.PURPLE_BOLD_BRIGHT +  "Client :"+ CostumColor.RESET);
        System.out.println("Nom du  Client -> "+CostumColor.PURPLE_BOLD_BRIGHT+  p.getClient().getNom()+ CostumColor.RESET);
        System.out.println("telephone du Client -> "+CostumColor.PURPLE_BOLD_BRIGHT+  p.getClient().getTelephone()+ CostumColor.RESET);
        System.out.println("Adresse du Client -> "+CostumColor.PURPLE_BOLD_BRIGHT+  p.getClient().getAdrresse()+ CostumColor.RESET);
    }

}
