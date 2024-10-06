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
import model.DAOTerritories;
import entity.Territories;
import java.util.Vector;

/**
 *
 * @author HP
 */
@WebServlet(name="TerritoriesController", urlPatterns={"/TerritoriesURL"})
public class TerritoriesController extends HttpServlet {
   
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
        DAOTerritories dao = new DAOTerritories();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            
            if (service.equals("deleteTeritorie")) {
                dao.deleteTeritorie(Integer.parseInt(request.getParameter("terid")));
                response.sendRedirect("TerritoriesURL?service=listAllTerritories");
                
            }
            if (service.equals("insertTerritorie")) {
                //get data
                String TerritoryID = request.getParameter("TerritoryID");
                String TerritoryDescription = request.getParameter("TerritoryDescription");
                String RegionID = request.getParameter("RegionID");
                if (TerritoryDescription == null) {
                    out.print("TerritoryDescription must be not empty");
                }
                //int territoryID = Integer.parseInt(TerritoryID);
                int regionID = Integer.parseInt(RegionID);
                Territories terr = new Territories(TerritoryID, TerritoryDescription, regionID);
                int n = dao.addTerritorie(terr);
                response.sendRedirect("TerritoriesURL?service=listAllTerritories");
            }
            if (service.equals("listAllTerritories")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TerritoriesController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("<form action=\"TerritoriesURL\" method=\"get\">\n"
                        + "  <p>\n"
                        + "    Search Name: <input type=\"text\" name=\"tename\">\n"
                        + "    <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                        + "    <input type=\"reset\" value=\"clear\">\n"
                        + "    <input type=\"hidden\" name=\"service\" value=\"listAllTerritories\">\n"
                        + "    \n"
                        + "  </p>\n"
                        + "</form>");
                //link insert 
                out.print("<p><a href=\"HTML/insertTerritories.html\">Insert Territories</a></p>");
                String sql = "SELECT * FROM Territories";
                String submit = request.getParameter("tename");
                if (submit == null) {
                    sql = "SELECT * FROM Territories";
                } else {
                    String tename = request.getParameter("tename");
                    sql = "SELECT * FROM Territories Where TerritoryDescription LIKE '%" + tename + "%'";
                }
                Vector<Territories> vector = dao.getTerritories(sql);
                out.println("<table border=\"1\">\n"
                        + "   <tr>\n"
                        + "    <th>TerritoryID</th>\n"
                        + "    <th>TerritoryDescription</th>\n"
                        + "    <th>RegionID</th>\n"
                        + "    <th>Update</th>\n"
                        + "    <th>Delete</th>\n"
                        + "   </tr>");
                for (Territories territories : vector) {
                    out.println(" <tr>\n"
                            + "   <td>" + territories.getTerritoryID() + "</td>\n"
                            + "   <td>" + territories.getTerritoryDescription() + "</td>\n"
                            + "   <td>" + territories.getRegionID() + "</td>\n"
                            + "   <td></td>\n"
                           + "<td><a href=\"TerritoriesURL?service=deleteTeritorie&terid="+territories.getTerritoryID()+"\">Delete product</a></td>\n"
                            + "   </tr>");
                }
                out.println("</table>");
                out.println("<h1>Servlet TerritoriesController at " + request.getContextPath() + "</h1>");
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
