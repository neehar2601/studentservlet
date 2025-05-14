import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        
        if ("admin".equals(username) && "admin123".equals(password)) {
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("admin", "true");

            response.sendRedirect("retrieve.html");
        } else {
            out.println("<h3>Invalid admin credentials</h3>");
            out.println("<a href='login.html'>Try Again</a>");
        }
    }
}
