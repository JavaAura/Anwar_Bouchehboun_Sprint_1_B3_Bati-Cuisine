package org.BatiCuisine.CouchePresentation.ConsoleUi;

import org.BatiCuisine.CouchePresentation.CostumColor;

public class ConsoleUi {

    public ConsoleUi(){

    }


    public void menuPrancipal(){
        do {
            System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
            System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===" + CostumColor.RESET);
            System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"=== === Menu Principal ===  ===" + CostumColor.RESET);
            System.out.println("|Appuyez sur 1 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Créer un nouveau projet    "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 2 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Afficher les projets existants  "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 3 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Calculer le coût d'un projet    "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 4 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"| _____Quitter__________"+ CostumColor.RESET);
            System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
            System.out.println("Choix => : " +CostumColor.PURPLE_BOLD_BRIGHT + " CHOIX "+ CostumColor.RESET);

        }while (true);
    }



}
