public class Task1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new MultiplicationTask(1));
        Thread t2 = new Thread(new MultiplicationTask(2));
        Thread t3 = new Thread(new MultiplicationTask(3));

        t1.start();
        t2.start();
        t3.start();
    }

    static class MultiplicationTask implements Runnable {
        private final int number;

        public MultiplicationTask(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) { // limit to 3 like in the image
                System.out.println(Thread.currentThread().getName() + ": " + number + " * " + i + " = " + (number * i));
                try {
                    Thread.sleep(100); // Delay for more readable interleaved output
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
