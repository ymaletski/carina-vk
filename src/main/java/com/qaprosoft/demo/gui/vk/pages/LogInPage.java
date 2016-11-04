package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.demo.testdata.TestData;

public class LogInPage extends AbstractPage implements TestData {
	
	@FindBy(id = "index_email")
	private ExtendedWebElement emailTextField;
	
	@FindBy(id = "index_pass")
	private ExtendedWebElement passwordTextField;
	
	@FindBy(id = "index_login_button")
	private ExtendedWebElement loginButton;

	public LogInPage(WebDriver driver) {
		super(driver);
		//setPageURL(R.TESTDATA.get("url_logIn_page"));
		setPageURL(URL_LOGIN_PAGE);
	}
	
	public NewsPage logIn(){
		assertElementPresent(emailTextField);
		click(emailTextField);
		type(emailTextField, Configuration.getEnvArg("user_email"));
		assertElementPresent(passwordTextField);
		click(passwordTextField);
		type(passwordTextField, Configuration.getEnvArg("user_password"));
		assertElementPresent(loginButton);
		click(loginButton);
		return new NewsPage(driver);
	}

}
