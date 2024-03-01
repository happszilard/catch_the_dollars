//Synchronizes the falling object controllers
public class ObjectController extends Thread {
    private final Map map;
    private final DollarBombController dollarBombController1;
    private final DollarBombController dollarBombController2;
    private int increment;

    public ObjectController(Map map, Main frame) {
        this.map = map;
        this.increment = 6;
        dollarBombController1 = new DollarBombController(0, increment, map, frame);
        dollarBombController2 = new DollarBombController(1, increment, map, frame);

    }

    @Override
    public void run() {
        dollarBombController1.start();
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dollarBombController2.start();

        //increases the speed of the falling objects
        while(!map.getGameOver()) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            increment += 2;
            dollarBombController1.setIncrement(increment);
            dollarBombController2.setIncrement(increment);
        }

    }
}
