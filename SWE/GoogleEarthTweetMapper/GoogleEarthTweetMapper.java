package eot_schachtschneider_kibet_dawuda;

import java.io.IOException;

public class GoogleEarthTweetMapper {
    // Location to store the KML file
    static String twitterKML = "/home/adian/Work/PLUS/SWE/GoogleEarthTweets.kml";

    public static void main(String[] args) {
        try {
            // Download the CSV file
            FileDownloader.download("http://www.berndresch.com/work/twitter.csv", "tweets.csv");
            String twitterCSV = "tweets.csv";
            // Parse the downloaded CSV file to get the tweet data
            CSVParser.parse(twitterCSV);
            // Set the bounding box
            KMLWriter.setBoundingBox("41.10", "40.18", "-73.40", "-74.8");
            // Get the map image using the given WMS URL and the bounding box extent
            WMSConnector.getPNG("https://maps.heigit.org/osm-wms/service?SERVICE=WMS&REQUEST=GetMap&VERSION=1.1.0", KMLWriter.boundingBox.get("west") + "," + KMLWriter.boundingBox.get("south") + "," + KMLWriter.boundingBox.get("east") + "," + KMLWriter.boundingBox.get("north"));
        } catch (Exception e) {
            // Throw a runtime exception if an error occurs
            throw new RuntimeException(e);
        } // End catch
        // Create the KML content by adding ground overlay and placemark(s) between the KML beginning and end
        String kmlContent = KMLWriter.kmlBeginning +
                KMLWriter.addGroundOverlay("/home/adian/IdeaProjects/GoogleEarthTweetMapper/wmsFile.png") +
                KMLWriter.addPlacemark(CSVParser.lng, CSVParser.lat, CSVParser.tweet, CSVParser.createdAt) +
                KMLWriter.kmlEnd;
        try {
            // Write the KML content to the specified file
            KMLWriter.writeKML(twitterKML, kmlContent);
            // Open the KML file in Google Earth
            GoogleEarthLauncher.openKML(twitterKML);
        } catch (IOException e) {
            // Throw a runtime exception if an error occurs
            throw new RuntimeException(e);
        } // End catch
    } // End main()
} // End class
