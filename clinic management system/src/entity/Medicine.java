package entity;

import java.io.Serializable;

/*
 * made by: Heng Hao Zhe
 */
public class Medicine implements Comparable<Medicine>, Serializable {
  private static final long serialVersionUID = 1L;
  private String id;
  private String name;
  private String description;
  private String category, dosageform, strength, indication, classification;
  private int stock;
  private double price;

  public Medicine() {
    this("", "", "", "", "", "", "", "", 0, 0);
  }

  public Medicine(String id, String name, String description, String category, String dosageform, String strength,
      String indication, String classification, int stock, double price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.category = category;
    this.dosageform = dosageform;
    this.strength = strength;
    this.indication = indication;
    this.classification = classification;
    this.stock = stock;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getStock() {
    return stock;
  }

  public double getPrice() {
    return price;
  }

  public String getCategory() {
    return category;
  }

  public String getDosageform() {
    return dosageform;
  }

  public String getStrength() {
    return strength;
  }

  public String getIndication() {
    return indication;
  }

  public String getClassification() {
    return classification;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setDosageform(String dosageform) {
    this.dosageform = dosageform;
  }

  public void setStrength(String strength) {
    this.strength = strength;
  }

  public void setIndication(String indication) {
    this.indication = indication;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  // public static Medicine parseMedicine(String txt) {
  // String buffer = txt.substring(1, txt.length() - 1);
  // String[] arr = buffer.split("\\|");
  // return new Medicine(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]),
  // Double.parseDouble(arr[4]));
  // }

  public void updateThis(Medicine medicine) {
    updateThis(medicine.toString());
  }

  public void updateThis(String txt) {
    String buffer = txt.substring(1, txt.length() - 1);
    String[] arr = buffer.split("\\|");
    id = arr[0];
    name = arr[1];
    description = arr[2];
    stock = Integer.parseInt(arr[3]);
    price = Double.parseDouble(arr[4]);
  }

  public String toString() {
    return String.format("|%s|%s|%s|%s|%s|%s|%s|%s|%d|%.2f|", id, name, description, category, dosageform, strength, indication,
        classification, stock, price);
  }

  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof Medicine))
      return false;
    Medicine buffer = (Medicine) obj;
    if (!buffer.id.equals(id))
      return false;
    if (!buffer.name.equals(name))
      return false;
    if (!buffer.description.equals(description))
      return false;
    if (!buffer.category.equals(category))
      return false;
    if (!buffer.dosageform.equals(dosageform))
      return false;
    if (!buffer.strength.equals(strength))
      return false;
    if (!buffer.indication.equals(indication))
      return false;
    if (!buffer.classification.equals(classification))
      return false;
    if (buffer.stock != stock)
      return false;
    if (buffer.price != price)
      return false;
    return true;
  }

  @Override
  public int compareTo(Medicine medicine) {
    int flag1 = this.name.compareTo(medicine.name);
    int flag2 = ((Integer) this.stock).compareTo(medicine.stock);
    int flag3 = category.compareTo(medicine.category);
    int flag4 = dosageform.compareTo(medicine.dosageform);
    int flag5 = indication.compareTo(medicine.indication);
    int flag6 = strength.compareTo(medicine.strength);
    if (flag1 != 0)
      return flag1;
    if (flag2 != 0)
      return flag2;
    if (flag3 != 0)
      return flag3;
    if (flag4 != 0)
      return flag4;
    if (flag5 != 0)
      return flag5;
    if (flag6 != 0)
      return flag6;
    return 0;
  }
}
