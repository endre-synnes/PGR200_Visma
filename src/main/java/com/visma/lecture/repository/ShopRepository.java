package com.visma.lecture.repository;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;
import static com.visma.lecture.Validators.Validator.*;

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
		validateOutputItem(item);
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

	//ex 2
	public List<Item> getAllItems() {
		List<Item> i = items
				.stream()
				.collect(Collectors.toList());
		validateOutputList(i);
		return i;
	}

	//ex 3
	public List<Item> getItemsInRange(int start, int end) {
		List<Item> list = items.stream()
				.sorted(Comparator.comparingInt(Item::getItemID))
				.filter(item -> item.getItemID() >= start)
				.filter(item -> item.getItemID() <= end)
				.collect(Collectors.toList());
		validateOutputList(list);
		return list;
	}

	//ex 4
	public List<Item> getItemsPerLocation(ItemLocation location) {
		List<Item> list = items
				.stream()
				.filter(item -> item.getItemLocation().equals(location))
				.collect(Collectors.toList());
		validateOutputList(list);
		return list;
	}
	

	//ex 5
	public List<Item> getItemsPerType(ItemType typeName) {
		List<Item> list = items.stream()
				.filter(item -> item.getItemType().equals(typeName))
				.collect(Collectors.toList());
		validateOutputList(list);
		return list;
	}


	//ex 6
	public List<Item> getItemsPerProducer(String producer) {
		final String formattedProducer = producer.replace(" ", "_");
		List<Item> list = items.stream()
				.filter(item -> item.getItemName().contains(formattedProducer))
				.collect(Collectors.toList());
		validateOutputList(list);
		return list;
	}
}
