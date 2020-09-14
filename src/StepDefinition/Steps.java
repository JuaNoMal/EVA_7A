package StepDefinition;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {
	WebDriver driver;

	@Given("Abrir Chrome")
	public void AbrirChrome()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\descargas\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/ReservaHora/index.jsp");
	}

	@When("ingresa Username {string} y Password {string}")
	public void ingresa_Username_y_Password(String string, String string2)
	{
		System.out.println("Este paso ingresa el nombre de usuario y contraseña en la pagina de login");
		driver.findElement(By.id("inputUser")).sendKeys(string);
		driver.findElement(By.id("inputPassword")).sendKeys(string2);
	}

	@Then("inicia sesion")
	public void inicia_sesion()
	{
		driver.findElement(By.id("loginbtn")).click();
	}

}