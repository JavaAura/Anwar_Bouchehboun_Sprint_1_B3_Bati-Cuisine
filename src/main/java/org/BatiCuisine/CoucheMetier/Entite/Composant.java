package org.BatiCuisine.CoucheMetier.Entite;

import org.BatiCuisine.CoucheMetier.Enum.TypeComposant;

public abstract class Composant {
    private  Integer id;
    private  String nom;
    private String typeComposant;
    private  double tauxTva;
    private Projet projet;


     public abstract  double calculerTotal();

    public Composant() {
    }

    public Composant(Integer id, String nom, String typeComposant, double tauxTva,Projet projet) {
        this.id = id;
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTva = tauxTva;
        this.projet=projet;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(double tauxTva) {
        this.tauxTva = tauxTva;
    }
}
