package com.shoppertrak;

import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shoppertrak.domain.TrafficRecord;
import com.shoppertrak.service.TrafficService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class TrafficServiceTest 
{

	@Autowired
	TrafficService target;
	
	@Before
	public void init() {}

	@After
	public void clean() {}
	
   @Test
   public void getAllTest() { 
      Collection<TrafficRecord> ret = target.getAll();
      TestCase.assertTrue(ret.size()>0);
   }
   
   @Test
   public void filterStoreIdTest() 
   { 
      List<TrafficRecord> trafficList = target.filterRecordsByClientId(100);
      
      System.out.println("[" + trafficList.size() + "]");
      
      TestCase.assertTrue(trafficList.size()>0);
   }
   
}
