package com.twu.biblioteca.core;

import java.util.List;
import java.util.Optional;

public interface LibraryItemRepository<T> extends Repository<T> {

    List<T> getAllAvailable();
    Optional<T> getOneByTitle(String title);
}
