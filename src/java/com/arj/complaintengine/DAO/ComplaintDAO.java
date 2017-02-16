/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.complaintengine.DAO;

import com.arj.complaintengine.entity.Complaint;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public interface ComplaintDAO {
    List<Complaint> getAll() throws SQLException, ClassNotFoundException;
    int insert(Complaint complaint) throws SQLException, ClassNotFoundException;
    String generateTrackingID(String deptCode) throws SQLException, ClassNotFoundException;
}
