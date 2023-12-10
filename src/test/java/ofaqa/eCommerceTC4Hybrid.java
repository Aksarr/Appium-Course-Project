package ofaqa;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ofaqa.pageObjects.android.CartPage;
import ofaqa.pageObjects.android.ProductCatalogue;
import ofaqa.testUtils.BaseTest;

public class eCommerceTC4Hybrid extends BaseTest {

	@BeforeMethod
	public void preSetup() {

		formPage.setActivity();

	}

	@Test(dataProvider = "getData")
	public void FillForm(HashMap<String, String> input) throws InterruptedException {

		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountry(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();

		double totalSum = cartPage.getPricesSum();
		double formattedSum = cartPage.getDisplayedAmount();
		Assert.assertEquals(totalSum, formattedSum);
		cartPage.acceptTerms();
		cartPage.submitOrder();

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\ofaqa\\testData\\eCommerce.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
