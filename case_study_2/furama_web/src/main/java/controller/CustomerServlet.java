package controller;

import model.person.Customer;
import model.person.CustomerType;
import service.impl.customer.CustomerService;
import service.impl.customer.CustomerTypeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleteCustomer(request, response);
                break;
            case "create":
                showFormCreate(request, response);
                break;
            case "update":
                showFormUpdate(request, response);
                break;
            case "search":
                searchCustomer(request, response);
                break;
            default:
                showCustomers(request, response);
        }
    }


    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/customer/update.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = CustomerService.getInstance().findByID(id);
        request.setAttribute("customer", customer);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/customer/create.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int idDelete = Integer.parseInt(request.getParameter("idDelete"));
        boolean check = CustomerService.getInstance().delete(idDelete);
        String mess = "Delete Customer failed.";
        if (check) {
            mess = "Delete Customer successfully.";
        }
        request.setAttribute("mess", mess);
        request.setAttribute("check", check);
        showCustomers(request, response);
    }

    private void showCustomers(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/customer/list.jsp");
        List<Customer> customerList = new ArrayList<>(CustomerService.getInstance().findAll("").values());
        request.setAttribute("customers", customerList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void searchCustomer(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/customer/list.jsp");
        String name = request.getParameter("searchName");
        String address = request.getParameter("searchAddress");
        String type = request.getParameter("searchType");

        List<Customer> customerList = new ArrayList<>(CustomerService.getInstance().find(name, address, type, "").values());
        request.setAttribute("customers", customerList);
        request.setAttribute("searchName", name);
        request.setAttribute("searchAddress", address);
        request.setAttribute("searchType", type);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                break;
            case "create":
                save(request, response);
                break;
            case "update":
                update(request, response);
                break;
            default:
                showCustomers(request, response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Boolean gender = Boolean.valueOf(request.getParameter("gender"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = df.parse(request.getParameter("dateOfBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String idCard = request.getParameter("idCard");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int customerTypeId = Integer.parseInt((request.getParameter("customerTypeId")));
        CustomerType customerType = CustomerTypeService.getInstance().findByID(customerTypeId);
/*String name, String iDCitizen, Date birthday, boolean sex, String phone,
                    String email, String address, CustomerType customerType*/
        boolean check = CustomerService.getInstance().insert(new Customer(name, idCard, dateOfBirth,
                gender, phoneNumber, email, address, customerType));

        try {
            if (check) {
                response.sendRedirect("/customer?msg=Insert%20customer's%20information%20successfully!");
            } else {
                response.sendRedirect("/customer?msg=Insert%20customer's%20information%20failure!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Boolean gender = Boolean.valueOf(request.getParameter("gender"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = df.parse(request.getParameter("dateOfBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String idCard = request.getParameter("idCard");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int customerTypeId = Integer.parseInt((request.getParameter("customerTypeId")));
        CustomerType customerType = CustomerTypeService.getInstance().findByID(customerTypeId);
        /*String name, String iDCitizen, Date birthday, boolean sex, String phone,
                   String email, String address, int iDCustomer, CustomerType customerType*/
        boolean check = CustomerService.getInstance().update(new Customer(name, idCard,
                dateOfBirth, gender, phoneNumber, email, address, id, customerType));

//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/customer/list.jsp");
        if (check) {
            request.setAttribute("msg", "Update customer's information successfully!");
        } else {
            request.setAttribute("msg", "Update customer's information failure!");
        }
        showCustomers(request, response);
       /* try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }*/
        /*try {
          if(check) {
              response.sendRedirect("/customer?msg=Update%20customer's%20information%20successfully!");
          }else {
              response.sendRedirect("/customer?msg=Update%20customer's%20information%20failure!");
          }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
