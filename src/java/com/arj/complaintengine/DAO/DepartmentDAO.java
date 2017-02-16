/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.complaintengine.DAO;

import com.arj.complaintengine.entity.Department;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public interface DepartmentDAO {
    List<Department> getAll() throws SQLException, ClassNotFoundException;
    String getByDeptCode(String deptCode) throws SQLException, ClassNotFoundException;
}
