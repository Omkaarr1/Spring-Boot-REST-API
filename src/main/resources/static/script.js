$(document).ready(function() {
    // Hide the after-login section initially
    $(".afterlogin").hide();
    $(".billingDetails").hide();
    $(".updateDetails").hide();

    // Check if the user is already logged in (by checking session storage)
    if (sessionStorage.getItem('isLoggedIn')) {
        $(".main").hide();
        $(".afterlogin").show();
    }

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
                            console.log("data -> "+response);
                            $('#fetchUser_Id').text(response);
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

    // Handle resource selection
    // $(".btn").on("click", function() {
    //     const resourceType = $(this).text();
    //     if(resourceType !== "Logout") {
    //         alert("You have selected: " + resourceType);
    //     }
    // });

    // Handle Logout button click
    $("#logoutBtn").on("click", function() {
        sessionStorage.removeItem('isLoggedIn');
        $(".afterlogin").hide();
        $(".main").show();
    });

     // Handle Billing Details button click
     $("#billingBtn").on("click", function() {
        $(".afterlogin").hide(); // Remove all existing content
        $(".billingDetails").show(); 
    });

    $("#updateBtn").click(()=>{
        $(".afterlogin").hide(); // Remove all existing content
        $(".updateDetails").show();
    });

    $("#validateBtn").click(()=>{

        const data = {
            "username": $("#username").val(),
            "password":$("#password").val()
        }

        console.log(data);

        $.ajax({
            url: '/user/authUser', // Replace with your server endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // Convert the JSON object to a string
            success: function(response) {
                console.log('Login Response:', response);
                if(response == false)
                   alert("username or password incorrect");
                else{
                $("#validateBtn").hide();
                $("#newPassword").show();
                $("#password").hide();
                $("#submitBtn").show();}
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
                if(response == false) {
                    alert("Internal Server Error");
                } else {
                    alert("Data Updated");
                    $(".updateDetails").hide();
                    $(".afterlogin").show();
                }
            },
            error: function(xhr, status, error) {
                console.error('Login Error:', error);
                alert('Login failed: ' + error);
            }
        });
    });

});
