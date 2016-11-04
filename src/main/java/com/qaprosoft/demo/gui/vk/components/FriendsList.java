package com.qaprosoft.demo.gui.vk.components;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;

public class FriendsList extends AbstractUIObject {
	
	@FindBy(xpath = ".//div[@class='friends_field friends_field_title']/a")
	private List<ExtendedWebElement> nameLabels;

	public FriendsList(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}
	
	public List<ExtendedWebElement> getNameLabels(){
		return nameLabels;
	}

}
