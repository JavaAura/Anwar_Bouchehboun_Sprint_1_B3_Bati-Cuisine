package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Devis;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.coucheServices.ComposantServices;
import org.BatiCuisine.coucheServices.DevisServices;
import org.BatiCuisine.coucheUtilitaire.DateUtlis;
import org.BatiCuisine.coucheUtilitaire.InputValidator;

import java.time.LocalDate;

public class DevisController {
     public  DevisController(){

     }
      public ComposantServices composantServices= new ComposantServices();
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
    public void devisTotal(Projet p) {
         double tva;
        double margeBeneficiaire;
        double coutFinal= p.getCoutTotal();
         tva=composantServices.getTva(p);
         margeBeneficiaire=p.getMargeBeneficiaire();
         //total avant
        double totalAvantTVA = p.calculerTotalMatriaux() + p.calculerTotalMainOeuvre();
        detailsProjet(p);
        System.out.println(CostumColor.RED_BOLD_BRIGHT+"--- Détail des Coûts ---"+ CostumColor.RESET);
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ matériaux ---------------- " + CostumColor.RESET);
        //materiaux
        p.afficherDetailsMateriaux();

        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", p.calculerTotalMatriaux());
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ main-d'œuvre ---------------- " + CostumColor.RESET);
       //Ouviers
        p.afficherDetailsOuvriers();

        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", p.calculerTotalMainOeuvre());


        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ Total Final :  ---------------- " + CostumColor.RESET);
     //accepte Tva
      if(tva>0){
          System.out.printf("**Coût total des matériaux avec  (%.0f%%) TVA : %.2f €**\n",tva*100,p.calculerTotalMateriauxAvecTVA(tva));
          System.out.printf("**Coût total de la  main-d'œuvre avec  (%.0f%%) TVA : %.2f €**\n",tva*100,p.calculerTotalMainOeuvreAvecTVA(tva));
      }
        System.out.printf("**Coût total avant marge : %.2f €**\n", totalAvantTVA);
//accpete margeBeneficiaire
        if (margeBeneficiaire > 0) {
            System.out.printf("**Coût total avec marge : %.2f €**\n", p.calculerTotalMateriauxAvecTVA(tva)+p.calculerTotalMainOeuvreAvecTVA(tva));

            System.out.printf("**Marge bénéficiaire (%.0f%%) : %.2f €**\n", margeBeneficiaire * 100, coutFinal-(p.calculerTotalMateriauxAvecTVA(tva)+p.calculerTotalMainOeuvreAvecTVA(tva)));
        }
        System.out.printf("**Coût total final du projet : %.2f €**\n",coutFinal);


       boolean accepte = InputValidator.getYesNoInput("Souhaitez-vous Accpete le devis  ? (yes/no) : ").equalsIgnoreCase("yes");
        if (accepte){
            Devis d= new Devis();
            d.setProjet(p);
            devisServices.accpeteDevis(d);
            System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ Accpete Devis ---------------- " + CostumColor.RESET);
        }else {
            System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------------ Refuser Devis ---------------- " + CostumColor.RESET);

        }

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
