import java.io.*;
import java.net.*;

public class seraff {

    static int a = 5;  
    static int b = 8;  

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is running...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String plainText = in.readLine();
                    
                    
                    String encryptedText = encrypt(plainText);
                    System.out.println("Received Plain Text: " + plainText);
                    System.out.println("Encrypted Text: " + encryptedText);

                    
                    out.println(encryptedText);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String text) {
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                int x = Character.toUpperCase(ch) - 'A';
                int enc = (a * x + b) % 26;
                cipher.append((char) (enc + 'A'));
            } else {
                cipher.append(ch); 
            }
        }

        return cipher.toString();
    }
}
