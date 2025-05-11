public class TestSleep extends Thread {
    private int threadNumber;

    public TestSleep(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        try {
            System.out.println("T" + threadNumber + "-ONE");
            Thread.sleep(100);
            System.out.println("T" + threadNumber + "-TWO");
            Thread.sleep(100);
            System.out.println("T" + threadNumber + "-THREE");
            Thread.sleep(100);
            System.out.println("T" + threadNumber + "-XXXXXXXXXX\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
