import java.io.*;
import java.net.Socket;

public class SocketTest {

    public static void main(String[] args) {

        //Open a socket as client
        int PortNumber = 42;
        Socket MyClient = null;
        BufferedReader input = null;//Was a data input stream
        DataOutputStream output = null;
        try {
            //Open a socket as client
            MyClient = new Socket("Machine name", PortNumber);
            //Create input stream
            input = new BufferedReader(new InputStreamReader((MyClient.getInputStream())));
            //See https://stackoverflow.com/questions/5611387/datainputstream-deprecated-readline-method
            //For why change
            //Create output stream
            output = new DataOutputStream((MyClient.getOutputStream()));
        } catch (IOException e) {
            System.out.println(e);
        }

        //Try to write data
        if (MyClient != null && output != null && input != null) {
            try {
                //Send message
                output.writeBytes("HELO\n");
                output.writeBytes("Hi there\n"); // message body
                output.writeBytes("\n.\n");
                output.writeBytes("QUIT");

                String responseLine;
                while ((responseLine = input.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("Ok") != -1) {
                        break;
                    }
                }


            } catch (IOException e) {
                System.out.println(e);
            }
        }

        //Close a socket
        try {
            output.close();
            input.close();
            MyClient.close();
        } catch (IOException e) {
            System.out.println(e);
        }


    }
}
