package de.jonas.informatik.linkedlist;

import java.util.Comparator;

/**
 * Mithilfe eines {@link CompV Komparator}, welcher eine Instanz eines {@link Comparator} darstellt, lassen sich zwei
 * {@link Player Spieler} basierend auf ihrem Wert vergleichen.
 */
public final class CompV implements Comparator<Player> {

    //<editor-fold desc="implementation">
    @Override
    public int compare(Player p1, Player p2) {
        return Double.compare(p1.getValue(), p2.getValue());
    }
    //</editor-fold>
}
