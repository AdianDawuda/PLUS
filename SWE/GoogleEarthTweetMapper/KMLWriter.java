package eot_schachtschneider_kibet_dawuda;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static eot_schachtschneider_kibet_dawuda.CSVParser.userID;

public class KMLWriter {
    // Beginning part of KML file
    static String kmlBeginning = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
            "<Document>\n";
    // End part of KML file
    static String kmlEnd = "</Document>\n" +
            "</kml>";
    // HashMap for storing the bounding box values
    static HashMap<String, String> boundingBox = new HashMap<>();


    /**
     * Writes content to a KML file with the specified file name
     *
     * @param fileName   the name of the file to write to
     * @param kmlContent the content to write to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void writeKML(String fileName, String kmlContent) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(kmlContent);
        writer.flush();
        writer.close();
    } // End writeKML()

    /**
     * Sets the boundingBox variable
     *
     * @param north the northernmost latitude of the bounding box
     * @param south the southernmost latitude of the bounding box
     * @param east  the easternmost longitude of the bounding box
     * @param west  the westernmost longitude of the bounding box
     */
    public static void setBoundingBox(String north, String south, String east, String west) {
        boundingBox.put("north", north);
        boundingBox.put("south", south);
        boundingBox.put("east", east);
        boundingBox.put("west", west);
    } // End setBoundingBox()

    /**
     * Creates a placemark with the given longitude, latitude, tweet content, and creation time for each coordinate provided
     *
     * @param lng       an ArrayList of longitudes
     * @param lat       an ArrayList of latitudes
     * @param tweet     an ArrayList of tweets
     * @param createdAt an ArrayList of tweet creation times
     * @return the placemark(s) with the provided information
     */
    public static String addPlacemark(ArrayList<String> lng, ArrayList<String> lat, ArrayList<String> tweet, ArrayList<String> createdAt) {
        String placemark = "";
        // Loop through all tweet data in the ArrayLists
        for (int i = 0; i < lng.size(); i++) {
            int placemarkNumber = i + 1;
            placemark += "\t\t<Placemark>\n" +
                    "\t\t\t<name>User ID: " + userID.get(i) + "</name>\n" +
                    "\t\t\t<description>" + tweet.get(i) + "\nTweet No. " + placemarkNumber + "\n Created at: " + createdAt.get(i) + "</description>\n" +
                    "\t\t\t<Style>\n" +
                    "\t\t\t<IconStyle>\n" +
                    "\t\t\t<scale>1.3</scale>\n" +
                    "\t\t\t<color></color>" +
                    "\t\t\t<Icon>\n" +
                    "\t\t\t<href>https://felixschachtschneider.com/twitterLogo.png</href>\n" +
                    "\t\t\t</Icon>\n" +
                    "\t\t\t</IconStyle>\n" +
                    "\t\t\t</Style>\n" +
                    "\t\t\t<Point>\n" +
                    "\t\t\t<extrude>1</extrude>\n" +
                    "\t\t\t<altitudeMode>relativeToGround</altitudeMode>\n" +
                    "\t\t\t\t<coordinates>" + lng.get(i) + "," + lat.get(i) + ",100</coordinates>\n" +
                    "\t\t\t</Point>\n" +
                    "<TimeStamp>\n" +
                    "<when>" + createdAt.get(i).substring(0, 10) + "T" + createdAt.get(i).substring(11, 22) + "</when>\n" +
                    "</TimeStamp>\n" +
                    "\t\t</Placemark>\n";
        }
        // Place all placemarks in a KML Folder element
        placemark = "<Folder>\n" +
                "\t<name>Tweets</name>\n" +
                "\t<description>Placemarks of tweets</description>\n" +
                placemark +
                "</Folder>\n";
        return placemark;
    } // End addPlacemark()

    /**
     * Creates a KML ground overlay element with a specified image
     *
     * @param overlayImage a string containing the file path or URL of the image to be used as the overlay
     * @return a string containing the KML ground overlay element
     */
    public static String addGroundOverlay(String overlayImage) {
        String groundOverlay = "<Folder>\n" +
                "\t<name>Ground Overlays</name>\n" +
                "\t<description>Ground overlays for the map.</description>\n" +
                "\t<GroundOverlay>\n" +
                "\t\t<name>OSM</name>\n" +
                "\t\t<description>Overlay of OpenStreetMaps at 30% opacity.</description>\n" +
                "\t\t<color>b3b3b3b3</color>\n" +
                "\t\t<Icon>\n" +
                "\t\t\t<href>" + overlayImage + "</href>\n" +
                "\t\t</Icon>\n" +
                "\t\t<LatLonBox>\n" +
                "\t\t\t<north>" + boundingBox.get("north") + "</north>\n" +
                "\t\t\t<south>" + boundingBox.get("south") + "</south>\n" +
                "\t\t\t<east>" + boundingBox.get("east") + "</east>\n" +
                "\t\t\t<west>" + boundingBox.get("west") + "</west>\n" +
                "\t\t\t<rotation></rotation>\n" +
                "\t\t</LatLonBox>\n" +
                "\t</GroundOverlay>\n" +
                "</Folder>\n";
        return groundOverlay;
    } // End addGroundOverlay()
} // End class
