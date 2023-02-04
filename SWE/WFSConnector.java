import java.io.IOException;
import java.util.HashMap;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.wfs.WFSDataStore;
import org.geotools.data.wfs.WFSDataStoreFactory;
import org.opengis.feature.Feature;

public class WFSConnector {

    public static void main(String[] args)
    {
        // define the getCapabilities request
        String wfsGetCapabilitiesURL = "https://data.wien.gv.at/daten/geo?service=wfs&version=1.1.0";

        // create WFSDataStoreFactory object
        WFSDataStoreFactory wfsDSF = new WFSDataStoreFactory();

        // create HashMap and fill it with connection parameters
        HashMap connectionParameters = new HashMap();
        connectionParameters.put(WFSDataStoreFactory.URL.key, wfsGetCapabilitiesURL);
        connectionParameters.put(WFSDataStoreFactory.TIMEOUT.key, 10000);
        connectionParameters.put(WFSDataStoreFactory.ENCODING.key, "UTF-8");


        // create a DataStore object
        try {

            WFSDataStore wfsDS = wfsDSF.createDataStore(connectionParameters);

            String[] typeNames = wfsDS.getTypeNames();

            for(int i=0; i<typeNames.length; i++)
            {
                System.out.println("typeName[" + i + "]:" + typeNames[i]);
            }

            String typeName = "ogdwien:OEFFHALTESTOGD";

            // retrieve the features from the service
            SimpleFeatureSource featureSource = wfsDS.getFeatureSource(typeName);

            // parse the result/features
            SimpleFeatureCollection featureCollection = featureSource.getFeatures();

            SimpleFeatureIterator featureIterator = featureCollection.features();

            while(featureIterator.hasNext())
            {
                // print the features to the screen
                Feature currentFeature = featureIterator.next();

                //System.out.println(currentFeature);

                // for type name ogdwien:OEFFHALTESTOGD
                String line = currentFeature.getProperty("HLINIEN").getValue().toString();
                String stop = currentFeature.getProperty("HTXTK").getValue().toString();

                // print stops of desired line
                if (line.equals("99A"))
                {
                    System.out.println(line + ": " + stop);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//main
}//class
