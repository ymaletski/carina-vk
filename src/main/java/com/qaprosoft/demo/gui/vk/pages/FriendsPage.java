package com.qaprosoft.demo.gui.vk.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.demo.gui.vk.components.FriendsList;
import com.qaprosoft.demo.models.Person;

public class FriendsPage extends BasePage {
	
	@FindBy(id = "s_search")
	private ExtendedWebElement searchTextField;
	
	@FindBy(id = "friends_list")
	//@FindBy(xpath ="//div[@id='friends_list']")
	private FriendsList friendsList;
			
	public FriendsPage(WebDriver driver) {
		super(driver);
		//setPageURL(R.TESTDATA.get("url_friends_page"));
		setPageURL(URL_FRIENDS_PAGE);
	}
	
	public List<Person> searchFriendsByName(String text){
		assertElementPresent(searchTextField);
		click(searchTextField);
		type(searchTextField, text);
		List<Person> persons = new ArrayList<Person>();
		for (ExtendedWebElement nameLabel : friendsList.getNameLabels()){
			assertElementPresent(nameLabel);
			Person person = new Person();
			String[] sub = nameLabel.getText().split(" ");
			person.setName(sub[0]);
			person.setSurname(sub[1]);
			persons.add(person);
		}
		return persons;
	}
	
	public List<Person> searchAllFriends(){
		List<Person> persons = new ArrayList<Person>();
		for (ExtendedWebElement nameLabel : friendsList.getNameLabels()){
			assertElementPresent(nameLabel);
			Person person = new Person();
			String[] sub = nameLabel.getText().split(" ");
			person.setName(sub[0]);
			person.setSurname(sub[1]);
			persons.add(person);
		}
		return persons;
	}
	
	public PersonPage openPersonPage(){
		return new PersonPage(driver);
	}
	
}
