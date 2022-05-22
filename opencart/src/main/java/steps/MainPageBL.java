package steps;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.MainPage;

public class MainPageBL {

    private MainPage mainPage;

    public MainPageBL() {
        mainPage = new MainPage();
    }

    public HeaderPageBL getHeaderPageBL() {
        return new HeaderPageBL();
    }

    public MainPageBL checkCurrencySymbolOnAllProducts(){
        String selectedCurrencyOnHomePage = new HeaderPageBL().getSelectedCurrencyLeftSymbol();
        for(WebElement price: mainPage.getPrice()){
            Assert.assertTrue(price.getText().contains(selectedCurrencyOnHomePage));
        }
        return this;
    }

    public MainPageBL addProductToCart(String productName) {
        ProductContainer product = mainPage.getProducts()
                .stream()
                .filter(e -> e.getProductTitle().getText().equals(productName))
                .findFirst()
                .orElseThrow(NullPointerException::new);
        product.getAddToCartButton().click();
        return this;
    }

    public MainPageBL addProductToComparePage(String productName) {
        ProductContainer product = mainPage.getProducts()
                .stream()
                .filter(e -> e.getProductTitle().getText().equals(productName))
                .findFirst()
                .orElseThrow(NullPointerException::new);
        product.getAddToCompareButton().click();
        return this;
    }

    public MainPageBL clickOnProductComparison() {
        mainPage.getProductComparison().click();
        return new MainPageBL();
    }

    public void verifyAddToCompare() {
        Assert.assertTrue(mainPage.getSuccessfulAddProductToCompareMessage().getText().contains("Success: "));
    }

}
