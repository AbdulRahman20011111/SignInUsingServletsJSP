package signinproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProfile extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s=req.getSession();
		String email= (String)	s.getAttribute("email");
		String sname= (String)	s.getAttribute("sname");
		String address= (String)	s.getAttribute("address");
		String branch= (String)	s.getAttribute("branch");
		
		String password= (String) s.getAttribute("password");

		
		int sid=(int)s.getAttribute("sid");



//		System.out.println(email);
		
//		Connection con=null;
//		PreparedStatement st=null;
//		ResultSet rs=null;
		
		
		
		
		
		PrintWriter pw=resp.getWriter();
		pw.print("<html><body>"
				+ "<h1> The  : "+email+"</h1>"
				+"<form action='u' method='get'>"
				+"<label>Enter name for updation</label>"
				+ "<input value= "+sname+"  type='text' name='sname'><br><br> "
				
				+"<label>Enter email for updation</label>"
				+ "<input value= "+email+"  type='text' name='email'><br><br> "
				
				+"<label>Enter address for updation</label>"
				+ "<input value= "+address+"  type='text' name='address'><br><br> "
				
				+"<label>Enter branch for updation</label>"
				+ "<input value= "+branch+"  type='text' name='branch'><br><br> "
				
				+"<label>Enter password for updation</label>"
				+ "<input value= "+password+"  type='text' name='password'><br><br> "
				
				
				+"<label>Cant update eid</label>"

				+ "<input value= "+sid+" readonly type='text' name='sid'><br><br> "

//				+ "<input value= ''  type='text' placeholder='enter eid for updation: ' name='eid'> <br><br> "
				+"<button type='submit' >Click here for Update</button>"
				+"</form>"
				+ "<h1></h1>"
				+ "</html></body>");
	}
}
