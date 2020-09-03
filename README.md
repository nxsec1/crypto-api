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
