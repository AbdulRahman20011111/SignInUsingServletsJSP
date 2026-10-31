package signinproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MySignUpProject extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
				Connection con=null;
				PreparedStatement st=null;
				ResultSet rs=null;

				String qry2="select * from studentinfo where email=? && password=? ";
				String uemail=req.getParameter("uemail");
				String upassword=req.getParameter("upassword");
//				System.out.println(uemail);
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps","root","root");
					
					
					 
					 st=con.prepareStatement(qry2);
					 st.setString(1, uemail);
					 st.setString(2, upassword);
					 rs=st.executeQuery();
					if (rs.next()) {
						 
						 String sname=rs.getString("sname");
						 String email=rs.getString("email");
//						 String branch=rs.getString("branch");
//						 String address=rs.getString("address");
//						 System.out.println(rs.getString("sname"));
	//					req.setAttribute("res", email);
						 
						 HttpSession fs=req.getSession();
						 fs.setAttribute("email", email);
//						 
							RequestDispatcher rd=req.getRequestDispatcher("si?res="+email);
							rd.forward(req, resp);
					}
					else {
						PrintWriter pw=resp.getWriter();
						pw.print("<html><body>"
								+ "<h1> Sorry no such user is present </h1>"
								
								+ "</html></body>");
					}
						
					 
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
						
						if (st!=null) {
							try {
								st.close();
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
