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

public class FetchLogin extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String res=req.getParameter("res");
		//String res=(String) req.getAttribute("res");
		
//		HttpSession us=req.getSession();
//		 String res1=(String)us.getAttribute("email");
//		 System.out.println(res1);
		
			HttpSession ses=req.getSession();
			 String res1=(String)ses.getAttribute("email");
			
			// System.out.println(res1);

		 
		String qry2="select * from studentinfo where email=?  ";
	//	System.out.println(res);
		
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps","root","root");
			
			
			 
			 st=con.prepareStatement(qry2);
			 st.setString(1, res);
			
			 rs=st.executeQuery();
			if (rs.next()) {
				 
				 String sname=rs.getString("sname");
				 String email=rs.getString("email");
				 String branch=rs.getString("branch");
				 String address=rs.getString("address");
				 String password=rs.getString("password");
				 int sid=rs.getInt("sid");
			//	 System.out.println(rs.getString("sname"));
				 
				 PrintWriter pw=resp.getWriter();
					pw.print("<html><body>"
							
							+ "<h1> Student Profile </h1>"
							
							+"<table border='2px'>"
								+"<tr>"
									+"<th> Student id </th>"
									+"<th>  Name </th>"
									+"<th> Email </th>"
									+"<th> Password </th>"
									+"<th> Address </th>"
									+"<th> Branch </th>"

								+"</tr>"
								
								+"<tr>"
									
									+"<th>"+sid+ " </th>"
									+"<th>  "+sname+ "</th>"
									+"<th> "+email+ "</th>"
									+"<th> "+password+ "  </th>"
									+"<th> "+address+ "  </th>"
									+"<th>"+branch+ "</th>"
	
								+"</tr>"

							+"</table> <br><br>"

//							+ "<h1> "+sname+ " </h1>"
//							+ "<h1> "+email+ " </h1>"
//							+ "<h1> "+branch+ " </h1>"
//							+ "<h1> "+password+ " </h1>"
//							+ "<h1> "+sid+ " </h1>"
//							+ "<h1> res1"+res1+ " </h1>"


							+"<form action='update' method='get'>"
							+"<a href='delete.html'><button type='button' > Want to Delete Row ?</button></a> <br><br>"

							+"<button > Want to update profile ?</button>"
							+"</form>"
							
							+ "</html></body>");
					ses.setAttribute("email", email);
					ses.setAttribute("sname", sname);
					ses.setAttribute("address", address);
					ses.setAttribute("branch", branch);
					ses.setAttribute("sid", sid);
					ses.setAttribute("password", password);

					



				 
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
