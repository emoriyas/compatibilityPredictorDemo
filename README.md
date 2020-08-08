# Compatibility Predictor

A simple application that takes in two JSON arrays and outputs a JSON array. The application is built with Java Spring and requires Maven to compile. An already compiled jar file is provided in case Maven is inaccessible.

Once running, the application will listen to port 8080 for POST requests with a JSON body. JSON should contain two arrays, one named "team" and other named "applicants". Array elements should contain a name and an attribute object with four attributes. Following is a sample JSON input:

```
{
    "team" : [
        {
            "name" : "Team1",
            "attributes" : {
                "strength" : 1,
                "intelligence" : 1,
                "endurance" : 1,
                "spicyFoodTolerance" : 10
            }
        }
    ],
    "applicants" : [
        {
            "name" : "Applicant1",
            "attributes" : {
                "strength" : 1,
                "intelligence" : 1,
                "endurance" : 1,
                "spicyFoodTolerance" : 1
            }
        }
    ] 
}
```
There is minimal error checking and improper JSON inputs may result in returning a 400/500 error. Any attribute not specified will default to 1.

A successful execution will return a 200 HTTP response with a JSON body that contains an array of applicants and his/her predicted compatibility score. Following is a sample JSON output:
```
{
    "scoredApplicants" : [
        {
            "name" : "Applicant1",
            "score" : 0.25
        },
        {
            "name" : "Applicant2",
            "score" : 0.75
        },
        {
            "name" : "Applicant3",
            "score" : 1.0},
        {
            "name" : "Applicant4",
            "score" : 1.0
        },
        {
            "name" : "Applicant5",
            "score" : 0.5
        }
    ]
}
```

Port can be customized in src/main/resources/application.properties. Sample CURL requests are provided in curlCommands.txt

### Compiling and Running
```
mvn clean package
```
Compile the project with maven with the command above.

```
java -jar ./target/compatibilityPredictor-0.0.1-SNAPSHOT.jar
```
Run the application with the command above. Application's port can be specified by adding `--server.port=####`.
