import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet_Login", value = "/login")
public class Servlet_Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name =request.getParameter("login");
        String pass =request.getParameter("password");
        if("dai".equals(name)&& "phan".equals(pass)){
            RequestDispatcher dispatcher=request.getRequestDispatcher("result.jsp");
            request.setAttribute("ketQua","hello Dai");

            dispatcher.forward(request,response);
        }else{
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);
        }
    }
}
