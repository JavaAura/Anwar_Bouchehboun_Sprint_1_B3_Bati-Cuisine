package org.BatiCuisine.coucheServices;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.couchePersistence.repository.MainoeuvreRepository;
import org.BatiCuisine.couchePersistence.repository.MateriauxRepository;

public class MainoeuvreServices {
    public MainoeuvreServices(){

    }

    public MainoeuvreRepository mainoeuvreRepository=new MainoeuvreRepository();


    public void createMainoeuvre(Mainœuvre mainœuvre){
        mainoeuvreRepository.create(mainœuvre);
    }

}
