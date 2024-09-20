package org.BatiCuisine.coucheServices;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.couchePersistence.repository.ProjetRepository;

import java.util.HashMap;
import java.util.List;

public class ProjetServices {
    public ProjetServices(){

    }

    public final ProjetRepository projetRepository = new ProjetRepository();


    public HashMap<String,Projet> Projet(){
      return projetRepository.getAll();
    }

}
