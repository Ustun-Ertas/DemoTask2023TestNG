package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasketPage;
import pages.ListePage;
import pages.LoginPage;
import pages.SamsungPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.ExcelUtil;

import java.util.List;

public class AmazonTestWithExcel extends TestBase{
    @DataProvider
    public Object[][] userData(){
        ExcelUtil testData=new ExcelUtil("src/test/resources/demo.xlsx","Sheet1");
        return testData.getDataArrayWithoutFirstRow();
    }
    @Test(dataProvider = "userData")
    public void test1(String email,String passwordd,String name) throws InterruptedException {


        LoginPage loginPage=new LoginPage();
        SamsungPage samsungPage=new SamsungPage();
        ListePage listePage=new ListePage();
        BasketPage basketPage=new BasketPage();

        extentLogger=report.createTest("amazon login ol");

        extentLogger.info("https://www.amazon.com.tr/ sitesi açılır.");
        driver.get(ConfigurationReader.get("url"));

        extentLogger.info("Ana sayfanın açıldığı kontrol edilir.");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com.tr/");

        extentLogger.info("Çerez tercihlerinden Çerezleri kabul et seçilir.");
        try {
            loginPage.cerezleriKabulEt.click();
        } catch (Exception e) {}

        extentLogger.info("Siteye login olunur.");
        loginPage.loginOl(email,passwordd);

        Thread.sleep(2000);
        extentLogger.info("Login işlemi kontrol edilir.");
        Assert.assertEquals(loginPage.hesapVeListeler.getText(),name);

        extentLogger.info("Arama alanına 'Samsung' yazılır ve aratılır");
        loginPage.aramaCubugu.sendKeys("samsung");
        loginPage.searchButton.click();

        extentLogger.info("Cep telefonları sekmesi seçilir");
        samsungPage.cepTelefonu.click();

        extentLogger.info("Gelen sayfada samsung ürün bulunduğu doğrulanır");
        Assert.assertFalse(samsungPage.samsung.isEmpty());
        List<String> elementsText = BrowserUtils.getElementsText(samsungPage.samsung);
        Assert.assertTrue(elementsText.get(0).contains("Samsung"));

        extentLogger.info("Arama sonuçlarından 2. sayfa tıklanır");
        samsungPage.sonrakiSayfa(2);

        extentLogger.info("2. sayfanın gösterimde olduğu onaylanır.");
        Assert.assertTrue(samsungPage.gosterilenUrunSayisi.getText().contains("25-48"));
        //Assert.assertTrue(samsungPage.ikinciSayfa.isSelected());

        extentLogger.info("Üstten 6. ürün seçilir");
        samsungPage.samsung.get(5).click();

        extentLogger.info("Beğen butonuna tıklanılır");
        samsungPage.listeyeEkle.click();

        extentLogger.info("'Ürün listenize eklendi' popup kontrolü yapılır");
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


        extentLogger.info("Ekran üstündeki hesabım alanında beğenilen ürün kontrolü yapılır");
        actions.moveToElement(listePage.merhabaGirisYapin).perform();
        Thread.sleep(1000);
        listePage.alisverisListesi.click();


        extentLogger.info("Beğenilen ürün seçilir ve sepete eklenir");
        Thread.sleep(2000);
        listePage.sepeteEkle.click();

        extentLogger.info("Sepetim sayfasına gidilir");
        listePage.sepetim.click();

        extentLogger.info("'Ürün sepete eklendi' kontrolü yapılır");
        Assert.assertFalse(basketPage.sepetimdekiUrunler.isEmpty());

        extentLogger.info("Ürün kaldır butonuna basılıp sepetten çıkarılır");
        basketPage.sil.click();

        extentLogger.info("Ürünün sepette olmadığı kontrol edilir");
        Assert.assertTrue(basketPage.silindiMesaji.isDisplayed());


    }
}
