package signinproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySignInUpProject extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement st=null;
		
		ResultSet rs=null;
		
		String qry="insert into studentinfo values (?,?,?,?,?,?)";
		
		
		
		

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps","root","root");
			
			int eid=Integer.parseInt(req.getParameter("id"));
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			String address=req.getParameter("address");
			String branch=req.getParameter("branch");
			ps=con.prepareStatement(qry);
			ps.setInt(1, eid);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.setString(5, address);
			ps.setString(6, branch);
			ps.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<html><body>"
					+ "<h1>inserted</h1>"
					+ "</html></body>");
			 
			RequestDispatcher r=req.getRequestDispatcher("signin.html");
			
			 r.forward(req, resp);
			 
			}catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
			}finally {
				if (con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (ps!=null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		
	}
}
