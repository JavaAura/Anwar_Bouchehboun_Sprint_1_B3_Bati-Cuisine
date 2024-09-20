package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Projet;

import org.BatiCuisine.coucheServices.ProjetServices;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.util.HashMap;

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
            projetHashMap.values().stream()
                    .forEach(Projet::Affiche);
        }
    }



}
