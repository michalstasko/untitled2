package weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by epic on 30.4.2014.
 */

@XmlRootElement(name = "response")
public class WundergroundXmlData implements Serializable {

    String version;

    String termsofService;

    public WundergroundCurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    @XmlElement(name = "current_observation")
    public void setCurrentObservation(WundergroundCurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    WundergroundCurrentObservation currentObservation;

    public String getVersion() {
        return version;
    }

    @XmlElement
    public void setVersion(String version) {
        this.version = version;
    }

    public String getTermsofService() {
        return termsofService;
    }

    @XmlElement
    public void setTermsofService(String termsofService) {
        this.termsofService = termsofService;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("version=")
                .append(this.version)
                .append("\n")
                .append("termsofService=")
                .append(termsofService)
                .append("\n")
                .append("currentObservation=")
                .append(this.currentObservation)
                .append("\n")
                ;
        return sb.toString();
    }
}
