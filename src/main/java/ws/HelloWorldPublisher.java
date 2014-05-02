package ws;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by epic on 26.4.2014.
 */
//Endpoint publisher
public class HelloWorldPublisher{

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
    }

}
