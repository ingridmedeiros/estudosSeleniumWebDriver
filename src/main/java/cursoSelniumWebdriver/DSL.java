package cursoSelniumWebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private static WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public static void escreve(String nm_campo, String texto) {
		driver.findElement(By.id(nm_campo)).sendKeys(texto);
	}
	
	public static String obterValorCampo(String nm_campo) {
		return driver.findElement(By.id(nm_campo)).getAttribute("value");
	}
	
	public static void clicarElemento(String nm_campo) {
		driver.findElement(By.id(nm_campo)).click();
	}
	
	public static boolean selecionarRadioButton(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public static void selecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		//combo.selectByIndex(2);
		//combo.selectByValue("superior");
		combo.selectByVisibleText(valor);
	}
	
	public static String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public static void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	public static String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public static String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	public static void alterarFocoDriver() {
		
	}
}
