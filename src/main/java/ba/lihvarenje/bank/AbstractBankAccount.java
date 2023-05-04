package ba.lihvarenje.bank;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * DAO
 * <p>
 * BankAccountDao bankAccountDao = new BankAccountDao();
 * BankAccount bankAccount = new BankAccount();
 * bankAccountDao.save(bankeAccount);
 * </p>
 * Active Record
 * <p>
 * BankAccount bankAccount = new BankAccount();
 * bankAccount.save();
 * </p>
 */
public abstract class AbstractBankAccount {

    //Desktop EMF
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("lihvarPU");

    public BankAccount getThis() {
        return (BankAccount) this;
    }

    public void save() {
        executeInTransaction(entityManager -> entityManager.persist(getThis()));
    }

    public void update() {
        executeInTransaction(entityManager -> entityManager.merge(getThis()));
    }

    public BankAccount get(String accountNumber) {
        Function<EntityManager, BankAccount> applier = (em) -> em.find(BankAccount.class, accountNumber);
        return executeInsideTransaction(applier);
    }

    public void delete() {
        executeInTransaction(em -> em.remove(getThis()));
    }

    private void executeInTransaction(Consumer<EntityManager> entityManagerConsumer) {
        try {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            entityManager.getTransaction().begin();
            entityManagerConsumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private BankAccount executeInsideTransaction(Function<EntityManager, BankAccount> applier) {
        try {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            entityManager.getTransaction().begin();
            BankAccount bankAccount = applier.apply(entityManager);
            entityManager.getTransaction().commit();
            return bankAccount;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static List<BankAccount> findAll() {
        try {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("BankAccount.findAll");
            entityManager.getTransaction().commit();
            List<BankAccount> bankAccounts = query.getResultList();
            return bankAccounts;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public static void transferMoney(BankAccount fromAccount, BankAccount toAccount, Double amount) {
        if (fromAccount == null || toAccount == null) {
            JOptionPane.showMessageDialog(null, "Moraš odabrati bankovne račune");
            return;
        }
        if (fromAccount.equals(toAccount)) {
            JOptionPane.showMessageDialog(null, "Ne možeš sam sebi transferirati novce");
            return;
        }
        if (amount <= 0) {
            JOptionPane.showMessageDialog(null, "Nema smisla negativan iznos");
            return;
        }
        if (fromAccount.getAmount() < amount) {
            JOptionPane.showMessageDialog(null, "Nema dovoljno sredstava na računu");
            return;
        }
        try {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            entityManager.getTransaction().begin();
            fromAccount.setAmount(fromAccount.getAmount() - amount);
            toAccount.setAmount(toAccount.getAmount() + amount);
//            entityManager.merge(fromAccount);
//            entityManager.merge(toAccount);
            fromAccount.update();
            toAccount.update();
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
