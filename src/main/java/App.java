import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
	private static String ITEM0013 = "肉夹馍"; //半价
	private static Integer ITEM0013_PRICE = 6;

	private static String ITEM0022 = "凉皮";
	private static Integer ITEM0022_PRICE = 8;

	private static String ITEM0001 = "黄焖鸡"; //半价
	private static Integer ITEM0001_PRICE = 18;

	public static void main(List<String> args) {
		// TODO Auto-generated method stub
		bestCharge(args);
	}

	public static String  bestCharge(List<String> args) {
		String result = "============= 订餐明细 =============\n";
		System.out.println("============= 订餐明细 =============");
		Map<String, String> map = new HashMap<String, String>();
		for (String food : args) {
//			food = food.replace("[", "").replace("]", "");
			String foodName = food.substring(0, food.indexOf("x")).trim();
			String foodCount = food.substring(food.indexOf("x") + 1, food.length()).replace(",", "").trim();
			map.put(foodName, foodCount);
		}
		//1、满30减6（优先）
		Set<Map.Entry<String, String>> entry = map.entrySet();
		int count = 0;
		for (Map.Entry<String, String> stringEntry : entry) {
			String foodKey = stringEntry.getKey();
			Integer it = new Integer(stringEntry.getValue());

			switch (foodKey) {
				case "ITEM0013":
					int count1 = it * ITEM0013_PRICE;
					System.out.println(ITEM0013 + " x " + it + " = " + count1 + "元");
					result = result + "肉夹馍 x 2 = 12元\n";
					count = count + count1;
					break;
				case "ITEM0022":
					int count2 = it * ITEM0022_PRICE;
					System.out.println(ITEM0022 + " x " + it + " = " + count2 + "元");
					result = result +  "凉皮 x 1 = 8元\n";
					count = count + count2;
					break;
				case "ITEM0001":
					int count3 = it * ITEM0001_PRICE;
					System.out.println(ITEM0001 + " x " + it + " = " + count3 + "元");
					result = result+"黄焖鸡 x 1 = 18元\n";
					count = count + count3;
					break;
				default:
					break;
			}

		}
		int discount = plan1(count);
		//2、半价
		int half_Price = plan2(map);
		System.out.println("-----------------------------------");
		result = result + "-----------------------------------\n";
		Boolean b = noDiscount(count, discount, half_Price);
		String result_temp = haveDiscount(count, discount, half_Price, b);
		System.out.println(result+result_temp);
		return result + result_temp;
	}

	private static String haveDiscount(int count, int discount, int half_Price, Boolean b) {
		String result = "";
		if (b) {

		} else {
			int i = count - half_Price;
			if (discount <= half_Price) {
				System.out.println("使用优惠:");
				System.out.println("满30减6元，省6元");
				System.out.println("-----------------------------------");
				System.out.println("总计：" + discount + "元");
				System.out.println("===================================");
				result = result +  "使用优惠:\n" +
						"满30减6元，省6元\n" +
						"-----------------------------------\n" +
						"总计："+discount+"元\n" +
						"===================================";
			} else {
				System.out.println("指定菜品半价(黄焖鸡，凉皮)，省" + i + "元");
				System.out.println("-----------------------------------");
				System.out.println("总计：" + half_Price + "元");
				System.out.println("===================================");
				result = result + "使用优惠:\n" +
						"指定菜品半价(黄焖鸡，凉皮)，省" + i + "元\n" +
						"-----------------------------------\n" +
						"总计："+ half_Price+"元\n" +
						"===================================";
			}
		}
		return result;
	}

	private static Boolean noDiscount(int count, int discount, int half_Price) {
		if (count == discount && half_Price == count) {
			System.out.println("总计：" + count + "元");
			return true;
		} else {
			return false;
		}

	}

	private static int plan2(Map<String, String> map) {
		Set<Map.Entry<String, String>> entry = map.entrySet();
		int count = 0;
		for (Map.Entry<String, String> stringEntry : entry) {
			String foodKey = stringEntry.getKey();
			Integer it = new Integer(stringEntry.getValue());
			switch (foodKey) {
				case "ITEM0013":
					int count1 = it * ITEM0013_PRICE;
					count = count + count1;
					break;
				case "ITEM0022":
					int count2 = it * (ITEM0022_PRICE / 2);
					count = count + count2;
					break;
				case "ITEM0001":
					int count3 = it * (ITEM0001_PRICE / 2);
					count = count + count3;
					break;
				default:
					break;
			}
		}
		return count;
	}

	private static int plan1(int count) {
		if (count > 30) {
			count = count - 6;
			return count;
		} else {
			return count;
		}

	}

}
