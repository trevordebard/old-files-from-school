import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class RetrieveComments
 */
public class RetrieveComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveComments() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		//String DB_URL = "jdbc:mysql://52.26.86.130:3306/student";
		String DB_URL = "jdbc:mysql://localhost:3306/wioc";

		// Database credentials
		String USER = "root";
		String PASS = "";

		Connection conn = null;
		Statement stmt = null;
		Statement s = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = (Statement) conn.createStatement();
			s = (Statement) conn.createStatement();

			String sql;
			
			sql = "SELECT * from T_Comments WHERE posts_id = '" +id+ "';";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			System.out.println(sql);
			while(rs.next()) {
				sql = "select users_username from T_Users where users_id='"+rs.getString("users_id")+"' limit 1;";
				String users_username = "b";
				ResultSet r = (ResultSet) s.executeQuery(sql);
				r.first();
				users_username = r.getString("users_username");
				System.out.println(users_username);
				r.close();
				System.out.println(sql);
				 String comment = rs.getString("comments_comment");
				 response.setContentType("text/html;charset=UTF-8");
				 String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
				 out.println("<li class=\"list-group-item list-group-item-action comment-lgi wrap-text\">"+
			          "<h5>"+users_username+"</h5>"+
			          "<p>"+comment+"</p></li>");
			}
		} catch (ClassNotFoundException | SQLException e) {
	
		// TODO Auto-generated catch block
		e.printStackTrace();
		response.sendRedirect("/index.html?error=true");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

