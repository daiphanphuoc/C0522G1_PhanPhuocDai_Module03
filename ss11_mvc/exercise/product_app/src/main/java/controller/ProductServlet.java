package controller;

import model.Product;
import service.IProductService;
import service.impl.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"", "/products"})
public class ProductServlet extends HttpServlet {
    private IProductService<Product> productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "add":
                showCreateForm(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
                break;
                /*case "delete":
                    showDeleteForm(request, response);
                    break;*/
            case "view":
                viewProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            case "delete":
                productService.delete(request.getParameter("id"));
                showList(request, response);
                break;
            default:
                showList(request, response);
        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addNewProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;

            default:

        }

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        productService.delete(request.getParameter("id"));
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String describe = request.getParameter("describe");
        String producer = request.getParameter("producer");
        Product product = productService.findId(id);

        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = request.getRequestDispatcher("view/product/error-404.jsp");
        } else {
            product.setId(id);
            product.setProductName(name);
            product.setProductPrice(price);
            product.setProductDescription(describe);
            product.setManufacturer(producer);
            productService.edit(id, product);
            request.setAttribute("product", product);
            request.setAttribute("message", "Cập nhập sản phẩm thành công");
            dispatcher = request.getRequestDispatcher("view/product/update.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewProduct(HttpServletRequest request, HttpServletResponse response) {
        String ID = request.getParameter("id");
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String describe = request.getParameter("describe");
        String producer = request.getParameter("producer");
        Product product = new Product(ID, name, price, describe, producer);
        productService.add(product);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/product/add.jsp");
        request.setAttribute("message", "Thêm mới sản phẩm thành công");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) {
        String nameSearch = request.getParameter("name");
        List<Product> productList = productService.searchByName(nameSearch);
        request.setAttribute("products", productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/product/list.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Product product = productService.findId(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = request.getRequestDispatcher("view/product/error-404.jsp");
        } else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("view/product/view.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Product product = productService.findId(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = request.getRequestDispatcher("view/product/error-404.jsp");
        } else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("view/product/update.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/product/add.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/product/list.jsp");
        request.setAttribute("products", productService.findByAll());
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
