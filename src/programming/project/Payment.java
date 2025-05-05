/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming.project;

/**
 *
 * @author Aly
 */
public class Payment {
//     8. Payment 
// • Responsibility: Handles payment information and processing 
// • Key Attributes: paymentId, bookingReference, amount, method, status, transactionDate 
// • Key Methods: processPayment(), validatePaymentDetails(), updateStatus() 
    private String paymentId;
    private String bookingReference;
    private double amount;
    private String method;
    private String status;
    private String transactionDate;

    public Payment(String paymentId, String bookingReference, double amount, String method, String status, String transactionDate) {
        this.paymentId = paymentId;
        this.bookingReference = bookingReference;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.transactionDate = transactionDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionDate() {
        return transactionDate;
    }
    
}
