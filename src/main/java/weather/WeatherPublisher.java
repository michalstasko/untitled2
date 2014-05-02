
package weather;

import javax.xml.ws.Endpoint;

/**
 * @author a
 */
public class WeatherPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:9999/ws/weather", new WeatherImplService());
    }
}
