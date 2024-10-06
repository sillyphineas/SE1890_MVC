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
import model.DAOCustomer;
import entity.Customer;

/**
 *
 * @author HP
 */
import java.util.Vector;
@WebServlet(name="CustomerController", urlPatterns={"/CustomerURL"})
public class CustomerController extends HttpServlet {
   
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
        DAOCustomer dao = new DAOCustomer();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");

            if (service.equals("insertCustomer")) {
                String CustomerID = request.getParameter("CustomerID");
                String CompanyName = request.getParameter("CompanyName");
                String ContactName = request.getParameter("ContactName");
                String ContactTitle = request.getParameter("ContactTitle");
                String Address = request.getParameter("Address");
                String City = request.getParameter("City");
                String Region = request.getParameter("Region");
                String PostalCode = request.getParameter("PostalCode");
                String Country = request.getParameter("Country");
                String Phone = request.getParameter("Phone");
                String Fax = request.getParameter("Fax");

                Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                int n = dao.addCCustomer(cus);
                response.sendRedirect("CustomerURL?service=listAllCustomers");
            }
            if (service.equals("listAllCustomers")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title></title>");
                out.println("</head>");
                out.println("<body>");
                out.print("""
                      <p><a href="HTML/insertCustomer.html">insert Customer</a></p>
                      """);
                out.print("""
                         <form action="CustomerURL" method="get">
                      <p>Search Name: <input type="text" name="cusId" id="">
                      <input type="submit" value="Search" name="submit">
                      <input type="reset" value="Clear">
                      <input type="hidden" name="service" value="listAllCustomers">
                      </p>
                          </form>""");
                String sql = "select * from Customers";
                String submit = request.getParameter("submit");
                if (submit == null) { //chua nhan submit --> khong search --> sql default
                    sql = "select * from Customers";
                } else {
                    String cusId = request.getParameter("cusId");
                    sql = "select * from Customers where CustomerID like '%" + cusId + "%'";
                }
                Vector<Customer> vector = dao.getCustomers(sql);
                out.println("""
                        <table border="1"> 
                                <tr>
                                    <th>CustomerID</th>
                                    <th>CompanyName</th>
                                    <th>ContactName</th>
                                    <th>ContactTitle</th>
                                    <th>Address</th>
                                    <th>City</th>
                                    <th>Region</th>
                                    <th>PostalCode</th>
                                    <th>Country</th>
                                    <th>Phone</th>
                                    <th>Fax</th>
                                </tr>
                        """);
                for (Customer cus : vector) {
                    out.print("<tr>\n"
                            + "<td>" + cus.getCustomerID() + "</td>\n"
                            + "<td>" + cus.getCompanyName() + "</td>\n"
                            + "<td>" + cus.getContactName()+ "</td>\n"
                            + "<td>" + cus.getContactTitle()+ "</td>\n"
                            + "<td>" + cus.getAddress()+ "</td>\n"
                            + "<td>" + cus.getCity()+ "</td>\n"
                            + "<td>" + cus.getRegion()+ "</td>\n"
                            + "<td>" + cus.getPostalCode()+ "</td>\n"
                            + "<td>" + cus.getCountry()+ "</td>\n"
                            + "<td>" + cus.getPhone()+ "</td>\n"
                            + "<td>" + cus.getFax()+ "</td>\n"
                            + "<td></td>\n"
                            + "<td><a href=\"CustomerURL?service=         &pid="+cus.getCustomerID()+"\">Delete customer</a></td>\n"
                                    
                            + "        </tr>");
                }
                out.println("</table>");
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
