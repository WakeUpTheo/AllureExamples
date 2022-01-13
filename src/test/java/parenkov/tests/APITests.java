package parenkov.tests;

import io.qameta.allure.Story;
import io.qameta.allure.Owner;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static parenkov.tests.Specs.baseRequest;

@Story("Demo Web Shop")
@Owner("Fedor Parenkov")
public class APITests extends TestsBase {

    @Test
    @Feature("Registration")
    @DisplayName("User registration via API")
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
                    .statusCode(302)
                    .log().all();
        });
    }

    @Test
    @Feature("Shopping Cart")
    @DisplayName("Adding an item to the Shopping Cart via API")
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
                    .log().all()
                    .body("success", is(true))
                    .body("message", is("The product has been added to your " +
                            "<a href=\"/cart\">shopping cart</a>"));
        });
    }

    @Test
    @Feature("Feedback")
    @DisplayName("Sending feedback by 'Contact Us' form via API")
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
                    .statusCode(200)
                    .log().all();
        });
    }
}