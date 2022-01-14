package Authentication;

import AuthenticationApp.AuthenticationPOA;
import Business.Campus;
import org.omg.CORBA.ORB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class AuthenticationOperations extends AuthenticationPOA {
    private ORB orb;
    private List<Campus> campuses = new ArrayList<>();
    private Logger log;

    public AuthenticationOperations() {
        super();
        Campus campusWestmount = new Campus("Westmount", 10, "WST");
        Campus campusKirkland = new Campus("Kirkland", 20, "KKL");
        Campus campusDorval = new Campus("Dorval", 30, "DVL");
        campuses.add(campusWestmount);
        campuses.add(campusDorval);
        campuses.add(campusKirkland);

        startLogger();

        log.info("Authentication Server created");
    }

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    @Override
    public String authorize(String id, boolean isAdmin) {
        if (id.length()!= 8)
            return "";
        String campus = id.substring(0,3);
        String role = id.substring(3,4);
        String number = id.substring(4);
        if (!(campus.equalsIgnoreCase("wst") || campus.equalsIgnoreCase("kkl") || campus.equalsIgnoreCase("dvl")))
            return "";
        if (!(isInteger(number)))
            return "";

        if (isAdmin){
            if (!(role.equalsIgnoreCase("a")))
                return "";
        } else {
            if (!(role.equalsIgnoreCase("s")))
                return "";
        }

        for (Campus c : campuses){
            if (c.getCode().equalsIgnoreCase(campus))
                return c.getName();
        }
        return "";
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }

    private void startLogger() {
        try {
            log =  Logger.getLogger("Authentication Server");
            log.setUseParentHandlers(false);
            String loggerFileName = "src/Logger/AUTHENTICATION.log";
            FileHandler fileHandler = new FileHandler(loggerFileName, true);
            log.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
