
package weather;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * @author a
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Weather {

    @WebMethod
    WundergroundXmlData getWeather(String countryCode, String cityName) throws IOException, JAXBException;
}
