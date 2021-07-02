package com.altapay.backend.model;

import java.util.List;


public class ShopOrder 
{
	String id;
	String paymentId;
	List<OrderLine> orderLines;
	
	public void setId(String id) 
	{
		this.id = id;
	}

	public void setPaymentId(String paymentId) 
	{
		this.paymentId = paymentId;
	}
	
	public void setOrderLines(List<OrderLine> orderLines)
	{
		this.orderLines = orderLines;
	}

	public void capture() 
	{
		
	}

	
	public void release() 
	{
		
		
	}
}
