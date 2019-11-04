package cursoSelniumWebdriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesJanelas {
	private WebDriver driver;
	
	@Before
	public void inicializa( ) {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 665));
		driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveInteragirComFrame() {	
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	public void deveInteragirComJanela() {		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
	

	@Test
	public void deveInteragirComJanelaSemTitulo() {		
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		//Altera o foco do driver para a modal de acoro com o ID gerado no WindowHanlder
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		
		//Altera o foco do driver da modal para a janela principal
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);;
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	}
}
