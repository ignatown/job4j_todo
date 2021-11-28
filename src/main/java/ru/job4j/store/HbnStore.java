package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import org.hibernate.Transaction;
import ru.job4j.model.User;

import javax.persistence.Query;
import java.util.function.Function;

import java.util.List;

public class HbnStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();


    private static final class Lazy {
        private static final Store INST = new HbnStore();
    }

    private <T> T session(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) session(session -> session.createQuery("from User where email=:email")
                .setParameter("email", email)
                .uniqueResult());
    }

    @Override
    public void saveUser(User user) {
        session(session -> session.save(user));
    }

    @Override
    public List<Item> findAllOffItems(int userId) {
        return this.session(
                session -> session.createQuery("from Item where done=false and user_id=:id")
                        .setParameter("id", userId)
                        .list()
        );
    }

    @Override
    public List<Item> findReallyAllItems(int userId) {
        return this.session(
                session -> session.createQuery("from Item where user_id=:id")
                        .setParameter("id", userId)
                        .list()
        );
    }

    @Override
    public void saveItem(Item item, String[] categoryIds) {
        session(session -> {
            for (String id: categoryIds) {
                item.addCategory(session.get(Category.class, Integer.parseInt(id)));
            }
            session.save(item);
            return true;
        });
    }

    @Override
    public void wasDone(int id) {
        session(session -> {
            return session.createQuery("update Item set done=:done where id=:id")
                    .setParameter("id", id)
                    .setParameter("done", true)
                    .executeUpdate();
        });
    }

    @Override
    public List<Category> findAllCategory() {
        return session(session -> session.createQuery("from ru.job4j.model.Category")
                .list()
        );
    }

    @Override
    public Item findById(int id) {
        return session(session -> session.get(Item.class, id));
    }
}
