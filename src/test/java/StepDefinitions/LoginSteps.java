package StepDefinitions;

import Base.BaseClass;
import Methods.BaseMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginSteps{

    BaseClass bc = new BaseClass();
    BaseMethods bm = new BaseMethods();

    @Given("VGP anasayfa acilir")
    public void vgp_anasayfa_acilir() {
        bm.goToPage(bc.baseUrl);
        /*bc.driver.manage().deleteAllCookies();
        Cookie cookie = new Cookie("jwtToken", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJEUEsxMTY2MyIsImV4cCI6MTYxMDcyNDk4Nn0.o3KldFgaHmhtgF5_58bV_JVFrZ90fAkCJiHSN4UA2D7Z5zLJydJeOaUQ2B26UxUoiPDdf0JZjreWbey-FrmIFQ%22%2C%22vgpUserResp%22%3A%7B%22status%22%3A%221%22%2C%22uuid%22%3A%22607ddf88-8644-4f82-8361-7ad193f12f47%22%2C%22request%22%3A%22821789%22%2C%22username%22%3A%22VGPENTEGRA%22%2C%22errorCode%22%3Anull%2C%22errorMessage%22%3Anull%2C%22validationCause%22%3Anull%2C%22hostName%22%3A%22qa-ekys-n131%22%2C%22body%22%3A%7B%22id%22%3A53498%2C%22username%22%3A%22DPK11663%22%2C%22tcID%22%3A%2229501203888%22%2C%22name%22%3A%22MUSTAFA%20BURAK%22%2C%22surname%22%3A%22AKTEL%22%2C%22gender%22%3A%22male%22%2C%22status%22%3A%7B%22id%22%3A2%2C%22name%22%3A%22APPROVED%22%2C%22descriptions%22%3A%5B%7B%22id%22%3A2%2C%22locale%22%3A%22TR%22%2C%22multiLanguageDescription%22%3A%22Onayl%C4%B1%22%7D%2C%7B%22id%22%3A2%2C%22locale%22%3A%22EN%22%2C%22multiLanguageDescription%22%3A%22Approved%22%7D%5D%7D%2C%22organization%22%3A%7B%22id%22%3A11663%2C%22shortName%22%3A%22AKFEL_G%22%2C%22name%22%3A%22Akfel%20Gaz%20Sanayi%20ve%20Ticaret%20A.%C5%9E.%22%2C%22etsoCode%22%3A%2240X0000000116637%22%2C%22organizationType%22%3A%7B%22id%22%3A15%2C%22name%22%3A%22DPK%22%2C%22description%22%3A%22DO%C4%9EALGAZ%22%7D%2C%22organizationStatus%22%3A%7B%22id%22%3A2%2C%22name%22%3A%22APPROVED%22%2C%22descriptions%22%3A%5B%7B%22id%22%3A6%2C%22locale%22%3A%22TR%22%2C%22multiLanguageDescription%22%3A%22Onayl%C4%B1%22%7D%2C%7B%22id%22%3A6%2C%22locale%22%3A%22EN%22%2C%22multiLanguageDescription%22%3A%22Approved%22%7D%5D%7D%7D%2C%22userType%22%3A%7B%22id%22%3A41%2C%22name%22%3A%22DPK_ADMIN%22%2C%22description%22%3A%22Do%C4%9Falgaz%20PK%20Y%C3%B6neticisi%22%2C%22descriptionEn%22%3A%22NaturalGas%20Admin%22%7D%2C%22phone%22%3A%22909999999999%22%2C%22email%22%3A%22deneme%40epias.com.tr%22%2C%22userInOrganizationRole%22%3A%22Admin%22%2C%22userMode%22%3A%22ADMIN%22%7D%7D%7D");
        bc.driver.manage().addCookie(cookie);
        bm.waitBySeconds(3);*/
        System.out.println("Thread basladi: _______ " + Thread.currentThread().getId());
        bm.waitBySeconds(7);
        //System.out.println("config parametresi:" + bm.getConfigValue("username"));
    }


    @When("bir kullanici secilir")
    public void bir_kullanici_secilir() {
        //bm.clickElement("kullanici_sec");
        //bm.clickElement("user_pkadmin");
        //bm.clickElement("login_button");
        System.out.println("When çalıştı.");
    }

    @Then("uygulama anasayfasina gittigi gorulur")
    public void uygulama_anasayfasina_gittigi_gorulur() {
        System.out.println("Then çalıştı");
    }
}
