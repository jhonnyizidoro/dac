package models;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Calendar;
import org.hibernate.Session;
import org.hibernate.Query;
import util.HibernateUtil;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    private int id;
    private String name;
    private String email;
    private String cpf;
    private String register;
    private Calendar birthDate;
    private boolean status;
    private String password;
    private int role;
    private Address address;

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

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    @Column(name="birth_date")
    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @OneToOne
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
     public static boolean auth(String email, String password) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Employee WHERE email = :email AND password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        Employee employee = (Employee) query.uniqueResult();
        session.close();
        return employee != null;
    }

}
