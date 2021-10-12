package ca.jrvs.practice.dataStructure;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private int age;

    public Employee() {}

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Employee abc = new Employee(55,"Phuong",22);
        Employee xyz = abc;
        Employee ooo = new Employee();
        System.out.println(abc.equals(xyz));
        System.out.println(abc.hashCode());
        System.out.println(abc.equals(ooo));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId() && getAge() == employee.getAge() && Objects.equals(getName(), employee.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge());
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
