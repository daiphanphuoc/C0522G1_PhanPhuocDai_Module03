package controller;

import model.O;
import service.impl.OService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "ThiServlet", value = "/servlet")
public class ThiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleteElement(request, response);
                break;
            case "create":
                showFormCreate(request, response);
                break;
            case "update":
                showFormUpdate(request, response);
                break;
            case "search":
                searchElements(request, response);
                break;
            default:
                showElements(request, response);
        }
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteElement(HttpServletRequest request, HttpServletResponse response) {
    }

    private void searchElements(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showElements(HttpServletRequest request, HttpServletResponse response) {
    }


    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/update.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        O element = OService.getInstance().findID(id);
        request.setAttribute("element", element);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/create.jsp");


        LocalDate minAge = LocalDate.now().minusYears(65);
        LocalDate maxAge = LocalDate.now().minusYears(18);
        request.setAttribute("minAge", minAge);
        request.setAttribute("maxAge", maxAge);

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
                showElements(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        /* int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Boolean gender = Boolean.valueOf(request.getParameter("gender"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = df.parse(request.getParameter("dateOfBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
       
        /*boolean check = OService.getInstance().update(new O(name, idCard,
                dateOfBirth));*/

//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/list.jsp");
       /* if (check) {
            request.setAttribute("msg", "Update customer's information successfully!");
        } else {
            request.setAttribute("msg", "Update customer's information failure!");
        }*/
        showElements(request, response);
       /* try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }*/
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        /*String name = request.getParameter("name");
        Boolean gender = Boolean.valueOf(request.getParameter("gender"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        try {
            dateOfBirth = df.parse(request.getParameter("dateOfBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
        boolean check = OService.getInstance().insert(new Customer(name, idCard, dateOfBirth,
                gender, phoneNumber, email, address, customerType));*/

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/customer/list.jsp");
       /* if (check) {
            request.setAttribute("msg", "Update customer's information successfully!");
        } else {
            request.setAttribute("msg", "Update customer's information failure!");
        }*/
        showElements(request, response);
       /* try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }*/

    }
}
