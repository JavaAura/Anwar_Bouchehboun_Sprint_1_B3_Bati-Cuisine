package org.BatiCuisine.CouchePresentation.ConsoleUi;

import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.CouchePresentation.controller.ClientController;
import org.BatiCuisine.coucheUtilitaire.InputValidator;

public class ConsoleUi {

    public ConsoleUi(){

    }



    public void menuPrancipal(){
        do {
            System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
            System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===" + CostumColor.RESET);
            System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"=== === Menu Principal ===  ===" + CostumColor.RESET);
            System.out.println("|Appuyez sur 1 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Créer un nouveau projet    "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 2 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Affiche   projets   "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 3 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Affcihe  le coût de devis  d'un projet    "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 4 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"| _____Quitter__________"+ CostumColor.RESET);
            System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
            System.out.println("Choix => : " +CostumColor.PURPLE_BOLD_BRIGHT + " CHOIX "+ CostumColor.RESET);
             int choix= InputValidator.getIntInput("Entre votre Choix :");
             switch (choix){
                 case 1:
                     new ClientController().menuStart();
                     break;
                 case 2:
                     new ProjetUi().menuProjet();
                     break;
                 case 3:
                       new DevisUi().menuDevis();
                     break;
                 case 4: System.out.println(CostumColor.PURPLE_BOLD_BRIGHT + "-----_____Quitter_______------" + CostumColor.RESET);
                     return;
                 default:
                     System.out.println(CostumColor.RED_BOLD_BRIGHT + "Invalid choice");
                     break;
             }
        }while (true);
    }



}
