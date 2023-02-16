package eot_schachtschneider_kibet_dawuda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloader {
    /**
     * Downloads a file from the specified URL and saves it to the filesystem under the provided name
     *
     * @param fileUrl  The URL of the file to be downloaded
     * @param fileName The name given to the downloaded file
     * @throws IOException If an error occurs while downloading or saving the file
     */
    public static void download(String fileUrl, String fileName) throws IOException {
        URL url = new URL(fileUrl);
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.getChannel().transferFrom(java.nio.channels.Channels.newChannel(url.openStream()), 0, Long.MAX_VALUE);
        outputStream.close();
    } // End download()
} // End class
