package de.jonas.informatik;

import java.util.Timer;
import java.util.TimerTask;

public final class MiddleEarth {

    /** Eine Instanz eines {@link Ranger}. */
    private static final Ranger ARAGORN = new Ranger();
    /** Eine Instanz eines {@link OrcSoldier}. */
    private static final OrcSoldier ORC = new OrcSoldier();
    /** Eine Instanz eines {@link CaveTroll}. */
    private static final CaveTroll TROLL = new CaveTroll();
    /** Eine Instanz eines {@link UrukHai}. */
    private static final UrukHai HAI = new UrukHai();


    public static void main(final String[] args) {
        final MiddleEarth middleEarth = new MiddleEarth();

        middleEarth.play();

        System.out.println("\n");
    }

    private void play() {
        ARAGORN.showMoney();
        ARAGORN.takeMoney(5);
        ARAGORN.showMoney();
        ARAGORN.showPosition();
        ARAGORN.move(10, 20);
        ARAGORN.showPosition();
        ARAGORN.showWeapon();

        ORC.move(16, 21);
        ARAGORN.fight(ORC);

        System.out.println(ORC.isAlive() ? "Der Ork lebt!" : "Der Ork ist tot!");
        System.out.println(ARAGORN.isAlive() ? "Der Jäger lebt!" : "Der Jäger ist tot!");
    }


    //<editor-fold desc="Entity">
    private static abstract class Entity {

        //<editor-fold desc="CONSTANTS">
        /** Die X-Koordinate, die ein {@link Entity} standardmäßig gesetzt bekommt. */
        private static final int INITIAL_POS_X = 0;
        /** Die Y-Koordinate, die ein {@link Entity} standardmäßig gesetzt bekommt. */
        private static final int INITIAL_POS_Y = 0;
        /** Der Überlebens-Status eines {@link Entity}, welcher standardmäßig gesetzt wird. */
        private static final boolean INITIAL_ALIVE_STATE = true;
        /** Der Radius, in dem sich eine Entität in der Nähe einer anderen Entität aufhält. */
        private static final int NEAR_RADIUS = 10;
        //</editor-fold>


        /** Die maximale Stärke, die ein {@link Entity} haben kann. */
        private final double maxStrength;
        /** Die aktuelle X-Koordinate des {@link Entity}. */
        private int posX;
        /** Die aktuelle Y-Koordinate des {@link Entity}. */
        private int posY;
        /** Die aktuelle Stärke des {@link Entity}. */
        private double strength;
        /** Der aktuelle Überlebens-Status des {@link Entity}. */
        private boolean alive;


        public Entity(final double maxStrength) {
            this.maxStrength = maxStrength;
            this.posX = INITIAL_POS_X;
            this.posY = INITIAL_POS_Y;
            this.strength = maxStrength;
            this.alive = INITIAL_ALIVE_STATE;
        }


        public int getPosX() {
            return posX;
        }

        public int getPosY() {
            return posY;
        }

        public boolean isAlive() {
            return alive;
        }

        public void setPosX(final int posX) {
            this.posX = posX;
        }

        public void setPosY(final int posY) {
            this.posY = posY;
        }

        public void setAlive(final boolean alive) {
            this.alive = alive;
        }

        /**
         * Verschiebt die {@link Entity Entität} um einen gewissen X- und Y-Wert.
         *
         * @param x Der X-Wert um den die {@link Entity Entität} verschoben werden soll.
         * @param y Der Y-Wert um den die {@link Entity Entität} verschoben werden soll.
         */
        public void move(final int x, final int y) {
            this.posX += x;
            this.posY += y;
        }

        /**
         * Fügt dem {@link Ranger} eine gewisse Anzahl an Stärke hinzu, aber maximal bis er eine Stärke von {@code
         * maxStrength} hat.
         *
         * @param strength Die Stärke, die dem {@link Entity} hinzugefügt werden soll.
         */
        public void recover(final double strength) {
            if (this.strength + strength > this.maxStrength) {
                this.strength = this.maxStrength;
                return;
            }

            this.strength += strength;
        }

        /**
         * Zieht dem {@link Entity} eine gewisse Anzahl an Stärke ab und sobald seine Stärke 0 oder kleiner ist, wird
         * sein Überlebens-Status auf {@code false} gesetzt.
         *
         * @param strength Die Stärke, die dem {@link Entity} abgezogen werden soll.
         */
        public void suffer(final double strength) {
            this.strength -= strength;

            if (this.strength < 1) {
                this.strength = 0;
                this.alive = false;
            }
        }

        /**
         * Zeigt aktuelle die Position an.
         */
        public void showPosition() {
            System.out.println("\n I am at (" + this.posX + " | " + this.posY + ").");
        }

        /**
         * Überprüft, ob sich die aktuelle Entität in der Nähe einer bestimmten gegnerischen Entität befindet.
         *
         * @param opponent Die gegnerische Entität.
         *
         * @return Wenn sich die gegnerische Entität in einem Radius von {@value NEAR_RADIUS} um die aktuelle Entität
         *     befindet, {@code true}, ansonsten {@code false}.
         */
        public boolean isNotInNear(final Entity opponent) {
            return this.getPosX() < (opponent.getPosX() - NEAR_RADIUS)
                || this.getPosX() > (opponent.getPosX() + NEAR_RADIUS)
                || this.getPosY() < (opponent.getPosY() - NEAR_RADIUS)
                || this.getPosY() > (opponent.getPosY() + NEAR_RADIUS);
        }

        /**
         * Lässt die aktuelle {@link Entity Entität} mit einer anderen Kämpfen (der Gegner der aktuellen Entität). Bzw
         * die aktuelle Entität greift die gegnerische Entität an.
         *
         * @param opponent Der Gegner der aktuellen {@link Entity Entität}.
         */
        public abstract void fight(final Entity opponent);
    }
    //</editor-fold>


    //<editor-fold desc="Ranger">

    /**
     * Ein {@link Ranger Jäger}.
     */
    private static final class Ranger extends Entity {

        //<editor-fold desc="CONSTANTS">
        /** Die maximale Stärke, die ein {@link Ranger} haben darf. */
        private static final double MAX_STRENGTH = 10D;
        /** Die {@link WeaponType Waffe}, mit der ein {@link Ranger} standardmäßig ausgerüstet wird. */
        private static final WeaponType INITIAL_WEAPON = WeaponType.KNIVE;
        /** Die Menge an Geld, die ein {@link Ranger} standardmäßig bekommt. */
        private static final int INITIAL_MONEY = 0;
        //</editor-fold>


        //<editor-fold desc="LOCAL_FIELDS">
        /** Die aktuelle {@link WeaponType Waffe} des {@link Ranger}. */
        private WeaponType weapon;
        /** Die aktuelle Anzahl an Geld, die der {@link Ranger} hat. */
        private int money;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link Ranger}.
         */
        public Ranger() {
            super(MAX_STRENGTH);
            this.weapon = INITIAL_WEAPON;
            this.money = INITIAL_MONEY;
        }
        //</editor-fold>


        /**
         * Fügt dem Kontostand des {@link Ranger Rangers} einen gewissen Betrag hinzu.
         *
         * @param money Der Betrag, der dem Kontostand des {@link Ranger Rangers} hinzugefügt werden soll.
         */
        public void takeMoney(final int money) {
            this.money += money;
        }

        /**
         * Zeigt den aktuellen Betrag an Geld des {@link Ranger Rangers} an.
         */
        public void showMoney() {
            System.out.println("\n I have " + this.money + " coins.");
        }

        /**
         * Prüft, ob der {@link Ranger} Geld hat.
         *
         * @return Zufalls die Anzahl an Geld größer als 0 ist, {@code true}, ansonsten {@code false}.
         */
        public boolean hasMoney() {
            return money > 0;
        }

        /**
         * Setzt die Waffe des {@link Ranger Rangers} neu.
         *
         * @param weapon Die neue {@link WeaponType Waffe} des Rangers.
         */
        public void setWeapon(final WeaponType weapon) {
            this.weapon = weapon;
        }

        /**
         * Zeigt die aktuelle Waffe des {@link Ranger Rangers} an.
         */
        public void showWeapon() {
            System.out.println("\n Beware! I have a " + this.weapon.getDisplayName());
        }

        //<editor-fold desc="implementation">
        @Override
        public void fight(final Entity opponent) {
            // fight until somebody die
            while (opponent.isAlive() && this.isAlive()) {
                // check if selected opponent is in near
                if (isNotInNear(opponent)) return;

                // damage opponent
                opponent.suffer(this.weapon.getDamage());

                // fight this instance
                opponent.fight(this);
            }
        }
        //</editor-fold>


        //<editor-fold desc="weapon-type">

        /**
         * Ein {@link WeaponType} spiegelt einen Waffen-Typen für einen {@link Ranger} wieder.
         */
        private enum WeaponType {

            //<editor-fold desc="VALUES">
            /** Ein {@link WeaponType Messer}. */
            KNIVE(
                "Messer",
                2D
            );
            //</editor-fold>


            //<editor-fold desc="LOCAL_FIELDS">
            /** Der Anzeige-Name der Waffe. */
            private final String displayName;
            /** Die Menge an Schaden, die die Waffe bei einer Attacke verübt. */
            private final double damage;
            //</editor-fold>


            //<editor-fold desc="CONSTRUCTORS">

            /**
             * Erzeugt mithilfe eines Anzeige-Namens einen neuen und vollständig unabhängigen {@link WeaponType
             * Waffen-Typ} für einen {@link Ranger}.
             *
             * @param displayName Der Anzeige-Name der Waffe.
             * @param damage      Die Menge an Schaden, die die Waffe bei einer Attacke verübt.
             */
            WeaponType(
                final String displayName,
                final double damage
            ) {
                this.displayName = displayName;
                this.damage = damage;
            }
            //</editor-fold>


            //<editor-fold desc="Getter">

            /**
             * Getter für den Anzeige-Name.
             *
             * @return Der Anzeige-Name des {@link WeaponType Waffen-Typs}.
             */
            public String getDisplayName() {
                return this.displayName;
            }

            /**
             * Getter für die Menge an Schaden.
             *
             * @return Die Menge an Schaden, welcher dieser {@link WeaponType Waffen-Typ} verübt.
             */
            public double getDamage() {
                return this.damage;
            }
            //</editor-fold>
        }
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="OrcSoldier">

    /**
     * Ein {@link OrcSoldier Ork}.
     */
    private static final class OrcSoldier extends Entity {

        //<editor-fold desc="CONSTANTS">
        /** Die maximale Stärke, die ein {@link OrcSoldier} haben darf. */
        private static final double MAX_STRENGTH = 15D;
        /** Die Menge an Schaden, die diese Instanz einer Entität verübt. */
        private static final double DAMAGE = 2;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link OrcSoldier Orks}.
         */
        public OrcSoldier() {
            super(MAX_STRENGTH);
        }
        //</editor-fold>


        //<editor-fold desc="implementation">
        @Override
        public void fight(final Entity opponent) {
            // fight until somebody die
            while (opponent.isAlive() && this.isAlive()) {
                // check if selected opponent is in near
                if (isNotInNear(opponent)) return;

                // damage opponent
                opponent.suffer(DAMAGE);

                // fight this instance
                opponent.fight(this);
            }
        }
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="CaveTroll">

    /**
     * Ein {@link CaveTroll Troll.}
     */
    private static final class CaveTroll extends Entity {

        //<editor-fold desc="CONSTANTS">
        /** Die maximale Stärke, die ein {@link CaveTroll} haben darf. */
        private static final double MAX_STRENGTH = 5D;
        /** Die Menge an Schaden, die diese Instanz einer Entität verübt. */
        private static final double DAMAGE = 1.2;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link CaveTroll}.
         */
        public CaveTroll() {
            super(MAX_STRENGTH);
        }
        //</editor-fold>


        //<editor-fold desc="implementation">
        @Override
        public void fight(final Entity opponent) {
            // fight until somebody die
            while (opponent.isAlive() && this.isAlive()) {
                // check if selected opponent is in near
                if (isNotInNear(opponent)) return;

                // damage opponent
                opponent.suffer(DAMAGE);

                // fight this instance
                opponent.fight(this);
            }
        }
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="UrukHai">

    /**
     * Ein {@link UrukHai Hai}.
     */
    private static final class UrukHai extends Entity {

        //<editor-fold desc="CONSTANTS">
        /** Die maximale Stärke, die ein {@link UrukHai} haben darf. */
        private static final double MAX_STRENGTH = 5D;
        /** Die Menge an Schaden, die diese Instanz einer Entität verübt. */
        private static final double DAMAGE = 1.5;
        //</editor-fold>


        //<editor-fold desc="CONSTRUCTORS">

        /**
         * Erzeugt eine neue und vollständig unabhängige Instanz eines {@link UrukHai}.
         */
        public UrukHai() {
            super(MAX_STRENGTH);
        }
        //</editor-fold>


        //<editor-fold desc="implementation">
        @Override
        public void fight(final Entity opponent) {
            // fight until somebody die
            while (opponent.isAlive() && this.isAlive()) {
                // check if selected opponent is in near
                if (isNotInNear(opponent)) return;

                // damage opponent
                opponent.suffer(DAMAGE);

                // fight this instance
                opponent.fight(this);
            }
        }
        //</editor-fold>
    }
    //</editor-fold>

}
