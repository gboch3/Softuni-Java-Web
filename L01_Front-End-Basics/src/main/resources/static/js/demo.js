function printStars(count) {
    console.log("*".repeat(count))
}

printStars(10);
// printStars("testMe");

separator();

let printStarsObject = function printStars() {
    console.log("*".repeat(20))
};

printStarsObject();

function separator() {
    console.log("------------------------------------------------");
}