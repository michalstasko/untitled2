package ws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by epic on 26.4.2014.
 */
public class HelloWorldClient{

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:9999/ws/hello");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        //QName qname = new QName("http://ws/", "HelloWorldImplService");
        QName qname = new QName("http://ws/", "HelloWorldImplService");

        Service service = Service.create(url, qname);

        HelloWorld hello = service.getPort(HelloWorld.class);

        System.out.println(hello.getHelloWorldAsString("john doe"));

    }

}
