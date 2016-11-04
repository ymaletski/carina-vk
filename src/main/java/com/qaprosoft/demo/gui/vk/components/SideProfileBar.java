package com.qaprosoft.demo.gui.vk.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.demo.gui.vk.pages.FriendsPage;
import com.qaprosoft.demo.gui.vk.pages.MyProfilePage;
import com.qaprosoft.demo.gui.vk.pages.NewsPage;

public class SideProfileBar extends AbstractUIObject {
	
	@FindBy(id = "l_pr")
	private ExtendedWebElement myPageLink;
	
	@FindBy(id = "l_nwsf")
	private ExtendedWebElement newsLink;
	
	@FindBy(id = "l_fr")
	private ExtendedWebElement friendsLink;

	public SideProfileBar(WebDriver driver, SearchContext searchContext){
		super(driver, searchContext);
	}
	
	public MyProfilePage openMyProfilePage(){
		assertElementPresent(myPageLink);
		click(myPageLink);
		return new MyProfilePage(driver); 
	}
	
	public NewsPage openNewsPage(){
		assertElementPresent(newsLink);
		click(newsLink);
		return new NewsPage(driver); 
	}
	
	public FriendsPage openFriendsPage(){
		assertElementPresent(friendsLink);
		click(friendsLink);
		return new FriendsPage(driver); 
	}

}
