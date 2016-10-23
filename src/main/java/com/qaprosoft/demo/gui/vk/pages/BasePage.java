package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.demo.gui.vk.components.ProfileMenu;
import com.qaprosoft.demo.gui.vk.components.SideProfileBar;

public class BasePage extends AbstractPage {
	
	@FindBy(id = "top_profile_link")
	private ExtendedWebElement profileLink;
	
	@FindBy(id = "top_profile_menu")
	private ProfileMenu profileMenu;
	
	@FindBy(id = "side_bar_inner")
	private SideProfileBar sideProfileBar;

	public BasePage(WebDriver driver) {
		super(driver);
	}
	
	public ProfileMenu getProfileMenu(){
		click(profileLink);
		return profileMenu;
	}
	
	public SideProfileBar getSideProfileBar(){
		return sideProfileBar;
	}

}
