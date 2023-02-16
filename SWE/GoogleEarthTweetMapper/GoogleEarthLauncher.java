package eot_schachtschneider_kibet_dawuda;

import java.io.IOException;

public class GoogleEarthLauncher {
    // Google Earth executable path
    static String googleEarthPath = "google-earth-pro";

    /**
     * Sets the path to the Google Earth executable
     *
     * @param googleEarthPath the path to the Google Earth executable
     */
    public void setGoogleEarthPath(String googleEarthPath) {
        GoogleEarthLauncher.googleEarthPath = googleEarthPath;
    } // End setGoogleEarthPath()

    /**
     * Opens a KML file with Google Earth
     *
     * @param fileName the name of the KML file to open
     * @throws IOException if an I/O error occurs
     */
    public static void openKML(String fileName) throws IOException {
        Runtime.getRuntime().exec(googleEarthPath + " " + fileName);
        System.out.println("launching Google Earth...");
    } // End openKML()
} // End class
