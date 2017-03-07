var images = [], x = -1;
var context = 
images[0] = "http://localhost:8080/ShoppingCart/static/images/adv/adv1.jpg";
images[1] = "http://localhost:8080/ShoppingCart/static/images/adv/adv3.jpg";
images[2] = "http://localhost:8080/ShoppingCart/static/images/adv/adv2.jpg";
function displayNextImage() {
    x = (x === images.length - 1) ? 0 : x + 1;
    document.getElementById("img").src = images[x];
}
function startTimer() {
    setInterval(displayNextImage, 3000);
}