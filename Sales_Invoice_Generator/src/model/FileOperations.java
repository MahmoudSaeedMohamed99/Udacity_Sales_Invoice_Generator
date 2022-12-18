package model;

import java.io.*;
import java.util.ArrayList;

public class FileOperations {

    public static void main(String[] args) {

        FileOperations fo = new FileOperations();

        try { fo.readFile(); }
        catch (Exception ex) { ex.printStackTrace(); }
    }

    private static final String delimeter = ",";
    private String invoiceHeaderSpliter, invoiceLineSpliter;
    private String[] invoiceHeaderArr, invoiceLineArr;
    private String invoiceHeaderPath = "C:\\Users\\Mahmoud Abdelhady\\IdeaProjects\\Sales_Invoice_Generator\\InvoiceHeader.csv";
    private String invoiceLinePath = "C:\\Users\\Mahmoud Abdelhady\\IdeaProjects\\Sales_Invoice_Generator\\InvoiceLine.csv";

    //WriteFile function to write date into two files InvoiceLine and InvoiceHeader
    public void writeFile(ArrayList<InvoiceHeader> invoiceHeaders) throws Exception {

        //Create ArrayList of invoice lines to hold objects of invoice lines
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();

        //A custom class to handle date format exception
        DateParseException dpe = new DateParseException();

        //Creating objects of invoice lines and giving data to constructor
        InvoiceLine invoiceLine1 = new InvoiceLine("shirt", 1, 150, 3);
        InvoiceLine invoiceLine2 = new InvoiceLine("shoes", 1, 150, 3);
        InvoiceLine invoiceLine3 = new InvoiceLine("soaks", 1, 200, 5);
        InvoiceLine invoiceLine4 = new InvoiceLine("t-shirt", 2, 300, 2);
        InvoiceLine invoiceLine5 = new InvoiceLine("pen", 2, 20, 10);
        InvoiceLine invoiceLine6 = new InvoiceLine("short", 2, 60, 1);

        //Creating objects of invoice headers and giving data to constructor
        InvoiceHeader invoiceHeader1 = new InvoiceHeader(1, dpe.parseDate("(16/12/2022)"), "Ahmed", invoiceLines);
        InvoiceHeader invoiceHeader2 = new InvoiceHeader(2, dpe.parseDate("(16/12/2022)"), "Mahmoud", invoiceLines);

        //Add invoice header objects to the ArrayList
        invoiceHeaders.add(invoiceHeader1);
        invoiceHeaders.add(invoiceHeader2);

        //Add invoice line objects to the ArrayList
        invoiceHeader1.getInvoiceLines().add(invoiceLine1);
        invoiceHeader1.getInvoiceLines().add(invoiceLine2);
        invoiceHeader1.getInvoiceLines().add(invoiceLine3);
        invoiceHeader2.getInvoiceLines().add(invoiceLine4);
        invoiceHeader2.getInvoiceLines().add(invoiceLine5);
        invoiceHeader2.getInvoiceLines().add(invoiceLine6);

        //Checking if invoiceHeader File && invoiceLine File do not exist , throw a FileNotFoundException
        if (!invoiceHeaderPath.endsWith(".csv") || !invoiceLinePath.endsWith(".csv")) {
            System.err.println("File Format Exception");
        }

        //Open two csv files with their paths
        File invoiceHeaderFile = new File(invoiceHeaderPath);
        File invoiceLineFile = new File(invoiceLinePath);

        //Checking if invoiceHeader File && invoiceLine File do not exist , throw a FileNotFoundException
        if (!invoiceHeaderFile.exists() || !invoiceLineFile.exists()) {
            throw new FileNotFoundException("File Not Found");
        }

        //Open two files to write into them
        FileWriter invoiceHeaderWriter = new FileWriter(invoiceHeaderFile);
        FileWriter invoiceLineWriter = new FileWriter(invoiceLineFile);

        //Looping on invoice header object and get data to append it to ArrayList
        for (InvoiceHeader invoiceHeader : invoiceHeaders) {
            invoiceHeaderWriter.append(String.valueOf(invoiceHeader.getInvoiceNum()));
            invoiceHeaderWriter.append(",");
            invoiceHeaderWriter.append(invoiceHeader.getInvoiceDate());
            invoiceHeaderWriter.append(",");
            invoiceHeaderWriter.append(invoiceHeader.getCustomerName());
            invoiceHeaderWriter.append("\n");
        }

        //Looping on invoice line object and get data to append it to ArrayList
        for (InvoiceLine invoiceLine : invoiceLines) {
            invoiceLineWriter.append(String.valueOf(invoiceLine.getInvoiceNum()));
            invoiceLineWriter.append(",");
            invoiceLineWriter.append(invoiceLine.getItemName());
            invoiceLineWriter.append(",");
            invoiceLineWriter.append(String.valueOf(invoiceLine.getItemPrice()));
            invoiceLineWriter.append(",");
            invoiceLineWriter.append(String.valueOf(invoiceLine.getCount()));
            invoiceLineWriter.append("\n");
        }

        //Flush two writers to save data to the files
        invoiceHeaderWriter.flush();
        invoiceLineWriter.flush();

        //Closing two writers
        invoiceHeaderWriter.close();
        invoiceLineWriter.close();
    }

    //ReadFile function to read all the data into both files InvoiceLine and InvoiceHeader files
    public void readFile() throws Exception {

        //A custom class to handle date format exception
        DateParseException dpe = new DateParseException();

        //Checking if invoiceHeader File && invoiceLine File do not exist , throw a FileNotFoundException
        if (!invoiceHeaderPath.endsWith(".csv") || !invoiceLinePath.endsWith(".csv")) {
            System.err.println("File Format Exception");
        }

        //Open two csv files with their paths
        File invoiceHeaderFile = new File(invoiceHeaderPath);
        File invoiceLineFile = new File(invoiceLinePath);

        //Checking if invoiceHeader File && invoiceLine File do not exist , throw a FileNotFoundException
        if (!invoiceHeaderFile.exists() || !invoiceLineFile.exists()) {
            throw new FileNotFoundException("File Not Found");
        }

        //If file exists , use FileReader to read the files
        FileReader invoiceHeaderReader = new FileReader(invoiceHeaderFile);
        FileReader invoiceLineReader = new FileReader(invoiceLineFile);

        //Create buffer reader to read two files
        BufferedReader invoiceHeaderBuffer = new BufferedReader(invoiceHeaderReader);
        BufferedReader invoiceLineBuffer = new BufferedReader(invoiceLineReader);

        //Loop on the invoiceHeader file and invoiceLine file line by line to read it and print it out on the console
        while ((invoiceHeaderSpliter = invoiceHeaderBuffer.readLine()) != null) {

            //Read first line into invoiceHeader file and split it by ',' and print it out
            invoiceHeaderArr = invoiceHeaderSpliter.split(delimeter);
            System.out.println(invoiceHeaderArr[0] + "\n" + "{\n" + dpe.parseDate(invoiceHeaderArr[1]) + ", " + invoiceHeaderArr[2]);
            while ((invoiceLineSpliter = invoiceLineBuffer.readLine()) != null) {

                //Read first line into invoiceHeader file and split it by ','
                invoiceLineArr = invoiceLineSpliter.split(delimeter);

                //Checking if invoiceNumber in invoiceHeader array equals to invoiceNumber in invoiceLine array then print its items
                if (invoiceHeaderArr[0].equals(invoiceLineArr[0])) {
                    System.out.println(invoiceLineArr[1] + ", " + invoiceLineArr[2] + ", " + invoiceLineArr[3]);

                    //If first condition equals false , step into else
                } else {
                    System.out.println("}");

                    //Read a new line in invoiceHeader file , split the string by ',' then print it out
                    invoiceHeaderSpliter = invoiceHeaderBuffer.readLine();
                    invoiceHeaderArr = invoiceHeaderSpliter.split(delimeter);
                    System.out.println(invoiceHeaderArr[0] + "\n" + "{\n" + dpe.parseDate(invoiceHeaderArr[1]) + ", " + invoiceHeaderArr[2]);

                    //Checking if invoiceNumber in invoiceHeader array equals to invoiceNumber in invoiceLine array then print its items
                    if (invoiceHeaderArr[0].equals(invoiceLineArr[0])) {
                        System.out.println(invoiceLineArr[1] + ", " + invoiceLineArr[2] + ", " + invoiceLineArr[3]);
                    }
                }
            }
            System.out.println("}");
        }

        //Closing BufferedReaders
        invoiceHeaderBuffer.close();
        invoiceLineBuffer.close();
    }
}
