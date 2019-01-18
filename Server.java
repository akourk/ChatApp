import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class Server {
    private ServerSocket server;
    private HashMap<Socket, PrintWriter> outStreams = new
            HashMap<Socket, PrintWriter>();
    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server is ready");
            while(true) {
                Socket socket = server.accept();
                //client is connected
                PrintWriter outstream = new PrintWriter(socket.getOutputStream());
                outStreams.put(socket, outstream);
                ServerThread serverThread = new ServerThread(this, socket);
                serverThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Server server = new Server(1234);
    }
    public void broadcast(String message) {
        for (Iterator iterator = outStreams.keySet().iterator(); iterator.hasNext();) {
            Socket socket = (Socket) iterator.next();
            PrintWriter outstream = outStreams.get(socket);
            outstream.println(message);
            outstream.flush();
        }

    }

}
