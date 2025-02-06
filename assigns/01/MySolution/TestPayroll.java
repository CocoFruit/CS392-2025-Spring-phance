
public class TestPayroll {
    public static void main(String[] args){
        // Initialize some employees
        Employee e1 = new Employee();
        e1.name = "Alice";
        e1.ID = 1;
        e1.salary = 1000.0;
        Employee e2 = new Employee();
        e2.name = "Bob";
        e2.ID = 2;
        e2.salary = 2000.0;
        Employee e3 = new Employee();
        e3.name = "Charlie";
        e3.ID = 3;
        e3.salary = 3000.0;
        Employee e4 = new Employee();
        e4.name = "David";
        e4.ID = 4;
        e4.salary = 4000.0;
        Employee e5 = new Employee();
        e5.name = "Eve";
        e5.ID = 5;
        e5.salary = 5000.0;
        Payroll p1 = new Payroll();

        // add the employees to the payroll
        System.out.println("Size of payroll 1: " + p1.size());
        p1.add_employee(e1);
        p1.add_employee(e2);
        p1.add_employee(e3);
        p1.add_employee(e4);
        System.out.println("Size of payroll 1 after adds: " + p1.size());
        System.out.println("");

        // make a second payroll
        Payroll p2 = new Payroll();
        p2.add_employee(e5);
        System.out.println("Size of payroll 2: " + p2.size());
        
        // add the first payroll to the second payroll
        p2.add_payroll(p1);        
        System.out.println("Size of payroll 2 after add payroll 1: " + p2.size());

        System.out.println("");
        // print the payrolls
        System.out.println("Payroll 1:");
        p1.print();
        System.out.println("\nPayroll 2:");
        p2.print();
        System.out.println("");
        
        // remove an employee from payroll 1
        try {
            p1.remove_employee(1);
        } catch (EmployeeIndexException e) {
            System.out.println("Employee index exception caught");
        }

        System.out.println("Payroll 1 size after removing Bob: "+ p1.size());

        // print the payrolls
        System.out.println("Payroll 1:");
        p1.print();
        System.out.println("");
        
        // find an employee in payroll 1
        try {
            System.out.println("Index of Charlie in payroll 1: " + p1.find_employee("Charlie"));
        } catch (EmployeeNotFoundException e) {
            System.out.println("Employee not found exception caught");
        }

        System.out.println("");

        // copy payroll 1 to payroll 2
        p2.copy_payroll(p1);
        System.out.println("Payroll 2 after copying payroll 1:");
        p2.print();
    }
}
