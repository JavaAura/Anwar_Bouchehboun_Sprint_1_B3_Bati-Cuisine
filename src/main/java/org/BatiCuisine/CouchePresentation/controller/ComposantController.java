package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.coucheServices.ComposantServices;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.util.HashMap;
import java.util.Optional;

public class ComposantController {

    HashMap<String, Mainœuvre> map=new HashMap<>();
    public static ComposantServices composantServices =new ComposantServices();
    public  ComposantController(){

    }


    public void getAllMainoeuvre(String nomProject){
        map=composantServices.getAll();
            System.out.printf("%-10s| %-20s | %-20s | %-20s | %-20s | %-20s | %-15s%n",
                    "ID", "Nom","type_composant", "tauxhoraire", " heurestravail", "productivite","nom_projet");
            System.out.print("------------------------------------------------------------------------------------------------------------------------------------------\n");
            map.values().stream().filter(p->p.getProjet().getNomProjet().equals(nomProject))
                    .forEach(Mainœuvre::affiche);


    }


}
