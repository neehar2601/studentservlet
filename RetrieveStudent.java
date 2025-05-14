import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RetrieveStudent extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || !"true".equals(session.getAttribute("admin"))) {
            response.sendRedirect("login.html");
            return;
        }

        int studentId = Integer.parseInt(request.getParameter("student_id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/StudentDB", "neehara", "Nee@20");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Students WHERE registration_number = ?");
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h3>Student Details</h3>");
                out.println("Registration Number: " + rs.getString("registration_number") + "<br>");
                out.println("Name: " + rs.getString("name") + "<br>");
                out.println("Email: " + rs.getString("email") + "<br>");
                out.println("Course: " + rs.getString("course") + "<br>");
            } else {
                out.println("<h3>No student found with ID: " + studentId + "</h3>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }

        out.println("<br><a href='index.html'>Back to Home</a>");
    }
}
