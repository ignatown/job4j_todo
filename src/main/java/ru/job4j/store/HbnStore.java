package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Item;
import org.hibernate.Transaction;

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
    public List<Item> findAllOffItems() {
        return this.session(
                session -> session.createQuery("from Item where done=false").list()
        );
    }

    @Override
    public List<Item> findReallyAllItems() {
        return this.session(
                session -> session.createQuery("from Item").list()
        );
    }

    @Override
    public void saveItem(Item item) {
        session(session -> session.save(item));
    }

    @Override
    public void wasDone(int id) {
        session(session -> {
            int rsl = session.createQuery("update Item set done=:done where id=:id")
                    .setParameter("id", id)
                    .setParameter("done", true)
                    .executeUpdate();
            return rsl;
        });
    }

    @Override
    public Item findById(int id) {
        return session(session -> session.get(Item.class, id));
    }
}
