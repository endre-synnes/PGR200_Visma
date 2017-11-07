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
import java.util.stream.Stream;

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

	/**
	 * ex 1		Gets a Map of items grouped by Location
	 * @return Map
	 */
	public Map<ItemLocation, List<Item>> getMapOfAllItemsPerLocation(){
		Map<ItemLocation, List<Item>> collection = shopRepository.getAllItems()
				.stream()
				.collect(Collectors.groupingBy(Item::getItemLocation));

		validateOutputMap(collection);
		return collection;
	}

	/**
	 * ex 2 	Gets a Map of all items grouped by Type
	 * @return Map
	 */
	public Map<ItemType, List<Item>> getMapOfAllItemsPerType() {
		Map<ItemType, List<Item>> collection = shopRepository.getAllItems()
				.stream()
				.collect(Collectors.groupingBy(Item::getItemType));

		validateOutputMap(collection);
		return collection;
	}

	/**
	 * ex 3 	Gets a Map of All items grouped by producer.
	 * @return Map
	 */
	public Map<String, List<Item>> getMapOfAllItemsPerProducer() {

		Map<String, List<Item>> collection = shopRepository.getAllItems()
				.stream()
				.collect(Collectors.groupingBy(e -> e.getItemName()
						.substring(0, e.getItemName().indexOf(" "))));

		validateOutputMap(collection);
		return collection;
	}

	/**
	 * ex 4 	Gets a Map of lists grouped by Stock value greater or less than 1500
	 * 			Key is a Boolean that is true if stock value is over 1500.
	 * @return Map
	 */
	public Map<Boolean, List<Item>> getMapOfAllItemsPerStock() {

		Map<Boolean, List<Item>> collection = shopRepository.getAllItems()
				.stream()
				.collect(Collectors.partitioningBy(item -> item.getStock() > 1500)
				);

		validateOutputMap(collection);
		return collection;
	}

	/**
	 * ex 5 Get an Item by Id
	 * @param id
	 * @return Item
	 */
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

	/**
	 * ex 6 Gets a string of All producers divided by "X"
	 * @return String
	 */
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
	 * @return List of Items
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
	 * ex 8 	Get List of all locations with less than X in stock
	 * @param numberInStock
	 * @return List of items
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
	 * @return List of items
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
	 * @return List of Items
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
	 * @return List of items
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
	 * @return double
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
	 * @return Item
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
	 * @return List of items
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
	 * ex 15 	I can not see the difference between this exercise and ex 9
	 * 			so i am here calling the method in ex 9.
	 * @param location
	 * @param stock
	 * @return
	 */
	public List<Item> getItemsInLocationXStockHigherThan(ItemLocation location, int stock) {
		return getItemsInLocationXWithMoreThanYInStock(location, stock);
	}


	/**
	 * ex 16 	getting list sorted by producer
	 * @return List of items
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
	 * @return List of items
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


	/**
	 * ex 18	Get a list of items sorted by stock-value high to low.
	 * @return List of items
	 */
	public List<Item> getItemsSortedByStock() {
		List<Item> items = shopRepository.getAllItems()
				.stream()
				.sorted(Comparator.comparingInt(Item::getStock).reversed())
				.collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}

	/**
	 * ex 19	Get a list of distinct items.
	 * @return List of items
	 */
	public List<Item> getAllDistinctItems() {
		List<Item> items = shopRepository.getAllItems()
				.stream()
				.distinct()
				.collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}


	/**
	 * ex 20	Getting list of items, this list contains two sublist.
	 * 			sublist 1 is a list of items with ID between a and b,
	 * 			same goes for sublist 2.
	 * @param a start of sublist 1
	 * @param b end of sublist 1
	 * @param x start of sublist 2
	 * @param y end of sublist 2
	 * @return List of items
	 */
	public List<Item> getListFromTwoSublistByIndexValues(int a, int b, int x, int y){
		List<Item> items = Stream.concat(
				shopRepository.getItemsInRange(a, b).stream(),
				shopRepository.getItemsInRange(x, y).stream()).collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}


	/**
	 * ex 21	Get list of items with three parameters: location, type and producer
	 * 			Without duplicates
	 * @param location
	 * @param type
	 * @param producer
	 * @return List of items
	 */
	public List<Item> getListOfItmesByLocationTypeAndProducer(ItemLocation location, ItemType type, String producer) {

		List<Item> items = Stream.of(shopRepository.getItemsPerLocation(location),
				shopRepository.getItemsPerType(type), shopRepository.getItemsPerProducer(producer))
				.flatMap(List::stream)
				.distinct()
				.collect(Collectors.toList());

		validateOutputList(items);
		return items;
	}

	/**
	 * ex 22	Get total stock for all items.
	 * @return int value
	 */
	public int getTotalStock() {
		int totalStock = shopRepository.getAllItems()
				.stream()
				.mapToInt(Item::getStock)
				.sum();

		validateOutputInt(totalStock);
		return totalStock;
	}
}
