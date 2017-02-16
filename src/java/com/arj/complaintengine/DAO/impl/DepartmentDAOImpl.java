/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.complaintengine.DAO.impl;

import com.arj.complaintengine.DAO.DepartmentDAO;
import com.arj.complaintengine.entity.Department;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public List<Department> getAll() throws SQLException, ClassNotFoundException {
        List<Department> deptList = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/complain_engine", "root", "");
        String sql = "SELECT * FROM tbl_department";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Department dept = new Department();
            dept.setDeptId(rs.getInt("deptid"));
            dept.setDeptName(rs.getString("deptname"));
            dept.setDeptCode(rs.getString("deptcode"));
            deptList.add(dept);
        }
        conn.close();
        return deptList;
    }

    @Override
    public String getByDeptCode(String deptCode) throws SQLException, ClassNotFoundException {
        for (Department d: getAll()){
            if(d.getDeptCode().equalsIgnoreCase(deptCode)){
                return d.getDeptCode();
            }
        }
        return null;
    }

}
