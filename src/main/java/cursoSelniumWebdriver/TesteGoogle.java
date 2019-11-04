package cursoSelniumWebdriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	private WebDriver driver;
	
	@Before
	public void inicializa( ) {
		//Caso eu não configure o driver global
		//System.setProperty("webdriver.chrome.driver", "D:/Ingrid/Estudos/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 665));
		driver.get("https://google.com.br");
	}
	
	@After
	public void finaliza() {
		//Quit fecha todas as abas e mata todas as instâncias do browser
		driver.quit();
	}
	
	@Test
	public void teste() {		
		//Compara o valor esperado com o valor atual
		Assert.assertEquals("Google", driver.getTitle());
	}
	
}
