import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketsFromBook {

    public static void main (String[] args) {
        SocketsFromBook client = new SocketsFromBook();
        client.doStuff();
    }

    public void doStuff() {
        try {
            Socket s = new Socket("127.0.0.1", 4242);

            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println("Today you should: " + advice);

            reader.close();//Close all the streams

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
