package org.BatiCuisine;

import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CouchePresentation.ConsoleUi.ConsoleUi;
import org.BatiCuisine.CouchePresentation.controller.ClientController;
import org.BatiCuisine.CouchePresentation.controller.ComposantController;
import org.BatiCuisine.CouchePresentation.controller.ProjetController;
import org.BatiCuisine.coucheUtilitaire.InputValidator;


public class Main {


    public static void main(String[] args) {

   /*    ClientController p=new ClientController();
        p.menuStart();

    */

   /*  String s=   InputValidator.getStringInput("Entre projet Recherche :");*/
  /* Projet  p= new ProjetController().findByName(s);
        new ClientController().createCpmposant(p);
        new ClientController().calculerCoutTotal(p);*/
     /*  new ConsoleUi().menuPrancipal();*/
      new  ProjetController().getProjet();

    }
    }