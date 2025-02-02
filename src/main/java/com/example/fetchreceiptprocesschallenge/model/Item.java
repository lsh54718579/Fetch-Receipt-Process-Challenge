package com.example.fetchreceiptprocesschallenge.model;



import jakarta.validation.constraints.Pattern;
import java.util.Objects;


public class Item {

  @Pattern(regexp = "^[\\w\\s\\-]+$")
  private String shortDescription;

  @Pattern(regexp = "^\\d+\\.\\d{2}$")
  private String price;

  public Item(String shortDescription, String price) {
    this.shortDescription = shortDescription;
    this.price = price;
  }

  public @Pattern(regexp = "^[\\w\\s\\-]+$") String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(@Pattern(regexp = "^[\\w\\s\\-]+$") String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public @Pattern(regexp = "^\\d+\\.\\d{2}$") String getPrice() {
    return price;
  }

  public void setPrice(@Pattern(regexp = "^\\d+\\.\\d{2}$") String price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return Objects.equals(shortDescription, item.shortDescription) && Objects.equals(price, item.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shortDescription, price);
  }

  @Override
  public String toString() {
    return "Item{" +
            "shortDescription='" + shortDescription + '\'' +
            ", price='" + price + '\'' +
            '}';
  }
}

