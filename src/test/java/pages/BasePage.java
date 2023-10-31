package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public abstract class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "myAccount")
    public WebElement girisYapVeyaUyeOl;

    @FindBy(css = "#login")
    public WebElement girisYap;

    @FindBy(xpath = "//button[.='Kabul et']")
    public WebElement cookieAccept;

    @FindBy(id = "sp-cc-accept")
    public WebElement cerezleriKabulEt;
    @FindBy(id = "sp-cc-rejectall-link")
    public WebElement cerezleriKabulEtmedenDevam;
    @FindBy(id = "nav-link-accountList-nav-line-1")
    public WebElement merhabaGirisYapin;
    @FindBy(xpath = "//a[@rel='nofollow']//span[@class=\"nav-action-inner\"]")
    public WebElement girisYapAmazon;

    @FindBy(id = "nav-link-accountList-nav-line-1")
    public WebElement hesapVeListeler;
    @FindBy(id = "twotabsearchtextbox")
    public WebElement aramaCubugu;

    @FindBy(id = "nav-search-submit-button")
    public WebElement searchButton;


}
