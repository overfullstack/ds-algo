package tbd;

import java.util.*;

/**
 * Inventory Optimization Problem: Given inventory with quantities and vendor orders, serve orders
 * to maximize inventory usage. Can only serve orders if order quantity <= available inventory
 * quantity.
 */
public class InventoryOptimizer {

	static void main() {
		// Test case 1: Basic scenario
		Map<String, Integer> inventory1 =
				Map.of(
						"apple", 10,
						"banana", 5,
						"orange", 8);
		List<Order> orders1 =
				List.of(
						new Order("apple", 3),
						new Order("banana", 5),
						new Order("apple", 7),
						new Order("orange", 4),
						new Order("banana", 2));

		System.out.println("Test Case 1:");
		System.out.println("Initial inventory: " + inventory1);
		System.out.println("Orders: " + orders1);

		OptimizationResult result1 = optimizeInventoryUsage(inventory1, orders1);
		System.out.println("Served orders: " + result1.servedOrders());
		System.out.println("Remaining inventory: " + result1.remainingInventory());
		System.out.println("Total inventory used: " + result1.totalInventoryUsed());
		System.out.println();

		// Test case 2: Edge case with insufficient inventory
		Map<String, Integer> inventory2 =
				Map.of(
						"item1", 2,
						"item2", 1);
		List<Order> orders2 =
				List.of(new Order("item1", 5), new Order("item2", 1), new Order("item1", 2));

		System.out.println("Test Case 2:");
		System.out.println("Initial inventory: " + inventory2);
		System.out.println("Orders: " + orders2);

		OptimizationResult result2 = optimizeInventoryUsage(inventory2, orders2);
		System.out.println("Served orders: " + result2.servedOrders());
		System.out.println("Remaining inventory: " + result2.remainingInventory());
		System.out.println("Total inventory used: " + result2.totalInventoryUsed());
		System.out.println();

		// Test case 3: Compare TreeSet approach with same data as Test Case 1
		System.out.println("Test Case 3 - TreeSet Approach:");
		System.out.println("Initial inventory: " + inventory1);
		System.out.println("Orders: " + orders1);

		OptimizationResult result3 = optimizeWithTreeSet(inventory1, orders1);
		System.out.println("Served orders (TreeSet): " + result3.servedOrders());
		System.out.println("Remaining inventory: " + result3.remainingInventory());
		System.out.println("Total inventory used: " + result3.totalInventoryUsed());
		System.out.println(
				"Same result as Stream approach: "
						+ (result1.totalInventoryUsed() == result3.totalInventoryUsed()));
		System.out.println();

		// Test case 4: TreeSet with floor() - demonstrating capacity-based order selection
		Map<String, Integer> inventory4 =
				Map.of(
						"widget", 15,
						"gadget", 8);
		List<Order> orders4 =
				List.of(
						new Order("widget", 20), // Too large, won't fit
						new Order("widget", 12), // Will fit
						new Order("widget", 4), // Will fit after 12
						new Order("gadget", 10), // Too large
						new Order("gadget", 6), // Will fit
						new Order("gadget", 3) // Will fit after 6
						);

		System.out.println("Test Case 4 - TreeSet Floor Approach:");
		System.out.println("Initial inventory: " + inventory4);
		System.out.println("Orders: " + orders4);

		/*OptimizationResult result4 = optimizeWithTreeSetFloor(inventory4, orders4);
		System.out.println("Served orders (Floor): " + result4.servedOrders());
		System.out.println("Remaining inventory: " + result4.remainingInventory());
		System.out.println("Total inventory used: " + result4.totalInventoryUsed());*/
	}

	/**
	 * Optimizes inventory usage by serving orders greedily to maximize total inventory consumption.
	 * Strategy: Sort orders by quantity in descending order to prioritize larger orders.
	 *
	 * @param initialInventory Map of item -> available quantity
	 * @param orders List of vendor orders
	 * @return OptimizationResult containing served orders and remaining inventory
	 */
	public static OptimizationResult optimizeInventoryUsage(
			Map<String, Integer> initialInventory, List<Order> orders) {

		// Create mutable copy of inventory for processing
		Map<String, Integer> currentInventory = new HashMap<>(initialInventory);

		// Sort orders by quantity in descending order to maximize inventory usage
		List<Order> sortedOrders =
				orders.stream().sorted(Comparator.comparingInt(Order::quantity).reversed()).toList();

		// Process orders greedily using functional approach
		List<Order> servedOrders = new ArrayList<>();
		int totalInventoryUsed =
				sortedOrders.stream()
						.mapToInt(
								order -> {
									int availableQuantity = currentInventory.getOrDefault(order.item(), 0);

									if (availableQuantity >= order.quantity()) {
										// Can fulfill the order
										servedOrders.add(order);
										currentInventory.merge(order.item(), -order.quantity(), Integer::sum);

										// Remove items with zero quantity for cleaner output
										if (currentInventory.get(order.item()) == 0) {
											currentInventory.remove(order.item());
										}

										return order.quantity();
									}
									return 0;
								})
						.sum();

		return new OptimizationResult(
				List.copyOf(servedOrders), Map.copyOf(currentInventory), totalInventoryUsed);
	}

	/**
	 * TreeSet-based approach: Automatically sorts orders by quantity (descending) and breaks ties by
	 * item name. TreeSet maintains sorted order without manual sorting, making the algorithm cleaner.
	 */
	public static OptimizationResult optimizeWithTreeSet(
			Map<String, Integer> initialInventory, List<Order> orders) {

		Map<String, Integer> currentInventory = new HashMap<>(initialInventory);

		// TreeSet with custom comparator: quantity descending, then item name ascending for
		// deterministic ordering
		TreeSet<Order> orderedSet =
				new TreeSet<>(
						Comparator.<Order>comparingInt(Order::quantity).reversed().thenComparing(Order::item));
		orderedSet.addAll(orders);

		List<Order> servedOrders = new ArrayList<>();
		int totalInventoryUsed = 0;

		// TreeSet automatically provides orders in optimal order (largest quantity first)
		for (Order order : orderedSet) {
			int availableQuantity = currentInventory.getOrDefault(order.item(), 0);

			if (availableQuantity >= order.quantity()) {
				servedOrders.add(order);
				currentInventory.merge(order.item(), -order.quantity(), Integer::sum);
				totalInventoryUsed += order.quantity();

				if (currentInventory.get(order.item()) == 0) {
					currentInventory.remove(order.item());
				}
			}
		}

		return new OptimizationResult(
				List.copyOf(servedOrders), Map.copyOf(currentInventory), totalInventoryUsed);
	}

	/**
	 * Advanced TreeSet approach using floor() method for efficient capacity-based order selection.
	 * Groups orders by item and uses floor() to find the largest order that fits within capacity.
	 * More efficient when there are many orders for the same item with different quantities.
	 */
	/*public static OptimizationResult optimizeWithTreeSetFloor(
					Map<String, Integer> initialInventory,
					List<Order> orders) {

			Map<String, Integer> currentInventory = new HashMap<>(initialInventory);

			// Group orders by item, each item has a TreeSet of quantities (descending order)
			Map<String, TreeSet<Integer>> ordersByItem = orders.stream()
					.collect(Collectors.groupingBy(
							Order::item,
							Collectors.mapping(
									Order::quantity,
									Collectors.toCollection(() -> new TreeSet<>(Comparator.reverseOrder()))
							)
					));

			List<Order> servedOrders = new ArrayList<>();
			int totalInventoryUsed = 0;

			// Process each item's inventory and find best fitting orders
			for (var entry : ordersByItem.entrySet()) {
					String item = entry.getKey();
					TreeSet<Integer> quantities = entry.getValue();
					int availableQuantity = currentInventory.getOrDefault(item, 0);

					// Use floor() to efficiently find largest quantity that fits within capacity
					while (availableQuantity > 0 && !quantities.isEmpty()) {
							Integer bestQuantity = quantities.floor(availableQuantity);

							if (bestQuantity != null) {
									// Found an order that fits within capacity
									Order servedOrder = new Order(item, bestQuantity);
									servedOrders.add(servedOrder);

									availableQuantity -= bestQuantity;
									totalInventoryUsed += bestQuantity;

									// Remove this quantity to avoid serving same order twice
									quantities.remove(bestQuantity);
							} else {
									// No more orders fit within remaining capacity for this item
									break;
							}
					}

					// Update inventory with remaining quantity
					if (availableQuantity > 0) {
							currentInventory.put(item, availableQuantity);
					} else {
							currentInventory.remove(item);
					}
			}

			return new OptimizationResult(
					List.copyOf(servedOrders),
					Map.copyOf(currentInventory),
					totalInventoryUsed
			);
	}*/

	/**
	 * Alternative approach: Using priority queue to serve highest quantity orders first. This ensures
	 * we maximize inventory usage by prioritizing larger orders.
	 */
	public static OptimizationResult optimizeWithPriorityQueue(
			Map<String, Integer> initialInventory, List<Order> orders) {

		Map<String, Integer> currentInventory = new HashMap<>(initialInventory);

		// Priority queue to prioritize orders by quantity (max heap)
		PriorityQueue<Order> orderQueue =
				new PriorityQueue<>(Comparator.comparingInt(Order::quantity).reversed());
		orderQueue.addAll(orders);

		List<Order> servedOrders = new ArrayList<>();
		int totalInventoryUsed = 0;

		while (!orderQueue.isEmpty()) {
			Order order = orderQueue.poll();
			int availableQuantity = currentInventory.getOrDefault(order.item(), 0);

			if (availableQuantity >= order.quantity()) {
				servedOrders.add(order);
				currentInventory.merge(order.item(), -order.quantity(), Integer::sum);
				totalInventoryUsed += order.quantity();

				if (currentInventory.get(order.item()) == 0) {
					currentInventory.remove(order.item());
				}
			}
		}

		return new OptimizationResult(
				List.copyOf(servedOrders), Map.copyOf(currentInventory), totalInventoryUsed);
	}

	/** Represents a vendor order for a specific item and quantity. */
	public record Order(String item, int quantity) {
		@Override
		public String toString() {
			return String.format("Order{%s: %d}", item, quantity);
		}
	}

	/** Result of inventory optimization containing served orders and remaining state. */
	public record OptimizationResult(
			List<Order> servedOrders, Map<String, Integer> remainingInventory, int totalInventoryUsed) {}
}
