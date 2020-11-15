package encryptdecrypt;

public class UnicodeEncryptor implements Encryptor {

    @Override
    public char[] encrypt(char[] source, int key) {
        char[] cypher = new char[source.length];
        for (int i = 0; i < cypher.length; i++) {
            cypher[i] = (char) (source[i] + key);
        }
        return cypher;
    }

    @Override
    public char[] decrypt(char[] cypher, int key) {
        char[] original = new char[cypher.length];
        for (int i = 0; i < original.length; i++) {
            original[i] = (char) (cypher[i] - key);
        }
        return original;
    }
}
