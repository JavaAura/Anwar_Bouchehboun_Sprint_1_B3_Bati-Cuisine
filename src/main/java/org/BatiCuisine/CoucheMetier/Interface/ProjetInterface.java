package org.BatiCuisine.CoucheMetier.Interface;

import org.BatiCuisine.CoucheMetier.Entite.Projet;

import java.util.HashMap;
import java.util.List;

public interface ProjetInterface <T>{
    T create(T t);
    HashMap<String, T> getAll();
}
