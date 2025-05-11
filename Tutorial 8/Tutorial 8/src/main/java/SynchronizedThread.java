public class SynchronizedThread extends Thread {
    private static final Object lock = new Object();

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 1000; i++) {
                int x = i * i;
            }
        }
    }
}
