package org.BatiCuisine.CoucheMetier.Entite;

import java.util.List;

public class Client {
    private Integer id;
    private String nom;
    private  String adrresse;
    private  String telephone;
    private boolean estProfessionnel;

    public Client(Integer id, boolean estProfessionnel, String telephone, String adrresse, String nom) {
        this.id = id;
        this.estProfessionnel = estProfessionnel;
        this.telephone = telephone;
        this.adrresse = adrresse;
        this.nom = nom;
    }
    public Client(){

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdrresse() {
        return adrresse;
    }

    public void setAdrresse(String adrresse) {
        this.adrresse = adrresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isEstProfessionnel() {
        return estProfessionnel;
    }

    public void setEstProfessionnel(boolean estProfessionnel) {
        this.estProfessionnel = estProfessionnel;
    }

    public void Affiche(){
        String format = "%-10s| %-20s | %-30s | %-15s%n";

        String professionStatus = isEstProfessionnel() ? "Professional" : "Non-professional";
        System.out.printf(format, "ID", "Name", "Address", "estProfessionnel");
        System.out.print("-------------------------------------------------------------------------------------\n");
        System.out.printf(format, getId(), getNom(), getAdrresse(), professionStatus);    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adrresse='" + adrresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", estProfessionnel=" + estProfessionnel +
                '}';
    }
}
