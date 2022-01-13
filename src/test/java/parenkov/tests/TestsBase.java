package parenkov.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import parenkov.config.App;
import parenkov.config.Project;
import parenkov.helpers.AllureAttachments;
import parenkov.helpers.DriverSettings;
import parenkov.helpers.DriverUtils;

import java.io.InputStream;

//@ExtendWith({AllureJunit5.class})
public class TestsBase {

    @BeforeAll
    static void testConfiguration() {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//        DriverSettings.configure();

        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
    }

//    @AfterEach
//    public void addAttachments() {
//        String sessionId = DriverUtils.getSessionId();
//
//        AllureAttachments.addScreenshotAs("Last screenshot");
//        AllureAttachments.addPageSource();
//        AllureAttachments.addBrowserConsoleLogs();
//
//        Selenide.closeWebDriver();
//
//        if (Project.isVideoOn()) {
//            AllureAttachments.addVideo(sessionId);
//        }
//    }

    public String request(int sheet, int row, int cell) throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("request.xlsx")) {
            XLS parsed = new XLS(stream);
            String body = parsed.excel.getSheetAt(sheet).getRow(row).getCell(cell).getStringCellValue();
            return body;
        }
    }
}