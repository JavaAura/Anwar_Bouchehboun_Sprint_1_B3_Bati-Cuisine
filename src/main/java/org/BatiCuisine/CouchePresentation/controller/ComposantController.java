package org.BatiCuisine.CouchePresentation.controller;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Materiaux;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.CouchePresentation.CostumColor;
import org.BatiCuisine.coucheServices.ComposantServices;
import org.BatiCuisine.coucheUtilitaire.InputValidator;
import org.BatiCuisine.coucheUtilitaire.LoggerMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComposantController {

         public ComposantController(){

         }

    HashMap<String, Mainœuvre> map= new HashMap<>();
    HashMap<String, Materiaux> materiauxHashMap= new HashMap<>();
    List<Mainœuvre> mainœuvres = new ArrayList<>();
    List<Materiaux> materiauxes= new ArrayList<>();
    public  final ComposantServices mainoeuvreServices=new ComposantServices();
    public static ComposantServices composantServices =new ComposantServices();

    // search mainOeuvre
    public void getAllMainoeuvre(String nomProject){

        map=composantServices.getAllMainoeuvre();
        if (!map.isEmpty()) {
            boolean projectExists = map.values().stream()
                    .anyMatch(p -> p.getProjet().getNomProjet().equals(nomProject));
            if (projectExists) {
                System.out.printf("%-10s| %-20s | %-20s | %-20s | %-20s | %-20s | %-15s%n",
                        "ID", "Nom", "type_composant", "heuresTravail ", "productivite ", "nom_projet", " tauxhoraire ");
                System.out.print("------------------------------------------------------------------------------------------------------------------------------------------\n");
                map.values().stream()
                        .filter(p -> p.getProjet().getNomProjet().equals(nomProject))
                        .forEach(Mainœuvre::affiche);
                double totalSum = map.values().stream()
                        .filter(p -> p.getProjet().getNomProjet().equals(nomProject))
                        .mapToDouble(Mainœuvre::calculerTotal)
                        .sum();
                System.out.printf(CostumColor.RED_BOLD_BRIGHT + "--- Total Cost Main d'œuvre : %.2f%n" + CostumColor.RESET, totalSum);
            }else{
                System.out.println("nom de Projet et vide");
            }
        }
}
  // search Materiaux
    public void getAllMateriaux(String nomProject){
        materiauxHashMap=composantServices.getAllMateriaux();
        if (!materiauxHashMap.isEmpty()) {
            boolean projectExists = materiauxHashMap.values().stream()
                    .anyMatch(p -> p.getProjet().getNomProjet().equals(nomProject));
            if (projectExists) {

                System.out.printf("%-10s| %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-15s%n",
                        "ID", "Nom", "type_composant", "nom_projet", " Quantite", "Coefficient Qualite", "Cout Transport", "Cout Unitaire");
                System.out.print("--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                materiauxHashMap.values().stream()
                        .filter(p -> p.getProjet().getNomProjet().equals(nomProject))
                        .forEach(Materiaux::affiche);
                double totalSum = materiauxHashMap.values().stream()
                        .filter(p -> p.getProjet().getNomProjet().equals(nomProject))
                        .mapToDouble(Materiaux::calculerTotal)
                        .sum();
                System.out.printf(CostumColor.RED_BOLD_BRIGHT + "--- Total Cost Materiaux : %.2f%n" + CostumColor.RESET, totalSum);
            }else {
                System.out.println("nom de Projet et vide");

            }
        }
    }


    //add mainOeuvre
    public  void mainOeuvre(Projet p){
        if (p==null){
            return;
        }
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------- Ajout de la main-d'œuvre ---------------- " + CostumColor.RESET);
        String continueInput;
        do {
            Mainœuvre mainœuvre = new Mainœuvre();
            String name = InputValidator.getStringInput("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste): ");
            mainœuvre.setNom(name);
            mainœuvre.setTypeComposant("MAINOEUVRE");
            mainœuvre.setProjet(p);

            double tauxHoraire = InputValidator.getDoubleInput("Entrez le taux horaire de cette main-d'œuvre (€/h): ");
            mainœuvre.setTauxHoraire(tauxHoraire);

            double heuresTravail = InputValidator.getDoubleInput("Entrez le nombre d'heures travaillées: ");
            mainœuvre.setHeuresTravail(heuresTravail);

            double productivite = InputValidator.getDouble("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité): ");
            mainœuvre.setProductiviteOuvrier(productivite);

            mainœuvres.add(mainœuvre);
            p.ajouterMainOuvrier(mainœuvre);
            System.out.println("Mainoeuvre ajouté avec succès !\n");

            continueInput = InputValidator.getYesNoInput("Voulez-vous ajouter un autre? (yes/no): ");

        } while (continueInput.equalsIgnoreCase("yes"));

        // mainœuvres.forEach(mainœuvre -> mainoeuvreServices.createMainoeuvre(mainœuvre));

    }

    //add matrieaux
    public void matriEuax(Projet p){
        if (p==null){
            return;
        }
        String continueInput;
        System.out.println(CostumColor.BLUE_BOLD_BRIGHT+"------- Ajout de la matériau ---------------- " + CostumColor.RESET);

        do {
            Materiaux materiaux = new Materiaux();

            String name = InputValidator.getStringInput("Entrez le nom du matériau: ");
            materiaux.setNom(name);
            materiaux.setTypeComposant("MATERIAUX");
            materiaux.setProjet(p);

            double quantité = InputValidator.getDoubleInput("Entrez la quantité de ce matériau (en litres): ");
            materiaux.setQuantite(quantité);

            double unitaire = InputValidator.getDoubleInput("Entrez le coût unitaire de ce matériau (€/litre) : ");
            materiaux.setCoutUnitaire(unitaire);

            double transport = InputValidator.getDoubleInput("Entrez le coût de transport de ce matériau (€): ");
            materiaux.setCoutTransport(transport);
            double qualite= InputValidator.getDouble("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : 1.0:");
            materiaux.setCoefficientQualite(qualite);
            materiauxes.add(materiaux);
            p.ajouterMatriaux(materiaux);
            System.out.println("Materiaux ajouté avec succès !\n");

            continueInput = InputValidator.getYesNoInput("Voulez-vous ajouter un autre? (yes/no): ");

        } while (continueInput.equalsIgnoreCase("yes"));

        // materiauxes.forEach(materiaux -> mainoeuvreServices.createMatrieaux(materiaux));
    }

    public void createCpmposant(Projet p) {
        if( InputValidator.askYesNoQuestion("Voulez-vous ajouter un Composant (yes - non)?")){
            mainOeuvre(p);
            matriEuax(p);
        }else{
            LoggerMessage.warn("Ajout refusé.");

        }

    }


    public void updateTva(double tva,int id){
        composantServices.updataTva(tva,id);
    }

    public  List<Mainœuvre> listeMainOeuvre(Projet projet){

        return composantServices.getAllMainoeuvreProject(projet);

    }
    public List<Materiaux> listeMateriaux(Projet projet){
        return  composantServices.getAllMateriauxProject(projet);
    }

}
