package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FacilityServlet", value = "/facility")
public class FacilityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleteFacility(request, response);
                break;
            case "create":
                showFormCreate(request, response);
                break;
            case "update":
                showFormUpdate(request, response);
                break;
            case "search":
                searchFacility(request, response);
                break;
            default:
                showFacilities(request, response);
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
                showFacilities(request, response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showFacilities(HttpServletRequest request, HttpServletResponse response) {
    }


    private void searchFacility(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteFacility(HttpServletRequest request, HttpServletResponse response) {
    }
}