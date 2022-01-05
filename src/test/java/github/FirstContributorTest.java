package github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FirstContributorTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void firstContributorShouldBeAndreiSolntsev() {
        // 1) Configuration.browserSize=""; чтобы сделать сайт маленьким и
        // проверить будет ли работать тест на маленьком экране, то есть селенид сам скролит к элементу


        // 2) открыть страничку репозитория selenide
        open("https://github.com/selenide/selenide");


        // 3) подвести мышку к первому элементу в области contributors
        //ищем в элементе layout sidebar элемент с текстом contributors
        $(".Layout-sidebar").$(Selectors.byText("Contributors")).closest("div")
                .$$("ul li").first().hover();


        /* 3а) второй вариант поиска элемента содержащего contributions
       $$(".Layout-sidebar .BorderGrid-row").find(text("Contributors"))
                .$$("ul li").first().hover();
        */

        // 4) check: в появившемся окне (оверлей) текст Andrei solntsev
        $$(".Popover-message").find(visible).shouldHave(text("Andrei Solntsev"));                       //$$ это массив элементов
        //или $$(".Popover-message").filterBy(visible).first();






    }



}
