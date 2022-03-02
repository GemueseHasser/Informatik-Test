package de.jonas.informatik.encryptor;

/**
 * Mithilfe dieses {@link EncryptionHandler} findet die gesamte Verschlüsselung statt. Also dieser Handler ist
 * ausschließlich für die Verschlüsselung eines Textes zuständig.
 */
public final class EncryptionHandler {

    //<editor-fold desc="encryption">

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
        final StringBuilder defaultTextBuilder = new StringBuilder();

        // translate umlauts
        for (int i = 0; i < defaultText.length(); i++) {
            switch (defaultText.charAt(i)) {
                case 'ä':
                case 'Ä':
                    defaultTextBuilder.append("ae");
                    continue;

                case 'ö':
                case 'Ö':
                    defaultTextBuilder.append("oe");
                    continue;

                case 'ü':
                case 'Ü':
                    defaultTextBuilder.append("ue");
                    continue;

                case 'ß':
                    defaultTextBuilder.append("ss");
                    continue;

                default:
                    defaultTextBuilder.append(defaultText.charAt(i));
            }
        }

        final String translatedDefaultText = defaultTextBuilder.toString().toUpperCase();

        final StringBuilder encryptedText = new StringBuilder();

        // encrypt text
        for (int i = 0; i < translatedDefaultText.length(); i++) {
            // skip special characters
            if (translatedDefaultText.charAt(i) < 'A' || translatedDefaultText.charAt(i) > 'Z') {
                encryptedText.append(translatedDefaultText.charAt(i));
                continue;
            }

            // check if encrypted char would be out of bounds
            if (translatedDefaultText.charAt(i) + key > 'Z') {
                final int distance = 'Z' - translatedDefaultText.charAt(i) + 1;
                encryptedText.append((char) ('A' + (key - distance)));
                continue;
            }

            // basic encryption
            encryptedText.append((char) (translatedDefaultText.charAt(i) + key));
        }

        return encryptedText.toString();
    }
    //</editor-fold>

}
