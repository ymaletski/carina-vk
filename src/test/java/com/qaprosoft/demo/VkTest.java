package com.qaprosoft.demo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qaprosoft.carina.core.foundation.UITest;
import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.demo.dao.mybatis.PersonDaoImpl;
import com.qaprosoft.demo.gui.vk.pages.EditContactsPage;
import com.qaprosoft.demo.gui.vk.pages.EditPage;
import com.qaprosoft.demo.gui.vk.pages.FriendsPage;
import com.qaprosoft.demo.gui.vk.pages.LogInPage;
import com.qaprosoft.demo.gui.vk.pages.MyProfilePage;
import com.qaprosoft.demo.gui.vk.pages.NewsPage;
import com.qaprosoft.demo.models.Person;
import com.qaprosoft.demo.testdata.TestData;

public class VkTest extends UITest implements TestData {
	
	private static final Logger TESTS_LOGGER = LogManager.getRootLogger(); 
	
	@Test(dataProvider = "SingleDataProvider", description = "JIRA#AUTO-10001")
	@XlsDataSourceParameters(path = "xls/testdata.xlsx", sheet = "numbers", dsUid = "TUID", dsArgs = "mobile, home")
	public void editContactsManyTimes(String mobile, String home) {
		LogInPage logInPage = new LogInPage(getDriver());
		logInPage.open();
		Assert.assertTrue(logInPage.isPageOpened(), "LogIn page is not opened.");
		NewsPage newsPage = logInPage.logIn();
		Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened.");
		EditPage editPage = newsPage.getProfileMenu().openEditPage();
		Assert.assertTrue(editPage.isPageOpened(), "Edit page is not opened.");
		EditContactsPage editContactsPage = editPage.openEditContactsPage();
		Assert.assertTrue(editContactsPage.isPageOpened(), 
				"Edit contacts page is not opened.");
		editContactsPage.editMobileTel(mobile);
		editContactsPage.editHomeTel(home);
		editContactsPage.getProfileMenu().logOut();
	}
	
	@Test(description = "JIRA#AUTO-10002")
	public void editContacts() {
		LogInPage logInPage = new LogInPage(getDriver());
		logInPage.open();
		Assert.assertTrue(logInPage.isPageOpened(), "LogIn page is not opened.");
		NewsPage newsPage = logInPage.logIn();
		Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened.");
		EditPage editPage = newsPage.getProfileMenu().openEditPage();
		Assert.assertTrue(editPage.isPageOpened(), "Edit page is not opened.");
		EditContactsPage editContactsPage = editPage.openEditContactsPage();
		Assert.assertTrue(editContactsPage.isPageOpened(), 
				"Edit contacts page is not opened.");
		editContactsPage.editMobileTel(MOBILE_TEL);
		editContactsPage.editHomeTel(HOME_TEL);
		editContactsPage.getProfileMenu().logOut();
	}
	
	@Test(description = "JIRA#AUTO-10003")
	public void findFriendsByName() {
		LogInPage logInPage = new LogInPage(getDriver());
		logInPage.open();
		Assert.assertTrue(logInPage.isPageOpened(), "LogIn page is not opened.");
		NewsPage newsPage = logInPage.logIn();
		Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened.");
		MyProfilePage myProfilePage = newsPage.getProfileMenu().openMyProfilePage();
		FriendsPage friendsPage = myProfilePage.getSideProfileBar().openFriendsPage();
		Assert.assertTrue(friendsPage.isPageOpened(), "Friends page is not opened.");
		for (Person person : friendsPage.searchFriendsByName(R.TESTDATA.get("friend_name"))){
			TESTS_LOGGER.info(person.getName()+" "+person.getSurname()+" "+person.getCity());
		}
		friendsPage.getProfileMenu().logOut();
	}
	
	@Test(description = "JIRA#AUTO-10004")
	public void findAllFriends() {
		LogInPage logInPage = new LogInPage(getDriver());
		logInPage.open();
		Assert.assertTrue(logInPage.isPageOpened(), "LogIn page is not opened.");
		NewsPage newsPage = logInPage.logIn();
		Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened.");
		MyProfilePage myProfilePage = newsPage.getProfileMenu().openMyProfilePage();
		FriendsPage friendsPage = myProfilePage.getSideProfileBar().openFriendsPage();
		Assert.assertTrue(friendsPage.isPageOpened(), "Friends page is not opened.");
		PersonDaoImpl personDao = new PersonDaoImpl();
		for (Person person : friendsPage.searchAllFriends()){
			personDao.insertPerson(person);
			TESTS_LOGGER.info(person.getName()+" "+person.getSurname() +" is added to db.");
		}
		for (Person person : personDao.getAllPersons())
			TESTS_LOGGER.info(person.getName()+" "+person.getSurname()+" is received from db.");
		friendsPage.getProfileMenu().logOut();
	}
	
	@Test(dataProvider = "createValidTestData")
	@MethodOwner(owner = "ymaletski")
	public void testValidPersons(String TUID, Person expectedPerson) {
		LogInPage logInPage = new LogInPage(getDriver());
		logInPage.open();
		Assert.assertTrue(logInPage.isPageOpened(), "LogIn page is not opened.");
		NewsPage newsPage = logInPage.logIn();
		Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened.");
		MyProfilePage myProfilePage = newsPage.getProfileMenu().openMyProfilePage();
		FriendsPage friendsPage = myProfilePage.getSideProfileBar().openFriendsPage();
		Assert.assertTrue(friendsPage.isPageOpened(), "Friends page is not opened.");
		TESTS_LOGGER.info(TUID+". Searching for person: "+expectedPerson.getName()+" "+expectedPerson.getSurname()+"...");
		for (Person actualPerson : friendsPage.searchAllFriends()){
			if ((actualPerson.getName().equalsIgnoreCase(expectedPerson.getName()))&
					(actualPerson.getSurname().equalsIgnoreCase(expectedPerson.getSurname()))){
				TESTS_LOGGER.info("Person "+actualPerson.getName()+" "+actualPerson.getSurname()+" is found!!!");
			}
		}		
	}
	
	@DataProvider(parallel = true, name = "createValidTestData")
	public Object[][] createValidTestData() {
		List<Person> persons = new ArrayList<Person>();
		PersonDaoImpl personDao = new PersonDaoImpl();
		persons.addAll(personDao.getAllPersons());
		int size = persons.size();
		Object[][] result = new Object[size][2];
		for (int i = 0; i < size; i++) {
			result[i] = new Object[] { "TUID: " + String.format("%05d", i + 1), persons.get(i) };
		}
		return result;
	}
}
