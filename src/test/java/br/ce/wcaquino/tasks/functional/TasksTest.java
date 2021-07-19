package br.ce.wcaquino.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {
        //WebDriver driver = new ChromeDriver();
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL(" http://192.168.100.6:4444/wd/hub"), cap);

        driver.navigate().to("http://192.168.100.6:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }


    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");

            //escrever data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");

            //clicar salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Success!", mensagem);

        } finally {
            //fechar browser
            driver.quit();
        }
    }

    @Test
    public void deveFalharSeDataPassada() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try {
            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");

            //escrever data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

            //clicar salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Due date must not be in past", mensagem);

        } finally {
            //fechar browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try {
            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição

            //escrever data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

            //clicar salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Fill the task description", mensagem);

        } finally {
            //fechar browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try {
            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via selenium");

            //escrever data


            //clicar salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Fill the due date", mensagem);

        } finally {
            //fechar browser
            driver.quit();
        }
    }

}
