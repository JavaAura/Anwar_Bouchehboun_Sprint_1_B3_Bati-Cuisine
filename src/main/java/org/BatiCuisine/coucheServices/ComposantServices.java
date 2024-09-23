package org.BatiCuisine.coucheServices;

import org.BatiCuisine.CoucheMetier.Entite.Mainœuvre;
import org.BatiCuisine.CoucheMetier.Entite.Materiaux;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.couchePersistence.repository.ComposantRepository;
import org.BatiCuisine.couchePersistence.repository.MainoeuvreRepository;
import org.BatiCuisine.couchePersistence.repository.MateriauxRepository;

import java.util.HashMap;
import java.util.List;

public class ComposantServices {
    public ComposantServices(){

    }

    public MainoeuvreRepository mainoeuvreRepository=new MainoeuvreRepository();
    public MateriauxRepository materiauxRepository = new MateriauxRepository();
   public ComposantRepository composantRepository= new ComposantRepository();
    public void createMainoeuvre(Mainœuvre mainœuvre){
        mainoeuvreRepository.create(mainœuvre);
    }
    public void createMatrieaux(Materiaux materiaux){
         materiauxRepository.create(materiaux);
    }
    public HashMap<String,Mainœuvre> getAllMainoeuvre(){
      return   mainoeuvreRepository.getAllMain();
    }

    public HashMap<String,Materiaux>getAllMateriaux(){
        return materiauxRepository.getAllMain();
    }
    public void updataTva(double tva,int id){
        composantRepository.update(tva,id);
    }
    public List<Mainœuvre>  getAllMainoeuvreProject(Projet projet){
   return   mainoeuvreRepository.getAllMain(projet);
    }
    public List<Materiaux> getAllMateriauxProject(Projet projet){
        return  materiauxRepository.getAllMain(projet);
    }
    public double getTva(Projet projet){
        return  composantRepository.getTva(projet);
    }
}
