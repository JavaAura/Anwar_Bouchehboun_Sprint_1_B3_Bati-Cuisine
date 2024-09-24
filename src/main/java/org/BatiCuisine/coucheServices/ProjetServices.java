package org.BatiCuisine.coucheServices;

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
     public void updateProjet(Projet projet){

        projetRepository.update(projet);
     }

     public Projet finAll(String projet){
   return   projetRepository.findByName(projet);
     }
    public Projet createProjet(Projet projet){

        return   projetRepository.create(projet);
    }
    public void updateEtat(Projet projet){
        projetRepository.updateEtat(projet);
    }
}
