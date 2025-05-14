import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String regNo = request.getParameter("reg_no");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/StudentDB", "neehara", "Nee@20");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Students (registration_number, name, email, course) VALUES (?, ?, ?, ?)");
            ps.setString(1, regNo);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, course);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3>Student Registered Successfully!</h3>");
            } else {
                out.println("<h3>Registration Failed</h3>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }

        out.println("<br><a href='index.html'>Back to Home</a>");
    }
}
