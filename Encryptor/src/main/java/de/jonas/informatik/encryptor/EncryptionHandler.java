package de.jonas.informatik.encryptor;

/**
 * Mithilfe dieses {@link EncryptionHandler} findet die gesamte Verschlüsselung statt. Also dieser Handler ist
 * ausschließlich für die Verschlüsselung eines Textes zuständig.
 */
public final class EncryptionHandler {

    /**
     * Verschlüsselt einen Text mithilfe eines Schlüssels, also der Anzahl an Stellen, um den jeder einzelne Buchstabe
     * verschoben wird und gibt diesen verschlüsselten Text dann zurück.
     *
     * @param defaultText Der Text, der verschlüsselt werden soll.
     * @param key         Der Schlüssel, mit dem der Text verschlüsselt wird, also die Anzahl an Stellen, um die jeder
     *                    einzelne Buchstabe verschoben wird.
     *
     * @return Einen vollständig mit dem Schlüssel {@code key} verschlüsselter Text.
     */
    public static String encrypt(final String defaultText, final int key) {
        return defaultText;
    }

}
