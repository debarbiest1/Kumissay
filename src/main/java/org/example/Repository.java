package org.example;

import java.util.List;

interface Repository<T> {
    void add(T item);
    List<T> showAll();

    void delete(int id);

    void update(T product1);
}