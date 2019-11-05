package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {
    private int id;
    private Calendar departureTime;
    private Calendar arrivalTime;
    private float economicValue;
    private float firstClassValue;
    private boolean status;
    private Airport airport;
    private Pilot pilot;
    private List<Order> orders;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Calendar departureTime) {
        this.departureTime = departureTime;
    }

    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Calendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public float getEconomicValue() {
        return economicValue;
    }

    public void setEconomicValue(float economicValue) {
        this.economicValue = economicValue;
    }

    public float getFirstClassValue() {
        return firstClassValue;
    }

    public void setFirstClassValue(float firstClassValue) {
        this.firstClassValue = firstClassValue;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "airport_id")
    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @ManyToOne
    @JoinColumn(name = "pilot_id")
    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    @OneToMany(mappedBy = "flight")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
