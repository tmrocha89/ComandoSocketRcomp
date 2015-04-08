package socketcomando;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author root
 */
public class SocketComando {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServerSocket serverSocket = null; //import java.net.ServerSocket;
        Socket socketClient = null; //import java.net.Socket;
        BufferedReader br = null;
        boolean continuar = true;
        try {
            /**
             * Crio um ServerSocket com um numero de porto 0 ("aleatorio")
             * Aceito 1 ligacao
             * E o servidor corre na minha maquina (localhost)
             */
            serverSocket = //new ServerSocket(1234, 1, InetAddress.getLocalHost());
                                        new ServerSocket(1234);
                    
            System.out.printf("IP:%s\nPorto:%s\n\n ",
                    serverSocket.getInetAddress().getHostAddress(),
                    serverSocket.getLocalPort());
            
            while(continuar){

                System.out.println("A espera de ligacao do host");
                socketClient = serverSocket.accept();
                System.out.printf("[Connected] %s\n",
                        socketClient.getInetAddress().getHostName());
                br = new BufferedReader(
                        new InputStreamReader(socketClient.getInputStream()));

                char op = (char)br.read();
                System.out.printf("[Client] %c\n",op);
                switch (op){
                    case 'n':   next(); break;
                    case 'p':   previous(); break;
                    case 'x':   continuar = false; break;
                    default:    System.out.println("what???");
                }
                socketClient.close(); 
            }
            serverSocket.close();
        } catch (IOException ex) {
            System.out.printf("Esquece lá isso das sockets\n%s",ex.getMessage());
        }
    }
    
    
    static private void previous(){
        try {
            System.out.println("previous");
            Robot r = new Robot();
            r.delay(1000);
            r.keyPress(KeyEvent.VK_LEFT);
            r.keyRelease(KeyEvent.VK_LEFT);
        } catch (AWTException ex) {
            System.out.println("Nao foi possivel executar o comando");
        }
    }
    
    static private void next(){
        try {
            System.out.println("Next");
            Robot r = new Robot();
            r.delay(1000);
            r.keyPress(KeyEvent.VK_RIGHT);
            r.keyRelease(KeyEvent.VK_RIGHT);
        } catch (AWTException ex) {
            System.out.println("Nao foi possivel executar o comando");
        }
    }
}
