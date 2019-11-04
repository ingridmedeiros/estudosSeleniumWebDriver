package cursoSelniumWebdriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	private WebDriver driver;
	
	@Before
	public void inicializa( ) {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 665));
		driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		DSL dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveInteragirComTextField() {		
		//Escrevo no form
		DSL.escreve("elementosForm:nome", "Teste de escrito");
		//Verifico se o que foi escrito é o resultado esperado
		Assert.assertEquals("Teste de escrito", DSL.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void deveInteragirComTextArea() {	
		DSL.escreve("elementosForm:sugestoes", "Este é um teste de textArea");
		Assert.assertEquals("Este é um teste de textArea", DSL.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {		
		DSL.clicarElemento("elementosForm:sexo:1");
		Assert.assertTrue(DSL.selecionarRadioButton("elementosForm:sexo:1"));
	}
	
	@Test
	public void deveInteragirComCheckBox() {		
		DSL.clicarElemento("elementosForm:comidaFavorita:3");
		Assert.assertTrue(DSL.selecionarRadioButton("elementosForm:comidaFavorita:3"));
	}
	
	@Test
	public void deveInteragirComComboBox() {		
		DSL.selecionarCombo("elementosForm:escolaridade", "2o grau completo");	
		Assert.assertEquals("2o grau completo", DSL.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresComboBox() {		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getOptions();
		//Verifica a quantidade de itens do combo
		Assert.assertEquals(8, options.size());
		
		//Verifica se determinado item está dentro das opções
		boolean encontrou = false;
		for (WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void deveInteragirComComboMultiplos() {		
		DSL.selecionarCombo("elementosForm:esportes", "Natacao");
		DSL.selecionarCombo("elementosForm:esportes", "Corrida");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		List<WebElement> opcoesSelecionadas = combo.getAllSelectedOptions();
		Assert.assertEquals(2, opcoesSelecionadas.size());
		
		combo.deselectByVisibleText("Corrida");
		opcoesSelecionadas = combo.getAllSelectedOptions();
		Assert.assertEquals(1, opcoesSelecionadas.size());	
	}
	
	@Test
	public void deveInteragirComBotoes() {
		DSL.clicarElemento("buttonSimple");
		Assert.assertEquals("Obrigado!", DSL.obterValorCampo("buttonSimple"));
	}
	
	@Test
	public void deveInteragirComLinks() {	
		DSL.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", DSL.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextoNaPagina() {	
//		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", DSL.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", DSL.obterTexto(By.className("facilAchar")));
	}
	
	
}
	