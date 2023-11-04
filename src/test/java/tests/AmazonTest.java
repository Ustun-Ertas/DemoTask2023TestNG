package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasketPage;
import pages.ListePage;
import pages.LoginPage;
import pages.SamsungPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Log;


import java.util.List;

public class AmazonTest extends TestBase{

    @Test
    public void testAmazon() throws InterruptedException {
        LoginPage loginPage=new LoginPage();
        SamsungPage samsungPage=new SamsungPage();
        ListePage listePage=new ListePage();
        BasketPage basketPage=new BasketPage();

        extentLogger=report.createTest("amazon login ol");

        Log.info("https://www.amazon.com.tr/ sitesi açılır.");
        driver.get(ConfigurationReader.get("url"));

        Log.info("Ana sayfanın açıldığı kontrol edilir.");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com.tr/");

        Log.info("Çerez tercihlerinden Çerezleri kabul et seçilir.");
        try {
            loginPage.cerezleriKabulEt.click();
        } catch (Exception e) {}

        Log.info("Siteye login olunur.");
        loginPage.loginOl(ConfigurationReader.get("email"),ConfigurationReader.get("password"));

        Thread.sleep(2000);
        Log.info("Login işlemi kontrol edilir.");
        Assert.assertEquals(loginPage.hesapVeListeler.getText(),ConfigurationReader.get("name"));

        //loging xpac
        Log.info("Arama alanına 'Samsung' yazılır ve aratılır");
        loginPage.aramaCubugu.sendKeys("samsung");
        loginPage.searchButton.click();

        Log.info("Cep telefonları sekmesi seçilir");
        samsungPage.cepTelefonu.click();

        Log.info("Gelen sayfada samsung ürün bulunduğu doğrulanır");
        Assert.assertFalse(samsungPage.samsung.isEmpty());
        List<String> elementsText = BrowserUtils.getElementsText(samsungPage.samsung);
        Assert.assertTrue(elementsText.get(0).contains("Samsung"));

        Log.info("Arama sonuçlarından 2. sayfa tıklanır");
        samsungPage.sonrakiSayfa(2);

        Log.info("2. sayfanın gösterimde olduğu onaylanır.");
        Assert.assertTrue(samsungPage.gosterilenUrunSayisi.getText().contains("25-48"));
        //Assert.assertTrue(samsungPage.ikinciSayfa.isSelected());

        Log.info("Üstten 6. ürün seçilir");
        samsungPage.samsung.get(5).click();

        Log.info("Beğen butonuna tıklanılır");
        samsungPage.listeyeEkle.click();

        Log.info("'Ürün listenize eklendi' popup kontrolü yapılır");
        Thread.sleep(3000);
        try {

            driver.findElement(By.xpath("(//span[@class='a-button a-button-primary'])[2]")).click();
        } catch (Exception e) {

            String text = listePage.urunEklendi.getText();

            if(text.equals("1 ürün şuraya eklendi:")){
                Assert.assertEquals(text,"1 ürün şuraya eklendi:");
            }
            else {
                Assert.assertEquals(text,"Bu ürün zaten şurada mevcut:");
            }
        }
        listePage.listeKapa.click();


        Log.info("Ekran üstündeki hesabım alanında beğenilen ürün kontrolü yapılır");
        actions.moveToElement(listePage.merhabaGirisYapin).perform();
        Thread.sleep(1000);
        listePage.alisverisListesi.click();


        Log.info("Beğenilen ürün seçilir ve sepete eklenir");
        Thread.sleep(2000);
        listePage.sepeteEkle.click();

        Log.info("Sepetim sayfasına gidilir");
        listePage.sepetim.click();

        Log.info("'Ürün sepete eklendi' kontrolü yapılır");
        Assert.assertFalse(basketPage.sepetimdekiUrunler.isEmpty());

        Log.info("Ürün kaldır butonuna basılıp sepetten çıkarılır");
        basketPage.sil.click();

        Log.info("Ürünün sepette olmadığı kontrol edilir");
        Assert.assertTrue(basketPage.silindiMesaji.isDisplayed());


    }
}
