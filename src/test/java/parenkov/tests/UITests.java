package parenkov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("UI")
@Epic("Demo Web Shop")
@Owner("Fedor Parenkov")
public class UITests extends UITestsBase {

    @Test
    @AllureId("6711")
    @Feature("Main page")
    @DisplayName("Checking page title")
    @Severity(SeverityLevel.MINOR)
    void checkingPageTitle() {
        step("Open main page", () ->
                open(""));
        step("Check title have text 'Demo Web Shop'", () -> {
            String expectedTitle = "Demo Web Shop";
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @AllureId("6708")
    @Feature("Search")
    @DisplayName("Products search from the catalog")
    @Severity(SeverityLevel.NORMAL)
    void successfulSearch() {
        step("Open main page", () ->
                open(""));
        step("Type 'internet' in the search field and click search button", () -> {
            $(".search-box-text").setValue("internet");
            $(".search-box-button").click();
        });
        step("Check that there are founded content", () ->
                $(".product-grid").shouldNotBe(empty));
    }

    @Test
    @AllureId("6707")
    @Feature("Search")
    @DisplayName("Search for the not existing in the catalog products")
    @Severity(SeverityLevel.NORMAL)
    void unsuccessfulSearch() {
        step("Open main page", () ->
                open(""));
        step("Type 'socks' in the search field and click search button", () -> {
            $(".search-box-text").setValue("socks");
            $(".search-box-button").click();
        });
        step("Check that no content was found", () ->
                $(".search-results")
                        .shouldHave(text("No products were found that matched your criteria.")));
    }

    @Test
    @AllureId("6710")
    @Feature("Shopping cart")
    @DisplayName("Adding a product to the shopping cart")
    @Severity(SeverityLevel.CRITICAL)
    void addingAnItemToTheShoppingCart() {
        step("Open a product page", () ->
                open("/computing-and-internet"));
        step("Click 'Add to cart' button", () ->
                $("#add-to-cart-button-13").click());
        step("Check that the product was add to the shopping cart", () -> {
            $("#bar-notification p")
                    .shouldHave(text("The product has been added to your shopping cart"));
            $("#topcartlink").shouldHave(text("(1)"));
        });
    }

    @Test
    @AllureId("6709")
    @Feature("Shopping cart")
    @DisplayName("Removing a product from the shopping cart [via API and UI]")
    @Severity(SeverityLevel.CRITICAL)
    void deletingAnItemFromTheShoppingCart() {
        step("Add a product to the shopping cart by API and set cookies", () -> {
            String cookie =
                    given()
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .body("addtocart_13.EnteredQuantity=3")
                            .when()
                            .post("/addproducttocart/details/13/1")
                            .then()
                            .statusCode(200)
                            .extract()
                            .cookie("Nop.customer");
            open("/Themes/DefaultClean/Content/images/logo.png");
            getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookie));
        });
        step("Open the shopping cart page", () ->
                open("/cart"));
        step("Remove the product from the shopping cart", () -> {
            $("[name=removefromcart]").click();
            $("input[name=updatecart]").click();
        });
        step("Remove the product from the shopping cart", () -> {
            $(".order-summary-content").shouldHave(text("Your Shopping Cart is empty!"));
            $("#topcartlink").shouldHave(text("(0)"));
        });
    }
}
