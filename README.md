# stock-management-visualDNA
This is a stock management service to maintain stock inventory for a restaurant.
## Running the service
prerequistes:
1. Maven 3
2. Java 8 
3. Google chrome postman Plugin as API client

clone the project locally and build the jar with "mvn package" from the root of the folder
Then you can run the Jar with "java -jar target/stock-management-1.0-SNAPSHOT.jar"

## Hitting the endpoints
the service base URL is http://<host>:8080/stock

Simply import [postman collection](postman.json) into google postan plugin to use pre-configured requests

examples of endpoints URLS:
- http://localhost:8080/stock/low  (to get all low quantity stock items)
- http://localhost:8080/stock/category/MEAT (to get all items for a given category)
- http://localhost:8080/stock/update/4?quantity=4&deduct=true ( to update stock quantity for an item)

## Database
this service currently use in-memory database which is seeded at runtime, However [schemas](src/main/resources/sql/schemas) are included to configure a server mode sql data.

