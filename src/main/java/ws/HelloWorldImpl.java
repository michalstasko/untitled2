package ws;

import javax.jws.WebService;

/**
 * Created by epic on 26.4.2014.
 */
//Service Implementation
@WebService(endpointInterface = "ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }

}