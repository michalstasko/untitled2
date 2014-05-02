/**
 * Copyright (c) 2013 Cleverlance Enterprise Solutions a.s.
 * http://www.cleverlance.com
 * All Rights Reserved.
 */

package weather;

import javax.xml.ws.Endpoint;

/**
 * @author tsenko
 */
public class WeatherPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:9999/ws/weather", new WeatherImplService());
    }
}
