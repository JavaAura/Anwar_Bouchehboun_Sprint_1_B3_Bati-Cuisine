package org.BatiCuisine.couchePersistence.repository;

import org.BatiCuisine.CoucheMetier.Entite.Devis;
import org.BatiCuisine.CoucheMetier.Interface.RepoInterface;

import java.util.List;

public class DevisRepository implements RepoInterface<Devis> {


    @Override
    public Devis create(Devis devis) {
        return null;
    }

    @Override
    public List<Devis> getAll() {
        return List.of();
    }
}
