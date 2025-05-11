public class ComparisonNormalAndSynchronizedThread {
    public static void main(String[] args) throws InterruptedException {
        long startTime, endTime;

        startTime = System.nanoTime();
        Thread[] normalThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            normalThreads[i] = new NormalThread();
            normalThreads[i].start();
        }
        for (Thread t : normalThreads) {
            t.join();
        }
        endTime = System.nanoTime();
        double normalDuration = (endTime - startTime) / 1_000_000_000.0;
        startTime = System.nanoTime();
        Thread[] syncThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            syncThreads[i] = new SynchronizedThread();
            syncThreads[i].start();
        }
        for (Thread t : syncThreads) {
            t.join();
        }
        endTime = System.nanoTime();
        double syncDuration = (endTime - startTime) / 1_000_000_000.0;

        System.out.printf("Normal thread = %.9f seconds\n", normalDuration);
        System.out.printf("Synchronized thread = %.9f seconds\n", syncDuration);
    }
}

