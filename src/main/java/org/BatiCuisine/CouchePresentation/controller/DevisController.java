package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Devis;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.coucheServices.DevisServices;
import org.BatiCuisine.coucheUtilitaire.DateUtlis;
import org.BatiCuisine.coucheUtilitaire.InputValidator;

import java.time.LocalDate;

public class DevisController {
     public  DevisController(){

     }

      public DevisServices devisServices=new DevisServices();

     public  void  createDevis(Projet projet){
         LocalDate datevalidite= DateUtlis.getDateInput("Entrez la date de validité du devis (format : jj/mm/aaaa):");
         Devis devis = new Devis();
         devis.setAccepte(false);
         devis.setProjet(projet);
         devis.setDateValidite(datevalidite);
         System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"Devis enregistré avec succès !"+CostumColor.RESET);
         devisServices.createDevis(devis);

     }
}
