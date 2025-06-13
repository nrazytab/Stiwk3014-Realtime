import java.util.concurrent.locks.ReentrantLock;

public class TrafficLightController {

    enum TrafficLightState {
        RED, GREEN, YELLOW
    }

    //state of the traffic light
    private TrafficLightState currentState;

    //ReentrantLock to protect the shared state
    private final ReentrantLock lock = new ReentrantLock();

    //constructor to initialize trafficlight to RED
    public TrafficLightController() {
        currentState = TrafficLightState.RED;
    }

    //to cycle through the lights
    public void startSimulation() {
        while (true) {
            lock.lock(); //acquire the lock before modifying shared state
            try {
                switch (currentState) {
                    case RED:
                        showRedLight(); //display RED
                        currentState = TrafficLightState.GREEN; //next state
                        break;
                    case GREEN:
                        showGreenLight(); //GREEN
                        currentState = TrafficLightState.YELLOW;
                        break;
                    case YELLOW:
                        showYellowLight(); //YELLOW
                        currentState = TrafficLightState.RED;
                        break;
                }
            } finally {
                lock.unlock(); //always release the lock
            }
        }
    }

    //Helper method to simulate RED light
    private void showRedLight() {
        System.out.println("🚦 RED light - STOP");
        sleep(5000); //wait 5 seconds
    }

    //Helper method to simulate GREEN light
    private void showGreenLight() {
        System.out.println("🚦 GREEN light - GO");
        sleep(4000); //4 seconds
    }

    //Helper method to simulate YELLOW light
    private void showYellowLight() {
        System.out.println("🚦 YELLOW light - SLOW DOWN");
        sleep(2000); //2 seconds
    }

    //Sleep utility with exception handling
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds); //pause
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted!");
        }
    }

    //start the simulation
    public static void main(String[] args) {
        TrafficLightController controller = new TrafficLightController();
        controller.startSimulation(); //run
    }
}
