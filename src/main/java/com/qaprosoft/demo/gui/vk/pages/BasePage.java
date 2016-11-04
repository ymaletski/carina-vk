package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.demo.gui.vk.components.ProfileMenu;
import com.qaprosoft.demo.gui.vk.components.SideProfileBar;
import com.qaprosoft.demo.testdata.TestData;

public class BasePage extends AbstractPage implements TestData {
	
	@FindBy(id = "top_profile_link")
	private ExtendedWebElement profileLink;
	
	@FindBy(id = "top_profile_menu")
	private ProfileMenu profileMenu;
	
	@FindBy(id = "side_bar_inner")
	private SideProfileBar sideProfileBar;

	public BasePage(WebDriver driver) {
		super(driver);
	}
	
	public ProfileMenu getProfileMenu() {
		assertElementPresent(profileLink);
		click(profileLink);
		return profileMenu;
	}
	
	public SideProfileBar getSideProfileBar() {
		return sideProfileBar;
	}

}
