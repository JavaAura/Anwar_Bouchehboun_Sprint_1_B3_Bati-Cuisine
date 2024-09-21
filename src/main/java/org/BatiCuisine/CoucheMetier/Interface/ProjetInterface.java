package org.BatiCuisine.CoucheMetier.Interface;

import org.BatiCuisine.CoucheMetier.Entite.Projet;

import java.util.HashMap;

public interface ProjetInterface <T>{
    T create(T t);
    HashMap<String, T> getAll();
    void update(T t);
    Projet findByName(String projet);
}
