package de.jonas.informatik.ship;

public final class Ship {

    private int ID;        // identification number
    private double mass;   // the ship's mass
    private String name;   // the given name
    private String home;   // the port of registry
    public int p = 0;        // a public variable

    // default constructor
    public Ship() {
        ID = 0;
        mass = 0;
        name = "none";
        home = "unknown";
    }

    // standard constructor
    public Ship(int i, double m, String n, String h) {
        // task #1
        this.ID = i;
        this.mass = m;
        this.name = n;
        this.home = h;
    }

    public Ship(final int id, final double mass, final String name) {
        this.ID = id;
        this.mass = mass;
        this.name = name;
        this.home = "Rotterdam";
    }

    public int dosomething(int q) {
        p += q;
        return p;
    }

    public int getID() {
        return ID;
    }

    public void setID(final int ID) {
        this.ID = ID;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(final double mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(final String home) {
        this.home = home;
    }
}

