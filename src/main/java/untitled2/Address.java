package untitled2;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by epic on 25.4.2014.
 */

//@Entity
//@Table(name = "tb_address")
@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable {

    //@Id
    //@GeneratedValue
    //int id;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    String street;

    @NotNull
    @NotEmpty
    String city;

    /*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    */

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
