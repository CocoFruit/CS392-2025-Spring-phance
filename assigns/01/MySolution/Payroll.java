
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;

    public Payroll() {
        this.people = new Employee[INITIAL_MAXIMUM_SIZE];
        this.maximum_size = INITIAL_MAXIMUM_SIZE;
        this.current_size = 0;
    }

    public int size() {
        return this.current_size;
    }

    public void print() {
        for(int i = 0; i < this.current_size; i++){
            System.out.println(this.people[i].name + " " + this.people[i].ID + " " + this.people[i].salary);
        }
    }

    public void add_employee(Employee newbie) {
        if (this.maximum_size == this.current_size){
            Employee[] temp_people = new Employee[this.maximum_size*2];
            for(int i = 0; i < this.maximum_size; i++){
                temp_people[i] = people[i];
            }
            people = temp_people;
            this.maximum_size *= 2;
        }
        people[this.current_size] = newbie;
        this.current_size += 1;
    }

    public void remove_employee(int i) throws EmployeeIndexException {
        if(i >= this.current_size || i < 0)
            throw new EmployeeIndexException();
        for(int j = i; j < this.current_size-1; j++){
            this.people[j] = this.people[j+1];
        }
        this.people[this.current_size - 1] = null; // not necessary but I think good practice
        this.current_size -= 1;   
    }
    
    public int find_employee(String name) throws EmployeeNotFoundException {
        for(int i = 0; i < this.current_size; i++){
            if(this.people[i].name.equals(name))
                return i;
        }
        throw new EmployeeNotFoundException();
    }

    public void add_payroll(Payroll source) {
        for(int i = 0; i < source.current_size; i++){
            this.add_employee(source.people[i]);
        }
    }

    public void copy_payroll(Payroll source) {
        this.people = new Employee[source.maximum_size];
        for (int i = 0; i < source.current_size; i++) {
            this.people[i] = source.people[i];
        }
        this.maximum_size = source.maximum_size;
        this.current_size = source.current_size;
    }

    private Employee people[];
    private int maximum_size;
    private int current_size;
    
}
