# crypto-api

The crypto-api is using Feign Client to get CryptoCurrency Information. 
WIREMOCK is used to stub the external URL:https://api.coingecko.com/api/v3/

# Branches(Master/DEV)

* Dev branch: COINGECKO API is used for real-time data
* Master Branch: WIREMOCK with stubbed information(updated to 3 Sepetember 2020) is used for testing purpose.

# How to run:
1.  Under this folder, run: docker-compose up --build
2.  Free to visit the following site which is pre-stubbed by WIREMOCK.
* http://localhost:3000/coins/bitcoin
* http://localhost:3000/coins/litecoin
* http://localhost:3000/coins/markets (which is equal to http://localhost:3000/coins/markets?vs_currency=aud&per_page=10&page=1)
* http://localhost:3000/coins/markets?vs_currency=usd&per_page=10&page=1
* http://localhost:3000/coins/markets?vs_currency=usd&per_page=1&page=1
3.  Stop the API, run: docker-compose down 

# API Endpoints
1. /coins/{currencyID}, where currencyID is id of a valid cryptocurrency
2. /coins/markets?vs_currency={currencyID}&per_page={paginationLimit}&page={pageNumber}, where currencyID should be either usd, aud, jpy(default value is aud); paginationLimit should be from 1 to 10 (default value is 10), and pageNumber should be an interger which is bigger than 0  (default value is 1).

# API Testing
1.  Under this folder, run: docker-compose up --build to run the API on http://localhost:3000 and access the Wiremock server on http://localhost:8080
2.  Run: cd api-testing. This goes to the folder of api-testing
3.  Run: mvn clean test. This could run the automated REST Assured API tests
