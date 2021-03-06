package parenkov.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import parenkov.helpers.Layer;
import parenkov.helpers.Microservice;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static parenkov.tests.Specs.baseRequest;

@Tag("API")
@Layer("API")
@Epic("Demo Web Shop")
public class APITests extends APITestsBase {

    @Test
    @AllureId("6712")
    @Owner("Fed Parenkov")
    @Microservice("Register")
    @Feature("Registration")
    @DisplayName("User registration")
    @Severity(SeverityLevel.CRITICAL)
    void userRegistration() throws Exception {
        String body = request(0, 1, 1);
        step("Register new user", () -> {
            given()
                    .spec(baseRequest)
                    .body(body)
                    .when()
                    .post("/register")
                    .then()
                    .statusCode(302);
        });
    }

    @Test
    @AllureId("6714")
    @Owner("Alex Examplov")
    @Microservice("Shopping Cart")
    @Feature("Shopping Cart")
    @DisplayName("Adding an item to the Shopping Cart")
    @Severity(SeverityLevel.CRITICAL)
    void addingAnItemToTheShoppingCart() throws Exception {
        String body = request(0, 2, 1);
        String cookie = request(0, 2, 2);
        step("Add an item with custom specs to the Shopping Cart", () -> {
            given()
                    .spec(baseRequest)
                    .body(body)
                    .cookie(cookie)
                    .when()
                    .post("/addproducttocart/details/74/1")
                    .then()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your " +
                            "<a href=\"/cart\">shopping cart</a>"));
        });
    }

    @Test
    @AllureId("6713")
    @Owner("Fed Parenkov")
    @Microservice("Contact Us")
    @Feature("Feedback")
    @DisplayName("Sending feedback by 'Contact Us'")
    @Severity(SeverityLevel.MINOR)
    void sendingFeedback() throws Exception {
        String body = request(0, 3, 1);
        String cookie = request(0, 3, 2);
        step("Fill the contact form and send feedback", () -> {
            given()
                    .spec(baseRequest)
                    .body(body)
                    .cookie(cookie)
                    .when()
                    .post("/contactus")
                    .then()
                    .statusCode(200);
        });
    }
}