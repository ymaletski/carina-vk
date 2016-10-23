/*
 * Copyright 2013-2016 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qaprosoft.demo;

import com.qaprosoft.demo.gui.gsmarena.components.compare.ModelSpecs;
import com.qaprosoft.demo.gui.gsmarena.pages.BrandModelsPage;
import com.qaprosoft.demo.gui.gsmarena.pages.CompareModelsPage;
import com.qaprosoft.demo.gui.gsmarena.pages.HomePage;
import com.qaprosoft.demo.gui.gsmarena.pages.ModelInfoPage;
import com.jayway.restassured.internal.assertion.AssertParameter;
import com.qaprosoft.carina.core.foundation.UITest;
import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

/**
 * This sample shows how create web test.
 * 
 * @author Alex Khursevich
 */
public class WebSampleTest extends UITest
{
	@Test(dataProvider = "SingleDataProvider", description = "JIRA#AUTO-10001")
	@MethodOwner(owner = "brutskov")
	@XlsDataSourceParameters(path = "xls/samples.xlsx", sheet = "GSMArena", dsUid = "TUID", dsArgs = "brand, model, display, camera, ram, battery")
	public void testModelSpecs(String brand, String model, String display, String camera, String ram, String battery)
	{
		// Open GSM Arena home page and verify page is opened
		HomePage homePage = new HomePage(getDriver());
		homePage.open();
		Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
		// Select phone brand
		BrandModelsPage productsPage = homePage.selectBrand(brand);
		// Select phone model
		ModelInfoPage productInfoPage = productsPage.selectModel(model);
		// Verify phone specifications
		Assert.assertEquals(productInfoPage.readDisplay(), display, "Invalid display info!");
		Assert.assertEquals(productInfoPage.readCamera(), camera, "Invalid camera info!");
		Assert.assertEquals(productInfoPage.readRam(), ram, "Invalid ram info!");
		Assert.assertEquals(productInfoPage.readBattery(), battery, "Invalid battery info!");
	}

	@Test(description = "JIRA#AUTO-10002")
	@Parameters
	public void testCompareModels()
	{
		// Open GSM Arena home page and verify page is opened
		HomePage homePage = new HomePage(getDriver());
		homePage.open();
		Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
		// Open model compare page
		CompareModelsPage comparePage = homePage.getFooterMenu().openComparePage();
		// Compare 3 models
		List<ModelSpecs> specs = comparePage.compareModels("Samsung Galaxy J3", "Samsung Galaxy J5", "Samsung Galaxy J7");
		// Verify model announced dates
		Assert.assertEquals(specs.get(0).readSpec(ModelSpecs.SpecType.ANNOUNCED), "2015, November");
		Assert.assertEquals(specs.get(1).readSpec(ModelSpecs.SpecType.ANNOUNCED), "2016, March");
		Assert.assertEquals(specs.get(2).readSpec(ModelSpecs.SpecType.ANNOUNCED), "2016, March");
	}
}
