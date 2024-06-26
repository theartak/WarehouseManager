package Warehouse;

import Exceptions.InsufficientItemException;
import Exceptions.InventoryFullException;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<ItemType, Integer> items;

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
            System.out.println("\nAdded " + item.getName() + " to warehouse.");
        }
    }

    // Remove an item from the inventory
    public void removeItem(Items item, int amount) throws InsufficientItemException {
        int currentAmount = items.get(item.getType());
        if (currentAmount >= amount) {
            items.put(item.getType(), currentAmount - amount);
            System.out.println("\nRemoved " + item.getName() + " from inventory.");
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
            System.out.println("\nTransferred " + amount + " " + item.getName() + " to another warehouse.");
        } else {
            System.out.println("\nInsufficient " + item.getName() + " in warehouse.");
        }
    }

    // Print the contents of the warehouse
    public void displayWarehouse() {
        System.out.println("Warehouse:");
        for (Map.Entry<ItemType, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Display the current warehouse data
    public static void displayWarehouseData(Warehouse[] warehouses) {
        System.out.println("\nWarehouse Data:");
        for (int i = 0; i < warehouses.length; i++) {
            System.out.println("\nWarehouse " + (i + 1) + ":");
            warehouses[i].displayWarehouse();
            System.out.println();
        }
    }
}