package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class LogInPage extends AbstractPage {
	
	@FindBy(id = "index_email")
	private ExtendedWebElement emailTextField;
	
	@FindBy(id = "index_pass")
	private ExtendedWebElement passwordTextField;
	
	@FindBy(id = "index_login_button")
	private ExtendedWebElement loginButton;

	public LogInPage(WebDriver driver) {
		super(driver);
		setPageURL(R.TESTDATA.get("url_logIn_page"));
	}
	
	public NewsPage logIn(){
		click(emailTextField);
		type(emailTextField, R.TESTDATA.get("user_email"));
		click(passwordTextField);
		type(passwordTextField, R.TESTDATA.get("user_password"));
		click(loginButton);
		return new NewsPage(driver);
	}

}
