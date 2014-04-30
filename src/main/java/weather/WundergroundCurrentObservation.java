package weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
* Created by epic on 30.4.2014.
*/
//@XmlRootElement(name = "current_observation")
public class WundergroundCurrentObservation implements Serializable {

    String station_id;

    String observation_time_rfc822;

    double temp_c;

    String forecast_url;

    String weather;

    public String getStation_id() {
        return station_id;
    }

    @XmlElement
    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getObservation_time_rfc822() {
        return observation_time_rfc822;
    }

    @XmlElement
    public void setObservation_time_rfc822(String observation_time_rfc822) {
        this.observation_time_rfc822 = observation_time_rfc822;
    }

    public double getTemp_c() {
        return temp_c;
    }

    @XmlElement
    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public String getForecast_url() {
        return forecast_url;
    }

    @XmlElement
    public void setForecast_url(String forecast_url) {
        this.forecast_url = forecast_url;
    }

    public String getWeather() {
        return weather;
    }

    @XmlElement
    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("forecast_url=")
            .append(this.getForecast_url())
            .append("\n")
            .append("observation_time_rfc822=")
            .append(this.getObservation_time_rfc822())
            .append("\n")
            .append("station_id=")
            .append(this.getStation_id())
            .append("\n")
            .append("temp_c=")
            .append(this.getTemp_c())
            .append("\n")
            .append("weather=")
            .append(this.getWeather())
            .append("\n")
            ;
        return sb.toString();
    }
}
