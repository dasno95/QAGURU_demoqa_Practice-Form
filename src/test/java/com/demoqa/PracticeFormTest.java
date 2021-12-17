package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successTest() {
        // open page with form
        open("https://demoqa.com/automation-practice-form");
        // fill form
        $("#firstName").setValue("Daria");
        $("#lastName").setValue("Koroleva");
        $("#userEmail").setValue("daria@k.com");
        // select gender
        $("[for='gender-radio-2']").click();
        $("#userNumber").setValue("1234567890");
        // fill date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("10");
        $(".react-datepicker__year-select").selectOptionByValue("1995");
        $(".react-datepicker__day--014:not(.react-datepicker__day--outside-month)").click();
        // select subjects
        $("#subjectsInput").setValue("Computer Science");
        $(".subjects-auto-complete__menu").click();
        // or
        $("#subjectsInput").setValue("Maths").pressEnter();
        // select hobbies
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();
        // upload picture
        File picture = new File("src/test/resources/image/DSCF0008.JPG");
        $("#uploadPicture").uploadFile(picture);
        $("#currentAddress").setValue("Street, 1");
        // select state and city
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Noida").pressEnter();
        // submit
        $("#submit").click();

        // check result form
        $(".modal-content").shouldHave(
                text("Daria Koroleva"),
                text("daria@k.com"),
                text("Female"),
                text("1234567890"),
                text("14 November,1995"),
                text("Computer Science, Maths"),
                text("Reading, Music"),
                text("DSCF0008.JPG"),
                text("Street, 1"),
                text("NCR Noida"));
    }
}
