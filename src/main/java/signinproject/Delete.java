package signinproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sid=Integer.parseInt(req.getParameter("delete"));
		Connection con=null;
		PreparedStatement st=null;
		String qryDelete="delete from studentinfo where sid=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_steps","root","root");
			
			
			 
			 st=con.prepareStatement(qryDelete);
			 st.setInt(1, sid);
			 



			  int row=st.executeUpdate();
			  System.out.println(row);
			 
			if (row>0) {

				 PrintWriter pw=resp.getWriter();
					pw.print("<html><body>"
							+ "<h1> deleted your row =: "+sid+"</h1>"
							+ "<h1> Number of rows deleted =: "+row+"</h1>"

							+"<a href='visit.html'><button type='button' >Return to Home page </button></a> <br><br>"

							+ "<h1></h1>"
							+ "</html></body>");
			}
				
				if (row==0) {
					PrintWriter pw=resp.getWriter();
					pw.print("<html><body>"
							+ "<h1> Number of rows deleted =: "+row+"</h1>"
							+ "<h1> so such row found </h1>"

							+"<a href='delete.html'><button type='button' >Return to Delete page </button></a> <br><br>"

							+"<a href='visit.html'><button type='button' >Return to Home page </button></a> <br><br>"

							+ "<h1></h1>"
							+ "</html></body>");
				}
			 
			}catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
			}
	}

}
