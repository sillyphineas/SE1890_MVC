/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;

/**
 *
 * @author HP
 */
@WebServlet(name="ProductController", urlPatterns={"/ProductURL"})
public class ProductController extends HttpServlet {
   
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
        DAOProduct dao = new DAOProduct();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            
            if (service.equals("deleteProduct")) {
                dao.deleteProduct(Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("ProductURL?service=listAllProducts");
            }
            
            if (service.equals("insertProduct")) {
                String ProductName = request.getParameter("ProductName");
                String SupplierID = request.getParameter("SupplierID");
                String CategoryID = request.getParameter("CategoryID");
                String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                String UnitPrice = request.getParameter("UnitPrice");
                String UnitsInStock = request.getParameter("UnitsInStock");
                String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                String ReorderLevel = request.getParameter("ReorderLevel");
                String Discontinued = request.getParameter("Discontinued");
                //check data(double check)
                if (ProductName.equals("")) {
                    out.print("product name is not empty");
                }
                //convert
                
                int SupplierId = Integer.parseInt(SupplierID);
                int CategoryId = Integer.parseInt(CategoryID);
                double UnitPricE = Double.parseDouble(UnitPrice);
                int UnitsInStocK = Integer.parseInt(UnitsInStock);
                int UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder);
                int ReorderLeveL = Integer.parseInt(ReorderLevel);
                boolean DiscontinueD = Integer.parseInt(Discontinued) == 1 ? true : false;
                Product pro = new Product(ProductName, SupplierId, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                int n = dao.addProduct(pro);
                response.sendRedirect("ProductURL?service=listAllProducts");
            }
            if (service.equals("listAllProducts")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("""
                      <p><a href="HTML/insertProduct.html">Insert product</a></p>
                      """);
                out.print("""
                         <form action="ProductURL" method="get">
                      <p>Search Name: <input type="text" name="pname" id="">
                      <input type="submit" value="Search" name="submit">
                      <input type="reset" value="Clear">
                      <input type="hidden" name="service" value="listAllProducts">
                      </p>
                          </form>""");
                String sql = "select * from Products";
                String submit = request.getParameter("submit");
                if (submit == null) { //chua nhan submit --> khong search --> sql default
                    sql = "select * from Products";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "select * from Products where ProductName like '%" + pname + "%'";
                }
                Vector<Product> vector = dao.getProducts(sql);
                out.println("""
                        <table border="1"> 
                                <tr>
                                    <th>ProductID</th>
                                    <th>ProductName</th>
                                    <th>SupplierID</th>
                                    <th>CategoryID</th>
                                    <th>QuantityPerUnit</th>
                                    <th>UnitPrice</th>
                                    <th>UnitsInStock</th>
                                    <th>UnitsOnOrder</th>
                                    <th>ReorderLevel</th>
                                    <th>Discontinued</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                        """);
                for (Product pro : vector) {
                    out.print("<tr>\n"
                            + "<td>" + pro.getProductID() + "</td>\n"
                            + "<td>" + pro.getProductName() + "</td>\n"
                            + "<td>" + pro.getSupplierID() + "</td>\n"
                            + "<td>" + pro.getCategoryID() + "</td>\n"
                            + "<td>" + pro.getQuantityPerUnit() + "</td>\n"
                            + "<td>" + pro.getUnitPrice() + "</td>\n"
                            + "<td>" + pro.getUnitsInStock() + "</td>\n"
                            + "<td>" + pro.getUnitsOnOrder() + "</td>\n"
                            + "<td>" + pro.getReorderLevel() + "</td>\n"
                            + "<td>" + pro.isDiscontinued() + "</td>\n"
                            + "<td></td>\n"
                            + "<td><a href=\"ProductURL?service=deleteProduct&pid="+pro.getProductID()+"\">Delete product</a></td>\n"
                                    
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
