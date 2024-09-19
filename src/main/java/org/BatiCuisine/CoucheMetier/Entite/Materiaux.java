package org.BatiCuisine.CoucheMetier.Entite;

import org.BatiCuisine.CoucheMetier.Enum.TypeComposant;

public class Materiaux extends Composant {
    private  double coutTransport;
    private double coefficientQualite;
    private double quantite;
    private  double coutUnitaire;

    public Materiaux() {

    }

    public Materiaux(Integer id, String nom, TypeComposant typeComposant, double tauxTva,Projet projet, double coutTransport, double coefficientQualite, double quantite, double coutUnitaire) {
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
        double baseCost = coutUnitaire * quantite;
        double qualityAdjustment = baseCost * (coefficientQualite / 100.0);

        return baseCost + qualityAdjustment + coutTransport;


    }
}
