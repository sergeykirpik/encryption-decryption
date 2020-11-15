package encryptdecrypt;

public interface Encryptor {

    char[] encrypt(char[] source, int key);

    char[] decrypt(char[] cypher, int key);
}
