package com.altapay.backend.services;

import com.altapay.backend.ioc.BackendContainer;
import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;


public class InventoryService 
{
	@SuppressWarnings("unused")
	private Inventory repository;
	BackendContainer backendContainer = new BackendContainer();

	public InventoryService(Inventory repository)
	{
		this.repository = repository;
	}
		


	public boolean checkInventory(Product product, int quantity)
	{
		boolean status = true;
		OrderLine orderLine= backendContainer.getOrderLine();
		
		if(orderLine.getProduct()==product)
		{
			if(quantity>orderLine.getQuantity())
				status= false;
			else 
				status= true;
		}
		return status;
	}
	
	public boolean takeFromInventory(Product product, int quantity)
	{		
		boolean status = true;
		OrderLine orderLine= backendContainer.getOrderLine();
		Inventory inventory= new Inventory();
		
		if(inventory.getProduct()==product)
		{
			if(quantity>inventory.getInventory())
				
				status= false;
			else 
				orderLine.setProduct(product);
				status= true;
		}
		return status;
	}
}
