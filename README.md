# Receipt Processor Web Service
- This service processes receipts, assigns points based on rules, and provides endpoints to interact with receipts and points.
- Challenge problem statement: https://github.com/fetch-rewards/receipt-processor-challenge

## Language and Framework
- Java 17
- Spring Boot 3.4.2
- Maven

## Architecture

- **MVC Pattern**: Clean separation of concerns between models, views, and controllers.
- **Repository**: Uses a `ConcurrentHashMap` to store data temporarily, since persistence isn't required.
- **Strategy Pattern**: Flexible and maintainable point calculation with interchangeable strategies.
- **Validation & Error Handling**: Request fields are validated, and errors are handled globally with meaningful messages.


# Setup Instruction
Since this application is written in Java 17 with Spring Boot 3.4+, it is dockerized for the interviewers to run locally, the DockerFile also contains Maven commands so no need for Maven/Gradle locally

## Step 1: Clone the Repository

Clone the repository to your local machine and navigate into the directory:
````
git clone https://github.com/lsh54718579/Fetch-Receipt-Process-Challenge.git
````
````
cd Fetch-Receipt-Process-Challenge
````
## Step 2: Build and run the Docker Image:
In the same directory as the local repo, run the following docker command in terminal:
````
docker build -t receipt-process-service .
````
````
docker run -p 8080:8080 receipt-process-service
````

## Step 3: Call the endpoints 
### 1. Process Receipt 
#### Example Request
````
   curl -X POST http://localhost:8080/receipts/process \
   -H "Content-Type: application/json" \
   -d '{
   "retailer": "Target",
   "purchaseDate": "2022-01-01",
   "purchaseTime": "13:01",
   "items": [
   {
   "shortDescription": "Mountain Dew 12PK",
   "price": "6.49"
   },
   {
   "shortDescription": "Emils Cheese Pizza",
   "price": "12.25"
   },
   {
   "shortDescription": "Knorr Creamy Chicken",
   "price": "1.26"
   },
   {
   "shortDescription": "Doritos Nacho Cheese",
   "price": "3.35"
   },
   {
   "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
   "price": "12.00"
   }
   ],
   "total": "35.35"
   }'
````

#### Example Response: 
````
   {
   "id": "7fb1377b-b223-49d9-a31a-5a02701dd310"
   }
````

### 2. Get Points for Receipt
#### Example Request
````
   curl -X GET http://localhost:8080/receipts/7fb1377b-b223-49d9-a31a-5a02701dd310/points
````
#### Example Response:
````
   {
   "points": 28
   }
````

