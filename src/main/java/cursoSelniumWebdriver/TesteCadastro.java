package cursoSelniumWebdriver;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

import cursoSelniumWebdriver.TesteRegras;

public class TesteCadastro {
	
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
	public void deveCadastrarComSucesso() {		
		DSL.escreve("elementosForm:nome", "Ingrid");	
		DSL.escreve("elementosForm:sobrenome", "Santos Medeiros");	
		DSL.clicarElemento("elementosForm:sexo:1");
		DSL.clicarElemento("elementosForm:comidaFavorita:3");
		DSL.selecionarCombo("elementosForm:escolaridade", "Superior");
		DSL.selecionarCombo("elementosForm:esportes", "Natacao");
		DSL.selecionarCombo("elementosForm:esportes", "Corrida");
		
		DSL.clicarElemento("elementosForm:cadastrar");

		Assert.assertEquals("Ingrid", DSL.obterTexto(By.cssSelector("#descNome > span")));
		Assert.assertEquals("Santos Medeiros", DSL.obterTexto(By.cssSelector("#descSobrenome > span")));
		Assert.assertEquals("Feminino", DSL.obterTexto(By.cssSelector("#descSexo > span")));
		Assert.assertEquals("Vegetariano", DSL.obterTexto(By.cssSelector("#descComida > span")));
		Assert.assertEquals("superior", DSL.obterTexto(By.cssSelector("#descEscolaridade > span")));
		Assert.assertEquals("Natacao Corrida", DSL.obterTexto(By.cssSelector("#descEsportes > span")));			
	}
	
	@Test
	public void deveValidarNomeObrigatorio( ) {
		DSL.clicarElemento("elementosForm:cadastrar");
				
		Alert alert = driver.switchTo().alert();
		String validaNome = alert.getText();
		Assert.assertEquals("Nome eh obrigatorio", validaNome);
		alert.accept();
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio( ) {		
		DSL.escreve("elementosForm:nome", "Ingrid");	
		DSL.clicarElemento("elementosForm:cadastrar");
		
		Alert alert = driver.switchTo().alert();
		String validaSobreNome = alert.getText();
		Assert.assertEquals("Sobrenome eh obrigatorio", validaSobreNome);
		alert.accept();		
	}
	
	@Test
	public void deveValidarSexoObrigatorio( ) {		
		DSL.escreve("elementosForm:nome", "Ingrid");	
		DSL.escreve("elementosForm:sobrenome", "Medeiros");	
		
		DSL.clicarElemento("elementosForm:cadastrar");
		
		Alert alert = driver.switchTo().alert();
		String validaSexo = alert.getText();
		Assert.assertEquals("Sexo eh obrigatorio", validaSexo);
		alert.accept();
	}
	
	@Test
	public void deveValidarComidaVegetariana( ) {		
		DSL.escreve("elementosForm:nome", "Ingrid");	
		DSL.escreve("elementosForm:sobrenome", "Medeiros");	
		DSL.clicarElemento("elementosForm:sexo:1");
		DSL.clicarElemento("elementosForm:comidaFavorita:3");
		DSL.clicarElemento("elementosForm:comidaFavorita:0");
		
		DSL.clicarElemento("elementosForm:cadastrar");
		
		Alert alert = driver.switchTo().alert();
		String validaComidaVegetariana = alert.getText();
		
		assertEquals("Tem certeza que voce eh vegetariano?", validaComidaVegetariana);
		alert.accept();
	}
	
	@Test
	public void deveValidarPraticaDeEsportes( ) {			
		DSL.escreve("elementosForm:nome", "Ingrid");	
		DSL.escreve("elementosForm:sobrenome", "Medeiros");	
		DSL.clicarElemento("elementosForm:sexo:1");
		DSL.clicarElemento("elementosForm:comidaFavorita:3");
		DSL.selecionarCombo("elementosForm:esportes", "Natacao");
		DSL.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		DSL.clicarElemento("elementosForm:cadastrar");
		
		Alert alert = driver.switchTo().alert();
		String validaPraticaEsportes = alert.getText();
		
		Assert.assertEquals("Voce faz esporte ou nao?", validaPraticaEsportes);
		alert.accept();
	}		
}
