package com.visma.lecture.common.database;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;

import java.util.ArrayList;
import java.util.List;

/**
 * Database class containing all database entries.
 *
 * @author Leo-Andreas Ervik
 */
public class Database {
	
	public static List<Item> itemTable = new ArrayList<>();
	
	private Database() {
	}
	
	static {
		/* OSLO */
		itemTable.add(new Item(1, "DELL LAPTOP", ItemLocation.OSLO, ItemType.ELECTRONICS, 2237));
		itemTable.add(new Item(2, "ACER LAPTOP", ItemLocation.OSLO, ItemType.ELECTRONICS, 5207));
		itemTable.add(new Item(3, "LENOVO LAPTOP", ItemLocation.OSLO, ItemType.ELECTRONICS, 399));
		itemTable.add(new Item(4, "MAC_BOOK LAPTOP", ItemLocation.OSLO, ItemType.ELECTRONICS, 6725));
		itemTable.add(new Item(5, "SAMSUNG LAPTOP", ItemLocation.OSLO, ItemType.ELECTRONICS, 5237));
		itemTable.add(new Item(6, "D_&_G SCARF", ItemLocation.OSLO, ItemType.CLOTHING, 4450));
		itemTable.add(new Item(7, "H_&_M JACKET", ItemLocation.OSLO, ItemType.CLOTHING, 8578));
		itemTable.add(new Item(8, "HUGO_BOSS TROUSERS", ItemLocation.OSLO, ItemType.CLOTHING, 8994));
		itemTable.add(new Item(9, "ALLSAINTS GLOVES", ItemLocation.OSLO, ItemType.CLOTHING, 9290));
		itemTable.add(new Item(10, "BULA TSHIRT", ItemLocation.OSLO, ItemType.CLOTHING, 91));
		itemTable.add(new Item(11, "BUDWEISER PILSNER", ItemLocation.OSLO, ItemType.BEVERAGE, 3385));
		itemTable.add(new Item(12, "SAGENE PILSNER", ItemLocation.OSLO, ItemType.BEVERAGE, 5057));
		itemTable.add(new Item(13, "PERONI PILSNER", ItemLocation.OSLO, ItemType.BEVERAGE, 4041));
		itemTable.add(new Item(14, "CORONA PILSNER", ItemLocation.OSLO, ItemType.BEVERAGE, 9772));
		itemTable.add(new Item(15, "CARLSBERG PILSNER", ItemLocation.OSLO, ItemType.BEVERAGE, 2350));
		/* SARPSBORG */
		itemTable.add(new Item(16, "DELL PC", ItemLocation.SARPSBORG, ItemType.ELECTRONICS, 460));
		itemTable.add(new Item(17, "ACER PC", ItemLocation.SARPSBORG, ItemType.ELECTRONICS, 4240));
		itemTable.add(new Item(18, "LENOVO PC", ItemLocation.SARPSBORG, ItemType.ELECTRONICS, 8497));
		itemTable.add(new Item(19, "ASUS PC", ItemLocation.SARPSBORG, ItemType.ELECTRONICS, 3683));
		itemTable.add(new Item(20, "SAMSUNG PC", ItemLocation.SARPSBORG, ItemType.ELECTRONICS, 1619));
		itemTable.add(new Item(21, "D_&_G JACKET", ItemLocation.SARPSBORG, ItemType.CLOTHING, 8340));
		itemTable.add(new Item(22, "H_&_M SCARF", ItemLocation.SARPSBORG, ItemType.CLOTHING, 1027));
		itemTable.add(new Item(23, "HUGO_BOSS GLOVES", ItemLocation.SARPSBORG, ItemType.CLOTHING, 4881));
		itemTable.add(new Item(24, "ALLSAINTS TROUSERS", ItemLocation.SARPSBORG, ItemType.CLOTHING, 1217));
		itemTable.add(new Item(25, "BULA BOXERS", ItemLocation.SARPSBORG, ItemType.CLOTHING, 8821));
		itemTable.add(new Item(26, "BUDWEISER IPA", ItemLocation.SARPSBORG, ItemType.BEVERAGE, 1140));
		itemTable.add(new Item(27, "SAGENE IPA", ItemLocation.SARPSBORG, ItemType.BEVERAGE, 8445));
		itemTable.add(new Item(28, "PERONI IPA", ItemLocation.SARPSBORG, ItemType.BEVERAGE, 8783));
		itemTable.add(new Item(29, "CORONA IPA", ItemLocation.SARPSBORG, ItemType.BEVERAGE, 7351));
		itemTable.add(new Item(30, "CARLSBERG IPA", ItemLocation.SARPSBORG, ItemType.BEVERAGE, 1637));
		/* DRAMMEN */
		itemTable.add(new Item(31, "DELL SMARTPHONE", ItemLocation.DRAMMEN, ItemType.ELECTRONICS, 17100));
		itemTable.add(new Item(32, "ACER SMARTPHONE", ItemLocation.DRAMMEN, ItemType.ELECTRONICS, 81999));
		itemTable.add(new Item(33, "LENOVO SMARTPHONE", ItemLocation.DRAMMEN, ItemType.ELECTRONICS, 77357));
		itemTable.add(new Item(34, "APPLE IPHONE", ItemLocation.DRAMMEN, ItemType.ELECTRONICS, 12722));
		itemTable.add(new Item(35, "SAMSUNG SMARTPHONE", ItemLocation.DRAMMEN, ItemType.ELECTRONICS, 89513));
		itemTable.add(new Item(36, "VOLT JACKET", ItemLocation.DRAMMEN, ItemType.CLOTHING, 4545));
		itemTable.add(new Item(37, "H_&_M TROUSERS", ItemLocation.DRAMMEN, ItemType.CLOTHING, 24366));
		itemTable.add(new Item(38, "XXL RUNNING_SHOES", ItemLocation.DRAMMEN, ItemType.CLOTHING, 28905));
		itemTable.add(new Item(39, "NIKE SHORTS", ItemLocation.DRAMMEN, ItemType.CLOTHING, 96035));
		itemTable.add(new Item(40, "ADIDAS TSHIRT", ItemLocation.DRAMMEN, ItemType.CLOTHING, 45495));
		itemTable.add(new Item(41, "BUDWEISER LAGER", ItemLocation.DRAMMEN, ItemType.BEVERAGE, 41158));
		itemTable.add(new Item(42, "SAGENE LAGER", ItemLocation.DRAMMEN, ItemType.BEVERAGE, 70741));
		itemTable.add(new Item(43, "PERONI LAGER", ItemLocation.DRAMMEN, ItemType.BEVERAGE, 69437));
		itemTable.add(new Item(44, "CORONA LAGER", ItemLocation.DRAMMEN, ItemType.BEVERAGE, 30772));
		itemTable.add(new Item(45, "CARLSBERG LAGER", ItemLocation.DRAMMEN, ItemType.BEVERAGE, 73415));
		/* LILLEHAMMER */
		itemTable.add(new Item(46, "HUAWEI SMARTPHONE", ItemLocation.LILLEHAMMER, ItemType.ELECTRONICS, 995));
		itemTable.add(new Item(47, "ACER ROUTER", ItemLocation.LILLEHAMMER, ItemType.ELECTRONICS, 234));
		itemTable.add(new Item(48, "DENON RECEIVER", ItemLocation.LILLEHAMMER, ItemType.ELECTRONICS, 600));
		itemTable.add(new Item(49, "APPLE AIRPODS", ItemLocation.LILLEHAMMER, ItemType.ELECTRONICS, 499));
		itemTable.add(new Item(50, "SAMSUNG WASHINGMACHINE", ItemLocation.LILLEHAMMER, ItemType.ELECTRONICS, 158));
		itemTable.add(new Item(51, "VOLT TROUSERS", ItemLocation.LILLEHAMMER, ItemType.CLOTHING, 828));
		itemTable.add(new Item(52, "H_&_M BRA", ItemLocation.LILLEHAMMER, ItemType.CLOTHING, 819));
		itemTable.add(new Item(53, "XXL SHOES", ItemLocation.LILLEHAMMER, ItemType.CLOTHING, 944));
		itemTable.add(new Item(54, "NIKE FOOTBALL_SHOES", ItemLocation.LILLEHAMMER, ItemType.CLOTHING, 722));
		itemTable.add(new Item(55, "ADIDAS SHORTS", ItemLocation.LILLEHAMMER, ItemType.CLOTHING, 1034));
		itemTable.add(new Item(56, "BUDWEISER SAISON", ItemLocation.LILLEHAMMER, ItemType.BEVERAGE, 1018));
		itemTable.add(new Item(57, "SAGENE SAISON", ItemLocation.LILLEHAMMER, ItemType.BEVERAGE, 238));
		itemTable.add(new Item(58, "PERONI SAISON", ItemLocation.LILLEHAMMER, ItemType.BEVERAGE, 1074));
		itemTable.add(new Item(59, "CORONA SAISON", ItemLocation.LILLEHAMMER, ItemType.BEVERAGE, 1281));
		itemTable.add(new Item(60, "CARLSBERG SAISON", ItemLocation.LILLEHAMMER, ItemType.BEVERAGE, 1280));
		/* HAMAR */
		itemTable.add(new Item(61, "DENON RECEIVER", ItemLocation.HAMAR, ItemType.ELECTRONICS, 3496));
		itemTable.add(new Item(62, "EPIPHONE GUITAR", ItemLocation.HAMAR, ItemType.ELECTRONICS, 65));
		itemTable.add(new Item(63, "ELECTROLUX HOOVER", ItemLocation.HAMAR, ItemType.ELECTRONICS, 0));
		itemTable.add(new Item(64, "GOOGLE HOME", ItemLocation.HAMAR, ItemType.ELECTRONICS, 3185));
		itemTable.add(new Item(65, "APPLE ROUTER", ItemLocation.HAMAR, ItemType.ELECTRONICS, 6077));
		itemTable.add(new Item(66, "MARIUS JUMPER_BLUE", ItemLocation.HAMAR, ItemType.CLOTHING, 4119));
		itemTable.add(new Item(67, "MARIUS JUMPER_RED", ItemLocation.HAMAR, ItemType.CLOTHING, 1541));
		itemTable.add(new Item(68, "MARIUS JUMPER_BLACK", ItemLocation.HAMAR, ItemType.CLOTHING, 2680));
		itemTable.add(new Item(69, "MARIUS JUMPER_WHITE", ItemLocation.HAMAR, ItemType.CLOTHING, 2336));
		itemTable.add(new Item(70, "MARIUS JUMPER_NEON", ItemLocation.HAMAR, ItemType.CLOTHING, 1104));
		itemTable.add(new Item(71, "SMIRNOFF VODKA", ItemLocation.HAMAR, ItemType.BEVERAGE, 2803));
		itemTable.add(new Item(72, "BRAASTAD COGNAC", ItemLocation.HAMAR, ItemType.BEVERAGE, 1963));
		itemTable.add(new Item(73, "JACK_DANIELS WHISKEY", ItemLocation.HAMAR, ItemType.BEVERAGE, 5518));
		itemTable.add(new Item(74, "CAPTAIN_MORGAN RUM", ItemLocation.HAMAR, ItemType.BEVERAGE, 2085));
		itemTable.add(new Item(75, "BOMBAY_SAPPHIRE GIN", ItemLocation.HAMAR, ItemType.BEVERAGE, 9208));
		itemTable.add(new Item(76, "BOMBAY_SAPPHIRE XOR", ItemLocation.HAMAR, ItemType.BEVERAGE, 9201));
	}
	
}
