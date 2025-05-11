public class MyThreadThread {
    public static void main(String[] args) {
        // Create and start threads
        TestSleep myThread1 = new TestSleep(1);
        TestSleep myThread2 = new TestSleep(2);

        // Start both threads
        myThread1.start();
        myThread2.start();
    }
}
