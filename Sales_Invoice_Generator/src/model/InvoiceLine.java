package model;

public class InvoiceLine {

    //Declaration InvoiceLine class variables
    private String itemName;
    private int invoiceNum , itemPrice , count;

    public InvoiceLine() { }
    public InvoiceLine(String itemName, int invoiceNum, int itemPrice, int count) {
        setItemName(itemName);
        setInvoiceNum(invoiceNum);
        setItemPrice(itemPrice);
        setCount(count);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
