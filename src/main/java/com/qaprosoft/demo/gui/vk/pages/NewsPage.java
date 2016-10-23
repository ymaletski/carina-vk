package com.qaprosoft.demo.gui.vk.pages;

import org.openqa.selenium.WebDriver;

import com.qaprosoft.carina.core.foundation.utils.R;

public class NewsPage extends BasePage {

	public NewsPage(WebDriver driver) {
		super(driver);
		setPageURL(R.TESTDATA.get("url_news_page"));
	}

}
