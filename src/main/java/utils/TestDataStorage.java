package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataStorage {
    private static final String FILE_PATH = "test-output/lastGeneratedEmail.txt";

    public static void setLastGeneratedEmail(String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLastGeneratedEmail() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {
                System.out.println("No email found. Make sure SignUpTest runs before LoginTest.");
                return null;
            }
            return new String(Files.readAllBytes(Paths.get(FILE_PATH))).trim();
        } catch (IOException e) {
            return null;
        }
    }
}
