function personalBMI(name, age, weight, height) {
    let bmi = Math.round(weight / (height / 100 * height / 100));

    let status = () => {
        if(bmi < 18.5)
            return "underweight"
        if (bmi < 25)
            return "normal"
        if (bmi < 30)
            return "overweight"
        return "obese"
    }

    let result = {
        name: name,
        personalInfo: {
            age: age,
            weight: weight,
            height: height
        },
        BMI: bmi,
        status: status()
    }

    if (status() === 'obese') {
        result.recommendation = 'admission required';
    }

    console.log(result);
}

personalBMI("Peter", 29, 75, 182);
personalBMI("Honey Boo Boo", 9, 57, 137);