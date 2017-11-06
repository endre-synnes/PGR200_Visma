package lecture.util;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;

import java.util.ArrayList;
import java.util.List;

public class ShopTestUtil {
	
	private final List<Item> items;
	
	public ShopTestUtil(){
		items = new ArrayList<>();
		items.add(new Item(2001, "Producer1 Test1", ItemLocation.OSLO, ItemType.BEVERAGE, 1));
		items.add(new Item(2002, "Producer2 Test2", ItemLocation.OSLO, ItemType.ELECTRONICS, 1));
		items.add(new Item(2003, "Producer3 Test3", ItemLocation.OSLO, ItemType.CLOTHING, 1));
		items.add(new Item(2004, "Producer4 Test4", ItemLocation.HAMAR, ItemType.BEVERAGE, 1));
		items.add(new Item(2005, "Producer5 Test5", ItemLocation.HAMAR, ItemType.ELECTRONICS, 1));
		items.add(new Item(2006, "Producer6 Test7", ItemLocation.HAMAR, ItemType.CLOTHING, 1));
		items.add(new Item(2007, "Producer11 Test12", ItemLocation.HAMAR, ItemType.CLOTHING, 100));
		items.add(new Item(2008, "Producer11 Test15", ItemLocation.HAMAR, ItemType.CLOTHING, 60));
		items.add(new Item(2010, "Producer13 Xest17", ItemLocation.OSLO, ItemType.BEVERAGE, 70));
		items.add(new Item(2009, "Producer12 Test16", ItemLocation.OSLO, ItemType.BEVERAGE, 70));
	}
	
	public List<Item> getItems() {
		return items;
	}
}
