import models.Employee;

public class Tests {
    public static void main(String[] args) {
        findEmployee();
    }
    
    private static void findEmployee() {
        if (Employee.auth("tesrte@teste.com", "159753")) {
            System.out.println("logou!");
        } else {
            System.out.println("Nao logou!");
        }
    }
    
}
