package github;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchSelenideRepo {

    @Test
    void shouldFindSelenideRepositoryInGithub() {
        //открыть страницу github.com
        open("https://github.com");
        //ввести в поле поиска selenide и нажать enter
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        //нажимаем линк от первого результата поиска
        $$("ul.repo-list li").first().$("a").click();   //если мы ищем список элементов, мы используем $$
        // check: в заголовке встречается selenide/selenide
        $("h1").shouldHave(text("selenide / selenide")); //нужны пробелы вокруг слэшей, так как будет перенос строки и текст будет не найден
        //sleep(5000); //временно посмотреть браузер открытым в конце

        /*  это все для selenide
        $("").click();
        $("").shouldBe(visible);

        все проверки нужно делать через should
         */

        /*
        это только для selenium и junit не знает такого
        Assertions.assertEquals($("").getText(), "selenide / selenide")
        это всё не сработает в selenide
         */

        // ARRANGE (optional) можно сразу что-то провеярть и совершат действие
        // ACT
        // ASSERT (проверка в конце)
        //это структура для одного теста

        // часто в структуре теста не обязательно на каждом шаге проверять сработал ли шаг, иногда достаточно arrange,
        // а потом просто act, act, act, assert




    }

}
