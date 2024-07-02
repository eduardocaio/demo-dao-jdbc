package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection Conn;

    public SellerDaoJDBC(Connection conn) {
        Conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Seller id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = Conn.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentID = department.Id WHERE seller.ID = ?");


            st.setInt(1, id);
            rs = st.executeQuery();


            if(rs.next()){
                Department dep = instatiateDepartment(rs);
                Seller obj = instatiateSeller(rs, dep);
                return obj;
            }
            return null;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }

        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }


    private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setEmail(rs.getString("email"));
        obj.setBaseSalary(rs.getDouble("basesalary"));
        obj.setBirthDate(rs.getDate("birthdate"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instatiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("departmentid"));
        dep.setName(rs.getString("depname"));
        return dep;
    }



    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = Conn.prepareStatement("SELECT seller.*,department.Name as depname FROM seller INNER JOIN department ON seller.departmentid = department.id WHERE DepartmentID = ? ORDER BY name");

            st.setInt(1, department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();


            while(rs.next()){

                Department dep = map.get(rs.getInt("departmentid"));

                if(dep == null){
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("departmentid"), dep);
                }

                Seller obj = instatiateSeller(rs, dep);
                list.add(obj);
            }
            return list;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }

        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = Conn.prepareStatement("SELECT seller.*,department.Name as depname FROM seller INNER JOIN department ON seller.departmentid = department.id ORDER BY id");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();


            while(rs.next()){

                Department dep = map.get(rs.getInt("departmentid"));

                if(dep == null){
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("departmentid"), dep);
                }

                Seller obj = instatiateSeller(rs, dep);
                list.add(obj);
            }
            return list;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }

        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

}
