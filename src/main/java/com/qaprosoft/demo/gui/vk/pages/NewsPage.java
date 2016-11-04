package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;

public class NewsPage extends BasePage {

	public NewsPage(WebDriver driver) {
		super(driver);
		//setPageURL(R.TESTDATA.get("url_news_page"));
		setPageURL(URL_NEWS_PAGE);
	}

}
