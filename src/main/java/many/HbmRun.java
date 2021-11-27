package many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {

    public static void main(String[] args) {
        List<Model> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model vaz = Model.of("vaz");
            session.save(vaz);
            Car one = Car.of("2101", vaz);
            Car two = Car.of("2102", vaz);
            Car three = Car.of("2103", vaz);
            session.save(one);
            session.save(two);
            session.save(three);

            list = session.createQuery(
                    "select distinct m from Model m join fetch m.cars"
            ).list();
            
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Car car: list.get(0).getCars()) {
            System.out.println(car);
        }
    }
}