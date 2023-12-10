package ofaqa.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import ofaqa.utils.AndroidActions;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayedAmount;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;

	public double getPricesSum() {

		int priceCount = productPrices.size();
		double totalSum = 0;
		for (int i = 0; i < priceCount; i++) {

			String amountString = productPrices.get(i).getText();
			double price = getFormattedAmount(amountString);
			totalSum = totalSum + price;

		}
		return totalSum;

	}

	public double getDisplayedAmount() {

		return getFormattedAmount(displayedAmount.getText());

	}

	public void acceptTerms() throws InterruptedException {

		longPressAction(terms);
		acceptButton.click();

	}

	public void submitOrder() {

		checkBox.click();
		proceed.click();

	}

}
