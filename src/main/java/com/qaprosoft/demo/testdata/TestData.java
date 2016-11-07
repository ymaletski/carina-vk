package com.qaprosoft.demo.testdata;

import com.qaprosoft.carina.core.foundation.utils.Configuration;

public interface TestData {
	
	static final String URL_LOGIN_PAGE = Configuration.getEnvArg("url_login_page");
	static final String URL_NEWS_PAGE = Configuration.getEnvArg("url_news_page");
	static final String URL_EDIT_PAGE = Configuration.getEnvArg("url_edit_page");
	static final String URL_FRIENDS_PAGE = Configuration.getEnvArg("url_friends_page");
	static final String URL_EDIT_CONTACTS_PAGE = Configuration.getEnvArg("url_edit_contacts_page");

}
