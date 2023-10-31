package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.Driver;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//div[.='Giriş yap']")
    public WebElement giris_uyeOl;

    @FindBy(id = "txtUserName")
    public WebElement emailBox;

    @FindBy(id = "btnLogin")
    public WebElement enterEmail;

    @FindBy(xpath = "//input[@placeholder='Şifre']")
    public WebElement passwordBox;

    @FindBy(id = "btnEmailSelect")
    public WebElement enterPassword;

    Actions actions=new Actions(Driver.get());
    @FindBy(xpath = "//span[.='Liste Oluşturun']")
    public WebElement listeOlustur;
    @FindBy(xpath = "//input[@class=\"a-button-input\"]")
    public WebElement birListeOlusturun;
    @FindBy(id = "ap_email")
    public WebElement telefonNoYaz;
    @FindBy(id = "continue")
    public WebElement devamEt;
    @FindBy(id = "ap_password")
    public WebElement sifre;
    @FindBy(id = "signInSubmit")
    public WebElement sifreGirisYap;
    public void loginOl(String telefon,String sifree){
        actions.moveToElement(merhabaGirisYapin).perform();
        girisYapAmazon.click();
        telefonNoYaz.sendKeys(telefon);
        devamEt.click();
        sifre.sendKeys(sifree);
        sifreGirisYap.click();
    }

}
