package cursoSelniumWebdriver;

import java.util.List;

import javax.servlet.annotation.WebListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class TesteAlert {
	private WebDriver driver;
	
	@Before
	public void inicializa( ) {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 665));
		driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		DSL dls = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveInteragirComAlertSimples() {		
		driver.findElement(By.id("alert")).click();
		
		//Mudando o foco do Selnium da página PARA o alert
		Alert alert = driver.switchTo().alert();
		String texto =  alert.getText();
		
		Assert.assertEquals("Alert Simples", texto);
		alert.accept(); //Clicando naquele botão de ok - voltando o foco para a página
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	public void deveInteragirComAlertConfirmOk() {		
		driver.findElement(By.id("confirm")).click();
		
		Alert alertOk = driver.switchTo().alert();
		alertOk.accept();
		
		String textoOk = alertOk.getText();
		Assert.assertEquals("Confirmado", textoOk);
		alertOk.accept();
	}
	
	@Test
	public void deveInteragirComAlertConfirmCancelado() {		
		driver.findElement(By.id("confirm")).click();
		
		Alert alertCancelar = driver.switchTo().alert();
		alertCancelar.dismiss();
		
		String textoNegado = alertCancelar.getText();
		Assert.assertEquals("Negado", textoNegado);
		alertCancelar.accept();
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {		
		driver.findElement(By.id("prompt")).click();

		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
	}
}
