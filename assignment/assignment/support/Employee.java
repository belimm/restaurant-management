package assignment.support;

public interface Employee {
    default double getSalary() { return 0; }
}
