package warehouse;

import exceptions.InsufficientItemException;
import exceptions.InventoryFullException;
import main.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Warehouse {
    private static final Logger logger = Logger.getLogger(Warehouse.class.getName());
    private final Map<ItemType, Integer> items;

    public Warehouse() {
        items = new HashMap<>();
        for (ItemType type : ItemType.values()) {
            items.put(type, 0);
        }
    }

    // Add an item to the warehouse
    public void addItem(Items item, int amount) throws InventoryFullException {
        int currentAmount = items.get(item.getType());
        if (amount > item.getType().getMaxCapacity() || currentAmount > item.getType().getMaxCapacity()) {
            throw new InventoryFullException("\nInventory for " + item.getType() + " is full.");
        } else {
            items.put(item.getType(), currentAmount + amount);
            logger.info("\nAdded " + item.getName() + " to warehouse.");
        }
    }

    // Remove an item from the inventory
    public void removeItem(Items item, int amount) throws InsufficientItemException {
        int currentAmount = items.get(item.getType());
        if (currentAmount >= amount) {
            items.put(item.getType(), currentAmount - amount);
            logger.info("\nRemoved " + item.getName() + " from inventory.");
        } else {
            throw new InsufficientItemException("\nNo " + item.getName() + " in inventory to remove.");
        }
    }

    // Transfer items to another warehouse
    public void transferItems(Warehouse otherWarehouse, Items item, int amount) throws InventoryFullException {
        int currentAmount = items.get(item.getType());
        if (currentAmount >= amount) {
            items.put(item.getType(), currentAmount - amount);
            otherWarehouse.addItem(item, amount);
            logger.info("\nTransferred " + amount + " " + item.getName() + " to another warehouse.");
        } else {
            logger.info("\nInsufficient " + item.getName() + " in warehouse.");
        }
    }

    // Print the contents of the warehouse
    public void displayWarehouse() {
        logger.info("Warehouse:");
        for (Map.Entry<ItemType, Integer> entry : items.entrySet()) {
            logger.info(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Display the current warehouse data
    public static void displayWarehouseData(Warehouse[] warehouses) {
        logger.info("\nWarehouse Data:");
        for (int i = 0; i < warehouses.length; i++) {
            logger.info("\nWarehouse " + (i + 1) + ":");
            warehouses[i].displayWarehouse();
            logger.info("");
        }
    }
}