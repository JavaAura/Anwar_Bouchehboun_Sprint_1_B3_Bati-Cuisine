package org.BatiCuisine.coucheServices;

import org.BatiCuisine.CoucheMetier.Entite.Devis;
import org.BatiCuisine.couchePersistence.repository.DevisRepository;

public class DevisServices {


    public  DevisServices (){

    }

    public static DevisRepository devisRepository = new DevisRepository();



    public Devis createDevis (Devis devis){
        return devisRepository.create(devis);
    }
}
