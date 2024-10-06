/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOOrders;

/**
 *
 * @author HP
 */
@WebServlet(name="OrdersController", urlPatterns={"/OrdersControllerURL"})
public class OrdersController extends HttpServlet {
   
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

        DAOOrders dao = new DAOOrders();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String service = request.getParameter("service");

            if (service.equals("deleteOrders")) {
                dao.deleteOrders(Integer.parseInt(request.getParameter("oid")));
                response.sendRedirect("OrdersControllerURL?service=listAllOrders");
            }

            if (service.equals("insertOrders")) {
                // Lấy dữ liệu từ request

                String CustomerID = request.getParameter("CustomerID");
                String EmployeeID = request.getParameter("EmployeeID");
                String OrderDate = request.getParameter("OrderDate");
                String RequiredDate = request.getParameter("RequiredDate");
                String ShippedDate = request.getParameter("ShippedDate");
                String ShipVia = request.getParameter("ShipVia");
                String Freight = request.getParameter("Freight");
                String ShipName = request.getParameter("ShipName");
                String ShipAddress = request.getParameter("ShipAddress");
                String ShipCity = request.getParameter("ShipCity");
                String ShipRegion = request.getParameter("ShipRegion");
                String ShipPostalCode = request.getParameter("ShipPostalCode");
                String ShipCountry = request.getParameter("ShipCountry");

                // check
                if (CustomerID.equals("")) {
                    out.print("CustomerID must not be empty");
                    return;
                }

                // convert value
                int EmployeeId = Integer.parseInt(EmployeeID);
                int ShipViA = Integer.parseInt(ShipVia);
                double FreighT = Double.parseDouble(Freight);
                
                // tạo đối tượng mới
                
                //Orders or = new Orders(ShipViA, CustomerID, EmployeeId, OrderDate, RequiredDate, ShippedDate, ShipViA, FreighT, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                Orders or = new Orders(CustomerID, EmployeeId, OrderDate, RequiredDate, ShippedDate, ShipViA, FreighT, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                // Thêm đơn hàng vào database
                int n = dao.insertOrders(or);
                response.sendRedirect("OrdersControllerURL?service=listAllOrders");
                
            }
            System.out.println(service);

            if (service.equals("listAllOrders")) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Orders</title>");
                out.println("</head>");
                out.println("<body>");

                out.print("<form action=\"OrdersControllerURL\" method=\"get\">\n"
                        + "  <p>\n"
                        + "    Search Name : <input type=\"text\" name=\"pname\" id=\"\">\n"
                        + "    <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                        + "    <input type=\"reset\" value=\"Clear\">\n"
                        + "    <input type=\"hidden\" name=\"service\" value=\"listAllOrders\">\n"
                        + "  </p>\n"
                        + "\n"
                        + "</form>");

                //link insert
                out.print("<p><a href=\"HTML/insertOrders.html\">Insert Orders</a></p>");

                String sql = "SELECT * FROM Orders";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT * FROM Orders";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "SELECT * FROM Orders\n"
                            + "Where CustomerID like '%" + pname + "%'";
                }
                Vector<Orders> vector = dao.getOrders(sql);

                out.println("<table border=\"1\">\n"
                        + "  \n"
                        + "  <tr>\n"
                        + "    <th>OrderID</th>\n"
                        + "    <th>CustomeerID</th>\n"
                        + "    <th>EmployeeID</th>\n"
                        + "    <th>OrderDate</th>\n"
                        + "    <th>RequiredDate</th>\n"
                        + "    <th>ShippedDate</th>\n"
                        + "    <th>ShipVia</th>\n"
                        + "    <th>Freight</th>\n"
                        + "    <th>ShipName</th>\n"
                        + "    <th>ShipAddress</th>\n"
                        + "    <th>ShipCity</th>\n"
                        + "    <th>ShipRegion</th>\n"
                        + "    <th>ShipPostalCode</th>\n"
                        + "    <th>ShipCountry</th>\n"
                        + "    <th>Update</th>"
                        + "    <th>Delete</th>"
                        + "    <th></th>\n"
                        + "  </tr>");

                for (Orders orders : vector) {
                    out.println("<tr>\n"
                            + "  <td>" + orders.getOrderID() + "</td>"
                            + "  <td>" + orders.getCustomerID() + "</td>"
                            + "  <td>" + orders.getEmployeeID() + "</td>"
                            + "  <td>" + orders.getOrderDate() + "</td>"
                            + "  <td>" + orders.getRequiredDate() + "</td>"
                            + "  <td>" + orders.getShippedDate() + "</td>"
                            + "  <td>" + orders.getShipVia() + "</td>"
                            + "  <td>" + orders.getFreight() + "</td>"
                            + "  <td>" + orders.getShipName() + "</td>"
                            + "  <td>" + orders.getShipAddress() + "</td>"
                            + "  <td>" + orders.getShipCity() + "</td>"
                            + "  <td>" + orders.getShipRegion() + "</td>"
                            + "  <td>" + orders.getShipPostalCode() + "</td>"
                            + "  <td>" + orders.getShipCountry() + "</td>"
                            + "  <td></td>"
                           + "<td><a href=\"OrdersControllerURL?service=deleteOrders&oid="+orders.getOrderID()+"\">Delete</a></td>\n"
                            + "</tr>");

                }

                out.println("</table>");
                out.println("<h1>Servlet Orders at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
