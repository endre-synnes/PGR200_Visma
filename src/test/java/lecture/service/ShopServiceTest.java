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

	//Oppgave 1
	@Test
	public void getMapPerLocation() throws Exception {
		Map<ItemLocation, List<Item>> map = shopService.getMapOfAllItemsPerLocation();
		//map.forEach((k, v) -> System.out.println(k + v));
		assertTrue(map.containsKey(ItemLocation.valueOf("OSLO")));
		assertEquals(2, map.size());
	}

	//Oppgave 2
	@Test
	public void getMapPerType() throws Exception {
		Map<ItemType, List<Item>> map = shopService.getMapOfAllItemsPerType();
		assertTrue(map.containsKey(ItemType.valueOf("BEVERAGE")));
		assertEquals(3, map.size());
	}

	//Oppgave 3
	@Test
	public void getMapPerProducer() throws Exception {
		Map<String, List<Item>> map = shopService.getMapOfAllItemsPerProducer();
		assertTrue(map.containsKey("Producer2"));
	}

	//Oppgave 4
	@Test
	public void getMapPerInStock() throws Exception {
		Map<Boolean, List<Item>> map = shopService.getMapOfAllItemsPerStock();
		assertEquals(10, map.get(false).size());
		assertEquals(0, map.get(true).size());
	}

	/** ex 5 */
	@Test
	public void getAnItemById() throws Exception {
		assertNotNull(shopService.getItemById(2001));
	}

	/** ex 5 test if method throws exception as it should */
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
		assertEquals(3, shopService.getItemsInLocationXWithLessThanYInStock(ItemLocation.OSLO, 10).size());

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
		assertEquals(9, shopService.getItemsWithNameStartingWith("T").size());

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
		assertTrue(average == 28.6);
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
}