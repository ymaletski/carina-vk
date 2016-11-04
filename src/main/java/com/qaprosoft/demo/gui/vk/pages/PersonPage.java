package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

public class PersonPage extends BasePage {
	
	@FindBy(xpath = "//h2[@class='page_name']")
	private ExtendedWebElement nameAndSurname;

	public PersonPage(WebDriver driver) {
		super(driver);
	}
	
	public String getName(){
		String[] sub = nameAndSurname.getText().split(" ");
		return sub[0];
	}
	
	public String getSurname(){
		String[] sub = nameAndSurname.getText().split(" ");
		return sub[1];
	}

}
