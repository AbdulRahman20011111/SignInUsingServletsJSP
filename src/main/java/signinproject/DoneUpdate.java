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

public class DoneUpdate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String UpdatedMail=req.getParameter("email");
		int sid=Integer.parseInt(req.getParameter("sid"));
		String UpdatedBranch=req.getParameter("branch");
		String UpdatedAddress=req.getParameter("address");
		String UpdatedName=req.getParameter("sname");
		String UpdatedPassword=req.getParameter("password");




//		System.out.println(UpdatedMail);
		
		Connection con=null;
		PreparedStatement st=null;
		
		String qry ="update studentinfo set email=?,sname=?,address=?,branch=?,password=? where sid=?";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps","root","root");
			
			
			 
			 st=con.prepareStatement(qry);
			 st.setString(1, UpdatedMail);
			 st.setString(2, UpdatedName);
			 st.setString(3, UpdatedAddress);
			 st.setString(4, UpdatedBranch);
			 st.setString(5, UpdatedPassword);



			 st.setInt(6, sid);
			 st.executeUpdate();
			 
			
//			 PrintWriter pw=resp.getWriter();
//				pw.print("<html><body>"
//						+ "<h1> The half of square is : "+UpdatedMail+"</h1>"
//						+ "<h1> The half of square is : "+sid+"</h1>"
//
//						
//						+ "<h1></h1>"
//						+ "</html></body>");
//				 HttpSession fs=req.getSession();
//				 fs.setAttribute("email", UpdatedMail);
				
				RequestDispatcher r=req.getRequestDispatcher("si?res="+UpdatedMail);
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
				
				if (st!=null) {
					try {
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
		
	}
	
}
