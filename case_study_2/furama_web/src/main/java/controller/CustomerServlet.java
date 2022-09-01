package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CustomerServlet", urlPatterns = {"","/customer"} )
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "delete":
                break;
            default:
                showCustomers(request,response);
        }
    }

    private void showCustomers(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "delete":
                break;
            default:
                showCustomers(request,response);
        }
    }
}
