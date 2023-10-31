package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListePage extends BasePage{

    @FindBy(xpath = "(//span[@class='a-size-medium-plus huc-atwl-header-main'])[1]")
    public WebElement urunEklendi;

    @FindBy(xpath = "//button[@data-action='a-popover-close']")
    public WebElement listeKapa;


    //@FindBy(xpath = "//span[@class='nav-text']")
    @FindBy(xpath = "(//span[.='Alışveriş Listesi'])[1]")
    public WebElement alisverisListesi;

    @FindBy(xpath = "//a[contains(.,'Sepete Ekle')]")
    public WebElement sepeteEkle;

    @FindBy(id = "nav-cart-count-container")
    public WebElement sepetim;

    @FindBy(xpath = "//input[@aria-labelledby='a-autoid-18-announce']")
    //@FindBy(css = "#a-autoid-18")
    public WebElement listeOlustur;
   // @FindBy(css = "#a-autoid-21")
    @FindBy(xpath = "//input[@aria-labelledby='a-autoid-21-announce']")
    public WebElement listeOlustur2;

    @FindBy(xpath = "//div[@id='wl-huc-post-create-msg']/span")
    public WebElement listeyeEklendi;


}
