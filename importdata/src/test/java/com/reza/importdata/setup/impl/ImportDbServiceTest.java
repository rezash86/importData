package com.reza.importdata.setup.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.reza.importdata.model.MarketPrice;


@RunWith(MockitoJUnitRunner.class)
public class ImportDbServiceTest {

	private ImportDbService serviceToTest;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		serviceToTest = spy(new ImportDbService() {
		});
	}
	
	@Test
	public void test_saveData() {
		List<MarketPrice> fetchData = serviceToTest.fetchData();
		if(fetchData != null && !fetchData.isEmpty()){
			boolean importData = serviceToTest.importData(fetchData);
			if(importData){
				List<MarketPrice> data = serviceToTest.getData(fetchData.get(0).getOriginalDateTime());
				assertNotNull(data);
				assertEquals(data.size(), fetchData.size());
				assertEquals(data.get(0).getHubname(), fetchData.get(0).getHubname());
			}
			
			serviceToTest.deleteData(fetchData.get(0).getOriginalDateTime());
		}
		
	}
	
	@Test
	public void test_fetchData(){
		List<MarketPrice> fetchData = serviceToTest.fetchData();
		if(fetchData != null && !fetchData.isEmpty()){
			LocalDateTime originalDateTime = fetchData.get(0).getOriginalDateTime();
			LocalDateTime nowTime = LocalDateTime.now();
			assertEquals(originalDateTime.getYear() , nowTime.getYear());
			assertEquals(originalDateTime.getMonth() , nowTime.getMonth());
			assertEquals(originalDateTime.getDayOfWeek() , nowTime.getDayOfWeek());
			
		}
	}
}
