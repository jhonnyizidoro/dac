package models;

import org.hibernate.Session;
import util.HibernateUtil;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Entity
@Table(name = "states")
public class State implements Serializable {
    private int id;
    private String name;
    private String code;
    private List<City> cities;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @OneToMany(mappedBy = "state")
    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public static List<State> get() {
        Session session = HibernateUtil.getSession();
        CriteriaQuery<State> criteriaQuery = session.getCriteriaBuilder().createQuery(State.class);
        criteriaQuery.from(State.class);
        List<State> states = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return states;
    }
}
