package dex.ivanova;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Condition.text;

public class Homework3part2 {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void SoftAssertionsSearch() {
        // перейти на страницу https://github.com/selenide/selenide

        open("https://github.com/selenide/selenide");

        // Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        // Откройте страницу SoftAssertions,
       $("#wiki-body").$(".markdown-body").$("ul").$("li",6).$("a").click();

        // проверьте что внутри есть пример кода для JUnit5
        $("#wiki-body").$(byAttribute("start", "3")).scrollTo().
        $("li").shouldHave(text("Using JUnit5 extend test class:"));

        executeJavaScript("alert('Ура, я сделала домашку!')");

        sleep(5000);







// sleep(5000);













    }









}
