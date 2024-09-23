package org.BatiCuisine.CoucheMetier.Interface;

import org.BatiCuisine.CoucheMetier.Entite.Projet;

import java.util.HashMap;
import java.util.List;

public interface ComposantInterface <T>{
   void  create(T T);
    HashMap<String, T> getAllMain();
    List<T> getAllMain(Projet projet);
}
