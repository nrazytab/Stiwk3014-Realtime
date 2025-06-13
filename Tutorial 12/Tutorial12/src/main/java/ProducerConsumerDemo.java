public class ProducerConsumerDemo {

    static class SharedData {
        private boolean dataReady = false;
        private String data;

        // Producer method
        public synchronized void produce() {
            try {
                System.out.println("Producer: Preparing data...");
                Thread.sleep(1000); // Simulate time to produce data
                data = "Hello from producer!";
                dataReady = true;
                System.out.println("Producer: Data is ready.");
                notifyAll(); // Notify waiting consumer
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Consumer method
        public synchronized void consume() {
            try {
                while (!dataReady) {
                    System.out.println("Consumer: Waiting for data...");
                    wait(); // Release lock and wait to be notified
                }
                System.out.println("Consumer: Received --> " + data);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        Thread consumerThread1 = new Thread(() -> sharedData.consume());
        Thread consumerThread2 = new Thread(() -> sharedData.consume());

        Thread producerThread = new Thread(() -> sharedData.produce());
        consumerThread1.start();
        consumerThread2.start();
        producerThread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
