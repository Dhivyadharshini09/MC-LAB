import java.io.*;
import java.net.*;

public class cliaff {

    static int a = 5;  
    static int b = 8;  

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            
            System.out.print("Enter text to encrypt: ");
            String text = consoleInput.readLine();

            
            out.println(text);

            
            String encryptedText = in.readLine();
            System.out.println("Encrypted Text from Server: " + encryptedText);

            String decryptedText = decrypt(encryptedText);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String text) {
        StringBuilder plain = new StringBuilder();

        int aInverse = modInverse(a, 26);

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                int c = Character.toUpperCase(ch) - 'A';
                int p = (aInverse * (c - b + 26)) % 26;
                plain.append((char) (p + 'A'));
            } else {
                plain.append(ch); 
            }
        }

        return plain.toString();
    }

    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        throw new IllegalArgumentException("No modular inverse for the given 'a'");
    }
}
