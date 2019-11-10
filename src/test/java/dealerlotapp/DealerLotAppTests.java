package dealerlotapp;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddCarPage;
import pages.EditCarPage;
import pages.HomePage;
import utils.DbUtil;

public class DealerLotAppTests {


    @Test
    public void verifyNumberOfCarsOnPageMatchNumberOfCarsInDatabase() {
        HomePage homePage = new HomePage();
        homePage.goToPage();
        int numberOfCarsOnPage = homePage.getNumberOfCarsOnPage();
        numberOfCarsOnPage = 20;
        System.out.println("There are " + numberOfCarsOnPage + " cars on HomePage");
        int numberOfCarsInDb = DbUtil.getNumberOfCarsFromDb();
        System.out.println("There are " + numberOfCarsInDb + " cars in database");
        Assert.assertEquals(numberOfCarsInDb, numberOfCarsOnPage);
    }

    @Test
    public void verifyAddingNewCarCreatesRowForNewCarInDatabase() {
        AddCarPage addCarPage = new AddCarPage();
        addCarPage.goToPage();
        addCarPage.enterYear(1976);
        addCarPage.selectMake("Audi");
        addCarPage.enterModel("Beetle");
        addCarPage.enterColor("blue");
        addCarPage.enterStock(787);
        addCarPage.enterImage("https://specials-images.forbesimg.com/imageserve/5d530ca995808800097cac0b/960x0.jpg?cropX1=498&cropX2=3378&cropY1=695&cropY2=2315");
        addCarPage.clickAddCarButton();
        boolean result = DbUtil.doesStockExistInDb(787);
        Assert.assertTrue(result);
    }

    @Test
    public void verifyDeletingACarRemovesCarFromDatabase() {
        HomePage homePage = new HomePage();
        homePage.goToPage();
        homePage.deleteCar(576);
        boolean result = DbUtil.doesStockExistInDb(576);
        Assert.assertFalse(result);
    }

    @Test
    public void verifyEditingCarModel() {
        EditCarPage editCarPage = new EditCarPage();
        editCarPage.goToPage(16);
        String newModelName = "Sportage";
        editCarPage.enterModel(newModelName);
        editCarPage.clickUpdateCarButton();
        String modelNameInDb = DbUtil.getModelNameForCar(16);
        Assert.assertEquals(modelNameInDb, newModelName);
    }


}
