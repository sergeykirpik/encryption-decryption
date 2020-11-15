package encryptdecrypt;

import java.io.*;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = Configuration.parse(args);
        String mode = config.getMode();
        char[] text = getText(config).toCharArray();
        int key = config.getKey();
        Encryptor encryptor = config.getEncryptor();

        char[] result = new char[0];
        if ("enc".equals(mode)) {
            result = encryptor.encrypt(text, key);
        }
        if ("dec".equals(mode)) {
            result = encryptor.decrypt(text, key);
        }
        try (PrintStream out = getPrintStream(config)) {
            out.println(result);
        }
    }

    private static String getText(Configuration config) throws IOException {
        if (config.getInFile() != null && config.getData().isEmpty()) {
            return Files.readString(config.getInFile().toPath());
        }
        return config.getData();
    }

    private static PrintStream getPrintStream(Configuration config) {
        PrintStream out = System.out;
        if (config.getOutFile() != null) {
            try {
                out = new PrintStream(new FileOutputStream(config.getOutFile()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return out;
    }
}

class Configuration {
    private String mode = "enc";
    private int key = 0;
    private String data = "";
    private File inFile;
    private File outFile;
    private Encryptor encryptor = new ShiftEncryptor();

    private Configuration() { }

    public String getMode() {
        return mode;
    }

    public int getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public File getInFile() {
        return inFile;
    }

    public File getOutFile() {
        return outFile;
    }

    public Encryptor getEncryptor() {
        return encryptor;
    }

    public static Configuration parse(String[] args) {
        Configuration p = new Configuration();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    p.mode = args[i + 1];
                    break;
                case "-key":
                    p.key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    p.data = args[i + 1];
                    break;
                case "-in":
                    p.inFile = new File(args[i + 1]);
                    break;
                case "-out":
                    p.outFile = new File(args[i + 1]);
                    break;
                case "-alg":
                    switch (args[i + 1]) {
                        case "shift":
                            p.encryptor = new ShiftEncryptor();
                            break;
                        case "unicode":
                            p.encryptor = new UnicodeEncryptor();
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
        return p;
    }
}