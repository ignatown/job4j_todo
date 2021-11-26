package many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book oneBook = Book.of("Book 1");
            Book twoBook = Book.of("Book 2");

            Author oneAuthor = Author.of("Author 1");
            oneAuthor.getBooks().add(oneBook);

            Author twoAuthor = Author.of("Author 2");
            twoAuthor.getBooks().add(oneBook);
            twoAuthor.getBooks().add(twoBook);

            session.persist(oneAuthor);
            session.persist(twoAuthor);

            Author removeAuthor = session.get(Author.class, 1);
            session.remove(removeAuthor);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}