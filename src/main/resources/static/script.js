$(document).ready(function() {
    // Hide the after-login section initially
    $(".afterlogin").hide();
    $(".billingDetails").hide();
    $(".updateDetails").hide();
    $("#vmDiv").hide();

    // Check if the user is already logged in and has a stored user ID
    if (sessionStorage.getItem('isLoggedIn') && sessionStorage.getItem('user_id')) {
        $(".main").hide();
        $(".afterlogin").show();
        $('#fetchUser_Id').text(sessionStorage.getItem('user_id'));
    }

    $("#backBtn").on("click", function() {
        $(".billingDetails").hide();  // Hide the billingDetails section
        $(".afterlogin").show();      // Show the afterlogin section again
    });

    $("#backBtnn").on("click", function() {
        $(".updateDetails").hide();  // Hide the billingDetails section
        $(".afterlogin").show();      // Show the afterlogin section again
    });

    // Handle Signup form submission
    $('.signup form').on('submit', function(event) {
        event.preventDefault();
        const formData = $(this).serializeArray();
        const data = {};

        // Convert the serialized form data into a JSON object
        $.each(formData, function(index, field) {
            data[field.name] = field.value;
        });

        $.ajax({
            url: '/user/createUser', // Replace with your server endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // Convert the JSON object to a string
            success: function(response) {
                console.log('Signup Response:', response);
                alert('Signup successful: ' + response);
            },
            error: function(xhr, status, error) {
                console.error('Signup Error:', error);
                alert('Signup failed: ' + error);
            }
        });
    });

    // Handle Login form submission
    $('.login form').on('submit', function(event) {
        event.preventDefault();
        const formData = $(this).serializeArray();
        const data = {};

        console.log(data);

        // Convert the serialized form data into a JSON object
        $.each(formData, function(index, field) {
            data[field.name] = field.value;
        });

        $.ajax({
            url: '/user/authUserByUsername', // Replace with your server endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // Convert the JSON object to a string
            success: function(response) {
                console.log('Login Response:', response);
                if(response){
                    $(".main").hide();
                    $(".afterlogin").show();

                    $.ajax({
                        url: '/user/getUser_id',  // The endpoint to fetch the user ID
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({
                            username: $("#usernamee").val()  // Replace 'yourUsername' with the actual username variable
                        }),
                        success: function(response) {
                            // Assuming the response contains the user ID
                            console.log("data -> " + response);
                            $('#fetchUser_Id').text(response);

                            // Store the user ID in sessionStorage
                            sessionStorage.setItem('user_id', response);
                        },
                        error: function(xhr, status, error) {
                            console.error('Error fetching user ID:', error);
                        }
                    });
                    sessionStorage.setItem('isLoggedIn', true); // Store login state
                }
                else
                alert('Login Failed, incorrect password')
            },
            error: function(xhr, status, error) {
                console.error('Login Error:', error);
                alert('Login failed: ' + error);
            }
        });
    });

    // Handle Logout button click
    $("#logoutBtn").on("click", function() {
        sessionStorage.removeItem('isLoggedIn');
        sessionStorage.removeItem('user_id');
        $(".afterlogin").hide();
        $(".main").show();
    });

    $("#updateBtn").click(() => {
        $(".afterlogin").hide(); // Remove all existing content
        $(".updateDetails").show();
    });

    $("#validateBtn").click(() => {

        const data = {
            "username": $("#username").val(),
            "password": $("#password").val()
        }

        console.log(data);

        $.ajax({
            url: '/user/authUser', // Replace with your server endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // Convert the JSON object to a string
            success: function(response) {
                console.log('Login Response:', response);
                if (response == false)
                   alert("username or password incorrect");
                else {
                $("#validateBtn").hide();
                $("#newPassword").show();
                $("#password").hide();
                $("#submitBtn").show(); 
                $("#backBtnn").hide();
                }
            },
            error: function(xhr, status, error) {
                console.error('Login Error:', error);
                alert('Login failed: ' + error);
            }
        });
    });

    $(".update.card form").on('submit', function(event) {
        event.preventDefault(); // Correct the preventDefault usage

        const data = {
            "username": $("#username").val(),
            "password": $("#newPassword").val()
        };

        console.log(data);

        $.ajax({
            url: '/user/updateUser', // Replace with your server endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // Convert the JSON object to a string
            success: function(response) {
                console.log('Login Response:', response);
                if (response == false) {
                    alert("Internal Server Error");
                } else {
                    $("#validateBtn").show();
                    $("#newPassword").hide();
                    $("#password").show();
                    $("#submitBtn").hide(); 
                    $("#backBtnn").show();
                    $(".updateDetails").hide();
                    $(".afterlogin").show();
                    $("#username").val("");
                    $("#password").val("");
                    alert("Data Updated");
                }
            },
            error: function(xhr, status, error) {
                console.error('Login Error:', error);
                alert('Login failed: ' + error);
            }
        });
    });

// Handle Billing Details button click
$("#billingBtn").on("click", function() {
    $(".afterlogin").hide(); // Remove all existing content
    $(".billingDetails").show(); 

    console.log($("#fetchUser_Id").text())

    $.ajax({
        url: '/resources/getResourcesBasedOnUserId',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            user_id: $("#fetchUser_Id").text()  // Replace 'yourUserId' with the actual user ID
        }),
        success: function(response) {
            var billingTable = $('#billingData');
            billingTable.empty();  // Clear the table before adding new data
            
            // Add table headers again after clearing the table
            var tableHeader = '<thead>' +
                              '<tr>' +
                                  '<th>Resource ID</th>' +
                                  '<th>Vendor ID</th>' +
                                  '<th>Type of Resource</th>' +
                                  '<th>Quantity</th>' +
                                  '<th>Price Per Hour</th>' +
                                  '<th>Total Hours Used</th>' +
                                  '<th>Total Cost</th>' + // Add a new column for Total Cost
                              '</tr>' +
                              '</thead>';
            billingTable.append(tableHeader);
    
            var tableBody = '<tbody>';
            var totalBill = 0; // Variable to accumulate the total bill amount

            // Iterate through the response and add rows to the table
            response.forEach(function(resource) {
                var totalCost = resource.pricePerHour * resource.totalNoOfHoursUsed * resource.quantity; // Calculate total cost for each resource
                totalBill += totalCost; // Add to the total bill

                var row = '<tr>' +
                            '<td>' + resource.resource_id + '</td>' +
                            '<td>' + resource.vendor_id + '</td>' +
                            '<td>' + resource.typeOfResource + '</td>' +
                            '<td>' + resource.quantity + '</td>' +
                            '<td>' + resource.pricePerHour + '</td>' +
                            '<td>' + resource.totalNoOfHoursUsed + '</td>' +
                            '<td>Rs.' + totalCost.toFixed(2) + '</td>' + // Add the total cost to the table
                          '</tr>';
                tableBody += row;
            });

            // Add a row for the total bill amount
            var totalRow = '<tr>' +
                           '<td colspan="6" style="text-align:right; font-weight:bold;">Total Bill:</td>' +
                           '<td style="font-weight:bold;">Rs.' + totalBill.toFixed(2) + '</td>' +
                           '</tr>';
            tableBody += totalRow;

            tableBody += '</tbody>';
    
            billingTable.append(tableBody);
        },
        error: function(xhr, status, error) {
            console.error('Error fetching resources:', error);
        }
        });
    });

    $("#vm").click(() => {
        $(".afterlogin").hide();
        $("#vmDiv").show();
        
        $.ajax({
            url: "/cloudvendor/getAll", // Assuming your API endpoint
            method: "GET",
            success: function(data) {
                let rows = '';
                let options = '<option value="">Select Vendor</option>';
                data.forEach(vendor => {
                    rows += `
                        <tr>
                            <td><input type="checkbox" class="vendorCheckbox" value="${vendor.vendor_id}"></td>
                            <td>${vendor.vendor_id}</td>
                            <td>${vendor.vendorName}</td>
                            <td>${vendor.vendorAddress}</td>
                            <td>${vendor.vendorPhoneNumber}</td>
                            <td>${vendor.quantity}</td>
                            <td>${vendor.price_per_hour}</td>
                        </tr>
                    `;
                });
                $("#vendorTable tbody").html(rows);
                $("#vendorSelect").html(options);
            },
            error: function(err) {
                console.error("Failed to fetch vendor details:", err);
            }
        });
    });



});
