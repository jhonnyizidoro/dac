package mb;

import models.Employee;
import models.State;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "employeeMB")
@RequestScoped
public class EmployeeMB implements Serializable {

    private Employee employee;
    private List<State> states;

    @PostConstruct
    public void init() {
        this.states = State.get();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void store() {
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
