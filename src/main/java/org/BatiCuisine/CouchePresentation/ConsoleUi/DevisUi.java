package org.BatiCuisine.CouchePresentation.ConsoleUi;

import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.CouchePresentation.controller.ComposantController;
import org.BatiCuisine.CouchePresentation.controller.DevisController;
import org.BatiCuisine.CouchePresentation.controller.ProjetController;
import org.BatiCuisine.coucheUtilitaire.InputValidator;

public class DevisUi {

    public DevisUi(){

    }

    public void menuDevis(){
        System.out.println(CostumColor.BROWN_BACKGROUND+CostumColor.WHITE_BOLD_BRIGHT+"-------------------------Affiche Devis Projet ---------------------------------- "+ CostumColor.RESET);
        String s=   InputValidator.getStringInput("Entre projet Recherche :");
        Projet p= new ProjetController().findByName(s);
        new ComposantController().listeMainOeuvre(p);
        new  ComposantController().listeMateriaux(p);
        new DevisController().devisTotal(p);
    }
}
