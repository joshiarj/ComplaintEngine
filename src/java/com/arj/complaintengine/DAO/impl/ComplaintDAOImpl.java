/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.complaintengine.DAO.impl;

import com.arj.complaintengine.DAO.ComplaintDAO;
import com.arj.complaintengine.entity.Complaint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class ComplaintDAOImpl implements ComplaintDAO {

    @Override
    public List<Complaint> getAll() throws SQLException, ClassNotFoundException {
        List<Complaint> complaintList = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/complain_engine", "root", "");
        String sql = "SELECT * FROM tbl_complain";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Complaint c = new Complaint();
            c.setId(rs.getInt("id"));
            c.setFirstName(rs.getString("firstname"));
            c.setLastName(rs.getString("lastname"));
            c.setEmail(rs.getString("email"));
            c.setContactNo(rs.getString("contactnumber"));
            c.setComplaintText(rs.getString("complaintext"));
            c.setTrackingId(rs.getString("trackingid"));
            complaintList.add(c);
        }
        conn.close();
        return complaintList;
    }

    @Override
    public int insert(Complaint complaint) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/complain_engine", "root", "");
        String sql = "INSERT INTO tbl_complain (firstname, lastname, email, contactnumber, complaintext, trackingid, status) "
                + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, complaint.getFirstName());
        stmt.setString(2, complaint.getLastName());
        stmt.setString(3, complaint.getEmail());
        stmt.setString(4, complaint.getContactNo());
        stmt.setString(5, complaint.getComplaintText());
        stmt.setString(6, complaint.getTrackingId());
        stmt.setBoolean(7, complaint.isStatus());
        int result = stmt.executeUpdate();
        conn.close();
        return result;
    }

    @Override
    public String generateTrackingID(String deptCode) throws SQLException, ClassNotFoundException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/complain_engine", "root", "");
        String sql = "SELECT * FROM tbl_complain WHERE trackingid LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%"+deptCode+"%");
        ResultSet rs = stmt.executeQuery();
        int count=0;
        while(rs.next()){
            count++;
        }
        String trackingId = year + "-" + deptCode + "-" + (count + 1);
        return trackingId;
    }
    
}
