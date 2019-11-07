package models;

import com.google.gson.annotations.Expose;
import org.hibernate.Session;
import org.hibernate.Query;
import util.HibernateUtil;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
public class City implements Serializable {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private State state;
    private List<Address> addresses;
    private List<Airport> airports;

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

    @ManyToOne
    @JoinColumn(name = "state_id")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @OneToMany(mappedBy = "city")
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(mappedBy = "city")
    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public static List<City> getByStateId(int stateId) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM City WHERE state.id = :stateId");
        query.setParameter("stateId", stateId);
        @SuppressWarnings("unchecked")
        List<City> cities = query.list();
        return cities;
    }
}
