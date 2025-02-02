package com.example.fetchreceiptprocesschallenge.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.Description;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Receipt {

  @Pattern(regexp = "^[\\w\\s\\-&]+$")
  @NotNull
  @Description("The name of the retailer or store the receipt is from.\n")
  private String retailer;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @NotNull
  private LocalDate purchaseDate;

  private String purchaseTime;

  private List<@Valid Item> items;

  @Pattern(regexp = "^\\d+\\.\\d{2}$")
  @NotNull
  private String total;

  public Receipt(String retailer, LocalDate purchaseDate, String purchaseTime, List<Item> items, String total) {
    this.retailer = retailer;
    this.purchaseDate = purchaseDate;
    this.purchaseTime = purchaseTime;
    this.items = items;
    this.total = total;
  }

  public @Pattern(regexp = "^[\\w\\s\\-&]+$") @NotBlank String getRetailer() {
    return retailer;
  }

  public void setRetailer(@Pattern(regexp = "^[\\w\\s\\-&]+$") @NotBlank String retailer) {
    this.retailer = retailer;
  }

  public String getPurchaseTime() {
    return purchaseTime;
  }

  public void setPurchaseTime(String purchaseTime) {
    this.purchaseTime = purchaseTime;
  }

  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public @Pattern(regexp = "^\\d+\\.\\d{2}$") String getTotal() {
    return total;
  }

  public void setTotal(@Pattern(regexp = "^\\d+\\.\\d{2}$") String total) {
    this.total = total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Receipt receipt = (Receipt) o;
    return Objects.equals(retailer, receipt.retailer) && Objects.equals(purchaseDate, receipt.purchaseDate) && Objects.equals(purchaseTime, receipt.purchaseTime) && Objects.equals(items, receipt.items) && Objects.equals(total, receipt.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(retailer, purchaseDate, purchaseTime, items, total);
  }

  @Override
  public String toString() {
    return "Receipt{" +
            "retailer='" + retailer + '\'' +
            ", purchaseDate=" + purchaseDate +
            ", purchaseTime='" + purchaseTime + '\'' +
            ", items=" + items +
            ", total='" + total + '\'' +
            '}';
  }
}

