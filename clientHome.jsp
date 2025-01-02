<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CNT 4714 Spring 2024 - Project 4</title>
        <style>
            body {
                background-color: #f0f0f0;
            }
            h1 {
                color: blue;
                text-align: center;
            }
            h2 {
                color: green;
                text-align: center;
            }
            p {
                text-align: center;
            }
            span {
                color: red;
            }
            .sql-box {
                text-align: center;
                width: 80%;
                margin: 0 auto;
                padding: 20px;
                background-color: white;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .sql-input {
                width: 60%;
                height: 250px;
                resize: none;
                margin-bottom: 10px;
            }
            .button-container {
                text-align: center; /* Center align the button container */
            }
            .sql-button {
                display: inline-block; /* Changed display to inline-block */
                margin-right: 10px; /* Added margin between buttons */
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .sql-button:hover {
                opacity: 0.8;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script type="text/javascript">
            function eraseText()
            {
                $("#cmd").val("");
            }
        </script>
        <script type="text/javascript">
            function eraseData()
            {
                $("#data").empty();
            }
        </script>
    </head>
    <body>
        <h1>Welcome to the Spring 2024 Project 4 Enterprise System</h1>
        <h2>A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container</h2>
        <hr>
        <p>You are connected to the Project 4 Enterprise System database as a <span>client-level</span> user. <br>Please enter any SQL query or update command in the box below.</p>
        <form action="clientHome" method="post" class="sql-box">
            <textarea id="cmd" name="sqlStatement"  class="sql-input" placeholder="Enter your SQL query or update command">${sqlStatement}</textarea>
            <div class="button-container">
                <input type="submit" value="Execute Command" name="executionButton" class="sql-button" />
                <input type="reset" value="Reset Form" name="resetButton" class="sql-button" onclick="javascript:eraseText();"/>
                <input type="button" value="Clear Results" name="clearButton" class="sql-button" onclick="javascript:eraseData();" />
            </div>
        </form>
        <p>
            All execution results will appear below this line.
        </p>
        <hr>
        <h3 style="text-align: center;">Execution Results:</h3>
        <table id="data">
            ${message}
        </table>
    </body>
</html>
