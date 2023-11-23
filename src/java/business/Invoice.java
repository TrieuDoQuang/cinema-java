/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.eclipse.persistence.annotations.UuidGenerator;
import java.sql.Date;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Admin
 */
@Entity
public class Invoice implements Serializable {

  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
  private List<Ticket> tickets;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Id
  @UuidGenerator(name = "UUID")
  @GeneratedValue(generator = "UUID")
  @Column(name = "invoice_id")
  private String invoiceID;
  private Date boughtDate;

  public List<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public int getTotalPrice() {
    int totalPrice = 0;
    for (Ticket ticket : tickets) {
      totalPrice += ticket.getShowtime().getPrice() * ticket.getSeatClass().getFactor();
    }
    return totalPrice;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getInvoiceID() {
    return invoiceID;
  }

  public void setInvoiceID(String invoiceID) {
    this.invoiceID = invoiceID;
  }

  public Date getBoughDate() {
    return boughtDate;
  }

  public void setBoughDate(Date boughDate) {
    this.boughtDate = boughDate;
  }

  public String getStandardSeats() {
    String standardSeat = "";
    String SeatName = "ABCDEFGHIJ";
    for (Ticket ticket : tickets) {
      if (ticket.getSeatClass().getId() == 2) {
        standardSeat += String.valueOf(SeatName.charAt((ticket.getSeatNumber() - 1) / 10))
            + String.valueOf((ticket.getSeatNumber() - 1) % 10 + 1) + "-";
      }
    }
    if (standardSeat.length() > 0) {
      standardSeat = standardSeat.substring(0, standardSeat.length() - 1);
    }
    return standardSeat;
  }

  public String getVipSeats() {
    String VipSeat = "";
    String SeatName = "ABCDEFGHIJ";
    for (Ticket ticket : tickets) {
      if (ticket.getSeatClass().getId() == 3) {
        VipSeat += String.valueOf(SeatName.charAt((ticket.getSeatNumber() - 1) / 10))
            + String.valueOf((ticket.getSeatNumber() - 1) % 10 + 1) + "-";
      }
    }
    if (VipSeat.length() > 0) {
      VipSeat = VipSeat.substring(0, VipSeat.length() - 1);
    }
    return VipSeat;
  }
  
  public int getStandardTotalPrice() {
    int totalPrice = 0;
    for (Ticket ticket : tickets) {
      if (ticket.getSeatClass().getId() == 2) {
        totalPrice += ticket.getShowtime().getPrice() * ticket.getSeatClass().getFactor();
      }
    }
    return totalPrice;
  }

  public int getVipTotalPrice() {
    int totalPrice = 0;
    for (Ticket ticket : tickets) {
      if (ticket.getSeatClass().getId() == 3) {
        totalPrice += ticket.getShowtime().getPrice() * ticket.getSeatClass().getFactor();
      }
    }
    return totalPrice;
  }
}
