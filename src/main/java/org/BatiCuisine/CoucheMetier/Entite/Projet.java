package org.BatiCuisine.CoucheMetier.Entite;

import org.BatiCuisine.CoucheMetier.Enum.EtatProjet;

import java.util.ArrayList;
import java.util.List;


public class Projet {
    private Integer id;
    private String nomProjet;
    private double margeBeneficiaire;
    private  double coutTotal;
   private EtatProjet etatProjet;
   private Client client;
   private double surface;
    private List<Materiaux> materiauxList = new ArrayList<>();
    private List<Mainœuvre> mainOeuvreList = new ArrayList<>();

    public List<Materiaux> getMateriauxList() {
        return materiauxList;
    }

    public void setMateriauxList(List<Materiaux> materiauxList) {
        this.materiauxList = materiauxList;
    }

    public List<Mainœuvre> getMainOeuvreList() {
        return mainOeuvreList;
    }

    public void setMainOeuvreList(List<Mainœuvre> mainOeuvreList) {
        this.mainOeuvreList = mainOeuvreList;
    }

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

    public void ajouterMainOuvrier(Mainœuvre mainœuvre) {
        mainOeuvreList.add(mainœuvre);
    }
    public void ajouterMatriaux(Materiaux materiaux) {
        materiauxList.add(materiaux);
    }

    public double calculerTotalMainOeuvre() {
        double total = 0.0;
        for (Mainœuvre mainOeuvre : mainOeuvreList) {
            total += mainOeuvre.calculerTotal();
        }
        return total;
    }
    public double calculerTotalMatriaux() {
        double total = 0.0;
        for (Materiaux materiaux : materiauxList) {
            total += materiaux.calculerTotal();
        }
        return total;
    }

    public double calculerTotalMainOeuvreAvecTVA(double tauxTVA) {
        double totalAvantTVA = calculerTotalMainOeuvre();
        return totalAvantTVA * (1 + tauxTVA);
    }
    public double calculerTotalMateriauxAvecTVA(double tauxTVA) {
        double totalAvantTVA = calculerTotalMatriaux();
        return totalAvantTVA * (1 + tauxTVA);
    }


    public void afficherDetailsOuvriers() {
        for (Mainœuvre mainouvrier : mainOeuvreList) {
            double coutTotal = mainouvrier.calculerTotal();
            System.out.printf("%s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.2f h, productivité : %.1f)\n",
                    mainouvrier.getNom(), coutTotal, mainouvrier.getTauxHoraire(), mainouvrier.getHeuresTravail(), mainouvrier.getProductiviteOuvrier());
        }
    }
    public void afficherDetailsMateriaux() {
        for (Materiaux materiau : materiauxList) {
            double coutTotal = materiau.calculerTotal();
            System.out.printf("%s : %.2f € (quantité : %.2f, coût unitaire : %.2f €/u, qualité : %.1f, transport : %.2f €)\n",
                    materiau.getNom(), coutTotal, materiau.getQuantite(), materiau.getCoutUnitaire(), materiau.getCoefficientQualite(), materiau.getCoutTransport());
        }
    }


}
