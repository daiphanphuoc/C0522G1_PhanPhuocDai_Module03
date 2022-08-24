import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", value = "/calculator")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {

        request.setCharacterEncoding("UTF-8");
        double firstNumber = Double.parseDouble(request.getParameter("firstOperand"));
        double secondNumber = Double.parseDouble(request.getParameter("secondOperand"));
        String operator = request.getParameter("operator");
        char op=' ';
        double result = 0;
        switch (operator) {
            case "ADDITION":
                result = firstNumber + secondNumber;
                op='+';
                break;
            case "SUBTRACTION":
                op='-';
                result = firstNumber - secondNumber;
                break;
            case "MULTIPLICATION":
                op='*';
                result = firstNumber * secondNumber;
                break;
            case "DIVISION":
                op='/';
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                }else{
                    throw new RuntimeException("Can't divide by zero");
                }
        }
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("display.jsp");
        request.setAttribute("result",result);
        request.setAttribute("firstNumber",firstNumber);
        request.setAttribute("secondNumber",secondNumber);
        request.setAttribute("operator",op);
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
