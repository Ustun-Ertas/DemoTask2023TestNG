package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasketPage extends BasePage{
    @FindBy(xpath = "//span[@class='a-truncate-cut']")
    public List<WebElement> sepetimdekiUrunler;

    @FindBy(xpath = "//input[@value='Sil']")
    public WebElement sil;

    @FindBy(xpath = "//span[contains(.,' Alışveriş Sepetiniz konumundan kaldırıldı. ')]")
    public WebElement silindiMesaji;
}
