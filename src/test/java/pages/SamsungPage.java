package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SamsungPage extends BasePage{

    @FindBy(xpath = "//span[.='Cep Telefonları']")
    public WebElement cepTelefonu;

    @FindBy(xpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']")
    public List<WebElement> samsung;

    @FindBy(xpath = "//a[.='Sonraki']")
    public WebElement sonraki;

    @FindBy(xpath = "//div[@class='a-section a-spacing-small a-spacing-top-small']")
    public WebElement gosterilenUrunSayisi;

    @FindBy(xpath = "//span[@aria-label='Geçerli sayfa, sayfa 2']")
    public WebElement ikinciSayfa;

    @FindBy(css = "#add-to-wishlist-button-submit")
    public WebElement listeyeEkle;

    public void sonrakiSayfa(int page){
        for (int i = 1; i <page ; i++) {
            sonraki.click();
        }
    }
}
