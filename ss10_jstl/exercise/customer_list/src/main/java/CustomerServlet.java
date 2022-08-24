import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {
    private static List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer(1, "Thuyên", "22/1/1990", "Việt Nam", "https://haycafe.vn/wp-content/uploads/2022/03/anh-chan-dung-1.jpg"));
        customers.add(new Customer(2, "Thiến", "22/1/1991", "Việt Nam", "https://vuinhiepanh.com/assets/uploads/2016/03/063.jpg"));
        customers.add(new Customer(3, "Thuyền", "22/1/1980", "Việt Nam", "https://haycafe.vn/wp-content/uploads/2022/03/cach-chup-hinh-chan-dung-nu-dep.jpg"));
        customers.add(new Customer(4, "Quyên", "22/1/1992", "Việt Nam", "https://s1.media.ngoisao.vn/resize_580/news/2021/09/06/1-cach-chup-anh-dep-ngoisaovn-w1024-h1344.jpeg"));
        customers.add(new Customer(5, "Thiền", "22/1/1994", "Việt Nam", "https://haycafe.vn/wp-content/uploads/2022/03/Hinh-chan-dung-nu-495x600.jpg"));
        customers.add(new Customer(6, "Tiên", "22/1/1999", "Việt Nam", "https://haycafe.vn/wp-content/uploads/2022/03/Hinh-anh-chan-dung-nu-thanh-thoat-403x600.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("display.jsp");
        request.setAttribute("customers", customers);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("display.jsp");
        request.setAttribute("customers", customers);
        requestDispatcher.forward(request, response);
    }
}
