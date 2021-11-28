package ru.job4j.store;

import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.List;

public interface Store {

    User findUserByEmail(String email);

    void saveUser(User user);

    List<Item> findAllOffItems(int userId);

    List<Item> findReallyAllItems(int userId);

    void saveItem(Item item, String[] categoryIds);

    void wasDone(int id);

    List<Category> findAllCategory();

    Item findById(int id);
}