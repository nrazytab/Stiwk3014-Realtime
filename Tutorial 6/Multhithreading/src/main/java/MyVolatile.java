import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyVolatile extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println(Thread.currentThread().getName() + ": Thread is running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ": Thread stopped.");
    }

    public void shutdown() {
        running = false;
    }

    public static void main(String[] args) {
        MyVolatile thread = new MyVolatile();
        thread.start();

        System.out.println("Press ENTER to stop the thread...");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        thread.shutdown();
    }
}
