package controller;

import model.facility.*;
import model.person.Customer;
import model.person.Division;
import model.person.EducationDegree;
import model.person.Position;
import repository.impl.facility.FacilityTypeRepository;
import service.impl.customer.CustomerService;
import service.impl.employee.DivisionService;
import service.impl.employee.EducationDegreeService;
import service.impl.employee.PositionService;
import service.impl.facility.FacilityService;
import service.impl.facility.FacilityTypeService;
import service.impl.facility.RentTypeService;

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
        String nameFacility = request.getParameter("nameFacility");
        double leasedArea = Double.parseDouble(request.getParameter("areaFacility"));

        double costFacility = Double.parseDouble(request.getParameter("costFacility"));
        int maxPeopleFacility = Integer.parseInt(request.getParameter("maxPeopleFacility"));
        int rentType = Integer.parseInt(request.getParameter("rentType"));
        RentType rent = RentTypeService.getInstance().findByID(rentType);
        int facilityType = Integer.parseInt(request.getParameter("facilityType"));
        FacilityType type = FacilityTypeService.getInstance().findByID(facilityType);
        String standardRoomFacility = (request.getParameter("standardRoomFacility"));

        String descriptionFacility =(request.getParameter("descriptionFacility"));
        double poolAreaFacility = Double.parseDouble((request.getParameter("poolAreaFacility")));
        int numberOfFloorsFacility = Integer.parseInt(request.getParameter("numberOfFloorsFacility"));
        String freeFacility =request.getParameter("freeFacility");
        Facility elememt =null;
        if("Villa".equals(type.getName())){
            elememt= new Villa(nameFacility,leasedArea,costFacility,maxPeopleFacility,rent,type,descriptionFacility,standardRoomFacility,numberOfFloorsFacility,poolAreaFacility);
        }else if("House".equals(type.getName())){
            elememt= new House(nameFacility,leasedArea,costFacility,maxPeopleFacility,rent,type,descriptionFacility,standardRoomFacility,numberOfFloorsFacility);

        }else if("Room".equals(type.getName())){
            elememt= new Room(nameFacility,leasedArea,costFacility,maxPeopleFacility,rent,type,descriptionFacility,freeFacility);

        }
        boolean check =FacilityService.getInstance().insert(elememt);
        if (check) {
            request.setAttribute("msg", "Insert facility's information successfully!");
        } else {
            request.setAttribute("msg", "Insert facility's information failure!");
        }
        showFacilities(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showFacilities(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/facility/list.jsp");
        List<Facility> facilityList = new ArrayList<>(FacilityService.getInstance().findAll("").values());
        request.setAttribute("facilityList", facilityList);
        List<FacilityType> typeList =new ArrayList<>(FacilityTypeService.getInstance().findAll().values());
        List<RentType> rentTypeList =new ArrayList<>(RentTypeService.getInstance().findAll().values());
        request.setAttribute("rentTypeList", rentTypeList);
        request.setAttribute("facilityTypeList", typeList);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void searchFacility(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/facility/list.jsp");
        String name = request.getParameter("nameSearch");
        String rentType = request.getParameter("rentType");
        String facilityType = request.getParameter("facilityType");

        List<Facility> facilityList = new ArrayList<>(FacilityService.getInstance().find(name,facilityType,rentType,"").values());
        request.setAttribute("facilityList", facilityList);
        request.setAttribute("nameSearch", name);
        List<FacilityType> typeList =new ArrayList<>(FacilityTypeService.getInstance().findAll().values());
        List<RentType> rentTypeList =new ArrayList<>(RentTypeService.getInstance().findAll().values());
        request.setAttribute("rentTypeList", rentTypeList);
        request.setAttribute("facilityTypeList", typeList);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/facility/update.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Facility facility = FacilityService.getInstance().findByID(id);
        request.setAttribute("facility", facility);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/facility/create.jsp");
        request.setAttribute("rentTypeList", RentTypeService.getInstance().findAll().values());
        request.setAttribute("facilityTypeList", FacilityTypeRepository.getInstance().findAll().values());
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFacility(HttpServletRequest request, HttpServletResponse response) {
        int idDelete = Integer.parseInt(request.getParameter("idDelete"));
        boolean check = FacilityService.getInstance().delete(idDelete);
        String mess = "Delete Facility failed.";
        if (check) {
            mess = "Delete Facility successfully.";
        }

        request.setAttribute("msg", mess);
        request.setAttribute("check", check);
        showFacilities(request, response);
    }
}