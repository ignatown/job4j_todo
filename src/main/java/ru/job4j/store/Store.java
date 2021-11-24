package ru.job4j.store;

import ru.job4j.model.Item;

import java.util.List;

public interface Store {

    List<Item> findAllOffItems();

    List<Item> findReallyAllItems();

    void saveItem(Item item);

    void wasDone(int id);

    Item findById(int id);
}