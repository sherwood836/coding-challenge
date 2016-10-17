package com.shoppertrak.domain;

import java.util.Date;
import java.util.List;
////////////////////////////////////////////////////////////////
//  Code created by Tom
////////////////////////////////////////////////////////////////

public class TrafficIntervalRecord 
{
	public int clientId;	
	public  int storeId;	
   public List<TrafficInterval> trafficList;

	public TrafficIntervalRecord setClientId(int clientId) {
		this.clientId = clientId;
		return this;
	}
	public TrafficIntervalRecord setStoreId(int storeId) {
		this.storeId = storeId;
		return this;
	}
}
