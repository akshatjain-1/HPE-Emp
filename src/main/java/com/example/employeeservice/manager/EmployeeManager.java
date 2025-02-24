@Component
public class EmployeeManager {
    @Autowired
    private Employees employees;

    @PostConstruct
    public void init() {
        employees.setEmployeeList(Arrays.asList(
            new Employee("1", "John", "Doe", "john.doe@example.com", "Developer"),
            new Employee("2", "Jane", "Doe", "jane.doe@example.com", "Manager")
        ));
    }
}
