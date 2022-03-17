package de.jonas.informatik.ship;

public final class ShipMain {

    private final Ship ship;
    private final Ship taskAShip;

    public ShipMain() {
        ship = new Ship();
        final Ship customShip = new Ship(1, 1., "a", "b");
        taskAShip = new Ship(2, 5, "Unicorn", "Bremen");

        // task #2: give the value of all parameters of both ships
        printValues(ship);
        printValues(customShip);
        printValues(taskAShip);
    }

    private void action() {
        System.out.println(ship.getID() == 5 || taskAShip.getID() == 5);

        ship.setHome("Hamburg");

        printValues(taskAShip);
        taskAShip.setName("Unicorn 2.0");
        taskAShip.setID(6);
        taskAShip.setMass(25D);
        taskAShip.setHome("Wülfrath");
        printValues(taskAShip);
    }

    private void printValues(final Ship ship) {
        System.out.println("--------------------");
        System.out.println("Schiff: " + ship.getName());
        System.out.println("\nID: " + ship.getID());
        System.out.println("Masse: " + ship.getMass());
        System.out.println("Herkunft: " + ship.getHome());
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        ShipMain h = new ShipMain();
        h.action();
    }
}

