import java.util.concurrent.locks.ReentrantReadWriteLock;

class BankAccountWithLock {
    private double balance;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public BankAccountWithLock(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reads  balance: " + balance);
            return balance;
        } finally {
            readLock.unlock();
        }
    }

    public void deposit(double amount) {
        writeLock.lock();
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposits: " + amount);
        } finally {
            writeLock.unlock();
        }
    }

    public void withdraw(double amount) {
        writeLock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdraws: " + amount);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds for: " + amount);
            }
        } finally {
            writeLock.unlock();
        }
    }
}

public class MainClass {
    public static void main(String[] args) {
        //create bankacc with RM1000 balance
        BankAccountWithLock myAccount = new BankAccountWithLock(1000.0);

        //thread to read balance
        Thread readerA = new Thread(() -> {
            myAccount.getBalance();
        }, "Reader-A");

        //read balance
        Thread readerB = new Thread(() -> {
            myAccount.getBalance();
        }, "Reader-B");

        //Thread to deposit
        Thread depositor = new Thread(() -> {
            myAccount.deposit(300.0);
        }, "Depositor");

        //thread to withdraw
        Thread withdrawer = new Thread(() -> {
            myAccount.withdraw(200.0);
        }, "Withdrawer");

        //start
        readerA.start();
        readerB.start();
        depositor.start();
        withdrawer.start();

        //wait all threads to habis
        try {
            readerA.join();
            readerB.join();
            depositor.join();
            withdrawer.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }
}

