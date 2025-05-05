/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming.project;

/**
 *
 * @author Aly
 */
public class Agent extends User {
//     3. Agent (Extends User) 
// • Responsibility: Manages bookings and assists customers 
// • Key Attributes: agentId, department, commission 
// • Key Methods: manageFlights(), createBookingForCustomer(), modifyBooking(), generateReports() 

    private String agentId;
    private String department;
    private double commission;

    public Agent(String agentId, String department, double commission, String userId, String username, String password, String name, String email, String contactInfo) {
        super(userId, username, password, name, email, contactInfo);
        this.agentId = agentId;
        this.department = department;
        this.commission = commission;
        
        
    }

    public String getAgentId() {
        return agentId;
    }

    public String getDepartment() {
        return department;
    }

    public double getCommission() {
        return commission;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
    
    

}
