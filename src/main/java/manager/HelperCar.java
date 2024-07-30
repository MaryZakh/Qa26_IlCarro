package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"), car.getAbout());

    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);

        //Gas
//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.selectByVisibleText(" Gas ");
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHomePage() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));

        // "7/27/2024", "7/30/2024"  27  30
        String[] from = dateFrom.split("/"); //["7"]["27"]["2024"]


        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locatorFrom));


        String[] to = dateTo.split("/");

        click(By.xpath("//div[text()=' " + to[1] + " ']"));

    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        // "10/15/2024", "12/10/2024"

        typeCity(city);
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        System.out.println(now);

        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));//2024-07-30
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        System.out.println(from);
//        LocalDate from1 = LocalDate.parse("2013:23/05",DateTimeFormatter.ofPattern("yyyy:dd/MM"));
//        System.out.println(from1);


        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));


        diffMonth = to.getMonthValue()-from.getMonthValue();
        if (diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }
        ////div[text()=' " + to[1] + " ']"
        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));

    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }



    }

}
