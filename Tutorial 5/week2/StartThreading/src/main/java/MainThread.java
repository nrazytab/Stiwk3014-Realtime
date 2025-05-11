public class MainThread {
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            TestSleep thread = new TestSleep(i);
            thread.start();

            try {
                thread.join(); // Wait for this thread to finish before starting the next
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
