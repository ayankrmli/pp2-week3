package week03.invoice;

/**
 * Represents an invoice for an item sold at a hardware store.
 */
public class Invoice {
    // Instance variables (private = encapsulation)
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;

    // Constructor: initializes all four instance variables.
    // It calls the setters so the validation rules are applied in ONE place.
    public Invoice(String partNumber, String partDescription,
                   int quantity, double pricePerItem) {
        setPartNumber(partNumber);
        setPartDescription(partDescription);
        setQuantity(quantity);
        setPricePerItem(pricePerItem);
    }

    // ----- Getters -----
    public String getPartNumber()      { return partNumber; }
    public String getPartDescription() { return partDescription; }
    public int getQuantity()           { return quantity; }
    public double getPricePerItem()    { return pricePerItem; }

    // ----- Setters -----
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    // If quantity is not positive, it is set to 0.
    public void setQuantity(int quantity) {
        this.quantity = (quantity > 0) ? quantity : 0;
    }

    // If price is not positive, it is set to 0.0.
    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = (pricePerItem > 0) ? pricePerItem : 0.0;
    }

    // Invoice amount = quantity * price per item
    public double getInvoiceAmount() {
        return quantity * pricePerItem;
    }
}
