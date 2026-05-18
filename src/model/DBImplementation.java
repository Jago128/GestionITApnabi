package model;

import java.util.ArrayList;
import java.util.List;
import static model.HibernateSession.getSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class DBImplementation implements GestionITDAO {

    @Override
    public List<Equipment> getEquipment() {
        Session session = getSessionFactory().openSession();
        List<Equipment> equipment = new ArrayList<>();

        try {
            String hql = "FROM Equipment e ORDER BY e.category ASC";
            Query<Equipment> query = session.createQuery(hql, Equipment.class);

            equipment = query.getResultList();
            System.out.println("Total de equipamiento encontrado: " + equipment.size());

        } catch (Exception e) {
            System.out.println("Error de base de datos al intentar obtener el equipamiento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return equipment;
    }

    @Override
    public boolean addEquipment(Category category, String brandAndModel, Status status, String assignedPerson) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Equipment equip = new Equipment(category, brandAndModel, status, assignedPerson);
            session.save(equip);
            transaction.commit();

            System.out.println("El equipamiento ha sido añadido correctamente.");
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error de base de datos al intentar añadir el equipamiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean modifyEquipment(Equipment eq) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(eq);
            transaction.commit();

            System.out.println("El equipamiento ha sido modificado correctamente.");
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error de base de datos al intentar modificar el equipamiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean removeEquipment(Equipment eq) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Equipment eqToDel = session.get(Equipment.class, eq.getId());

            if (eqToDel != null) {
                session.delete(eqToDel);
                transaction.commit();
                System.out.println("El equipamiento ha sido eliminado correctamente.");
                return true;
            } else {
                System.out.println("El equipamiento proveeido no existe.");
                return false;
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error de base de datos al intentar borrar el equipamiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
}
