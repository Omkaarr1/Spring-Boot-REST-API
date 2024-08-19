$(document).ready(function() {
    $("#dataForm").hide();
    $('#getDataBtn').click(function() {
    $("#dataForm").hide();

        // Perform a GET request when the button is clicked

        $.get("http://localhost:8080/cloudvendor/get/"+$("#vid").val(), function(data) {
            // Convert the JSON object to a formatted string
            const jsonString = JSON.stringify(data, null, 4);
            // Display the JSON string in the dataContainer div
            $('#dataContainer').html(jsonString);
        }).fail(function() {
            // Handle errors
            $('#dataContainer').html('<p>An error occurred while fetching data.</p>');
        });
    });

    $('#getDataBtn2').click(function() {
    $("#dataForm").hide();

        // Perform a GET request when the button is clicked
        $.get('http://localhost:8080/cloudvendor/getAll', function(data) {
            // Convert the JSON object to a formatted string
            const jsonString = JSON.stringify(data, null, 4);
            // Display the JSON string in the dataContainer div
            $('#dataContainer').html(jsonString);
        }).fail(function(err) {

            // Handle errors
            $('#dataContainer').html(err);
        });
    });

    $("#getDataBtn3").click(()=>{
        $("#dataForm").show();
    })

    $('#dataForm').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission
        
        // Create a JavaScript object to hold the form data
        var formData = {
            vendor_id: $('#id').val(),
            vendorName: $('#name').val(),
            vendorAddress: $('#address').val(),
            vendorPhoneNumber: $('#phno').val()
        };
        
        // Send the data using a POST request with JSON format
        $.ajax({
            url: 'http://localhost:8080/cloudvendor/add', // Replace with your actual URL
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                // Display the response data
                $('#resultContainer').html('<p>Form submitted successfully!</p><pre>' + JSON.stringify(response, null, 4) + '</pre>');
                console.log(response);
            },
            error: function(xhr, status, error) {
                // Handle errors
                $('#resultContainer').html('<p>An error occurred while submitting the form.</p>');
                console.log('Error:', error);
            }
        });
        $("#dataForm").hide();

    });
});