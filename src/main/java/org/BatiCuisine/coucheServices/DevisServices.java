package org.BatiCuisine.coucheServices;

import org.BatiCuisine.CoucheMetier.Entite.Devis;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.couchePersistence.repository.DevisRepository;

public class DevisServices {


    public  DevisServices (){

    }

    public static DevisRepository devisRepository = new DevisRepository();



    public Devis createDevis (Devis devis){
        return devisRepository.create(devis);
    }
    public  void accpeteDevis(Devis devis){
        devisRepository.accepteDevis(devis);
    }
    public  Devis valideDevis(Projet projet){
        return  devisRepository.valideDevis(projet);
    }
}
