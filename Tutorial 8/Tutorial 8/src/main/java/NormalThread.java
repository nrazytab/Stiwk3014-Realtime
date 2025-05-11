public class NormalThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            int x = i * i;
        }
    }
}

