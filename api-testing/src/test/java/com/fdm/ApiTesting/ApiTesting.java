package com.fdm.ApiTesting;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTesting
{
    private static final String BASE_URI = "http://localhost:3000";

    @BeforeAll
    public void init() {
        baseURI = BASE_URI;
    }

    @Test
    public void that_get_currency_response_time_is_lessThan1000L() {
        get("/coins/bitcoin").then().time(lessThan(1000L));
    }

    @Test
    public void test_that_api_returns_200_for_valid_currency() {
        get("/coins/bitcoin")
            .then()
            .assertThat().statusCode(200);
    }


    @Test
    public void test_that_api_returns_404_for_invalid_currency() {
        get("/coins/bitco")
            .then()
            .assertThat()
            .statusCode(404);
        }

    @Test
    public void test_that_market_is_valid() {
        get("/coins/markets?currency=aud&per_page=10&page=1").then() .assertThat().statusCode(200);
    }

    @Test
    public void that_market_response_time_below_1000L() {
        get("/coins/markets?currency=aud&per_page=10&page=1").then().time(lessThan(1000L));
    }

    @Test
    public void that_market_invaid_with_invalid_cureency() {
        get("/coins/markets?currency=abc&per_page=10&page=1").then().assertThat().statusCode(404);
    }
    
    @Test
    public void that_market_invaid_with_invalid_page() {
        get("/coins/markets?currency=aud&per_page=10&page=11").then().assertThat().statusCode(404);
    }
}
