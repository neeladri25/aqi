Get Air Quality Index by City
1. This API processes and returns only the essential data from the third-party API response, ensuring that the most relevant information is included for efficiency and clarity.
2. EndPoint: http://localhost:8080/api/air-quality-index/?city=Pune
3. Method: GET
4. Description: Fetches the air quality index data for the specified city.
5. URL Parameters: city (required) The name of the city for which to fetch the air quality index.
6. curl --location 'http://localhost:8080/api/air-quality-index/?city=Pune'

Caching
1. The API caches responses based on the city name. If a request for the same city is made again within the cache duration, the API will return the cached response instead of calling the external API again.
2. Cache entries expire after a set duration, with a maximum limit on entries to control memory usage.

Error Handling
1. Errors are handled by throwing custom exceptions that provide meaningful error messages.

