// Define a template for animal cards
let cardBlueprint = `<div class="col">
                  <div class="card animal-card">
                      <img src="img/&src;" class="card-img-top" alt="AnimalImage">
                      <div class="card-body">
                          <h5 class="card-title animal-card-title">&species;</h5>
                          <p class="card-text">Name: &name; </p>
                          <p class="card-text">Age: &age; years old</p>
                          <p class="card-text">Color: &color;</p>
                          <p class="card-text">Gender: &gender; </p>
                          <p class="animal-card-price text-end">&price;dkk</p>
                      </div>
                  </div>
              </div>`;

// Fetch the XML file containing pet information
fetch('../savedInformation/petsForWebsite.xml')
.then(response => response.text()) // Parse response as text
.then(data => {
    // Parse the XML data
    let parser = new DOMParser();
    let xmlDoc = parser.parseFromString(data, "text/xml");

    // Check if there are no pets in the XML file
    if (xmlDoc.getElementsByTagName("pets")[0].childElementCount == 0) {
        document.getElementById("noPetsMessage").style.display = "flex"; // Show "no pets" message
    }

    // Loop through each pet in the XML file
    for (let element of xmlDoc.getElementsByTagName("pets")[0].children) {
        let currentElement = cardBlueprint;

        // Set the appropriate image based on the pet's type
        switch (element.tagName) {
            case "Rodent":
                currentElement = currentElement.replace("&src;", "Hamster.jpg");
                break;
            case "Dog":
                if (getRandomInt(2) == 1) {
                    currentElement = currentElement.replace("&src;", "dog.jpg");
                } else {
                    currentElement = currentElement.replace("&src;", "dog1.jpg");
                }
                break;
            case "Cat":
                if (getRandomInt(2) == 1) {
                    currentElement = currentElement.replace("&src;", "cat.jpg");
                } else {
                    currentElement = currentElement.replace("&src;", "Cat2.jpg");
                }
                break;
            case "Bird":
                if (getRandomInt(2) == 1) {
                    currentElement = currentElement.replace("&src;", "bird.jpg");
                } else {
                    currentElement = currentElement.replace("&src;", "bird.PNG");
                }
                break;
            case "Fish":
                currentElement = currentElement.replace("&src;", "fish.jpg");
                break;
        }

        // Replace placeholders with actual pet information
        currentElement = currentElement.replace("&species;", element.getElementsByTagName("species")[0].textContent);
        currentElement = currentElement.replace("&name;", element.getElementsByTagName("name")[0].textContent);
        currentElement = currentElement.replace("&age;", element.getElementsByTagName("age")[0].textContent);
        currentElement = currentElement.replace("&color;", element.getElementsByTagName("color")[0].textContent);
        currentElement = currentElement.replace("&gender;", element.getElementsByTagName("gender")[0].textContent);
        currentElement = currentElement.replace("&price;", element.getElementsByTagName("price")[0].textContent);

        // Append the populated card to the container
        document.getElementById("pets").innerHTML += currentElement;
    }
})
.catch(error => {
    console.error('Error:', error); // Log errors to the console
});

// Function to generate a random integer from 0 to max-1
function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}
