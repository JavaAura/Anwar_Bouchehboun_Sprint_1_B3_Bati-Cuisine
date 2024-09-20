package org.BatiCuisine.CoucheMetier.Interface;

import java.util.HashMap;

public interface ComposantInterface <T>{
   void  create(T T);
    HashMap<String, T> getAll();
}
