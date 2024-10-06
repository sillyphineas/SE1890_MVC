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
import entity.Shipper;
import java.util.Vector;
import model.DAOShipper;

/**
 *
 * @author HP
 */
@WebServlet(name="ShipperController", urlPatterns={"/ShipperURL"})
public class ShipperController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOShipper dao = new DAOShipper();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        
            String service = request.getParameter("service");
            
            if (service.equals("deleteShippers")) {
                dao.deleteShippers(Integer.parseInt(request.getParameter("shipperID")));
                response.sendRedirect("ShipperURL?service=listAllShippers");
                
            }
            
            if(service.equals("insertShippers")){
                //get data
                String CompanyName = request.getParameter("CompanyName");
                String Phone = request.getParameter("Phone");
                String ShipperStatus = request.getParameter("ShipperStatus");
                
                if(CompanyName.equals("")){
                    out.print("CompanyName must be empty");
                }
                int ShipperStatuS = Integer.parseInt(ShipperStatus);
                Shipper ship = new Shipper(CompanyName, Phone, ShipperStatuS);
                int n = dao.addShipper(ship);
                response.sendRedirect("ShipperURL?service=listAllShippers");
            }
            
            if(service.equals("listAllShippers")){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShipperController</title>");
            out.println("</head>");
            out.println("<body>");
            //form for search
            out.print("<form action=\"ShipperURL\" method=\"get\">\n"
                    + "  <p>\n"
                    + "    Search Name: <input type=\"text\" name=\"sname\" id=\"\">\n"
                    + "    <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "    <input type=\"reset\" value=\"clear\">\n"
                    + "    <input type=\"hidden\" name=\"service\" value=\"listAllShippers\">\n"
                    + "  </p>\n"
                    + "</form>");
            //link insert
            out.print("<p><a href=\"HTML/insertShipper.html\">Insert Shipper</a></p>");
            String sql = "SELECT * FROM Shippers";
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "SELECT * FROM Shippers";
            } else {
                String sname = request.getParameter("sname");
                sql = "SELECT * FROM Shippers Where CompanyName like '%" + sname + "%' ";
            }
                Vector<Shipper> vector = dao.getShipper(sql);
            //shipper table
            out.println("<table border=\"1\">\n"
                    + "  <tr>\n"
                    + "    <th>ShipperIDint</th>\n"
                    + "    <th>CompanyName</th>\n"
                    + "    <th>Phone</th>\n"
                    + "    <th>ShipperStatus</th>\n"
                    + "    <th>Update</th>\n"
                    + "    <th>Delete</th>\n"
                    + "  </tr>\n"
            );
            for (Shipper shipper : vector) {
                out.print("<tr>\n"
                        + "    <td>" + shipper.getShipperID() + "</td>\n"
                        + "    <td>" + shipper.getCompanyName() + "</td>\n"
                        + "    <td>" + shipper.getPhone() + "</td>\n"
                        + "    <td>" + shipper.getShipperStatus() + "</td>\n"
                        + "    <td></td>\n"
                        + " <td><a href=\"ShipperURL?service=deleteShippers&shipperID="+shipper.getShipperID()+"\">Delete product</a></td>\n"
                        + "  </tr>");
            }
            out.println("</table>");
            out.println("<h1>Servlet ShipperController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }}
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
