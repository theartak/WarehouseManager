package main;

import exceptions.InsufficientItemException;
import exceptions.InventoryFullException;
import warehouse.Items;
import warehouse.Warehouse;
import warehouse.ItemType;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InventoryFullException {

        final String ironDescription = "Used for crafting and upgrading weapons and buildings.";
        final String steelDescription = "Used for crafting and upgrading vehicles.";
        final String copperDescription = "Used for crafting and upgrading electronics";
        final String oreDescription = "Used for melting ore into iron, steel, or copper";

        final String ironIcon = "iron_icon.png";
        final String steelIcon = "steel_icon.png";
        final String copperIcon = "copper_icon.png";
        final String oreIcon = "ore_icon.png";

        // Create some items
        Items iron = new Items("Iron", ironDescription, ironIcon, ItemType.IRON);
        Items steel = new Items("Steel", steelDescription, steelIcon, ItemType.STEEL);
        Items copper = new Items("Copper", copperDescription, copperIcon, ItemType.COPPER);

        // Create inventories/warehouses
        Warehouse playerWarehouse = new Warehouse();
        Warehouse warehouse1 = new Warehouse();
        Warehouse warehouse2 = new Warehouse();
        Warehouse warehouse3 = new Warehouse();

        // Add items to player's warehouse
        try {
            playerWarehouse.addItem(iron, 5);
            playerWarehouse.addItem(steel, 4);
            playerWarehouse.addItem(copper, 5);
        } catch (InventoryFullException e) {
            logger.info(e.getMessage());
        }

        // Remove an item from player's inventory
        try {
            playerWarehouse.removeItem(iron, 1);
        } catch (InsufficientItemException e) {
            logger.info(e.getMessage());
        }

        // Display player's warehouse
        playerWarehouse.displayWarehouse();

        // Transfer items from player to merchant
        try {
            playerWarehouse.transferItems(warehouse1, iron, 1);
            playerWarehouse.transferItems(warehouse2, steel, 1);
            playerWarehouse.transferItems(warehouse3, copper, 1);
        } catch (InventoryFullException e) {
            logger.info(e.getMessage());
        }

        // Display player's and merchant's inventory after transfer
        logger.info("\nPlayer's Warehouse:");
        playerWarehouse.displayWarehouse();
        logger.info("\nNew Warehouse 1:");
        warehouse1.displayWarehouse();
        logger.info("\nNew Warehouse 2:");
        warehouse2.displayWarehouse();
        logger.info("\nNew Warehouse 3:");
        warehouse3.displayWarehouse();

        // Populate warehouses
        try {
            warehouse1.addItem(new Items("Iron", ironDescription, ironIcon, ItemType.IRON), 5);
            warehouse2.addItem(new Items("Ore", oreDescription, oreIcon, ItemType.ORE), 3);
            warehouse3.addItem(new Items("Copper", copperDescription, copperIcon, ItemType.COPPER), 30);
        } catch (InventoryFullException e) {
            logger.info(e.getMessage());
        }

        try {
            warehouse2.removeItem(iron, 3);
            warehouse3.removeItem(copper, 11);
        } catch (InsufficientItemException e) {
            logger.info(e.getMessage());
        }

        // Display warehouse data
        Warehouse.displayWarehouseData(new Warehouse[]{warehouse1, warehouse2, warehouse3});
    }
}