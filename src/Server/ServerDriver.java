package Server;

import UDP.UdpOperations;
import org.omg.CORBA.ORB;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class ServerDriver {
    private static ORB orb;
    public static void main(String args[]) {

        // Starting Authentication and DRRS servers
        DRRSAndAuthenticationThread drrsAndAuthenticationThread = new DRRSAndAuthenticationThread(orb, args);
        Thread serverThread = new Thread(drrsAndAuthenticationThread);
        serverThread.start();

        // Starting UDP server
        try{
            DatagramSocket ds = new DatagramSocket(8000);
            byte[] receive = new byte[65535];
            DatagramPacket dpReceive = null;
            System.out.println("The UDP server started on port 8000");
            System.out.println("UDP server running...");
            while(true) {
                dpReceive = new DatagramPacket(receive, receive.length);
                try {
                    ds.receive(dpReceive);
                    UdpOperations udpOperations = new UdpOperations(ds, dpReceive);
                    udpOperations.start();
                } catch (IOException e) {
                    System.out.println("Error receiving packet.\nMessage: " + e.getMessage());
                }
            }
        } catch (SocketException e){
            System.out.println("Error starting UDP server. \nMessage: " + e.getMessage());
        }

        System.out.println("UDP Exiting ...");

    }

}
