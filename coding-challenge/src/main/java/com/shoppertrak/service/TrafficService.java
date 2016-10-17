package com.shoppertrak.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppertrak.dao.TrafficDao;
import com.shoppertrak.domain.TrafficInterval;
import com.shoppertrak.domain.TrafficIntervalRecord;
import com.shoppertrak.domain.TrafficRecord;

@Service
public class TrafficService {
	
	TrafficDao dao;
	 
   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Autowired
	public TrafficService(TrafficDao dao) {
		this.dao = dao;
	}
	
	public TrafficRecord get(int id) {
		return dao.get(id);
	}

	public void save(TrafficRecord r) {
		dao.save(r);
	}

	public Collection<TrafficRecord> getAll() {
		return dao.getAll();
	}

	public void delete(int id) {
		dao.delete(id);
	}

	////////////////////////////////////////////////////////////////
	//  Code created by Tom
	////////////////////////////////////////////////////////////////
   public TrafficIntervalRecord buildTrafficIntervalRecord(int clientId,
                                                           int storeId,
                                                           Date startTime,
                                                           Date endTime)
   {
      TrafficIntervalRecord trafficIntervalRecord = new TrafficIntervalRecord();

      
      trafficIntervalRecord.clientId = clientId;
      trafficIntervalRecord.storeId = storeId;
      
      long currentTime = startTime.getTime();
      
      trafficIntervalRecord.trafficList = new ArrayList<TrafficInterval>();
      
      while (endTime.getTime() > currentTime)
      {
         TrafficInterval trafficInterval = new TrafficInterval();
         
         trafficInterval.interval = df.format(new Date(currentTime + 1000*60*15));
         
         trafficIntervalRecord.trafficList.add(trafficInterval);
         
         currentTime = currentTime + 1000*60*15;
      }
      
      return trafficIntervalRecord;
   }

   public TrafficIntervalRecord filterIntervalRecord(TrafficIntervalRecord trafficIntervalRecord)
   {
      List<TrafficRecord> trafficList = filterRecordsByClientId(trafficIntervalRecord.clientId);
            
      if (!trafficList.isEmpty() && -1 < trafficIntervalRecord.storeId)
      {
         trafficList = filterRecordsByStoreId(trafficList, trafficIntervalRecord.storeId);
      }
      
      if (!trafficList.isEmpty())
      {
         trafficList = filterRecordsByIntervals(trafficList, trafficIntervalRecord);
      }
      
      return trafficIntervalRecord;
   }

   public List<TrafficRecord> filterRecordsByIntervals(List<TrafficRecord> trafficList, TrafficIntervalRecord trafficIntervalRecord)
   {
      for (TrafficInterval trafficInterval : trafficIntervalRecord.trafficList)
      {
         try
         {
            Date endIntervalDate = df.parse(trafficInterval.interval);
            Date startIntervalDate = new Date(endIntervalDate.getTime() - 1000*60*15);
            
            for(TrafficRecord traffic : trafficList)
            {
               if ((traffic.min5_dt.after(startIntervalDate))
                    && (traffic.min5_dt.before(endIntervalDate)  || traffic.min5_dt.equals(endIntervalDate)))
               {
                  trafficInterval.enters+=traffic.enters;
                  trafficInterval.exits+=traffic.exits;
               }
            }
         } 
         catch (ParseException e)
         {
            // TODO Auto-generated catch block
         }

      }
      
      return trafficList;
   }

   public List<TrafficRecord> filterRecordsByStoreId(List<TrafficRecord> trafficList, int storeId)
   {
      List<TrafficRecord> newTrafficList = new ArrayList<TrafficRecord>();
      
      for(TrafficRecord traffic : trafficList)
      {
         if (storeId == traffic.storeId)
            newTrafficList.add(traffic);
      }
      
      return newTrafficList;
   }

   public List<TrafficRecord> filterRecordsByClientId(int clientId)
   {
      List<TrafficRecord> trafficList = new ArrayList<TrafficRecord>();
      
      for(TrafficRecord traffic : getAll())
      {
         if (clientId == traffic.clientId)
            trafficList.add(traffic);
      }
      
      return trafficList;
   }
   ////////////////////////////////////////////////////////////////
   //  End Code created by Tom
   ////////////////////////////////////////////////////////////////
   
   
   
}
