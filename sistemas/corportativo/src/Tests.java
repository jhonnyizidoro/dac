import models.City;
import models.Employee;
import models.State;

import java.util.List;

public class Tests {
    public static void main(String[] args) {
        getCitiesByState();
    }
    
    private static void findEmployee() {
        if (Employee.auth("tesrte@teste.com", "159753")) {
            System.out.println("logou!");
        } else {
            System.out.println("Nao logou!");
        }
    }

    private static void getAllStates() {
        List<State> states = State.get();
        for (State state : states) {
            System.out.println(state.getName());
        }
    }

    private static void getCitiesByState() {
        List<City> cities = City.getByStateId(1);
    }
    
}
