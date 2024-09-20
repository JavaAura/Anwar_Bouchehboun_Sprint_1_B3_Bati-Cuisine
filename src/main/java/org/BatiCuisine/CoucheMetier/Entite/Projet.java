package org.BatiCuisine.CoucheMetier.Entite;

import org.BatiCuisine.CoucheMetier.Enum.EtatProjet;


public class Projet {
    private Integer id;
    private String nomProjet;
    private double margeBeneficiaire;
    private  double coutTotal;
   private EtatProjet etatProjet;
   private Client client;
   private double surface;

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public Projet(Integer id, String nomProjet, double margeBeneficiaire, double coutTotal, EtatProjet etatProjet, Client client,double surface) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
        this.surface=surface;
    }
    public Projet(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void Affiche(){

        System.out.printf("%-10s| %-20s | %-30s | %-30s | %-15s%n", getId(), getNomProjet(),getSurface(), getEtatProjet(), getClient().getNom());    }
    @Override
    public String toString() {
        return "Projet :" + '\n'+
                "nomProjet=" + nomProjet + '\n' +
                "etatProjet=" + etatProjet+ '\n' +
                "client=" + client.getNom() + '\n'+
                "surface=" + surface + '\n'
                ;
    }
}
