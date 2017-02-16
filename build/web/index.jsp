<%@page import="com.arj.complaintengine.entity.Complaint"%>
<%@page import="com.arj.complaintengine.entity.Department"%>
<%@page import="com.arj.complaintengine.DAO.impl.DepartmentDAOImpl"%>
<%@page import="com.arj.complaintengine.DAO.DepartmentDAO"%>
<%@page import="com.arj.complaintengine.DAO.impl.ComplaintDAOImpl"%>
<%@page import="com.arj.complaintengine.DAO.ComplaintDAO"%>
<%
    String message = "";
    String errorIcon = "<font color='red'><span class='glyphicon glyphicon-info-sign'></span> ";
    ComplaintDAO complaintDAO = new ComplaintDAOImpl();
    DepartmentDAO deptDAO = new DepartmentDAOImpl();
    if (request.getMethod().equalsIgnoreCase("post")) {
        if (!request.getParameter("firstname").equals("") && !request.getParameter("lastname").equals("")
                && !request.getParameter("email").equals("") && !request.getParameter("contactno").equals("")
                && !request.getParameter("complainttext").equals("")) {
            if (!request.getParameter("deptcode").equalsIgnoreCase("deptselect")) {
                Complaint c = new Complaint();
                c.setFirstName(request.getParameter("firstname"));
                c.setLastName(request.getParameter("lastname"));
                c.setEmail(request.getParameter("email"));
                c.setContactNo(request.getParameter("contactno"));
                c.setComplaintText(request.getParameter("complainttext"));
                c.setTrackingId(complaintDAO.generateTrackingID(request.getParameter("deptcode")));
                c.setStatus(false);
                int result = complaintDAO.insert(c);
                if (result > 0) {
                    message = "<font color='green'><span class='glyphicon glyphicon-ok'></span> "
                            + "Your complaint has been successfully registered! Your tracking ID is: "
                            + c.getTrackingId() + "</font>";
                }
            } else {
                message = errorIcon + "Please select a department and try again.</font>";
            }
        } else {
            message = errorIcon + "Field(s) cannot be left blank! Please try again.</font>";
        }
    }
%>
<%@include file="shared/header.jsp" %>
<h3><center>Add new complaint</center></h3>
<div>
    <%=message%>
</div>
<form method="post" action="">
    <div class="form-group">
        <label>First name<font color="red">*</font>:</label>
        <input type="text" name="firstname" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Last name<font color="red">*</font>:</label>
        <input type="text" name="lastname" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Valid Email Address<font color="red">*</font>:</label>
        <input type="text" name="email" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Contact No.<font color="red">*</font>:</label>
        <input type="number" name="contactno" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Department<font color="red">*</font>:</label>
        <select name="deptcode">
            <option value="deptselect">Select a Department</option>
            <%
                for (Department d : deptDAO.getAll()) {%>
            <option value=<%=d.getDeptCode()%>><%=d.getDeptName()%></option>
            <% }%>
        </select>
    </div>
    <div class="form-group">
        <label>Complaint Message<font color="red">*</font>:</label>
        <!--<input type="text" name="complainttext" class="form-control"/>-->
        <div><textarea name="complainttext" rows="5" cols="75" placeholder="Enter complaint here..."></textarea></div>
    </div>
    <button type="submit" class="btn btn-success"><b>Submit complaint</b>
        <!--<span class="glyphicon glyphicon-plus"></span>--> 
    </button>
</form>
<%@include file="shared/footer.jsp" %>