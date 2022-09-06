package controller;

import model.business.Contract;
import model.business.ContractDetail;
import model.dto.ContractDetailDTO;
import model.facility.Facility;
import model.person.*;
import service.impl.business.ContractService;
import service.impl.customer.CustomerService;
import service.impl.dto.ContractDetailDTOService;
import service.impl.employee.DivisionService;
import service.impl.employee.EducationDegreeService;
import service.impl.employee.EmployeeService;
import service.impl.employee.PositionService;
import service.impl.facility.FacilityService;

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

@WebServlet(name = "ContractServlet", value = "/contract")
public class ContractServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleteContract(request, response);
                break;
            case "create":
                showFormCreate(request, response);
                break;
            case "update":
                showFormUpdate(request, response);
                break;
            case "search":
                searchContract(request, response);
                break;
            case "modal":
                findContract(request, response);
                break;
            default:
                showContract(request, response);
        }
    }

    private void findContract(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/contract/list.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Contract> contracts = new ArrayList<>(ContractService.getInstance().findAll().values());
        List<Facility> facilityList = new ArrayList<>(FacilityService.getInstance().findAll("").values());
        List<Customer> customerList = new ArrayList<>(CustomerService.getInstance().findAll("").values());
        List<Employee> employeeList = new ArrayList<>(EmployeeService.getInstance().findAll("").values());
        List<ContractDetailDTO> contractDetailDTOS = new ArrayList<>
                (ContractDetailDTOService.getInstance().find(id).values());
        request.setAttribute("modal", "showAttachFacility");
        request.setAttribute("contractList", contracts);
        request.setAttribute("facilityList", facilityList);
        request.setAttribute("customerList", customerList);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("contractDetailDTOS", contractDetailDTOS);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteContract(HttpServletRequest request, HttpServletResponse response) {
        int idDelete = Integer.parseInt(request.getParameter("idDelete"));
        boolean check = ContractService.getInstance().delete(idDelete);
        String msg = "Delete Contract failed.";
        if (check) {
            msg = "Delete Contract successfully.";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("check", check);
        showContract(request, response);
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/contract/create.jsp");
        List<Customer> customerList = new ArrayList<>(CustomerService.getInstance().findAll("").values());
        List<Employee> employeeList = new ArrayList<>(EmployeeService.getInstance().findAll("").values());
        List<Facility> facilityList = new ArrayList<>(FacilityService.getInstance().findAll("").values());
        request.setAttribute("customerList", customerList);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("facilityList", facilityList);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) {
    }

    private void searchContract(HttpServletRequest request, HttpServletResponse response) {
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
                showContract(request, response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = df.parse(request.getParameter("startDay"));
            end = df.parse(request.getParameter("endDay"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double deposit = Double.parseDouble(request.getParameter("deposit"));
        int employee = Integer.parseInt(request.getParameter("employee"));
        int customer = Integer.parseInt(request.getParameter("customer"));
        int facility = Integer.parseInt(request.getParameter("facility"));
        boolean check = ContractService.getInstance().insert(new Contract(start, end, deposit, customer, employee, facility));
        if (check) {
            request.setAttribute("msg", "Insert Contract's information successfully!");
        } else {
            request.setAttribute("msg", "Insert Contract's information failure!");
        }
        showContract(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = df.parse(request.getParameter("startDay"));
            end = df.parse(request.getParameter("endDay"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double deposit = Double.parseDouble(request.getParameter("deposit"));
        int employee = Integer.parseInt(request.getParameter("employee"));
        int customer = Integer.parseInt(request.getParameter("customer"));
        int facility = Integer.parseInt(request.getParameter("facility"));
        boolean check = ContractService.getInstance().update(new Contract(id, start, end, deposit, customer, employee, facility));
        if (check) {
            request.setAttribute("msg", "Insert Contract's information successfully!");
        } else {
            request.setAttribute("msg", "Insert Contract's information failure!");
        }
        showContract(request, response);
    }

    private void showContract(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/contract/list.jsp");

        List<Contract> contracts = new ArrayList<>(ContractService.getInstance().findAll().values());
        List<Facility> facilityList = new ArrayList<>(FacilityService.getInstance().findAll("").values());
        List<Customer> customerList = new ArrayList<>(CustomerService.getInstance().findAll("").values());
        List<Employee> employeeList = new ArrayList<>(EmployeeService.getInstance().findAll("").values());
        List<ContractDetailDTO> contractDetailDTOS = new ArrayList<>
                (ContractDetailDTOService.getInstance().findAll().values());

        request.setAttribute("contractList", contracts);
        request.setAttribute("facilityList", facilityList);
        request.setAttribute("customerList", customerList);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("contractDetailDTOS", contractDetailDTOS);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
