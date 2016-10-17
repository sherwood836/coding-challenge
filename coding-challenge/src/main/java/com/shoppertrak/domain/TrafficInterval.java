package com.shoppertrak.domain;

import java.util.Date;
////////////////////////////////////////////////////////////////
//  Code created by Tom
////////////////////////////////////////////////////////////////

public class TrafficInterval 
{
	public int enters;
	public int exits;
	
	public String interval;

	public TrafficInterval setEnters(int enters) 
	{
		this.enters = enters;
		return this;
	}
	
	public TrafficInterval setExits(int exits) 
	{
		this.exits = exits;
		return this;
	}

}
