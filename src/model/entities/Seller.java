package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {

    private Integer Id;
    private String Name;
    private String Email;
    private Date BirthDate;
    private Double BaseSalary;

    private Department Department;


    public Seller() {

    }

    public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, model.entities.Department department) {
        Id = id;
        Name = name;
        Email = email;
        BirthDate = birthDate;
        BaseSalary = baseSalary;
        Department = department;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public Double getBaseSalary() {
        return BaseSalary;
    }

    public model.entities.Department getDepartment() {
        return Department;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public void setBaseSalary(Double baseSalary) {
        BaseSalary = baseSalary;
    }

    public void setDepartment(model.entities.Department department) {
        Department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(Id, seller.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", BirthDate=" + BirthDate +
                ", BaseSalary=" + BaseSalary +
                ", Department=" + Department +
                '}';
    }
}
