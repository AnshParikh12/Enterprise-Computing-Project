import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

@SuppressWarnings("serial")
public class AuthenticationServlet extends HttpServlet {   
	
   // process "post" requests from clients
   @Override
   protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  {
      //Variables used to connect to the database
      Connection connection;
      Properties properties = new Properties();
      Properties user = new Properties();
      FileInputStream filein = null;
      FileInputStream userIn = null;
      PreparedStatement p;
      boolean credentialsMatch = false;
      String username = "", password = "";
      try
      {         
         //Reading information for normal connection
         Class.forName("com.mysql.cj.jdbc.Driver");
         filein = new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.1_Tomcat10-1/webapps/Project-4/WEB-INF/lib/credentialsdb.properties");
         userIn = new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.1_Tomcat10-1/webapps/Project-4/WEB-INF/lib/root.properties");
         user.load(userIn);
         properties.load(filein);

         //Making connections
         connection = DriverManager.getConnection(properties.getProperty("MYSQL_DB_URL"), user.getProperty("MYSQL_DB_USERNAME"), user.getProperty("MYSQL_DB_PASSWORD"));
         
         //Getting username and password from the html form post
         username = request.getParameter("uname");
         password = request.getParameter("psw");

         //Using prepared statements
         p = connection.prepareStatement("select * from usercredentials where login_username = ? and login_password = ?");
         p.setString(1, username);
         p.setString(2, password);

         ResultSet resultSet = p.executeQuery();
         
         //If the resultSet has some values in it then it means there are matching credentials in the database 
         if(resultSet.next())
         {
            credentialsMatch = true;
         }
         else
         {
            credentialsMatch = false;
         }
      }
      catch(Exception s)
      {
         s.printStackTrace();
         System.exit(1);
      }

      //If credentials matched then redirecting accordingly
      if(credentialsMatch)
      {
         if(username.equals("root"))
            response.sendRedirect("rootHome.jsp");
         else if(username.equals("client"))
            response.sendRedirect("clientHome.jsp");
         else if(username.equals("dataentryuser"))
            response.sendRedirect("dataentryuserHome.jsp");
         else if(username.equals("theaccountant"))
            response.sendRedirect("accountantHome.jsp");
      }
      //Credentials did not match
      else
      {
         response.sendRedirect("errorpage.html");

      }

   } //end doPost() method
   
} //end WelcomeServlet class

