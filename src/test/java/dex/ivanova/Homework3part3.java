package dex.ivanova;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Condition.text;

public class Homework3part3 {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void relocationRectangles() {
        //- Откройте https://the-internet.herokuapp.com/drag_and_drop
        open("https://the-internet.herokuapp.com/drag_and_drop");
        //- Перенесите прямоугольник А на место В
        // почему не работает????    actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
        $("#column-a").dragAndDropTo($("#column-b"));
        // - Проверьте, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("b"));
        $("#column-b").shouldHave(text("a"));


    }


}




