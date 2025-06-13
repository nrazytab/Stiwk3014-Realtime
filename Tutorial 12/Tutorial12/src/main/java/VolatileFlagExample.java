public class VolatileFlagExample {

    private static boolean running = true;

    //synchronized setter
    public static synchronized void setRunning(boolean value) {
        running = value;
    }

    //synchronized getter
    public static synchronized boolean isRunning() {
        return running;
    }

    public static void main(String[] args) {
        //thread that runs continuously until 'running' becomes false
        Thread worker = new Thread(() -> {
            System.out.println("Worker thread started...");
            while (isRunning()) {
                //simulate work
            }
            System.out.println("Worker thread stopped...");
        });

        worker.start();

        //main thread sleeps for a bit then signals stop
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        setRunning(false); //update the flag
    }
}
