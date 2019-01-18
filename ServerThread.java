import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {

    private Server server;
    private Socket socket;

    public ServerThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inputStream = new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));

            while(true) {
                String message = inputStream.readLine();
                System.out.println("Server is broadcasting...");
                server.broadcast(message);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
