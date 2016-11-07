package com.qaprosoft.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.UITest;
import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
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

public class DataprovidesVKTest extends UITest implements TestData {
	
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
