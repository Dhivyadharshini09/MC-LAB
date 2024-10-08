
import java.io.*;
import java.net.*;


public class servig{
    public static  void main(String[] args){

        try(ServerSocket serverSocket = new ServerSocket(12345)){
            System.out.println("Server is running.....");

            while (true) { 
                try(Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true)){

                        String plain = in.readLine();
                        String key = in.readLine();

                        String encrypted = encrypt(plain,key);
                        System.out.println("Plain text " + plain);
                        System.out.println("Encrypted text " + encrypted);

                        out.println(encrypted);

                    }
                
            }        

        }catch(IOException e){
            e.printStackTrace();

        }

    }
    public static String encrypt(String text,String key){
        StringBuilder cipher = new StringBuilder();

        key = key.toUpperCase();
        text = text.toUpperCase();

        for(int i = 0,j=0; i < text.length(); i++){
            char ch = text.charAt(i);
            if(Character.isLetter(ch)){
                int p = ch -'A';
                int k = key.charAt(j % key.length()) - 'A';
                int c = (p+k) % 26;
                cipher.append((char)(c + 'A'));
                j++;
            }
            else{
                cipher.append(ch);
            }
        }
        return cipher.toString();
    }
}