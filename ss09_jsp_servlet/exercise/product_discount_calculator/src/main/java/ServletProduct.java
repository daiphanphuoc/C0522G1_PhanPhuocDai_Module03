import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletProduct", value = "/product")
public class ServletProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("product");
        double price = Double.parseDouble(request.getParameter("price"));
        double percent = Double.parseDouble(request.getParameter("percent"));
        double amount =percent*price*0.01;
        double discountPrice=price-amount;
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("result.jsp");
        request.setAttribute("name",name);
        request.setAttribute("price",price);
        request.setAttribute("percent",percent);
        request.setAttribute("amount",amount);
        request.setAttribute("discount",discountPrice);
        requestDispatcher.forward(request,response);
    }
}
