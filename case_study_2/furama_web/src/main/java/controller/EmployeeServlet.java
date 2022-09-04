package controller;

import model.person.*;
import service.impl.customer.CustomerService;
import service.impl.customer.CustomerTypeService;
import service.impl.employee.DivisionService;
import service.impl.employee.EducationDegreeService;
import service.impl.employee.EmployeeService;
import service.impl.employee.PositionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleteEmployee(request, response);
                break;
            case "create":
                showFormCreate(request, response);
                break;
            case "update":
                showFormUpdate(request, response);
                break;
            case "search":
                searchEmployee(request,response);
                break;
            default:
                showEmployees(request, response);
        }
    }

    private void showEmployees(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/employee/list.jsp");
        List<Employee> employees = new ArrayList<>(EmployeeService.getInstance().findAll("").values());
        request.setAttribute("employees", employees);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void searchEmployee(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/employee/list.jsp");
        String name = request.getParameter("searchName");
        String address = request.getParameter("searchAddress");
        String phone = request.getParameter("searchPhone");

        List<Employee> employees = new ArrayList<>(EmployeeService.getInstance().find(name, phone, address, "").values());
        request.setAttribute("employees", employees);
        request.setAttribute("searchName", name);
        request.setAttribute("searchAddress", address);
        request.setAttribute("searchPhone", phone);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/employee/update.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = EmployeeService.getInstance().findByID(id);
        request.setAttribute("employee", employee);
        List<Division> divisionList = new ArrayList<>(DivisionService.getInstance().findAll().values());
        List<Position> positionList = new ArrayList<>(PositionService.getInstance().findAll().values());
        List<EducationDegree> degreeList = new ArrayList<>(EducationDegreeService.getInstance().findAll().values());
        request.setAttribute("divisionList",divisionList);
        request.setAttribute("positionList",positionList);
        request.setAttribute("degreeList",degreeList);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/employee/create.jsp");
        List<Division> divisionList = new ArrayList<>(DivisionService.getInstance().findAll().values());
        List<Position> positionList = new ArrayList<>(PositionService.getInstance().findAll().values());
        List<EducationDegree> degreeList = new ArrayList<>(EducationDegreeService.getInstance().findAll().values());
        request.setAttribute("divisionList",divisionList);
        request.setAttribute("positionList",positionList);
        request.setAttribute("degreeList",degreeList);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        int idDelete = Integer.parseInt(request.getParameter("idDelete"));
        boolean check = EmployeeService.getInstance().delete(idDelete);
        String msg = "Delete Employee failed.";
        if (check) {
            msg = "Delete Employee successfully.";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("check", check);
        showEmployees(request, response);
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
                showEmployees(request, response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
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
        int degreeId = Integer.parseInt((request.getParameter("degree")));
        EducationDegree degree = EducationDegreeService.getInstance().findByID(degreeId);
        int positionId =Integer.parseInt((request.getParameter("position")));
        Position position= PositionService.getInstance().findByID(positionId);
        int divisionId =Integer.parseInt((request.getParameter("division")));
        Division division= DivisionService.getInstance().findByID(divisionId);
        double salary = Double.parseDouble(request.getParameter("salary"));
        String user =request.getParameter("userName");
/*String name, String iDCitizen, Date birthday, boolean sex, String phone, String email, String address,
 EducationDegree degree, Position position, Division division, double salary, String userName) */
        boolean check = EmployeeService.getInstance().insert(new Employee(name, idCard, dateOfBirth,
                gender, phoneNumber, email, address, degree,position,division,salary,user));

        try {
            if (check) {
                response.sendRedirect("/employee?msg=Insert%20employee's%20information%20successfully!");
            } else {
                response.sendRedirect("/employee?msg=Insert%20employee's%20information%20failure!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
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
        int degreeId = Integer.parseInt((request.getParameter("degree")));
        EducationDegree degree = EducationDegreeService.getInstance().findByID(degreeId);
        int positionId =Integer.parseInt((request.getParameter("position")));
        Position position= PositionService.getInstance().findByID(positionId);
        int divisionId =Integer.parseInt((request.getParameter("division")));
        Division division= DivisionService.getInstance().findByID(divisionId);
        double salary = Double.parseDouble(request.getParameter("salary"));
        String user =request.getParameter("userName");
/*String name, String iDCitizen, Date birthday, boolean sex, String phone, String email, String address,
 EducationDegree degree, Position position, Division division, double salary, String userName) */
        boolean check = EmployeeService.getInstance().update(new Employee(name, idCard, dateOfBirth,
                gender, phoneNumber, email, address,id, degree,position,division,salary,user));
        if (check) {
            request.setAttribute("msg", "Update customer's information successfully!");
        } else {
            request.setAttribute("msg", "Update customer's information failure!");
        }
        showEmployees(request,response);
        /*try {
            if (check) {
                response.sendRedirect("/employee?msg=Insert%20employee's%20information%20successfully!");
            } else {
                response.sendRedirect("/employee?msg=Insert%20employee's%20information%20failure!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
