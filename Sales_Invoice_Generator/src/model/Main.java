package model;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        FileOperations fo = new FileOperations();
        ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();

        try {
            fo.writeFile(invoiceHeaders);
            fo.readFile();
        }
        catch (Exception ex) { ex.printStackTrace(); }
    }
}
