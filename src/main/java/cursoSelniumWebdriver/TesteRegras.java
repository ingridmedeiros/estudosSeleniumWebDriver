package cursoSelniumWebdriver;

import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteRegras {
	public WebDriver driver;
	
	@Before
	public void inicializa( ) {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 665));
		driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
}
