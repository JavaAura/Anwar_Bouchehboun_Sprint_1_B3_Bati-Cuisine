package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Projet;

import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.coucheServices.ProjetServices;
import org.BatiCuisine.coucheUtilitaire.InputValidator;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.util.HashMap;
import java.util.List;

public class ProjetController {
    public ProjetController(){

    }

    public final ProjetServices projetServices = new ProjetServices();
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

  public Projet findByName(String projet){
     return projetServices.finAll(projet);

    }

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
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ matériaux ---------------- " + CostumColor.RESET);
        p.afficherDetailsMateriaux();
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", coutTotalMateriaux);
        if (appliquerTVA) {
            System.out.printf("**Coût total des matériaux avec TVA : %.2f €**\n",p.calculerTotalMateriauxAvecTVA(tauxTVA));

        }
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ main-d'œuvre ---------------- " + CostumColor.RESET);

        p.afficherDetailsOuvriers();
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", coutTotalMainOeuvre);

        if (appliquerTVA) {
            System.out.printf("**Coût total de la main-d'œuvre Avec TVA : %.2f €**\n",  p.calculerTotalMainOeuvreAvecTVA(tauxTVA));

        }
        System.out.printf("**Coût total avant marge : %.2f €**\n", totalAvantTVA);
        if (appliquerMargeB) {
            double  marge = coutFinal - totalAvecTVA;
            System.out.printf("**Marge bénéficiaire (%.0f%%) : %.2f €**\n", margeBeneficiaire * 100, marge);
        }

        System.out.printf("**Coût total final du projet : %.2f €**\n", coutFinal);
    }

}
