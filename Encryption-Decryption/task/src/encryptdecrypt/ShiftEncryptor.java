package encryptdecrypt;

import java.util.Arrays;

public class ShiftEncryptor implements Encryptor {

    static private final String[] circles = {
        "abcdefghijklmnopqrstuvwxyz",
        "abcdefghijklmnopqrstuvwxyz".toUpperCase(),
    };

    public char[] encrypt(char[] source, int key) {
        char[] cypher = Arrays.copyOf(source, source.length);
        for (int i = 0; i < cypher.length; i++) {
            for (String circle: circles) {
                int charIndex = circle.indexOf(source[i]);
                if (charIndex != -1) {
                    cypher[i] = shiftForward(circle, charIndex, key);
                }
            }
        }
        return cypher;
    }

    @Override
    public char[] decrypt(char[] cypher, int key) {
        char[] original = Arrays.copyOf(cypher, cypher.length);
        for (int i = 0; i < cypher.length; i++) {
            for (String circle: circles) {
                int charIndex = circle.indexOf(cypher[i]);
                if (charIndex != -1) {
                    original[i] = shiftBackward(circle, charIndex, key);
                }
            }
        }
        return original;
    }

    private char shiftForward(String circle, int charIndex, int key) {
        int newIndex = (charIndex + key) % circle.length();
        return circle.charAt(newIndex);
    }

    private char shiftBackward(String circle, int charIndex, int key) {
        int newIndex = (charIndex - key) % circle.length();
        while (newIndex < 0) {
            newIndex += circle.length();
        }
        return circle.charAt(newIndex);
    }
}