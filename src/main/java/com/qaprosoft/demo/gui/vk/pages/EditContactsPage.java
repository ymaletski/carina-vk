package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

public class EditContactsPage extends EditPage {

	@FindBy(id = "pedit_mobile")
	private ExtendedWebElement mobileTelTextField;

	@FindBy(id = "pedit_home")
	private ExtendedWebElement homeTelTextField;
	
	public EditContactsPage(WebDriver driver) {
		super(driver);
		setPageURL(R.TESTDATA.get("url_edit_contacts_page"));
	}
	
	public void editMobileTel(String text){
		click(mobileTelTextField);
		type(mobileTelTextField, text);
		saveButton.click();
	}
	
	public void editHomeTel(String text){
		click(homeTelTextField);
		type(homeTelTextField, text);
		saveButton.click();
	}

}
