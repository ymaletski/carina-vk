package com.qaprosoft.demo.gui.vk.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.demo.gui.vk.components.FriendsList;

public class FriendsPage extends BasePage {
	
	@FindBy(id = "s_search")
	private ExtendedWebElement searchTextField;
	
	//@FindBy(id = "friends_list")
	@FindBy(xpath ="//div[@id='friends_list']")
	private FriendsList friendsList;
			
	public FriendsPage(WebDriver driver) {
		super(driver);
		setPageURL(R.TESTDATA.get("url_friends_page"));
	}
	
	public List<String> searchFriendsByName(String text){
		searchTextField.click();
		type(searchTextField, text);
		List<String> names = friendsList.getListOfFriends();
		return names;
	}
	
}
