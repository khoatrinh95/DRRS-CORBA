package Client;

import AuthenticationApp.Authentication;
import AuthenticationApp.AuthenticationHelper;
import DRRSApp.DRRS;
import DRRSApp.DRRSHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Scanner;

public class ClientAdmin {
    static ORB orb;
    static org.omg.CORBA.Object objRef;
    static NamingContextExt ncRef;
    static DRRS drrs;
    static AdminOperations adminOperations;
    public static void main(String[] args) {
        // authenticate user
        String campusName = null;
        do {
            try {
                orb = ORB.init(args, null);
                objRef =   orb.resolve_initial_references("NameService");
                ncRef = NamingContextExtHelper.narrow(objRef);
                Authentication authentication = AuthenticationHelper.narrow(ncRef.resolve_str("Authentication"));
                adminOperations = new AdminOperations(authentication);
                campusName = adminOperations.authenticateAdmin();

                if (campusName.isEmpty()){
                    System.out.println("Cannot authenticate admin");
                }
            } catch (Exception e) {
                System.out.println("Cannot authenticate admin");
            }
        } while (campusName.isEmpty());

        String adminId = adminOperations.getAdminId();
        // connect user to their server
        try {
            drrs = DRRSHelper.narrow(ncRef.resolve_str(campusName));
            adminOperations = new AdminOperations(drrs, adminId);
            adminOperations.askActionFromAdmin(campusName);
        } catch (Exception e) {
            System.out.println("Cannot connect user to server");
        }

    }
}
