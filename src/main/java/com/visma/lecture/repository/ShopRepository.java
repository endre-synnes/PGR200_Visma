package com.visma.lecture.repository;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository class for shop
 *
 * @author Leo-Andreas Ervik
 */
public class ShopRepository {

	private final List<Item> items;

	public ShopRepository(List<Item> items) {
		this.items = items;
	}

	public Item findItemById(Integer id) {
		return items.stream()
				.filter(e -> e.getItemID().equals(id))
				.findFirst()
				.orElse(null);
	}

	public Boolean create(Item item) {
		return items.add(item);
	}

	public Boolean update(Item item) {
		Item i = findItemById(item.getItemID());
		delete(i.getItemID());
		return create(item);
	}

	public Boolean delete(Integer itemId) {
		return items.removeIf(e -> e.getItemID().equals(itemId));
	}

	public List<Item> getAllItems() {
		return Optional.ofNullable(items).orElse(null)
				.stream()
				.collect(Collectors.toList());
	}

	public List<Item> getItemsInRange(int start, int end) {

//		Comparator<Item> byId = Comparator.comparingInt(Item::getItemID);

		return items.stream()
				.skip(start)
				.limit(end)
//				.sorted(byId)
				.collect(Collectors.toList());
	}

	public List<Item> getItemsPerLocation(ItemLocation location) {
		return items
				.stream()
				.filter(item -> item.getItemLocation().equals(location))
				.collect(Collectors.toList())
				;

	}
	

	public List<Item> getItemsPerType(ItemType typeName) {
		return items.stream()
				.filter(item -> item.getItemType().equals(typeName))
				.collect(Collectors.toList());
	}

	public List<Item> getItemsPerProducer(String producer) {
		final String formattedProducer = producer.replace(" ", "_");
		return items.stream()
				.filter(item -> item.getItemName().contains(formattedProducer))
				.collect(Collectors.toList());
	}
}
