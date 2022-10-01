package Numadic.NUProducts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testcase {

	public static void main(String[] args) {
		
		
		String CurrentWorkDir = System.getProperty("user.dir");
		String ChromePath = "J:\\NewWorkspace\\NUProducts\\src\\main\\java\\Executable2\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver", ChromePath);
		WebDriver driver = new ChromeDriver();
		driver.get("https://numadic.com/careers/qa-engineer.php");
	}

}
