import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CurrencyConverterServlet", value = "/converter")
public class CurrencyConverterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        request.setCharacterEncoding("UTF-8");
        double rate = Double.parseDouble(request.getParameter("rate"));
        double usd = Double.parseDouble(request.getParameter("usd"));

        double vnd = rate * usd;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("result.jsp");
        request.setAttribute("rate", rate);
        request.setAttribute("usd", usd);
        request.setAttribute("vnd", vnd);
        requestDispatcher.forward(request, response);
    }
}
