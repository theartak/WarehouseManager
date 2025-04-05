package warehouse;

public class Items {
    private String name;
    private String description;
    private String icon;
    private ItemType type;

    public Items() {

    }

    public Items(String name, String description, String icon, ItemType type) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}