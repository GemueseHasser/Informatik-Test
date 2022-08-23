package de.jonas.informatik.vlist;

public final class Player {

    private String name;
    private double value;
    private String team;


    public Player(
        final String name,
        final double value,
        final String team
    ) {
        this.name = name;
        this.value = value;
        this.team = team;
    }


    public void print() {
        System.out.println(this);
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getTeam() {
        return team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nWert: " + value + "\nTeam: " + team;
    }

}
