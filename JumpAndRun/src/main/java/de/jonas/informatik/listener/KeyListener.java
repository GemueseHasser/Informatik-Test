package de.jonas.informatik.listener;

import de.jonas.informatik.Game;

import java.awt.event.KeyEvent;

/**
 * Der {@link KeyListener} stellt eine Instanz eines {@link java.awt.event.KeyListener} dar. Dieser Listener wird immer
 * dann getriggert, wenn eine beliebige Taste auf der Tastatur gedrückt wird. Mithilfe dieses Listeners wird die
 * Steuerung des Spiels implementiert.
 */
public final class KeyListener implements java.awt.event.KeyListener {

    //<editor-fold desc="implementation">
    @Override
    public void keyTyped(final KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        // check if user pressed jump key
        if (keyEvent.getKeyCode() != KeyEvent.VK_UP && keyEvent.getKeyCode() != KeyEvent.VK_SPACE) return;

        // jump
        Game.getGameInstance().getPlayer().initJump();
    }

    @Override
    public void keyReleased(final KeyEvent keyEvent) {

    }
    //</editor-fold>
}
