package com.qaprosoft.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qaprosoft.carina.core.foundation.UITest;
import com.qaprosoft.carina.core.foundation.utils.R;
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
	
	@Test(description = "JIRA#AUTO-10001")
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
		editContactsPage.editMobileTel(R.TESTDATA.get("mobile_telephone"));
		editContactsPage.editHomeTel(R.TESTDATA.get("home_telephone"));
		editContactsPage.getProfileMenu().logOut();
	}
	
	@Test(description = "JIRA#AUTO-10002")
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
	
	@Test(description = "JIRA#AUTO-10003")
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
	
}
