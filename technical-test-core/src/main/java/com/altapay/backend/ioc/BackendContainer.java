package com.altapay.backend.ioc;

import com.altapay.backend.controllers.BackendController;
import com.altapay.backend.model.IModelFactory;
import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.repositories.ShopOrderRepository;

public class BackendContainer implements IModelFactory {

	public BackendController getBackendController() 
	{
		return new BackendController(getShopOrderRepository(this));
				
	}

	
	private static ShopOrderRepository shopOrderRepository=null;
	
	private static ShopOrderRepository getShopOrderRepository(BackendContainer backendContainer) 
	{
		if(shopOrderRepository==null)
		{
		return new ShopOrderRepository(backendContainer);
		}
		return shopOrderRepository;
	}

	@Override
	public ShopOrder getShopOrder() 
	{
		
		return new ShopOrder();
	}

	@Override
	public Inventory getInventory() 
	{
		
		return new Inventory();

	}

	@Override
	public OrderLine getOrderLine() 
	{
		
		return new OrderLine();
	}

	@Override
	public Product getProduct() 
	{
		
		return new Product();
	}
	

}
