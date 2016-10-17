package com.shoppertrak;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.shoppertrak.exception.InvalidDateException;
import com.shoppertrak.web.TrafficResource;
////////////////////////////////////////////////////////////////
//  Code created by Tom
////////////////////////////////////////////////////////////////

public class DateParserTest
{

   @Test
   public void testMonth()
   {
      TrafficResource trafficResource = new TrafficResource();
      
      for(int i = 0; i < 100; i++)
      {
         String date = "2016";
         if (i < 10) date = date + "0" + i + "010000";
         else date = date + i + "010000";
         
         try
         {
            trafficResource.checkDateString(date);
            if (12 < i || 0 == i) fail(i + " hour did not throw a date exception");

         }
         catch(InvalidDateException e)
         {
            if (12 >= i && 0 != i) fail(i + "hour threw a date exception");
         }
         
      }
   }

   @Test
   public void testDay()
   {
      TrafficResource trafficResource = new TrafficResource();

      try
      {
         trafficResource.checkDateString("200009310000");
         fail("date did not throw a date exception");
      }
      catch(InvalidDateException e)
      {
      }
      try
      {
         trafficResource.checkDateString("200004310000");
         fail("date did not throw a date exception");
      }
      catch(InvalidDateException e)
      {
      }
      try
      {
         trafficResource.checkDateString("200011310000");
         fail("date did not throw a date exception");
      }
      catch(InvalidDateException e)
      {
      }
      try
      {
         trafficResource.checkDateString("200006310000");
         fail("date did not throw a date exception");
      }
      catch(InvalidDateException e)
      {
      }
      try
      {
         trafficResource.checkDateString("200002290000");
         fail("date did not throw a date exception");
      }
      catch(InvalidDateException e)
      {
      }
      try
      {
         trafficResource.checkDateString("200102290000");
         fail("date did not throw a date exception");
      }
      catch(InvalidDateException e)
      {
      }
      try
      {
         trafficResource.checkDateString("200402300000");
         fail("date did not throw a date exception");
      }
      catch(InvalidDateException e)
      {
      }

      try
      {
         trafficResource.checkDateString("201603013030");
         fail("date did not throw a date exception");
      }
      catch(InvalidDateException e)
      {
      }
      
      
   }

   @Test
   public void testHour()
   {
      TrafficResource trafficResource = new TrafficResource();
      
      for(int i = 0; i < 100; i++)
      {
         String date = "20160301";
         if (i < 10) date = date + "0" + i + "00";
         else date = date + i + "00";
         
         try
         {
            trafficResource.checkDateString(date);
            if (24 <= i) fail(i + " hour did not throw a date exception");

         }
         catch(InvalidDateException e)
         {
            if (24 > i) fail(i + "hour threw a date exception");
         }
         
      }
     
   }

   @Test
   public void test15Minute()
   {
      TrafficResource trafficResource = new TrafficResource();
            
      for(int i = 0; i < 100; i++)
      {
         String date = "2016030101";
         if (i < 10) date = date + "0" + i;
         else date = date + i;
         
         try
         {
            trafficResource.checkDateString(date);
            if (60 <= i || 0 != i%15) fail(i + " minute did not throw a date exception");

         }
         catch(InvalidDateException e)
         {
            if (60 > i && 0 == i%15) fail(i + " minute threw a date exception");
         }
         
      }
     
   }

}
