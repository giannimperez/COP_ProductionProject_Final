package application;

import java.util.Date;

/**
 * Creates a ProductionRecord of an existing product
 */
public class ProductionRecord {

    //Fields
    public int productionNumber, productID, itemCount;
    public String serialNumber;
    public Date dateProduced;
    public Product productProduced;

    /**
     *  Constructs ProductionRecord with productID
     *
     * @param productID
     */
    public ProductionRecord(int productID) {
        this.productID = productID;
        productionNumber = 0;
        serialNumber = "0";
        Date Date = new Date();
    }

    /**
     * Constructs ProductionRecord with itemCount and Product
     *
     * @param productProduced
     * @param itemCount
     */
    public ProductionRecord(Product productProduced, int itemCount) {
        this.productProduced = productProduced;
        this.itemCount = itemCount;
        //String test = productProduced.getItemType().getLabel();
        //System.out.println(test);
        serialNumber = productProduced.getManufacturer().substring(0, 3) + productProduced.getItemType().getLabel() +
                String.format("%05d", itemCount);

    }

    /**
     * Constructs ProductionRecord with all params
     *
     * @param productionNumber
     * @param productID
     * @param serialNumber
     * @param dateProduced
     */
    public ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
        this.productID = productID;
        this.productionNumber = productionNumber;
        this.serialNumber = serialNumber;
        this.dateProduced = dateProduced;
    }

    //Setters

    /**
     *
     * @param productionNumber
     */
    public void setProductionNum(int productionNumber) {
        this.productionNumber = productionNumber;
    }

    /**
     * sets productID
     *
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * sets serialNum
     *
     * @param serialNumber
     */
    public void setSerialNum(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * sets dateProduced
     *
     * @param dateProduced
     */
    public void setProdDate(Date dateProduced) {
        this.dateProduced = dateProduced;
    }

    /**
     * sets productProduced
     *
     * @param productProduced
     */
    public void setProductProduced(Product productProduced) {
        this.productProduced = productProduced;
    }

    //Getters

    /**
     * returns productionNumber
     *
     * @return productionNumber
     */
    public int getProductionNum() {
        return productionNumber;
    }

    /**
     * returns productID
     *
     * @return productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * returns serialNumber
     *
     * @return serialNumber
     */
    public String getSerialNum() {
        return serialNumber;
    }

    /**
     * returns dateProduced
     *
     * @return dateProduced
     */
    public Date getProdDate() {
        return dateProduced;
    }

    /**
     * returns productProduced
     *
     * @return productProduced
     */
    public Product getProductProduced() {
        return productProduced;
    }

    /**
     * returns productionRecord details
     *
     * @return productionRecord details
     */
    @Override
    public String toString() {
        return ("Prod. Num: " + productionNumber + " Product ID: " + productID + " Serial Num: " + serialNumber + " Date: " + dateProduced + "\n");
    }
}