package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {

    public static void main(String[] args) {


        Scanner ler = new Scanner(System.in);

        DepartmentDao departmentDAO = DaoFactory.createDepartmentDao();

        System.out.println("=====TEST 1: insert department=====");
        Department dep = new Department("Fridge");
        departmentDAO.insert(dep);
        System.out.println("INSERTED! ID: " + dep.getId());



        System.out.println("=====TEST 2: findById department=====");
        Department departmentId = departmentDAO.findById(5);
        System.out.println(departmentId);

        System.out.println("=====TEST 3: update department=====");
        Department up = departmentDAO.findById(9);
        up.setName("Animals");
        departmentDAO.update(up);
        System.out.println("UPDATED!");

        System.out.println("=====TEST 4: delete department=====");
        System.out.println("Enter ID department to delete: ");
        int id = ler.nextInt();
        ler.nextLine();

        departmentDAO.deleteById(id);
        System.out.println("DELETED!");
        

        System.out.println("=====TEST 4: findAll department=====");
        List<Department> departments = departmentDAO.findAll();
        for (Department department : departments) {
            System.out.println(department);
        }




    }

}
