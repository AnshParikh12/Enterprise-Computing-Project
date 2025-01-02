import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.ResultSetMetaData;

public class DataEntryUserJobsServlet extends HttpServlet{
    
    // process "post" requests from clients
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  {
        
        //Get the message from jsp file, it is the output string to be converted to a table
        String message = "";

        //Check which button is clicked
        String button = request.getParameter("enterButton");

        //Variables used to connect to the database
        Connection connection;
        Properties properties = new Properties();
        Properties user = new Properties();
        FileInputStream filein = null;
        FileInputStream userIn = null;
        
        try
        {
            //Reading information for normal connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            filein = new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.1_Tomcat10-1/webapps/Project-4/WEB-INF/lib/project4.properties");
            userIn = new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.1_Tomcat10-1/webapps/Project-4/WEB-INF/lib/dataentryuser.properties");
            user.load(userIn);
            properties.load(filein);

            //Making connections
            connection = DriverManager.getConnection(properties.getProperty("MYSQL_DB_URL"), user.getProperty("MYSQL_DB_USERNAME"), user.getProperty("MYSQL_DB_PASSWORD"));

            //Select which operation to run
            if(button != null && button.equals("Enter Job Record Into Database"))
            {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into jobs values(?, ?, ?, ?)");
                String jnum, jname, numworkers, city;

                jnum = request.getParameter("jnum");
                jname = request.getParameter("jname");
                numworkers = request.getParameter("numworkers");
                city = request.getParameter("j-city");

                //Setting properties for the table
                message = message + "<table border='1' style='margin: 0 auto;'>";
                preparedStatement.setString(1, jnum);
                preparedStatement.setString(2, jname);
                preparedStatement.setString(3, numworkers);
                preparedStatement.setString(4, city);

                int result = preparedStatement.executeUpdate();

                if(result > 0)
                {
                    message = message + "<tr bgcolor=\"#7cf79f\"><td style=\"text-align: center;\"><b>New jobs record: (" + jnum + ", " + jname + ", " + numworkers + ", " + city + ") - successfully entered into database.</b></td></tr>";
                }
            }
        }
        catch(SQLException s)
        {
            message = message + "<tr bgcolor=\"#ff0000\"><td style=\"color: #ffffff; text-align: center;\"><b>Error executing the SQL statement:</b><br>" + s.getMessage() + "</td></tr>";
        }
        catch(Exception i)
        {
            message = message + "<tr bgcolor=\"#ff0000\"><td style=\"color: #ffffff; text-align: center;\"><b>Error executing the SQL statement:</b><br>" + i.getMessage() + "</td></tr>";
        }

        // Get HttpSession object
        HttpSession session = request.getSession();
        // Set the message attribute in the session
        request.setAttribute("message", message);
        // Forward the request to the frontend
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dataentryuserHome.jsp");
        dispatcher.forward(request, response);

    }

}
