package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Materiaux;
import org.BatiCuisine.coucheServices.ComposantServices;

import java.util.HashMap;


public class ComposantController {

    HashMap<String, Mainœuvre> map=new HashMap<>();
    HashMap<String, Materiaux> materiauxHashMap= new HashMap<>();
    public static ComposantServices composantServices =new ComposantServices();
    public  ComposantController(){

    }


    public void getAllMainoeuvre(String nomProject){
        map=composantServices.getAllMainoeuvre();
            System.out.printf("%-10s| %-20s | %-20s | %-20s | %-20s | %-20s | %-15s%n",
                    "ID", "Nom","type_composant", "tauxhoraire", " heurestravail", "productivite","nom_projet");
            System.out.print("------------------------------------------------------------------------------------------------------------------------------------------\n");
            map.values().stream().filter(p->p.getProjet().getNomProjet().equals(nomProject))
                    .forEach(Mainœuvre::affiche);

    }

    public void getAllMateriaux(String nomProject){
        materiauxHashMap=composantServices.getAllMateriaux();
        System.out.printf("%-10s| %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-15s%n",
                "ID", "Nom","type_composant", "nom_projet", " Quantite", "Coefficient Qualite","Cout Transport","Cout Unitaire");
        System.out.print("--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        materiauxHashMap.values().stream().filter(p->p.getProjet().getNomProjet().equals(nomProject))
                .forEach(Materiaux::affiche);
    }

}
