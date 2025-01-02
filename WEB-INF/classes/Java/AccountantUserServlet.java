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
import java.sql.CallableStatement;


public class AccountantUserServlet extends HttpServlet{
    
    // process "post" requests from clients
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  {
        //Get the message from jsp file, it is the output string to be converted to a table
        String message = "";

        //Check which button is clicked
        String button = request.getParameter("operation");

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
            userIn = new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.1_Tomcat10-1/webapps/Project-4/WEB-INF/lib/theaccountant.properties");
            user.load(userIn);
            properties.load(filein);

            //Making connections
            connection = DriverManager.getConnection(properties.getProperty("MYSQL_DB_URL"), user.getProperty("MYSQL_DB_USERNAME"), user.getProperty("MYSQL_DB_PASSWORD"));
            java.sql.Statement stmt = connection.createStatement();

            //Setting properties for the table
            message = message + "<table border='1' style='margin: 0 auto;'>";
            CallableStatement cstmt = connection.prepareCall("call Get_The_Maximum_Status_Of_All_Suppliers()");
            ResultSet result = result = cstmt.executeQuery();

            //Select which operation to run
            if(button != null && button.equals("operation1"))
            {
                cstmt = connection.prepareCall("call Get_The_Maximum_Status_Of_All_Suppliers()");
                result = cstmt.executeQuery();
                //Executing the command and then converting the result set to an html table
                // result = stmt.executeQuery("call project4.Get_The_Maximum_Status_Of_All_Suppliers();");
            }
            else if (button != null && button.equals("operation2"))
            {
                cstmt = connection.prepareCall("call Get_The_Sum_Of_All_Parts_Weights");
                result = cstmt.executeQuery();
            }
            else if (button != null && button.equals("operation3"))
            {
                cstmt = connection.prepareCall("call Get_The_Total_Number_Of_Shipments");
                result = cstmt.executeQuery();
            }
            else if (button != null && button.equals("operation4"))
            {
                cstmt = connection.prepareCall("call Get_The_Name_Of_The_Job_With_The_Most_Workers()");
                result = cstmt.executeQuery();
            }
            else if (button != null && button.equals("operation5"))
            {
                cstmt = connection.prepareCall("call List_The_Name_And_Status_Of_All_Suppliers()");
                result = cstmt.executeQuery();
            }

            //Getting the meta data for the table headers
            ResultSetMetaData meta = result.getMetaData();
            int columnCount = meta.getColumnCount();
            message = message + "<tr>";
            for (int i = 1; i <= columnCount; i++)
            {
                message = message + "<th style='background-color: #007bff; color: white; text-align: center'>" + meta.getColumnName(i) + "</th>";
            }
            message = message + "</tr>";

            // Generate HTML table data
            while (result.next())
            {
                message = message + "<tr>";
                for (int i = 1; i <= columnCount; i++)
                {
                    String bgColor = (result.getRow() % 2 == 0) ? "#a6a6a6" : "#ffffff";
                    message = message + "<td style='background-color: " + bgColor + "; text-align: center'>" + result.getString(i) + "</td>";
                }
                message = message + "</tr>";
            }
            message = message + "</table>";
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/accountantHome.jsp");
        dispatcher.forward(request, response);

    }

}
