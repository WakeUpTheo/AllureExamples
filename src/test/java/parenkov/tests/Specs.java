package parenkov.tests;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static parenkov.filters.CustomLogFilter.customLogFilter;

public class Specs {
    public static RequestSpecification baseRequest = with()
            .filter(customLogFilter().withCustomTemplates())
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .log().method()
            .log().uri()
            .log().headers()
            .log().body();
}