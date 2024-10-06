/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import entity.OrderDetails;
import model.DAOOrderDetails;
/**
 *
 * @author HP
 */
@WebServlet(name="OrderDetailsController", urlPatterns={"/OrderDetailsController"})
public class OrderDetailsController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrderDetails dao = new DAOOrderDetails();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("deleteOrderDetails")) {
                dao.deleteOrderDetails(Integer.parseInt(request.getParameter("oid")),Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("OrderDetailsController?service=listAllOrderDetails");
            }
            
            if(service.equals("insertOrderDetails")){
                

                String OrderID = request.getParameter("OrderID");
                String ProductID = request.getParameter("ProductID");
                String UnitPrice = request.getParameter("UnitPrice");
                String Quantity = request.getParameter("Quantity");
                String Discount = request.getParameter("Discount");
                String OrderDetailStatus = request.getParameter("OrderDetailStatus");
                
                int OrderId = Integer.parseInt(OrderID);
                int ProductId = Integer.parseInt(ProductID);
                double UnitPricE = Double.parseDouble(UnitPrice);
                int QuantitY = Integer.parseInt(Quantity);
                double DiscounT = Double.parseDouble(Discount);
                int OrderDetailStatuS = Integer.parseInt(OrderDetailStatus);
                
                OrderDetails od = new OrderDetails(OrderId, ProductId, UnitPricE, QuantitY, DiscounT, OrderDetailStatuS);
                int n = dao.addOrderDetails(od);
                response.sendRedirect("OrderDetailsController?service=listAllOrderDetails");
                
                
            }
            if (service.equals("listAllOrderDetails")) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Order details Controller</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("""
                         <form action="OrderDetailsController" method="get">
                             <p>Search Name: <input type="text" name="Lname" id="">
                             <input type="submit" value="Search" name="submit">
                             <input type="reset" value="Clear">
                             <input type="hidden" name="service" value="listAllOrderDetails">
                             </p>
                        <p>Sort By:
                                <select name="sortBy">
                                    <option value="EmployeeID">EmployeeID</option>
                                    <option value="LastName">LastName</option>
                                    <option value="FirstName">FirstName</option>
                                </select>
                                <select name="sortOrder">
                                    <option value="ASC">Ascending</option>
                                    <option value="DESC">Descending</option>
                                </select>
                                <input type="submit" value="Sort" name="sort">
                            </p>
                         </form>""");
                out.print("""
                      <p><a href="HTML/insertOrderDetails.html">Insert order details</a></p>
                      """);
//                HttpSession session = request.getSession();
//
//                // Lấy tham số từ form
//                String submit = request.getParameter("submit");
//                String sort = request.getParameter("sort");
//                String lastName;
//
//                if (submit != null) {
//                    // Nếu có tìm kiếm, lưu từ khóa tìm kiếm vào session
//                    lastName = request.getParameter("Lname");
//                    session.setAttribute("lastName", lastName);
//                } else {
//                    // Nếu không có submit, lấy từ khóa từ session
//                    lastName = (String) session.getAttribute("lastName");
//                }
//
//                String sortBy = request.getParameter("sortBy");
//                String sortOrder = request.getParameter("sortOrder");
//
//                // Câu truy vấn SQL ban đầu
//                String sql = "SELECT * FROM Employees";
//                if (lastName != null && !lastName.isEmpty()) {
//                    sql += " WHERE LastName LIKE '%" + lastName + "%'";
//                }
//
//                // Kiểm tra xem người dùng có yêu cầu sắp xếp không
//                if (sort != null && sortBy != null && sortOrder != null) {
//                    sql += " ORDER BY " + sortBy + " " + sortOrder;
//                }
                String sql = "select * from [dbo].[Order Details]";
                String submit = request.getParameter("submit");
                if (submit == null) { //chua nhan submit --> khong search --> sql default
                    sql = "select * from [Order Details]";
                } else {
                    String Lname = request.getParameter("Lname");
                    sql = "select * from [Order Details] where OrderID = " + Lname;
                }
                // Truy vấn dữ liệu từ database
                Vector<OrderDetails> vector = dao.getOrderDetails(sql);
                out.println("""
                        <table border="1"> 
                                <tr>
                                    <th>OrderID</th>
                                    <th>ProductID</th>
                                    <th>UnitPrice</th>
                                    <th>Quantity</th>
                                    <th>Discount</th>
                                    <th>OrderDetailStatus</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                        """);
                for (OrderDetails od : vector) {
                    out.print("<tr>\n"
                            + "<td>" + od.getOrderID()+ "</td>\n"
                            + "<td>" + od.getProductID()+ "</td>\n"
                            + "<td>" + od.getUnitPrice()+ "</td>\n"
                            + "<td>" + od.getQuantity()+ "</td>\n"
                            + "<td>" + od.getDiscount()+ "</td>\n"
                            + "<td>" + od.getOrderDetailStatus()+ "</td>\n"
                            + "<td></td>\n"
                            + "<td><a href=\"OrderDetailsController?service=deleteOrderDetails&oid=" + od.getOrderID() + "&pid=" + od.getProductID() + "\">Delete Order details</a></td>\n"
                            + "        </tr>");
                }
                out.println("</body>");
                out.println("</html>");
            }

        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
