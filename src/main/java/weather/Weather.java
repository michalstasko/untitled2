package weather;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by epic on 30.4.2014.
 */
public class Weather {

    private final String USER_AGENT = "Mozilla/28.0";

    public static final String WUNDERGROUND_KEY = "b9dc6112782dc357";

    public static final String WUNDERGROUND_URL_BASE = "http://api.wunderground.com/api/" + WUNDERGROUND_KEY + "/";

    public static final String WUNDERGROUND_REQUEST = WUNDERGROUND_URL_BASE + "conditions/q/SK/Bratislava.xml";

    public static void main(String[] argv) throws IOException, JAXBException {
        Weather weather = new Weather();
        weather.sendGet();
    }

    private void sendGet() throws IOException, JAXBException {
        URL url = new URL(Weather.WUNDERGROUND_REQUEST);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        JAXBContext jaxbContext = JAXBContext.newInstance(WundergroundXmlData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        WundergroundXmlData weatherData = (WundergroundXmlData) jaxbUnmarshaller.unmarshal(in);
        System.out.println(weatherData.toString());

        //printResponseToConsole(in);



    }

    private void printResponseToConsole(BufferedReader in) throws IOException {
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }

}
