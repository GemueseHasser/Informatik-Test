package de.jonas.informatik.constants;

/**
 * Ein {@link JumpState} stellt den Status des Springens dar. Das Springen gliedert sich in drei Bereiche: das
 * Hochspringen, das in der Luft fliegen und das wieder auf den Boden fallen.
 */
public enum JumpState {

    //<editor-fold desc="VALUES">
    /** Der standard {@link JumpState}, welcher beschreibt, dass gerade kein Sprung ausgeführt wird. */
    DEFAULT,
    /** Der {@link JumpState}, der das Hochspringen beschreibt. */
    JUMPING,
    /** Der {@link JumpState}, der das in der Luft fliegen beschreibt. */
    FLYING,
    /** Der {@link JumpState}, der das wieder auf den Boden fallen beschreibt. */
    FALLING
    //</editor-fold>

}
