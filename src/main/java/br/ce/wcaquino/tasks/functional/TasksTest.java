package br.ce.wcaquino.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return  driver;
    }


    @Test
    public void deveSalvarTarefaComSucesso() {
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
            String mensagem  = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Success!", mensagem);

        } finally {
            //fechar browser
            driver.quit();
        }
    }

    @Test
    public void deveFalharSeDataPassada() {
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
            String mensagem  = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Due date must not be in past", mensagem);

        } finally {
            //fechar browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
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
            String mensagem  = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Fill the task description", mensagem);

        } finally {
            //fechar browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
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
            String mensagem  = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Fill the due date", mensagem);

        } finally {
            //fechar browser
            driver.quit();
        }
    }

}
