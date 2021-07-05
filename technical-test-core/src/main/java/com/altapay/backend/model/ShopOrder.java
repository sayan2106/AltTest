package com.altapay.backend.model;

import java.util.List;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.ioc.BackendContainer;
import com.altapay.backend.repositories.InventoryRepository;
import com.altapay.backend.services.InventoryService;
import com.altapay.backend.services.MerchantApiService;
import com.altapay.util.HttpUtil;
import com.altapay.util.XpathUtil;


public class ShopOrder 
{
	
	private static final HttpUtil HttpUtil = null;
	private static final XpathUtil XpathUtil = null;
	String id;
	String paymentId;
	List<OrderLine> orderLines;
	BackendContainer backendContainer = new BackendContainer();
	
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

	public void capture() throws MerchantApiServiceException 
	{
		// TODO: use the InventoryService to check inventory before capturing
		InventoryRepository inventoryRepository=new InventoryRepository();
		Inventory inventory=new Inventory();
		
		InventoryService inventoryService=new InventoryService(inventoryRepository.load(id));
		if (inventoryService.checkInventory(backendContainer.getProduct(),Integer.parseInt(id)))
		{
		   // TODO: Use the MerchantApiService to capture the payment. 
			MerchantApiService merchantApiService=new MerchantApiService(HttpUtil, XpathUtil);
			merchantApiService.capturePayment(this);
		}
		   // TODO: use the InventoryService to take from inventory after capturing 
		inventoryService.takeFromInventory(inventory.getProduct(), inventory.getInventory());
	}

	// Release is a synonym for canceling a payment
	public void release() 
	{
		
		// TODO: Use the MerchantApiService to release the payment.
		
		MerchantApiService merchantApiService=new MerchantApiService(HttpUtil, XpathUtil);
		try {
			merchantApiService.releasePayment(this);
		} catch (MerchantApiServiceException e) {
			e.printStackTrace();
		}
	}
}
