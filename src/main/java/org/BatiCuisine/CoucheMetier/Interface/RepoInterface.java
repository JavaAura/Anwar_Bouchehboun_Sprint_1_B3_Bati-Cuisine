package org.BatiCuisine.CoucheMetier.Interface;

import java.util.List;
import java.util.Optional;

public interface RepoInterface<T> {
    T create(T t);
    List<T> getAll();

}
