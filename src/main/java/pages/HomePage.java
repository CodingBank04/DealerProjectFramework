package pages;

import common.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
public class HomePage {
    private By stockLocator = By.id("stockNumber");

    public int getNumberOfCarsOnPage() {
        List<WebElement> stockElementList = Driver.getDriver().findElements(stockLocator);
        int count = stockElementList.size();
        return count;
    }

    public void goToPage() {
        Driver.getDriver().get("http://dealerlot.tk/");
    }

    public void deleteCar(int stockNumber) {
        By deleteButton = By.xpath("//a[@href='/delete/" + stockNumber + "']");
        Driver.getDriver().findElement(deleteButton).click();
    }

}
