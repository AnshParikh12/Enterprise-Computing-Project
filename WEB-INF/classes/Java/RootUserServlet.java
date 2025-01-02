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

public class RootUserServlet extends HttpServlet{
    
    // process "post" requests from clients
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  {
        //Get the sql statement from the jsp file and message is the output string to be converted to a table
        String sqlStatement = request.getParameter("sqlStatement");
        String message = "";

        //Check which button is clicked
        String button = request.getParameter("executionButton");

        if(button.equals("Execute Command"))
        {          

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
                userIn = new FileInputStream("C:/Program Files/Apache Software Foundation/Tomcat 10.1_Tomcat10-1/webapps/Project-4/WEB-INF/lib/root.properties");
                user.load(userIn);
                properties.load(filein);

                //Making connections
                connection = DriverManager.getConnection(properties.getProperty("MYSQL_DB_URL"), user.getProperty("MYSQL_DB_USERNAME"), user.getProperty("MYSQL_DB_PASSWORD"));
                java.sql.Statement stmt = connection.createStatement();

                //Setting properties for the table
                message = message + "<table border='1' style='margin: 0 auto;'>";

                //Trimming the white spaces
                sqlStatement = sqlStatement.trim();

                String buffer = sqlStatement.substring(0, 6);
                
                //Select query
                if(buffer.equals("select"))
                {
                    //Executing the command and then converting the result set to an html table
                    ResultSet result = stmt.executeQuery(sqlStatement);

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
                //Update queries
                else
                {
                    //We have to implement the business logicin this casee
                    if(sqlStatement.contains("shipments"))
                    {
                        int result = stmt.executeUpdate(sqlStatement);
                        int businessResult = stmt.executeUpdate("UPDATE suppliers AS s INNER JOIN shipments AS sh ON s.snum = sh.snum SET s.status = s.status + 5 WHERE sh.quantity >= 100");
                        message = message + "<tr bgcolor=\"#7cf79f\"><td style=\"text-align: center;\"><b>The statement executed successfully. A total of " + result + " row(s) were updated<br>Business Logic Detected! - Updating Supplier Status<br>Business Logic updated " + businessResult + " supplier status marks.</b></td></tr>";
                    }
                    //Normal update case
                    else
                    {
                        int result = stmt.executeUpdate(sqlStatement);
                        message = message + "<tr bgcolor=\"#7cf79f\"><td style=\"text-align: center;\"><b>The statement executed successfully. A total of " + result + " row(s) were updated<br>Business Logic Not Triggered!</b></td></tr>";
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
            request.setAttribute("sqlStatement", sqlStatement);
            // Forward the request to the frontend
            RequestDispatcher dispatcher = request.getRequestDispatcher("/rootHome.jsp");
            dispatcher.forward(request, response);
        }

    }

}
