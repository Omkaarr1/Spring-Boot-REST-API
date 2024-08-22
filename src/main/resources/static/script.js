$(document).ready(function() {
    // Handle Signup form submission
    $('.signup form').on('submit', function(event) {
        event.preventDefault();
        const formData = $(this).serializeArray();
        const data = {};

        // Convert the serialized form data into a JSON object
        $.each(formData, function(index, field) {
            data[field.name] = field.value;
        });

        // console.log(data);

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

        // console.log(data);

        $.ajax({
            url: '/user/authUser', // Replace with your server endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // Convert the JSON object to a string
            success: function(response) {
                console.log('Login Response:', response);
                alert('Login successful: ' + response);
            },
            error: function(xhr, status, error) {
                console.error('Login Error:', error);
                alert('Login failed: ' + error);
            }
        });
    });
});
