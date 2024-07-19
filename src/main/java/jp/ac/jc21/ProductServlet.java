package jp.ac.jc21;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns = { "/product" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final String dbServer = "192.168.54.231";
	final String dbPort = "3306";
	final String dbName = "test2023";
	final String user = "test2023";
	final String pass = "test2023";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String url = "jdbc:mysql://"+dbServer+"/"+dbName;
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<h2>Connect to : ").append(url).append("</h2>");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection  conn = DriverManager.getConnection(url, user, pass);
			
			String sql ="SELECT MAKER_CODE,MAKER_NAME FROM MAKER";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			
			ArrayList<String[]> item = new ArrayList<>();
			while(rs.next()==true) {
				String[] s = new String[3];
				s[0]=rs.getString("MAKER_CODE");
				s[1]=rs.getString("MAKER_NAME");
				item.add(s);
				
			}
			request.setAttribute("item",item);
			
			
			String code = request.getParameter("ID");
			String sql2 = "SELECT PRODUCT_CODE,PRODUCT_NAME,MAKER_CODE FROM PRODUCT";
			
			if(code != null) {
				sql2 += " WHERE MAKER_CODE = ?";
			}
			
			PreparedStatement statement2 = conn.prepareStatement(sql2);
			
			if(code != null) {
				statement2.setString(1, code);
			}
			
			ResultSet rs2 = statement2.executeQuery();
			
			ArrayList<String[]> item2 = new ArrayList<>();
			while(rs2.next()==true) {
				String[] s2 = new String[3];
				s2[0]=rs2.getString("PRODUCT_CODE");
				s2[1]=rs2.getString("PRODUCT_NAME");
				s2[2]=rs2.getString("MAKER_CODE");
				item2.add(s2);
			}
			request.setAttribute("item2", item2);
			
			String sql1 ="SELECT PRODUCT_CODE,PRODUCT_NAME,MAKER_CODE FROM PRODUCT";
						
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			
			ResultSet rs1 = statement1.executeQuery();
			
			ArrayList<String[]> item1 = new ArrayList<>();
			while(rs1.next()==true) {
				String[] s1 = new String[3];
				s1[0]=rs1.getString("PRODUCT_CODE");
				s1[1]=rs1.getString("PRODUCT_NAME");
				s1[2]=rs1.getString("MAKER_CODE");
				item1.add(s1);
			}
			request.setAttribute("item1", item1);
			
			
			
			
			RequestDispatcher rd = 
						request.getRequestDispatcher("/WEB-INF/jsp/product.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		


	}

}