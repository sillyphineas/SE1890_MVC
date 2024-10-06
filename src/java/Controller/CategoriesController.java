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
import entity.Categories;
import model.DAOCategories;

/**
 *
 * @author HP
 */
@WebServlet(name="CategoriesController", urlPatterns={"/CategoriesController"})
public class CategoriesController extends HttpServlet {
   
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
        
        DAOCategories dao = new DAOCategories();
   
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
         
            
            
         
            String service = request.getParameter("service");
             if (service == null) {
            out.println("Service parameter is missing.");
            return;
        }
               //Insert
            if(service.equals("insertCategories")){
              // get data
              String CategoryName = request.getParameter("CategoryName");
              String Description = request.getParameter("Description");
              String Picture = request.getParameter("Picture");
              
              //check data
              if(CategoryName.equals("")){
                  out.print("Category Name must not be empty");
              }
              
              //covert data
              
              Categories pro = new Categories(CategoryName, Description, Picture);
              int n = dao.addCategories(pro);
              response.sendRedirect("CategoriesController?service=listAllCategories");
              
            
              
            }
            if(service.equals("listAllCategories")){
                
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoriesController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"CategoriesController\" method=\"get\">\n"
                    + "    <p>\n"
                    + "      Search name: <input type=\"text\" name=\"Category\" >\n"
                    + "      <input type=\"submit\" name=\"submit\" value=\"Search\" >\n"
                    + "      <input type=\"reset\" name=\"clear\" value=\"reset\">\n"
                    + "  <input type=\"hidden\" name=\"service\" value=\"listAllCategories\"> "
                    + " </p>\n"
                    + "\n"
                    + "</form>");
            out.print("<p><a href=\"HTML/InsertCategories.html\">Insert Category</a></p>");
           
            String sql = "SELECT * FROM Categories";
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "select * from Categories";
            } else {
                String pname = request.getParameter("Category");
                sql = "SELECT *\n"
                        + "  FROM [SE1890_1].[dbo].[Categories]\n"
                        + "  where CategoryName like '%" +pname+ "%'";
            }
            Vector<Categories> vector = dao.getCategories(sql);
            out.println("<table border=1>\n"
                    + " <tr>\n"
                    + "    <th>CategoryID </th>\n"
                    + "    <th>CategoryName</th>\n"
                    + "    <th>Description</th>\n"
                    + "    <th>Picture </th>\n"
                    + " </tr>\n");
            for (Categories categories : vector) {
                out.println(" <tr>\n"
                        + "   <td>" + categories.getCategoryID() + "</td>\n"
                        + "   <td>" + categories.getCategoryName() + "</td>\n"
                        + "   <td>" + categories.getDescription() + "</td>\n"
                        + "   <td>" + categories.getPicture() + "</td>\n"
                        + "\n"
                        + " </tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }}
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
