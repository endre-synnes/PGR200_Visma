package lecture.service;

import com.visma.lecture.common.database.Database;
import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;
import com.visma.lecture.repository.ShopRepository;
import com.visma.lecture.service.ShopService;
import lecture.util.ShopTestUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ShopServiceTest {
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	private ShopRepository shopRepository, bigShopRepository;
	private ShopService shopService, bigShopService;
	
	@Before
	public void setUp() throws Exception {
		shopRepository = new ShopRepository(new ShopTestUtil().getItems());
		shopService = new ShopService(shopRepository);

		bigShopRepository = new ShopRepository(Database.itemTable);
		bigShopService = new ShopService(bigShopRepository);
	}
	
//	@Test
//	public void shouldThrowExceptionOnMissingInput() throws Exception {
//
//		expected.expect(InvalidCriteriaException.class);
//		expected.expectMessage("Input was null, empty or lower than 0.");
//	}

	/**
	 *  Test if i can get a Map per location and contains key.
	 * @throws Exception
	 */
	@Test
	public void getMapPerLocation() throws Exception {
		Map<ItemLocation, List<Item>> map = shopService.getMapOfAllItemsPerLocation();
		//map.forEach((k, v) -> System.out.println(k + v));
		assertTrue(map.containsKey(ItemLocation.valueOf("OSLO")));
		assertEquals(2, map.size());
	}

	/**
	 * ex 2 	test if Map contains the key i am asking for, and size is as expected.
	 * @throws Exception
	 */
	@Test
	public void getMapPerType() throws Exception {
		Map<ItemType, List<Item>> map = shopService.getMapOfAllItemsPerType();
		assertTrue(map.containsKey(ItemType.valueOf("BEVERAGE")));
		assertEquals(3, map.size());
	}

	/**
	 * ex 3		Test if Map contains the key i am asking for.
	 * @throws Exception
	 */
	@Test
	public void getMapPerProducer() throws Exception {
		Map<String, List<Item>> map = shopService.getMapOfAllItemsPerProducer();
		assertTrue(map.containsKey("Producer2"));
	}

	/**
	 * ex 4		Test if the Map returns expected size per key.
	 * @throws Exception
	 */
	@Test
	public void getMapPerInStock() throws Exception {
		Map<Boolean, List<Item>> map = shopService.getMapOfAllItemsPerStock();
		assertEquals(11, map.get(false).size());
		assertEquals(0, map.get(true).size());
	}

	/**
	 * ex 5 	Test if i can find an item by id.
	 * @throws Exception
	 */
	@Test
	public void getAnItemById() throws Exception {
		assertNotNull(shopService.getItemById(2001));
	}

	/**
	 * ex 5 	test if method throws exception as it should
 	 * @throws Exception
	 */
	@Test
	public void getAnItemByIdThatDoesNotExist() throws Exception {
		expected.expect(NoItemFoundForCriteriaException.class);
		expected.expectMessage("No items were found for the given search criteria.");
		shopService.getItemById(1000);
	}

	/**
	 * ex 6 	Create a string of all producers separated by: X
	 * @throws Exception
	 */
	@Test
	public void getStringOfProducers() throws Exception {
		String producers = shopService.getStringOfAllProducers();
		assertTrue(producers.contains("X"));
	}


	/**
	 * ex 7		Test if list of locations with more than X in stock is correct
	 * @throws Exception
	 */
	@Test
	public void getListOfAllLocationsWithMoreThanXInStock() throws Exception {
		List<ItemLocation> locations = shopService.getListOfAllLocationsWithMoreThanXInStock(10);
		assertEquals(2, locations.size());
	}


	/**
	 * ex 8		Test if list of locations with less than X in stock is correct
	 * @throws Exception
	 */
	@Test
	public void getListOfAllLocationsWithLessThanXInStock() throws Exception {
		List<ItemLocation> locations = shopService.getListOfAllLocationsWithLessThanXInStock(10);
		assertEquals(2, locations.size());
	}


	/**
	 * ex 9		Test if you can get list of items in location X with amount more than Y in stock, and throws
	 * 			exception if value is not found.
	 * @throws Exception
	 */
	@Test
	public void getListOfItemsInLocationXWithMoreThanY() throws Exception {
		assertNotNull(shopService.getItemsInLocationXWithMoreThanYInStock(ItemLocation.OSLO, 10));
		assertEquals(2, shopService.getItemsInLocationXWithMoreThanYInStock(ItemLocation.OSLO, 10).size());


		expected.expect(NoItemFoundForCriteriaException.class);
		expected.expectMessage("No items were found for the given search criteria.");
		shopService.getItemsInLocationXWithMoreThanYInStock(ItemLocation.OSLO, 200);
	}

	/**
	 * ex 10	Test if you can get list of items in location X with amount less than Y stock, and throws
	 * 			exception if value is not found.
	 * @throws Exception
	 */
	@Test
	public void getListOfItemsInLocationXWithLessThanY() throws Exception {
		assertNotNull(shopService.getItemsInLocationXWithLessThanYInStock(ItemLocation.OSLO, 10));
		assertEquals(4, shopService.getItemsInLocationXWithLessThanYInStock(ItemLocation.OSLO, 10).size());

		expected.expect(NoItemFoundForCriteriaException.class);
		expected.expectMessage("No items were found for the given search criteria.");
		shopService.getItemsInLocationXWithLessThanYInStock(ItemLocation.OSLO, 1);
	}


	/**
	 * ex 11 	Test if this method can find the one product name starting with "X"
	 * @throws Exception
	 */
	@Test
	public void getItemsWithNameStartingWithX() throws Exception {
		//Testing with ShopTestUtil
		assertEquals(1, shopService.getItemsWithNameStartingWith("X").size());
		assertEquals(10, shopService.getItemsWithNameStartingWith("T").size());

		//Testing with Database-class
		assertEquals(1, bigShopService.getItemsWithNameStartingWith("X").size());
	}


	/**
	 * ex 12	Test if average stock of items in Oslo is 28.6
	 * @throws Exception
	 */
	@Test
	public void getAverageItemStockForLocationX() throws Exception {
		double average = shopService.getAverageItemStockForLocationX(ItemLocation.OSLO);
		assertTrue(average == 24.0);
	}


	/**
	 * ex 13 	find item with most in stock
	 * @throws Exception
	 */
	@Test
	public void getItemWithMostInStock() throws Exception {
		Item item = shopService.getItemWithMostInStock();
		assertTrue(item.getItemID() == 2007);
	}


	/**
	 * ex 14 	find item with least in stock
	 * @throws Exception
	 */
	@Test
	public void getItemsWithLeastInStock() throws Exception {
		Item item = shopService.getItemWithLeastInStock();
		assertTrue(item.getItemID() == 2001);
	}


	/**
	 * ex 15	Testing for stock higher than Y in location X.
	 * @throws Exception
	 */
	@Test
	public void getItemsInLocationXStockHigherThanY() throws Exception {
		List<Item> items = shopService.getItemsInLocationXStockHigherThan(ItemLocation.OSLO, 10);
		assertEquals(2, items.size());
	}


	/**
	 * ex 16	Getting sorted list by producer name.
	 * 			Comparing String so does not work so good when only difference in producer-name is a number.
	 * @throws Exception
	 */
	@Test
	public void getAllItemsSortedByProducer() throws Exception {
		List<Item> sortedList = shopService.getItemsSortedByProducer();
		assertTrue(sortedList.get(10).getItemID() == 2006);
	}


	/**
	 * ex 17	Getting list sorted by name.
	 * @throws Exception
	 */
	@Test
	public void getAllItemsSortedByName() throws Exception {
		List<Item> sortedList = shopService.getItemsSortedByName();
		assertTrue(sortedList.get(10).getItemID() == 2010);
	}


	/**
	 * ex 18 	Getting a list of all items sorted by stock value high to low
	 * @throws Exception
	 */
	@Test
	public void getAllItemsSortedByStock() throws Exception {
		List<Item> items = shopService.getItemsSortedByStock();
		assertTrue(items.get(0).getItemID() == 2007);
	}


	/**
	 * ex 19	Getting a list of all items but without duplicates,
	 * 			so if one object is equal on every parameter it will not be added to this list.
	 * @throws Exception
	 */
	@Test
	public void getAllItemsWithoutDuplicates() throws Exception {
		List<Item> items = shopService.getAllDistinctItems();
		assertEquals(10, items.size());
	}


	/**
	 * ex 20	Getting list of items, this list contains two sublist.
	 * 			sublist 1 is a list of items with ID between a and b,
	 * 			same goes for sublist 2.
	 * @throws Exception
	 */
	@Test
	public void getListOfTwoSubLists() throws Exception {
		List<Item> items = shopService.getListFromTwoSublistByIndexValues(2001, 2002, 2006, 2008);
		assertEquals(6, items.size());
	}


	/**
	 * ex 21	Get list of items with three sub-lists containing all
	 * 			items with that parameter: location, type and producer.
	 * 			Test if list contains two elements with this parameter.
	 * @throws Exception
	 */
	@Test
	public void getListOfItemsByLocationTypeAndProducer() throws Exception {
		ItemLocation location = ItemLocation.OSLO;
		ItemType type = ItemType.ELECTRONICS;
		String producer = "Producer2";
		List<Item> items = shopService.getListOfItmesByLocationTypeAndProducer(location, type, producer);
		assertEquals(6, items.size());
	}


	/**
	 * ex 22	Get total stock for all items.
	 * 			Test if 307 is correct
	 * @throws Exception
	 */
	@Test
	public void getTotalInStockForAllItems() throws Exception {
		int totalStock = shopService.getTotalStock();
		assertEquals(307, totalStock);
	}
}