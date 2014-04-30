package untitled2;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by epic on 24.4.2014.
 */

@Entity
@Table(name="tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    int id;

    @Column(unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50, message = "Name must contain between 3 and 50 characters")
    String name;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50, message = "Surname must contain between 3 and 50 characters")
    String surname;

    @Valid
    //@OneToOne
    @Embedded
    Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
