package com.qaprosoft.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.UITest;
import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.demo.gui.gsmarena.pages.BrandModelsPage;
import com.qaprosoft.demo.gui.gsmarena.pages.HomePage;
import com.qaprosoft.demo.gui.gsmarena.pages.ModelInfoPage;
import com.qaprosoft.demo.gui.vk.pages.EditContactsPage;
import com.qaprosoft.demo.gui.vk.pages.EditPage;
import com.qaprosoft.demo.gui.vk.pages.FriendsPage;
import com.qaprosoft.demo.gui.vk.pages.LogInPage;
import com.qaprosoft.demo.gui.vk.pages.MyProfilePage;
import com.qaprosoft.demo.gui.vk.pages.NewsPage;

public class VkTest extends UITest {
	
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
		editContactsPage.editMobileTel(R.TESTDATA.get("user_mobile_tel"));
		editContactsPage.editHomeTel(R.TESTDATA.get("user_home_tel"));
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
		for (String name : friendsPage.searchFriendsByName(R.TESTDATA.get("friend_name"))){
			System.out.println(name);
		}
		friendsPage.getProfileMenu().logOut();
	}
}
