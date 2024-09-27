package org.BatiCuisine.coucheServices;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Devis;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.couchePersistence.repository.DevisRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    //methode  return map
    public Map<String,Client> getClient(List<Devis> devis){
       return devis.stream().map(map->map.getProjet().getClient())
               .collect(Collectors.toMap(Client::getNom,client -> client));
    }
}
