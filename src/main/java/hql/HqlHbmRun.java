package hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HqlHbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            session.save(Candidate.of("Nikita", 1, 100000));
            session.save(Candidate.of("Sergey", 5, 200000));
            session.save(Candidate.of("Pavel", 2, 150000));

            List<Candidate> allCandidates = session.createQuery("from Candidate ").list();
            System.out.println(allCandidates);

            Candidate byId = (Candidate) session.createQuery("from Candidate where id=:id")
                    .setParameter("id", 1)
                    .uniqueResult();
            System.out.println(byId);

            Candidate byName = (Candidate) session.createQuery("from Candidate where name=:name")
                    .setParameter("name", "Pavel")
                    .uniqueResult();
            System.out.println(byName);

            session.createQuery("update Candidate set experience=:experience where id=:id")
                    .setParameter("experience", 3)
                    .setParameter("id", 1)
                    .executeUpdate();

            session.createQuery("delete from Candidate where id=:id")
                    .setParameter("id", 2)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }
}