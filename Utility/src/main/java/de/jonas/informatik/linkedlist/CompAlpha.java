package de.jonas.informatik.linkedlist;

import java.util.Comparator;

/**
 * Mithilfe eines {@link CompAlpha Komparator}, welcher eine Instanz eines {@link Comparator} darstellt, lassen sich
 * zwei {@link Player Spieler} alphabetisch nach ihrem Team bzw. bei gleichem Team alphabetisch nach ihrem Namen
 * vergleichen.
 */
public final class CompAlpha implements Comparator<Player> {

    //<editor-fold desc="implementation">
    @Override
    public int compare(final Player p1, final Player p2) {
        // check if both players are in the same team
        if (p1.getTeam().equalsIgnoreCase(p2.getTeam())) {
            if (p1.getName().compareToIgnoreCase(p2.getName()) < 0) return -1;
            if (p1.getName().compareToIgnoreCase(p2.getName()) > 0) return 1;

            return p1.getName().compareToIgnoreCase(p2.getName());
        }

        if (p1.getTeam().compareToIgnoreCase(p2.getTeam()) < 0) return -1;
        if (p1.getTeam().compareToIgnoreCase(p2.getTeam()) > 0) return 1;

        return p1.getTeam().compareToIgnoreCase(p2.getTeam());
    }
    //</editor-fold>
}
