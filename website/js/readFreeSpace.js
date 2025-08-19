// Fetch the text file containing the number of free spaces
fetch('../savedInformation/getFreeSpace.txt')
.then(response => response.text()) // Parse the response as text
.then(data => {
    // Update the HTML element with the retrieved free spaces data
    document.getElementById('freeSpaces').innerText = data;
})
.catch(error => {
    // Log the error to the console and display an error message in the HTML
    console.error('Error:', error);
    document.getElementById('freeSpaces').innerText = "An error has occurred";
});
