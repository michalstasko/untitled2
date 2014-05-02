package weather;

import javax.jws.WebService;
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
@WebService(endpointInterface = "weather.Weather")
public class WeatherImplService implements Weather {

    private final String USER_AGENT = "Mozilla/28.0";

    private static final String WUNDERGROUND_KEY = "b9dc6112782dc357";

    private static final String WUNDERGROUND_URL_BASE = "http://api.wunderground.com/api/" + WUNDERGROUND_KEY + "/";

    private static final String WUNDERGROUND_REQUEST = WUNDERGROUND_URL_BASE + "conditions/q/SK/Bratislava.xml";

    public static void main(String[] argv) throws IOException, JAXBException {
        WeatherImplService weatherImplService = new WeatherImplService();
        weatherImplService.sendGet();
    }

    private void sendGet() throws IOException, JAXBException {
        WundergroundXmlData weatherData = this.getWeather("SK", "Bratislava");
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

    @Override
    public WundergroundXmlData getWeather(String countryCode, String cityName) throws IOException, JAXBException {

        String request = WUNDERGROUND_URL_BASE + String.format("conditions/q/%s/%s.xml", countryCode, cityName);
        URL url = new URL(request);
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
        return weatherData;
    }
}
