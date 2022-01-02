package dex.ivanova;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void RegistrationTest() {
        // Open website
        open("https://demoqa.com/automation-practice-form");

        // Find Element and Set value
        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Smirnov");
        $("#userEmail").setValue("alex.smirnov@gmail.com");
        $(".custom-control-label").click();
        $("#userNumber").setValue("5648798798");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("2014");
        $x("//div[contains(text(),'15')]").click();
        $("#subjectsInput").setValue("Eng").pressEnter();
        $x("//label[contains(text(),'Sports')]").click();
        $x("//label[contains(text(),'Reading')]").click();
        $x("//label[contains(text(),'Music')]").click();
        File file = new File("src/test/img/1.png");
        Selenide.$(byId("uploadPicture")).uploadFile(file);
        $("#currentAddress").setValue("Moscow, Manoilov Street, 64");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Gurgaon").pressEnter();
        $("#submit").scrollTo().click();

        // Assertion
        $("#example-modal-sizes-title-lg").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Alex"));
        $(".table-responsive").shouldHave(text("Smirnov"));
        $(".table-responsive").shouldHave(text("alex.smirnov@gmail.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("5648798798"));
        $(".table-responsive").shouldHave(text("15 May,2014"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Sports, Reading, Music"));
        $(".table-responsive").shouldHave(text("1.png"));
        $(".table-responsive").shouldHave(text("NCR Gurgaon"));

    }

}