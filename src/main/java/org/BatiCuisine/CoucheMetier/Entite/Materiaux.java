package org.BatiCuisine.CoucheMetier.Entite;

public class Materiaux extends Composant {
    private  double coutTransport;
    private double coefficientQualite;
    private double quantite;
    private  double coutUnitaire;

    public Materiaux() {

    }

    public Materiaux(Integer id, String nom, String typeComposant, double tauxTva,Projet projet, double coutTransport, double coefficientQualite, double quantite, double coutUnitaire) {
        super(id, nom, typeComposant, tauxTva,projet);
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
        this.quantite = quantite;
        this.coutUnitaire = coutUnitaire;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    @Override
    public double calculerTotal() {
        double coût_de_base = coutUnitaire * quantite * coefficientQualite;

        return coût_de_base + coutTransport;

    }

    @Override
    public void affiche() {
        System.out.printf("%-10s| %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-15s%n", getId(), getNom(),getTypeComposant(), getProjet().getNomProjet(),getQuantite(), getCoefficientQualite(),getCoutTransport(),getCoutUnitaire());

    }

    @Override
    public void totalComposant() {
        System.out.printf("%-10s| %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-15s%n", getId(), getNom(),getTypeComposant(), getProjet().getNomProjet(),getQuantite(), getCoefficientQualite(),getCoutTransport(),getCoutUnitaire());

    }
}
