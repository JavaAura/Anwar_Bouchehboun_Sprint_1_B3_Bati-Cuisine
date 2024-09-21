package org.BatiCuisine.coucheServices;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Materiaux;
import org.BatiCuisine.couchePersistence.repository.MainoeuvreRepository;
import org.BatiCuisine.couchePersistence.repository.MateriauxRepository;

import java.util.HashMap;

public class ComposantServices {
    public ComposantServices(){

    }

    public MainoeuvreRepository mainoeuvreRepository=new MainoeuvreRepository();
    public MateriauxRepository materiauxRepository = new MateriauxRepository();

    public void createMainoeuvre(Mainœuvre mainœuvre){
        mainoeuvreRepository.create(mainœuvre);
    }
    public void createMatrieaux(Materiaux materiaux){
         materiauxRepository.create(materiaux);
    }
    public HashMap<String,Mainœuvre> getAllMainoeuvre(){
      return   mainoeuvreRepository.getAll();
    }

    public HashMap<String,Materiaux>getAllMateriaux(){
        return materiauxRepository.getAll();
    }

}
