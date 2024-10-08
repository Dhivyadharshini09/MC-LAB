import java.io.*;
import java.net.*;

public class clivig{
    public static void main(String[] args){
        try(Socket socket = new Socket("localhost",12345);
        BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))){

            System.out.println("Enter plain text ");
            String text = consoleInput.readLine();
            System.out.println("Enter encryption key");
            String key = consoleInput.readLine();
            out.println(text);
            out.println(key);

            String encrypted = in.readLine();
            System.out.println("Encrypted text " + encrypted);

            String decrypted = decrypt(text,key);
            System.out.println("Decrypted text " + decrypted);

        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
    public static String decrypt(String text, String key){

        StringBuilder plain = new StringBuilder();
        key = key.toUpperCase();
        text = text.toUpperCase();

        for(int i = 0,j=0;i < text.length(); i++){
            char ch = text.charAt(i);
            if(Character.isLetter(ch)){
                int c = ch - 'A';
                int k = key.charAt(j % key.length()) - 'A';
                int p = (c-k+26) % 26;
                plain.append((char)(p + 'A'));    
            }else{
                plain.append(ch);

            }
        }
        return plain.toString();
    }
}