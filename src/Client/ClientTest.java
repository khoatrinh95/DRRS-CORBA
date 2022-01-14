package Client;

import Authentication.AuthenticationOperations;
import AuthenticationApp.Authentication;
import AuthenticationApp.AuthenticationHelper;
import DRRSApp.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class ClientTest {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            DRRS addobj = DRRSHelper.narrow(ncRef.resolve_str("Dorval"));
            Authentication authentication = AuthenticationHelper.narrow(ncRef.resolve_str("Authentication"));

            String authen = authentication.authorize("DVLA1234", true);
            System.out.println(authen);


            Scanner c=new Scanner(System.in);
            System.out.println("Welcome to the addition system:");
            String result = null;
            result = addobj.createRoom("001", "12/01/2021", "07:00", "19:00");
            System.out.println(result);

            result = addobj.createRoom("001", "12/01/2021", "19:00", "22:00");
            System.out.println(result);

            result = addobj.bookRoom("DVLS1234", "dorval", "001", "12/01/2021", 1);
            System.out.println(result);

            String bookingID = result.substring(result.length()-5);

            result = addobj.changeReservation("DVLS1234", bookingID, "dorval", "12/01/2021", "001", "2");
            System.out.println(result);

//            String[] timeslots = addobj.getTimeSlots("12/01/2021", "002", "dorval");
//            for (String s : timeslots){
//                System.out.println(s);
//            }
        }
        catch (Exception e) {
            System.out.println("Hello Client exception: " + e);
            e.printStackTrace();
        }

    }
}
