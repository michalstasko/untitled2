
package weather;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author a
 */
public class WeatherClient {
    public static void main(String[] args) throws Exception {

        // this url connects to the manually exported webservice
        //URL url = new URL("http://localhost:9999/ws/weather");
        //URL url = new URL("http://10.15.2.19:9999/ws/weather");

        // this url connects the spring exported webservice
        //URL url = new URL("http://localhost:8081/ws/weather");
        URL url = new URL("http://10.0.0.13:8081/ws/weather");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        //QName qname = new QName("http://ws/", "HelloWorldImplService");
        QName qname = new QName("http://weather/", "WeatherImplServiceService");

        Service service = Service.create(url, qname);

        Weather weather = service.getPort(Weather.class);

        System.out.println("Current weather is:");
        System.out.println(weather.getWeather("Slovakia", "Bratislava").toString());
        System.out.println(weather.getWeather("Austria", "Vienna").toString());

    }
}
