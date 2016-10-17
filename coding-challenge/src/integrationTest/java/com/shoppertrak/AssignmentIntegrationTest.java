package com.shoppertrak;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.shoppertrak.domain.TrafficInterval;
import com.shoppertrak.domain.TrafficRecord;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

////////////////////////////////////////////////////////////////
//  Code created by Tom - But I didn't get it to work....
////////////////////////////////////////////////////////////////

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebIntegrationTest(randomPort = true)
public class AssignmentIntegrationTest {

	private static final String ID = "{id}";
	
	@Value("${local.server.port}")
	private int port;

	@Before
	public void init() {
		RestAssured.port = port;
		RestAssured.basePath = "/api/v1.0/traffic";
	}

	@After
	public void clean() {

	}
	
	// clientId: 100,
	// storeId: 1001
	@Test
	void firstTest() 
	{       
      //List<TrafficInterval> trafficIntervalList = 
            when()
              .get("/client/100/store/1001/startTime/201603010000/endTime/201603010100");
            //.body("clientId", is("100"))
            //.body("storeId", is("1001"))
            //.extract().body().path("/traffic");

      //TestCase.assertTrue(4==trafficIntervalList.size());
   }

//   // clientId: 100,
//   // storeId: 1001
//   @Test
//   void secondTest() 
//   {     
//      //List<TrafficInterval> trafficIntervalList = 
//         when()
//           .get("client/100/store/1001/startTime/201603010000/endTime/201603010115")
//         .then()
//         .statusCode(200);
//         //.body("clientId", is("100"))
//         //.body("storeId", is("1001"))
//         //.extract().body().path("/traffic");
//      
//      //TestCase.assertTrue(5==trafficIntervalList.size());
//   }

	void getNotFound(TrafficRecord rec) {
	     when()
	        .get(ID, rec.id)
         .then()
        	.statusCode(404);
	}
		
}
