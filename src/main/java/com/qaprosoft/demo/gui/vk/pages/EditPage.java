package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

public class EditPage extends BasePage {
	
	@FindBy(xpath = "//button[@class='flat_button button_big_width']")
	protected ExtendedWebElement saveButton;
	
	@FindBy(id = "ui_rmenu_contacts")
	private ExtendedWebElement editContactsLink;
	
	public EditPage(WebDriver driver) {
		super(driver);
		//setPageURL(R.TESTDATA.get("url_edit_page"));
		setPageURL(URL_EDIT_PAGE);
	}
	
	public EditContactsPage openEditContactsPage(){
		assertElementPresent(editContactsLink);
		click(editContactsLink);
		return new EditContactsPage(driver);
	}

}
