package application;

/**
 * Creates a Product, implements Item
 */
public class Product implements Item {

    private int Id;
    public String Type;
    private String Manufacturer;
    private String Name;
    private ItemType itemType;

    /**
     *
     */
    Product() {
    }

    /**
     * Constructs a product
     *
     * @param Id product id
     * @param name product name
     * @param manufacturer product manufacturer
     * @param itemType product item type
     */
    Product(int Id, String name, String manufacturer, ItemType itemType) {
        this.Id = Id;
        this.Name = name;
        this.Manufacturer = manufacturer;
        this.itemType = itemType;
    }

    /**
     * Constructs a product without id
     *
     * @param name product name
     * @param manufacturer product manufacturer
     * @param itemType product item type
     */
    Product(String name, String manufacturer, ItemType itemType) {

        this.Name = name;
        this.Manufacturer = manufacturer;
        this.itemType = itemType;
    }

    /**
     * returns the product details
     *
     * @return
     */
    public String toString() {
        return "Name: " + Name + "\n" + "Manufacturer: " + Manufacturer + "\n" + "Type: "
                + itemType;
    }

    /**
     * returns id
     *
     * @return Id
     */
    public int getId() {
        return Id;
    }

    /**
     * returns manufacturer
     *
     * @return manufacturer
     */
    public String getManufacturer() {
        return Manufacturer;
    }

    /**
     * sets manufacturer
     *
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    /**
     * returns name
     *
     * @return name
     */
    public String getName() {
        return Name;
    }

    /**
     * sets name
     *
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * sets item type
     *
     * @return itemType
     */
    public ItemType getItemType() {
        return itemType;
    }
}

class Widget extends Product {
    Widget(int Id, String name, String manufacturer, ItemType type) {

        super(Id, name, manufacturer, type);
    }
}
