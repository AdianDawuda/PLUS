package eot_schachtschneider_kibet_dawuda;

import org.geotools.ows.wms.WebMapServer;
import org.geotools.ows.wms.request.GetMapRequest;
import org.geotools.ows.wms.response.GetMapResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class WMSConnector {
    // A static BufferedImage object to hold the image data
    static BufferedImage image;

    /**
     * Retrieves a PNG image from a WMS and saves it to the filesystem
     *
     * @param wmsURL      The URL of the WMS server to retrieve the image from
     * @param boundingBox The bounding box coordinates of the area of interest in the format "minX,minY,maxX,maxY"
     * @throws Exception If an error occurs while retrieving or saving the image
     */
    public static void getPNG(String wmsURL, String boundingBox) throws Exception {
        URL url = new URL(wmsURL);
        WebMapServer WMS = new WebMapServer(url);
        GetMapRequest request = WMS.createGetMapRequest();
        String format = "image/png";
        request.setFormat(format);
        request.setDimensions("2000", "2000");
        request.setTransparent(true);
        request.addLayer("osm_auto:all", "");
        request.setSRS("EPSG:4326");
        request.setBBox(boundingBox);
        GetMapResponse response = WMS.issueRequest(request);

        // If the response is of the expected format write it to the working dir with feedback
        if (response.getContentType().equalsIgnoreCase(format)) {
            System.out.println("PNG recieved");
            image = ImageIO.read(response.getInputStream());
            File wmsFile = new File("wmsFile.png");
            ImageIO.write(image, "png", wmsFile);
        } else {
            System.out.println("PNG not recieved");
        } // End else
    } // End getPNG()
} // End class()
