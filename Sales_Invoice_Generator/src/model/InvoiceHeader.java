package model;

import java.util.ArrayList;

public class InvoiceHeader {

    //Declaration InvoiceHeader class variables
    private int invoiceNum;
    private String customerName , invoiceDate;
    private ArrayList<InvoiceLine> invoiceLines;

    public InvoiceHeader() { }

    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName, ArrayList<InvoiceLine> invoiceLines) {
        setInvoiceNum(invoiceNum);
        setInvoiceDate(invoiceDate);
        setCustomerName(customerName);
        setInvoiceLines(invoiceLines);
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
}
