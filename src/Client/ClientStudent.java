package Client;

import AuthenticationApp.Authentication;
import AuthenticationApp.AuthenticationHelper;
import Business.Campus;
import DRRSApp.DRRS;
import DRRSApp.DRRSHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

public class ClientStudent {
    static ORB orb;
    static org.omg.CORBA.Object objRef;
    static NamingContextExt ncRef;
    public static void main(String args[]) {
        String studentId = null;
        Authentication authInterface = null;
        DRRS drrs = null;
        Scanner scanner = new Scanner(System.in);
        String campusName = "";

        // Ask if they want to see timeslots or book
        // if timeslot -> establish udp here
        boolean signIn = false;
        while(!signIn){
            System.out.println("What would you like to do?");
            System.out.println("\t1. Get available time slots");
            System.out.println("\t2. Sign in");
            System.out.println("\t3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    // establish udp
                    try{
                        getAvailableTimeSlots(scanner);
                    }catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    signIn = true;
                    break;
                case 3:
                    System.out.println("System shutting down...");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. System shut down...");
            }
        }

        // connect to authentication server
        try {
            orb = ORB.init(args, null);
            objRef =   orb.resolve_initial_references("NameService");
            ncRef = NamingContextExtHelper.narrow(objRef);
            authInterface = AuthenticationHelper.narrow(ncRef.resolve_str("Authentication"));

        } catch (Exception e) {
            System.out.println("Cannot connect to the authentication server");
        }

        StudentOperations studentOperations = new StudentOperations(authInterface);

        // authenticate student
        do {
            try {
                System.out.println("Please enter your student ID: ");
                studentId = scanner.nextLine();
                campusName = studentOperations.authenticateStudent(studentId);
            } catch (Exception e) {
                System.out.println("Cannot authenticate student");
            }

            if (campusName.isEmpty())
                System.out.println("cannot find campus associated with this student");
            else {
                // connect student to campus
                try {
                    drrs = DRRSHelper.narrow(ncRef.resolve_str(campusName));
                    studentOperations = new StudentOperations(drrs, studentId);
                } catch (Exception e) {
                    System.out.println("Cannot connect to campus");
                    return;
                }
            }
        }
        while (campusName.isEmpty());

        // Ask actions from student
        studentOperations.askActionFromStudent(studentId);


    } //end main

    private static void getAvailableTimeSlots(Scanner scanner) throws IOException {

        System.out.println("Please enter the date (MM/DD/YYYY): ");
        String input = scanner.nextLine();

        // pack request to send over udp
        HashMap<String, Object> udpHashMap = new HashMap<>();
        udpHashMap.put("action", "getAvailableTimeSlots");
        udpHashMap.put("date", input);

        // set up the udp connection - connect to port 8000
        DatagramSocket socket = new DatagramSocket();

        // make packet and send
        byte[] outgoing = serialize(udpHashMap);
        DatagramPacket outgoingPacket = new DatagramPacket(outgoing, outgoing.length, InetAddress.getLocalHost(), 8000);
        socket.send(outgoingPacket);

        // receive request
        byte[] incoming = new byte[1000];
        DatagramPacket incomingPacket = new DatagramPacket(incoming, incoming.length);
        socket.receive(incomingPacket);

        StringBuilder result = data(incoming);

        if (result.length() == 0)
            System.out.println("No record found on " + input + "\n");
        else {
            System.out.println("Here are the records of available time slots on "+ input +": ");
            System.out.println(result);
        }

    }

    private static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    private static byte[] serialize(Object obj) throws IOException {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }
}
