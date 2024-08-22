$(document).ready(function() {
    // Hide the after-login section initially
    $(".afterlogin").hide();

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

        // Convert the serialized form data into a JSON object
        $.each(formData, function(index, field) {
            data[field.name] = field.value;
        });

        $.ajax({
            url: '/user/authUser', // Replace with your server endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // Convert the JSON object to a string
            success: function(response) {
                console.log('Login Response:', response);
                $(".main").hide();
                $(".afterlogin").show();
                sessionStorage.setItem('isLoggedIn', true); // Store login state
            },
            error: function(xhr, status, error) {
                console.error('Login Error:', error);
                alert('Login failed: ' + error);
            }
        });
    });

    // Handle resource selection
    $(".btn").on("click", function() {
        const resourceType = $(this).text();
        alert("You have selected: " + resourceType);
        // Additional logic for handling resource selection can be added here
    });

    // Example: Display the selected resource type after login
    // $(".afterlogin").on("show", function() {
    //     $(".afterlogin").html("<h2>Welcome! Choose your resources.</h2>");
    //     $(".afterlogin").append("<p>Your selected resource: " + selectedResource + "</p>");
    // });
});
