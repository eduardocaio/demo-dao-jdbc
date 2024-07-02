package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOJDBC implements DepartmentDao {

    private Connection Conn;

    public DepartmentDAOJDBC(Connection conn) {
        Conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = Conn.prepareStatement("INSERT INTO department (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected>0){

                rs = st.getGeneratedKeys();

                if(rs.next()){
                    int id = rs.getInt("id");
                    obj.setId(id);
                }
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Department obj) {

        PreparedStatement st = null;

        try{
            st = Conn.prepareStatement("UPDATE department SET name = ? WHERE id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);

        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;
        try{
            st = Conn.prepareStatement("DELETE FROM department WHERE id = ?");

            st.setInt(1, id);

            st.executeUpdate();

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = Conn.prepareStatement("SELECT department.* FROM department WHERE department.ID = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()) {
                Department obj = instatianteDepartment(rs);
                return obj;
            }
            else{
                return null;
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    private Department instatianteDepartment(ResultSet rs) throws SQLException {
        Department obj = new Department();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        return obj;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = Conn.prepareStatement("SELECT * FROM department ORDER BY id");

            rs = st.executeQuery();

            List<Department> list = new ArrayList<Department>();


            while(rs.next()){
                Department dep = instatianteDepartment(rs);
                list.add(dep);
            }

            return list;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
