package selenide;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {

    void browser_command_examples() {
        //команды для браузера
        //-Dselenide.baseUrl=https://github.com
        open("https://google.com");  // браузер запускается без кукис и локалсторис
        // и остается пока не сделаешь команду закрытия браузера или не выполнить команду очистики кукис и локалсторадж
        open("/customers/orders");
        open("/", AuthenticationType.BASIC, "user", "password");

        Selenide.back();
        Selenide.refresh();

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

        Selenide.confirm(); // OK in alert dialogs (диалоговые окна, типа да- отмена) для стандартного браузерного алерта
        Selenide.dismiss(); // Cancel in alert dialogs

        Selenide.closeWindow(); // close active tab window
        Selenide.closeWebDriver(); // close browser completely

        Selenide.switchTo().frame("new");  // переход внутрь iframe, современный, внутри него полный html документ
        //чтобы искать внутри iframe нужно сначала перейтив  него, иначе искомый элемент просто будет не найден в дломе, так как он во фрейме
        Selenide.switchTo().defaultContent(); // выйти назад из фрейма

        Selenide.switchTo().window("The internet"); // можно переключатьсмя по окнам браузера, по тайтлу например


    } // тут список разворачивается и внутри команды браузера

    void selectors_examples() {
        $("div").click(); // по умолчанию ищут первый див
        element("div").click(); //это для языка котлин
        $("div",2).click(); // ищет второй див


        $x("//h1/div").click(); // одно и то же как внизу, так короче
        $(byXpath("//h1/div")).click();

        $(byText("full text")).click(); //поиск элемента по тексту
        $(withText("ull text")).click(); //по частичному совпадению

        $("").parent(); //навигация по дереву, родительский
        $("").sibling(1); //соседи дереву внутри дерева
        //$("").previous(); //соседи дереву внутри дерева вверх   // не работает
        $("").preceding(1);
        $("").closest("div"); //ищет вверх по дереве первый элемент типа див, можно все что угодно искат
        $("").lastChild();
        $("div").$("h1").find(byText("abd")).click(); // можно комбиницровать элементы


        //very optional
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click();

        $(byId("mytext")).click();
        $("#mytext").click();

        $(byClassName("red")).click();
        $(".red").click();

    }

    void actions_examples () {

        $("").click();
        $("").doubleClick();
        $("").contextClick();  // клик правой кнопкой мыши

        $("").hover();

        $("").setValue("text");
        $("").append("text");
        $("").clear();
        $("").setValue("");    //clear

        $("div").sendKeys("c");  // горячая клавиша на элементе, или посимвольный ввод текста в элемент
        actions().sendKeys("c").perform();            // горячая клавиша на всеё страничке
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f"));  // комбинация клавиш CTRL+f
        // или $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));


        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();

        // комплексные действия для мышки и клавиатуры
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300,200).release().perform();

        // комплексные действия для мышки и клавиатуры которыве не работают на современных фреймворках (старые реализации дропдауна)
        $("").selectOption("dropdown_text");
        $("").selectRadio("Radio_options");



    }

    void assertion_examples() {
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);

        // длинные таймауты
        $("").shouldBe(visible, Duration.ofSeconds(30));
        $("").waitUntil(visible, 30000); // старый синтаксис



    }

    void conditions_examples() {
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc")); // подстроку не чувствительно к регистру
        $("").shouldHave(exactText("abc")); // полную строку
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$")); // регулярные выражения

        $("").shouldHave(cssClass("red"));
        //если class="red-active",то это два класса, то нужно писать  $("").shouldHave(cssClass("red"),cssClass("active"));


        $("").shouldHave(cssValue("font-size", "12"));

        $("").shouldHave(value("25")); // если это поле input то у него нет текста, у него есть value
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);


        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name","example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked);  // для чекбоксов и радиобаттонов тоже

        $("").shouldBe(exist);  // проверка на то, что элемент вообще есть в Доме

        //проверка на disabled аттрибуты, может не работать в современных фреймворках, кнопок
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);


    }

    void collections_examples() {

     $$("div");    // does nothing

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1));  //находит все дивы где есть 123
        $$("div").excludeWith(text("123")).shouldHave(size(1));  //находит все дивы где нет 123

        $$("div").first().click();
        elements("div").first().click(); //для котлин
        //$("div").click();

        $$("div").last().click();
        $$("div").get(1).click();  //the second (statrs with 0)
        $("div",1).click();   //same as previous
        $$("div").findBy(text("123")).click();   //finds first

        //assertions for collections
        $$("div").shouldHave(size(0));
        $$("div").shouldBe(CollectionCondition.empty); //the same

        $$("div").shouldHave(texts("abc", "def", "ghi")); // если порядок важен чтоб шли по очереди
        $$("div").shouldHave(exactTexts("abc", "def", "ghi"));

        $$("div").shouldHave(textsInAnyOrder("abc", "def", "ghi")); // если порядок не важен
        $$("div").shouldHave(exactTextsCaseSensitiveInAnyOrder("abc", "def", "ghi"));

        $$("div").shouldHave(itemWithText("abc"));   //only one text

        $$("div").shouldHave(sizeGreaterThan(0));    //возвращает количество результатов поиска
        $$("div").shouldHave(sizeGreaterThanOrEqual(1));
        $$("div").shouldHave(sizeLessThan(3));
        $$("div").shouldHave(sizeLessThanOrEqual(2));





    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); //only for a href links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more commont but may have problems

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // dont forget to submit!!!
        $("uploadButton").click();






    }

    void javascript_examples () { // запуск javascript
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]+arguments[1])","abc",12);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1])",6,7);

    }

}
