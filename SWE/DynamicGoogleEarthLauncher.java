import java.io.FileWriter;
import java.io.IOException;

public class DynamicGoogleEarthLauncher {

    static String[] coordinates = {"13.03978,47.82281", "13.06024,47.78861", "13.03364,47.80569", "13.05354,47.80628", "13.04747,47.81912"};

    public static void main(String[] args) throws IOException {
        String placeMarkBeginning = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
                "<Document>";
        String placemarkEnd = "</Document>\n" +
                "</kml>";
        //combine all kml elements
        String fileContent = placeMarkBeginning + addPlacemark() + placemarkEnd;
        //Write to the kml file
        FileWriter writer = new FileWriter("/home/adian/Work/PLUS/SWE/unit3dynamic.kml");
        writer.write(fileContent);
        writer.flush();
        writer.close();
        String googleEarthPath = "google-earth-pro";
        String kml1 = "/home/adian/Work/PLUS/SWE/unit3dynamic.kml";
        //open the kml file with google earth
        try {
            Runtime.getRuntime().exec(googleEarthPath + " " + kml1);
            System.out.println("launching Google Earth...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //add coordinates from the string[] into the variable "placemark"
    public static String addPlacemark() {
        String placemark = "";
        for (int i = 0; i < coordinates.length; i++) {
            int point = i+1;
            placemark += "<Placemark>\n" +
                    "    <name>Point " + point + "</name>\n" +
                    "    <description>placemark</description>\n" +
                    "    <Point>\n" +
                    "\t    <coordinates>" + coordinates[i] + "</coordinates>\n" +
                    "    </Point>\n" +
                    "  </Placemark>\n";
        }
        return placemark;
    }
}
