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
            .sql-inner-box {
                border: 5px solid #ccc;
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
                margin-top: 10px;
                padding: 10px 20px;
                background-color: green;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-bottom: 20px;
            }
            table {
                width: 60%;
                border-collapse: collapse;
                margin-bottom: 20px;
                margin: 0 auto;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
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
        <h2>Data Entry Application</h2>
        <hr>
        <p>You are connected to the Project 4 Enterprise System database as a <span>data-entry-level</span> user. 
            <br>Enter the data values in a form below to add a new record to the corresponding database table.</p>
        <hr> 
        <div class="sql-box">
            <div class="sql-inner-box">
                <form action="dataentryuserHomeSuppliers" method="post" id="supplier-form">
                    <h3>Suppliers Record Insert</h3>
                    <table id="supplier-table">
                        <tr>
                            <th>snum</th>
                            <th>sname</th>
                            <th>status</th>
                            <th>city</th>
                        </tr>
                        <tr>
                            <td><input type="text" name="snum"></td>
                            <td><input type="text" name="sname"></td>
                            <td><input type="text" name="status"></td>
                            <td><input type="text" name="s-city"></td>
                        </tr>
                    </table>
                    <div class="button-container">
                        <input type="submit" value="Enter Supplier Record Into Database" name="enterButton" class="sql-button" />
                        <input type="button" value="Clear Data and Results" name="clearButton" class="sql-button" onclick="javascript:eraseData();" />
                    </div>
                </form>
            </div>

            <div class="sql-inner-box">
                <form action="dataentryuserHomeParts" method="post" id="part-form">
                    <h3>Parts Record Insert</h3>
                    <table id="supplier-table">
                        <tr>
                            <th>pnum</th>
                            <th>pname</th>
                            <th>color</th>
                            <th>weight</th>
                            <th>city</th>
                        </tr>
                        <tr>
                            <td><input type="text" name="pnum"></td>
                            <td><input type="text" name="pname"></td>
                            <td><input type="text" name="color"></td>
                            <td><input type="text" name="weight"></td>
                            <td><input type="text" name="p-city"></td>
                        </tr>
                    </table>
                    <div class="button-container">
                        <input type="submit" value="Enter Part Record Into Database" name="enterButton" class="sql-button" />
                        <input type="button" value="Clear Data and Results" name="clearButton" class="sql-button" onclick="javascript:eraseData();" />
                    </div>
                </form>
            </div>

            <div class="sql-inner-box">
                <form action="dataentryuserHomeJobs" method="post" id="job-form">
                    <h3>Jobs Record Insert</h3>
                    <table id="supplier-table">
                        <tr>
                            <th>jnum</th>
                            <th>jname</th>
                            <th>numworkers</th>
                            <th>city</th>
                        </tr>
                        <tr>
                            <td><input type="text" name="jnum"></td>
                            <td><input type="text" name="jname"></td>
                            <td><input type="text" name="numworkers"></td>
                            <td><input type="text" name="j-city"></td>
                        </tr>
                    </table>
                    <div class="button-container">
                        <input type="submit" value="Enter Job Record Into Database" name="enterButton" class="sql-button" />
                        <input type="button" value="Clear Data and Results" name="clearButton" class="sql-button" onclick="javascript:eraseData();" />
                    </div>
                </form>
            </div>

            <div class="sql-inner-box">
                <form action="dataentryuserHomeShipments" method="post" id="shipment-form">
                    <h3>Shipments Record Insert</h3>
                    <table id="supplier-table">
                        <tr>
                            <th>snum</th>
                            <th>pnum</th>
                            <th>jnum</th>
                            <th>quantity</th>
                        </tr>
                        <tr>
                            <td><input type="text" name="sh-snum"></td>
                            <td><input type="text" name="sh-pnum"></td>
                            <td><input type="text" name="sh-jnum"></td>
                            <td><input type="text" name="quantity"></td>
                        </tr>
                    </table>
                    <div class="button-container">
                        <input type="submit" value="Enter Shipment Record Into Database" name="enterButton" class="sql-button" />
                        <input type="button" value="Clear Data and Results" name="clearButton" class="sql-button" onclick="javascript:eraseData();" />
                    </div>
                </form>
            </div>
        </div>
        <h3 style="text-align: center;">Execution Results:</h3>
        <table id="data">
            ${message}
        </table>
    </body>
</html>
