Get Air Quality Index by City
1. EndPoint: http://localhost:8080/api/air-quality-index/?city=Pune
2. Method: GET
3. Description: Fetches the air quality index data for the specified city.
4. URL Parameters: city (required) The name of the city for which to fetch the air quality index.
5. curl --location 'http://localhost:8080/api/air-quality-index/?city=Pune'

Caching
1. The API caches responses based on the city name. If a request for the same city is made again within the cache duration, the API will return the cached response instead of calling the external API again.
2. Cache entries expire after a set duration, with a maximum limit on entries to control memory usage.

Error Handling
1. Errors are handled by throwing custom exceptions that provide meaningful error messages.

