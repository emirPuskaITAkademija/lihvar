package ba.lihvarenje;

import ba.lihvarenje.bank.BankAccount;
import ba.lihvarenje.gui.BankAccountPanel;
import ba.lihvarenje.movie.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 *
 *
 *
 * 1400 -> employees
 *   -> thread
 *   main
 *
 *   1. Computational Intensive
 *       broj thread  = broj jezgri procesira  8 16
 *
 *   2. IO intensive ->
 *       broj thread = 1 / (1-BF) 16 32
 *
 *
 *
 *       JS
 *
 *       Java -> ScriptEngine
 *
 * tomcat -> 16 GB
 *
 */
public class Application {
    public static void main(String[] args) {
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("lihvarPU");
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("Movie.findAll");
        entityManager.getTransaction().commit();
        List<Movie> movies = query.getResultList();
        movies.forEach(System.out::println);
//        SwingUtilities.invokeLater(() -> {
//            try {
//                createAndShowGUI();
//            } catch (UnsupportedLookAndFeelException e) {
//                throw new RuntimeException(e);
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            } catch (InstantiationException e) {
//                throw new RuntimeException(e);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }

    private static void createAndShowGUI() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //Windows OS
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Bank Transfer");
        BankAccountPanel bankAccountPanel = new BankAccountPanel();
        frame.setContentPane(bankAccountPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
