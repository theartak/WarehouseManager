package Warehouse;

public enum ItemType {
    STEEL(50),
    IRON(100),
    COPPER(150),
    ORE(300);

    private final int maxCapacity;

    ItemType(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}