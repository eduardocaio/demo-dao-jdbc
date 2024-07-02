package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;
import model.dao.impl.DepartmentDAOJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDAOJDBC(DB.getConnection());
    }

}
