package lecture.repository;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;
import com.visma.lecture.repository.ShopRepository;
import lecture.util.ShopTestUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Leo-Andreas Ervik
 */
public class ShopRepositoryTest {
	
	@Rule
	public ExpectedException expected = ExpectedException.none();

	private List<Item> items;
	private ShopRepository shopRepository;

	@Before
	public void setUp() throws Exception {
		items = new ShopTestUtil().getItems();
		shopRepository = new ShopRepository(items);
	}



	@Test
	public void findItemById() throws Exception {
		assertThat(shopRepository.findItemById(2001), is(items.get(0)));
	}

	@Test
	public void shouldReturnNullNotFoundById() throws Exception {
		assertThat(shopRepository.findItemById(1), nullValue());
	}
	
	@Test
	public void add() throws Exception {
		Integer size = items.size();
		Boolean add = shopRepository.create(new Item(2007, "Producer11 Test12", ItemLocation.SARPSBORG, ItemType.CLOTHING, 1));
		
		assertThat(add, is(Boolean.TRUE));
		assertThat(items.size(), is(size + 1)); //replace with .getAll() when implemented
		assertThat(shopRepository.findItemById(2007).getItemName(), is("Producer11 Test12"));
	}

	@Test
	public void update() throws Exception {
		Boolean update = shopRepository.update(new Item(2001, "Producer10 Test1", ItemLocation.OSLO, ItemType.BEVERAGE, 10));
		
		assertThat(update, is(Boolean.TRUE));
		assertThat(shopRepository.findItemById(2001).getStock(), is(10));
		assertThat(shopRepository.findItemById(2001).getItemName(), is("Producer10 Test1"));
	}

	@Test
	public void remove() throws Exception {
		Boolean remove = shopRepository.delete(2001);
		
		assertThat(remove, is(Boolean.TRUE));
		assertThat(items.size(), is(10)); //replace with .getAll() when implemented
		assertThat(shopRepository.findItemById(2001), nullValue());
	}


	//Oppg 2
	@Test
	public void getListOfAllItems() throws Exception {
		Integer size = items.size();
		List<Item> itemsFromRepo = shopRepository.getAllItems();
		assertThat(size, is(itemsFromRepo.size()));
	}

	//Oppg 3
	@Test
	public void listOfItemsInRangeIsTheSame() throws Exception {
		List<Item> itemsInRange = shopRepository.getItemsInRange(2001, 2003);
		assertEquals(4, itemsInRange.size());
	}

	//Oppg 4
	@Test
	public void getItemsPerLocation() throws Exception {
		ItemLocation cityName = ItemLocation.valueOf("OSLO");
		List<Item> itemsInOslo = shopRepository.getItemsPerLocation(cityName);
		assertEquals(6, itemsInOslo.size());
	}


	//Oppg 5
	@Test
	public void getItemsPerType() throws Exception {
		ItemType typeName = ItemType.ELECTRONICS;
		List<Item> electronicItems = shopRepository.getItemsPerType(typeName);
		assertEquals(3, electronicItems.size());
	}

	//Oppg 6
	@Test
	public void getItemsPerProducer() throws Exception {
		String producer = "Producer3";
		List<Item> itemsFromHugoBoss = shopRepository.getItemsPerProducer(producer);
		assertEquals(1, itemsFromHugoBoss.size());
	}
}