package com.shoppertrak.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppertrak.domain.TrafficIntervalRecord;
import com.shoppertrak.domain.TrafficRecord;
import com.shoppertrak.exception.InvalidDateException;
import com.shoppertrak.exception.RecordNotFound;
import com.shoppertrak.service.TrafficService;


@RestController
@RequestMapping("api/v1.0/traffic")
public class TrafficResource {
	
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	TrafficService service;

   ////////////////////////////////////////////////////////////////
   //  Code created by Tom
   ////////////////////////////////////////////////////////////////
   @ApiOperation(value = "Get Traffic by Client, Store, and Date Range")
   @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid date") })
   @RequestMapping(value = "client/{clientId}/store/{storeId}/startTime/{startTimeStr}/endTime/{endTimeStr}", method = RequestMethod.GET)
   public @ResponseBody TrafficIntervalRecord get(@ApiParam(value = "Client Id", required = true) @PathVariable int clientId,
                                                  @ApiParam(value = "Store Id", required = true) @PathVariable int storeId,
                                                  @ApiParam(value = "Start Time", required = true) @PathVariable String startTimeStr,
                                                  @ApiParam(value = "End Time", required = true) @PathVariable String endTimeStr) 
   {
      SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
      
      Date startDt = null;
      Date endDt = null;
      
      checkDateString(startTimeStr);
      checkDateString(endTimeStr);
      
      try 
      {
         startDt = df.parse(startTimeStr);
         endDt = df.parse(endTimeStr);
      } 
      catch (ParseException e) 
      {
         throw new InvalidDateException();
      }
      
      TrafficIntervalRecord trafficIntervalRecord = service.buildTrafficIntervalRecord(clientId, storeId, startDt, endDt);
      
      return service.filterIntervalRecord(trafficIntervalRecord);
   }

   @ApiOperation(value = "Get Traffic by Client and Date Range")
   @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid date") })
   @RequestMapping(value = "client/{clientId}/startTime/{startTimeStr}/endTime/{endTimeStr}", method = RequestMethod.GET)
   public @ResponseBody TrafficIntervalRecord get(@ApiParam(value = "Client Id", required = true) @PathVariable int clientId,
                                                  @ApiParam(value = "Start Time", required = true) @PathVariable String startTimeStr,
                                                  @ApiParam(value = "End Time", required = true) @PathVariable String endTimeStr) 
   {
      SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
      
      Date startDt = null;
      Date endDt = null;
      
      checkDateString(startTimeStr);
      checkDateString(endTimeStr);
      
      try 
      {
         startDt = df.parse(startTimeStr);
         endDt = df.parse(endTimeStr);
      } 
      catch (ParseException e) 
      {
         throw new InvalidDateException();
      }
      
      TrafficIntervalRecord trafficIntervalRecord = service.buildTrafficIntervalRecord(clientId, -1, startDt, endDt);
      
      return service.filterIntervalRecord(trafficIntervalRecord);
   }

	public void checkDateString(String startTimeStr) throws InvalidDateException
   {
	   if ("yyyyMMddHHmm".length() != startTimeStr.length()) throw new InvalidDateException();
	         
	   try
	   {
         int minute = Integer.parseInt(startTimeStr.substring("yyyyMMddHHmm".length() - 2));
         int hour = Integer.parseInt(startTimeStr.substring("yyyyMMddHHmm".length() - 4, "yyyyMMddHHmm".length() - 2));
         int day = Integer.parseInt(startTimeStr.substring("yyyyMMddHHmm".length() - 6, "yyyyMMddHHmm".length() - 4));
         int month = Integer.parseInt(startTimeStr.substring("yyyyMMddHHmm".length() - 8, "yyyyMMddHHmm".length() - 6));
         int year = Integer.parseInt(startTimeStr.substring(1, 5));

         if (60 <= minute || 0 != minute%15)  throw new InvalidDateException();
         if (24 <= hour)  throw new InvalidDateException();
         if (12 < month || 0 == month)  throw new InvalidDateException();
         if (24 <= hour)  throw new InvalidDateException();
         if (24 <= hour)  throw new InvalidDateException();
         if (31 < day || 0 == day)  throw new InvalidDateException();
         if ((4 == month || 6 == month || 9 == month || 11 == month)
              && (30 < day))  throw new InvalidDateException();
         if ((2 == month) && (30 < day))  throw new InvalidDateException();
         if ((2 == month) && (29 < day) && 0 == year%1000)  throw new InvalidDateException();
         else if ((2 == month) && (28 < day) && 0 == year%100)  throw new InvalidDateException();
         else if ((2 == month) && (29 < day) && 0 == year%4)  throw new InvalidDateException();
         else if ((2 == month) && (28 < day))  throw new InvalidDateException();
         
	   }
	   catch(NumberFormatException e)
	   {
	      throw new InvalidDateException();
	   }
   }
   ////////////////////////////////////////////////////////////////
   //  End Code created by Tom
   ////////////////////////////////////////////////////////////////

   @ApiOperation(value = "Delete Traffic Record")
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@ApiParam(value = "Record Id", required = true) @PathVariable int id) {
		service.delete(id);
	}
	
	@ApiOperation(value = "Get All Traffic Records")
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<TrafficRecord> getAllRecords() {
		return service.getAll();
	}
	
	@ApiOperation(value = "Save Traffic Records")
	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody TrafficRecord record) {
		service.save(record);
	}
	
   @ApiOperation(value = "Get Traffic Record")
   @ApiResponses(value = {@ApiResponse(code = 404, message = "Record is not found") })
   @RequestMapping(value = "{id}", method = RequestMethod.GET)
   public @ResponseBody TrafficRecord get(@ApiParam(value = "Record Id", required = true) @PathVariable int id) {

      TrafficRecord  rec = service.get(id);
      if (rec == null) {
         logger.info("Record is not found for macAddress:" + id);
         throw new RecordNotFound(id);
      }
      return rec;
   }



}
