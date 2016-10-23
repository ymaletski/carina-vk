package com.qaprosoft.demo.gui.vk.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.demo.gui.vk.pages.EditPage;
import com.qaprosoft.demo.gui.vk.pages.MyProfilePage;

public class ProfileMenu extends AbstractUIObject {
	
	@FindBy(id = "top_myprofile_link")
	private ExtendedWebElement myProfileLink;

	@FindBy(id = "top_edit_link")
	private ExtendedWebElement editLink;
	
	@FindBy(id = "top_logout_link")
	private ExtendedWebElement logOutLink;

	public ProfileMenu(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}
	
	public MyProfilePage openMyProfilePage()
	{
		myProfileLink.click();
		return new MyProfilePage(driver);
	}
	
	public EditPage openEditPage()
	{
		editLink.click();
		return new EditPage(driver);
	}
	
	public void logOut(){
		logOutLink.click();
	}

}
