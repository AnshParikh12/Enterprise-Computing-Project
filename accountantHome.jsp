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
            #operation-form {
                text-align: left;
                margin-bottom: 10px; /* Added margin at the bottom */
                margin-left: 50px;
            }
            #operation-form input[type="radio"] {
                margin-bottom: 15px; /* Added margin between radio buttons */
            }
            #operation-form label {
                font-size: 25px; /* Changed font size for labels */
                color: #555; /* Changed font color for labels */
                font-weight: bold;
            }
            .sql-button:hover {
                opacity: 0.8;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
        <p>You are connected to the Project 4 Enterprise System database as a <span>accountant-level</span> user. <br>Please select the operation you would like to perform from the list below.</p>
        <div class="sql-box">
            <form action="accountantHome" method="post" id="operation-form">
                <input type="radio" id="operation1" name="operation" value="operation1">
                <label for="operation1">Get The Maximum Status Value Of All Suppliers (Returns a maximum value)</label><br>
                <input type="radio" id="operation2" name="operation" value="operation2">
                <label for="operation2">Get The Total Weight Of All Parts (Returns a sum)</label><br>
                <input type="radio" id="operation3" name="operation" value="operation3">
                <label for="operation3">Get The Total Number Of Shipments (Returns the current number of shipments in total)</label><br>
                <input type="radio" id="operation4" name="operation" value="operation4">
                <label for="operation4">Get The Name And Number Of Workers Of The Job With The Most Workers (Returns two values)</label><br>
                <input type="radio" id="operation5" name="operation" value="operation5">
                <label for="operation5">List The Name And Status Of Every Supplier (Returns a list of supplier names with status)</label><br>
                <div class="button-container">
                    <input type="submit" value="Execute Command" name="executionButton" class="sql-button" />
                    <input type="button" value="Clear Results" name="clearButton" class="sql-button" onclick="javascript:eraseData();" />
                </div>
            </form>
        </div>
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
