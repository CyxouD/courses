package service;

import domain.Account;
import domain.Client;
import domain.Transaction;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class BankAccountControlService {
    private static final String PERSISTENCE_UNIT = "com.courses.spalah.jpa";
    private static EntityManager entityManager;

    public BankAccountControlService() {
        entityManager = this.initEntityManager();
    }

    public static /* varargs */ void main(String ... args) {
        BankAccountControlService bankAccountControlService = new BankAccountControlService();
        System.out.println(Arrays.toString(bankAccountControlService.getClients().toArray()));
        Client client = new Client();
        client.setInn(545644);
        client.setName("Vasya");
        client.setSurname("Pupkin");
        Account account = new Account();
        account.setClientInn(client.getInn());
        account.setBalance(0.0);
        Transaction transaction = new Transaction();
        transaction.setAccountId(10);
        transaction.setSum(-50.0);
        System.out.println(Arrays.toString(bankAccountControlService.findAccountsOfClient(client.getInn()).toArray()));
        account.setId(10);
        System.out.println(Arrays.toString(bankAccountControlService.findTransactionsOfAccount(account.getId()).toArray()));
        System.out.println(Arrays.toString(bankAccountControlService.findTransactionsOfClient(client.getInn()).toArray()));
    }

    public List<Account> findAccountsOfClient(int clientInn) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("findAccountByClientINN").setParameter("clientINN", (Object)clientInn);
        List accounts = query.getResultList();
        entityManager.getTransaction().commit();
        return accounts;
    }

    public List<Transaction> findTransactionsOfAccount(int accountId) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("findTransactionByClientAccount").setParameter("accountId", (Object)accountId);
        List transactions = query.getResultList();
        entityManager.getTransaction().commit();
        return transactions;
    }

    public List<Transaction> findTransactionsOfClient(int clientInn) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("findTransactionByClientINN").setParameter("clientINN", (Object)clientInn);
        List transactions = query.getResultList();
        entityManager.getTransaction().commit();
        return transactions;
    }

    public List<Client> getClients() {
        entityManager.getTransaction().begin();
        List clients = entityManager.createQuery("from Client", (Class)Client.class).getResultList();
        entityManager.getTransaction().commit();
        return clients;
    }

    public void addTransaction(Transaction transaction) throws WithdrawMoreThanOnAccountException {
        double accountBalance = this.getAccountBalance(transaction.getAccountId());
        if (accountBalance + transaction.getSum() < 0.0) {
            throw new WithdrawMoreThanOnAccountException(this, "Trying to withdraw " + transaction.getSum() + " from account balance = " + accountBalance);
        }
        entityManager.getTransaction().begin();
        entityManager.persist((Object)transaction);
        entityManager.getTransaction().commit();
    }

    private double getAccountBalance(int accountId) {
        Account account = (Account)entityManager.createQuery("from Account a where a.id = :accountId", (Class)Account.class).setParameter("accountId", (Object)accountId).getSingleResult();
        return account.getBalance();
    }

    public void addClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist((Object)client);
        entityManager.getTransaction().commit();
    }

    public void addStartBalance(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist((Object)account);
        entityManager.getTransaction().commit();
    }

    private EntityManager initEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory((String)"com.courses.spalah.jpa");
        return entityManagerFactory.createEntityManager();
    }

public class WithdrawMoreThanOnAccountException
extends Exception {
    public WithdrawMoreThanOnAccountException(String s) {
        super(s);
    }
}
}