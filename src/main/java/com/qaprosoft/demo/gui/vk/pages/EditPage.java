package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

public class EditPage extends BasePage {
	
	@FindBy(xpath = "//button[@class='flat_button button_big_width']")
	protected ExtendedWebElement saveButton;
	
	@FindBy(id = "ui_rmenu_contacts")
	private ExtendedWebElement editContactsLink;
	
	public EditPage(WebDriver driver) {
		super(driver);
		setPageURL(R.TESTDATA.get("url_edit_page"));
	}
	
	public EditContactsPage openEditContactsPage(){
		editContactsLink.click();
		return new EditContactsPage(driver);
	}

}
