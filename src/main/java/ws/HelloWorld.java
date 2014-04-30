package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by epic on 26.4.2014.
 */
//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld{

    @WebMethod
    String getHelloWorldAsString(String name);

}
