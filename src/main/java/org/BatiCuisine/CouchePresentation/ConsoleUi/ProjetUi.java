package org.BatiCuisine.CouchePresentation.ConsoleUi;

import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.CouchePresentation.controller.ComposantController;
import org.BatiCuisine.CouchePresentation.controller.ProjetController;
import org.BatiCuisine.coucheUtilitaire.InputValidator;


public class ProjetUi {
    public  ProjetUi(){

    }
    public static ComposantController composantController = new ComposantController();
    public  static  ProjetController projetController=new ProjetController();
    public void menuProjet(){
        do {
            System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
            System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===" + CostumColor.RESET);
            System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"=== === Menu Projet ===  ===" + CostumColor.RESET);
            System.out.println("|Appuyez sur 1 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Afficher les projets    "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 2 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Recherche par Nom projet   "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 3 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"|• Affiche  Composant de projet    "      + CostumColor.RESET);
            System.out.println("|Appuyez sur 4 pour" +CostumColor.PURPLE_BOLD_BRIGHT +"| _____Quitter__________"+ CostumColor.RESET);
            System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"----------------------------------------------------------- "+ CostumColor.RESET);
            System.out.println("Choix => : " +CostumColor.PURPLE_BOLD_BRIGHT + " CHOIX "+ CostumColor.RESET);
            int choix= InputValidator.getIntInput("Entre votre Choix :");
            switch (choix){
                case 1:
                    new ProjetController().getProjet();
                    break;
                case 2:
                    getByName();
                    break;
                case 3:
                    composantAll();
                    break;
                case 4: System.out.println(CostumColor.PURPLE_BOLD_BRIGHT + "-----_____Quitter_______------" + CostumColor.RESET);
                    return;
                default:
                    System.out.println(CostumColor.RED_BOLD_BRIGHT + "Invalid choice");
                    break;
            }
        }while (true);
    }


    public void getByName(){
        String nom= InputValidator.getStringInput("Entre Nom De Projet :");
    projetController.getByNameProjet(nom);

    }

    public  void composantAll(){
        String nom= InputValidator.getStringInput("Entre Nom De Projet :");
        System.out.println(CostumColor.RED_BOLD_BRIGHT+"---   Composaant  ---"+ CostumColor.RESET);
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ matériaux ---------------- " + CostumColor.RESET);
        composantController.getAllMateriaux(nom);
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ Main d'oeuvre ---------------- " + CostumColor.RESET);
       composantController.getAllMainoeuvre(nom);

    }
}
