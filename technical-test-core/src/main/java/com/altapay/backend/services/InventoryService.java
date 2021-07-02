package com.altapay.backend.services;

import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.Product;
import com.altapay.backend.repositories.InventoryRepository;

public class InventoryService 
{
	private InventoryRepository repository;

	public InventoryService(InventoryRepository repository)
	{
		this.repository = repository;
	}
	
	public boolean checkInventory(Product product, int quantity)
	{
		Inventory inventory=repository.load(product.getId());
		if(inventory.getInventory()==0)
			return false;
		else 
			return true;
	}
	
	public boolean takeFromInventory(Product product, int quantity)
	{
		Inventory inventory=repository.load(product.getId());
		if(inventory.getProduct()==null)
			return false;
		else 
			return true;
	}
}
