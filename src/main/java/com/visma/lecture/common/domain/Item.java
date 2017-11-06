package com.visma.lecture.common.domain;

import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;

/**
 * Item domain class
 *
 * @author Leo-Andreas Ervik
 */
public class Item {

	private final Integer itemID;
	private final String itemName;
	private final ItemLocation itemLocation;
	private final ItemType itemType;
	private final Integer stock;

	public Item(Integer itemID, String itemName, ItemLocation itemLocation, ItemType itemType, Integer stock) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemLocation = itemLocation;
		this.itemType = itemType;
		this.stock = stock;
	}

	public Integer getItemID() {
		return itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public ItemLocation getItemLocation() {
		return itemLocation;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public Integer getStock() {
		return stock;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Item item = (Item) o;

		if (itemID != null ? !itemID.equals(item.itemID) : item.itemID != null) return false;
		if (itemName != null ? !itemName.equals(item.itemName) : item.itemName != null) return false;
		if (itemLocation != item.itemLocation) return false;
		if (itemType != item.itemType) return false;
		return stock != null ? stock.equals(item.stock) : item.stock == null;
	}

	@Override
	public int hashCode() {
		int result = itemID != null ? itemID.hashCode() : 0;
		result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
		result = 31 * result + (itemLocation != null ? itemLocation.hashCode() : 0);
		result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
		result = 31 * result + (stock != null ? stock.hashCode() : 0);
		return result;
	}
}
