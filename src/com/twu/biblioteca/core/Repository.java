package com.twu.biblioteca.core;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();

}
