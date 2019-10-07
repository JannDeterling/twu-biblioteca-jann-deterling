package com.twu.biblioteca.core;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    List<T> getAll();

    List<T> getAllAvailable();

    Optional<T> getOneByTitle(String title);
}
