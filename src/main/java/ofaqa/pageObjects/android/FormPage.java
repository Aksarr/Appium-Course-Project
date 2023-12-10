package ofaqa.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import ofaqa.utils.AndroidActions;

public class FormPage extends AndroidActions {

	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Alex");

//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

//	driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;

//	driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement maleOption;

//	driver.findElement(By.id("android:id/text1")).click();
	@AndroidFindBy(id = "android:id/text1")
	private WebElement countryPicklist;

//	driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;

	public void setActivity() {

		// set the screen to Home page
		Activity activity = new Activity("com.androidsample.generalstore",
				"com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);

	}

	public void setNameField(String name) {

		nameField.sendKeys(name);
		driver.hideKeyboard();

	}

	public void setGender(String gender) {

		if (gender.contains("female")) {

			femaleOption.click();

		} else {

			maleOption.click();

		}

	}

	public void setCountry(String countryName) {

		countryPicklist.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();

	}

	public ProductCatalogue submitForm() {

		shopButton.click();
		return new ProductCatalogue(driver);

	}

}
