import sun.print.PrinterJobWrapper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ignaciosantonjamolina on 22/11/14.
 */
public class servidorWeb {
    public static void main(String args[]){
        ServerSocket ss = null;

        try {
            ss = new ServerSocket(8888);
            while(true){
            Socket s = ss.accept();
            PrintWriter toClient = new PrintWriter(s.getOutputStream());
            Scanner fromClient = new Scanner (s.getInputStream());

            String peticion = fromClient.nextLine();
            if(peticion.toLowerCase().startsWith("get")){
                toClient.print("HTTP/1.1 200 OK \r\n");
                toClient.print("Content-Type: Text/HTML \r\n\r\n");
                String nomFichero = "/Users/ignaciosantonjamolina/IdeaProjects/nuevo/src/index.html";
                File file = new File(nomFichero);
                Scanner fichero = new Scanner (file);
                while(fichero.hasNext()){
                    toClient.println(fichero.nextLine());
                }
                toClient.flush();
            }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
