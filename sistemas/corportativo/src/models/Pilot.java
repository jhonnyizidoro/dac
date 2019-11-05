package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "pilots")
public class Pilot implements Serializable {
    private int id;
    private String name;
    private String email;
    private String cpf;
    private Calendar birthDate;
    private String brevet;
    private boolean status;
    private Address address;
    private List<Flight> flights;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name="birth_date")
    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getBrevet() {
        return brevet;
    }

    public void setBrevet(String brevet) {
        this.brevet = brevet;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "pilot")
    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
