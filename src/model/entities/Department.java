package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private Integer Id;
    private String Name;

    public Department() {
    }

    public Department(String name) {
        Name = name;
    }

    public Department(String name, Integer id) {
        Name = name;
        Id = id;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }

    @Override
    public String toString() {
        return "Department{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                '}';
    }


}