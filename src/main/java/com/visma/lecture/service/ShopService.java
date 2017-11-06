package com.visma.lecture.service;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;
import com.visma.lecture.repository.ShopRepository;
import lombok.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.visma.lecture.Validators.Validator.*;

/**
 * Service class for shop
 *
 * @author Leo-Andreas Ervik
 */
public class ShopService {
	
	private final ShopRepository shopRepository;
	
	public ShopService(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	//Oppgave 1
	public Map<ItemLocation, List<Item>> getMapOfAllItemsPerLocation(){
		Map<ItemLocation, List<Item>> collection = shopRepository.getAllItems()
				.stream()
				.collect(Collectors.groupingBy(Item::getItemLocation));

		validateOutputMap(collection);
		return collection;
	}

	//Oppgave 2
	public Map<ItemType, List<Item>> getMapOfAllItemsPerType() {
		Map<ItemType, List<Item>> collection = shopRepository.getAllItems()
				.stream()
				.collect(Collectors.groupingBy(Item::getItemType));

		validateOutputMap(collection);
		return collection;
	}

	//Oppgave 3
	public Map<String, List<Item>> getMapOfAllItemsPerProducer() {

		Map<String, List<Item>> collection = shopRepository.getAllItems()
				.stream()
				.collect(Collectors.groupingBy(e -> e.getItemName()
						.substring(0, e.getItemName().indexOf(" "))));

		validateOutputMap(collection);
		return collection;
	}

	//Oppgave 4
	public Map<Boolean, List<Item>> getMapOfAllItemsPerStock() {

		Map<Boolean, List<Item>> collection = shopRepository.getAllItems()
				.stream()
				.collect(Collectors.partitioningBy(item -> item.getStock() > 1500)
				);

		validateOutputMap(collection);
		return collection;
	}

	//Oppgave 5
	public Item getItemById(int id) {
		validateInputInteger(id);

		Item item = shopRepository.getAllItems()
				.stream()
				.filter(e -> e.getItemID().equals(id))
				.findFirst()
				.orElseThrow(() ->
						new NoItemFoundForCriteriaException("No items were found for the given search criteria."));
		validateOutputItem(item);
		return item;
	}

	//Oppgave 6
	public String getStringOfAllProducers() {
		String producers = shopRepository.getAllItems()
				.stream()
				.map(Item::getItemName)
				.map(e -> e.substring(0, e.indexOf(" ")))
				.collect(Collectors.joining("X"));
		validateOutputString(producers);
		return producers;
	}

	/**
	 * ex 7		Get a list of all locations with more than X in stock of any type of item.
	 * @param numberInStock
	 * @return
	 */
	public List<ItemLocation> getListOfAllLocationsWithMoreThanXInStock(int numberInStock) {
		validateInputInteger(numberInStock);

		List<ItemLocation> locations = shopRepository.getAllItems()
				.stream()
				.filter(e -> e.getStock() > numberInStock)
				.map(Item::getItemLocation)
				.distinct()
				.collect(Collectors.toList());

		validateOutputList(locations);
		return locations;
	}


	/**
	 * ex 8 get List of all locations with less than X in stock
	 * @param numberInStock
	 * @return
	 */
	public List<ItemLocation> getListOfAllLocationsWithLessThanXInStock(int numberInStock) {
		validateInputInteger(numberInStock);
		List<ItemLocation> locations = shopRepository.getAllItems()
				.stream()
				.filter(e -> e.getStock() < numberInStock)
				.map(Item::getItemLocation)
				.distinct()
				.collect(Collectors.toList());

		validateOutputList(locations);
		return locations;
	}

	/**
	 * ex 9 	Get list of items in location X with more than Y in stock
	 * @param location
	 * @param numberInStock
	 * @return
	 */
	public List<Item> getItemsInLocationXWithMoreThanYInStock(ItemLocation location, int numberInStock) {
		validateInputInteger(numberInStock);
		validateInputLocation(location);

		List<Item> items = shopRepository.getAllItems()
				.stream()
				.filter(item -> item.getItemLocation().equals(location))
				.filter(item -> item.getStock() > numberInStock)
				.collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}

	/**
	 * ex 10 	Get a list of items in location X with less than Y in stock
	 * @param location
	 * @param numberInStock
	 * @return
	 */
	public List<Item> getItemsInLocationXWithLessThanYInStock(ItemLocation location, int numberInStock) {
		validateInputInteger(numberInStock);
		validateInputLocation(location);

		List<Item> items = shopRepository.getAllItems()
				.stream()
				.filter(item -> item.getItemLocation().equals(location))
				.filter(item -> item.getStock() < numberInStock)
				.collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}


	/**
	 * ex 11 Get a list of items with name starting with "X".
	 * @return
	 */
	public List<Item> getItemsWithNameStartingWith(String character) {

		List<Item> items = shopRepository.getAllItems()
				.stream()
				.filter(item -> item
						.getItemName()
						.toUpperCase()
						.substring(item
								.getItemName()
								.indexOf(" ")+1)
						.startsWith(character))
				.collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}

	/**
	 * ex 12 	Get average stock count for items in location X
	 * @param location
	 * @return
	 */
	public double getAverageItemStockForLocationX(ItemLocation location) {
		validateInputLocation(location);
		double average = shopRepository.getAllItems()
				.stream()
				.filter(item -> item.getItemLocation().equals(location))
				.mapToInt(Item::getStock)
				.average()
				.getAsDouble();

		validateOutputDouble(average);
		return average;
	}


	/**
	 * ex 13 	Get item with the highest number in stock
	 * @return
	 */
	public Item getItemWithMostInStock() {
		Item item = shopRepository.getAllItems()
				.stream()
				.max(Comparator.comparingInt(Item::getStock))
				.get();

		validateOutputItem(item);
		return item;
	}

	/**
	 * ex 14 	Get item with the lowest number in stock
	 * @return
	 */
	public Item getItemWithLeastInStock() {
		Item item = shopRepository.getAllItems()
				.stream()
				.min(Comparator.comparingInt(Item::getStock))
				.get();

		validateOutputItem(item);
		return item;
	}


	/**
	 * ex 16 	getting list sorted by producer
	 * @return
	 */
	public List<Item> getItemsSortedByProducer() {
		List<Item> items = shopRepository.getAllItems()
				.stream()
				.sorted(Comparator.comparing(Item::getItemName))
				.collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}

	/**
	 * ex 17	getting list sorted by name
	 * @return
	 */
	public List<Item> getItemsSortedByName() {
		List<Item> items = shopRepository.getAllItems()
				.stream()
				.sorted(Comparator.comparing(item -> item.getItemName()
						.substring(item.getItemName().indexOf(" "))))
				.collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}
}
