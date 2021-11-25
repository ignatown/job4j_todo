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

            Car one = Car.of("2101");
            Car two = Car.of("2102");
            Car three = Car.of("2103");
            Car four = Car.of("2104");
            Car five = Car.of("2105");

            session.save(one);
            session.save(two);
            session.save(three);
            session.save(four);
            session.save(five);

            Model vaz = Model.of("VAZ");
            vaz.addCar(session.load(Car.class, 1));
            vaz.addCar(session.load(Car.class, 2));
            vaz.addCar(session.load(Car.class, 3));
            vaz.addCar(session.load(Car.class, 4));
            vaz.addCar(session.load(Car.class, 5));

            session.save(vaz);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}