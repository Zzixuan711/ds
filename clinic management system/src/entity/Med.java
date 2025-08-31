package entity;

import utility.Addon;

public class Med implements Comparable<Med> {
  public String name, category, dosageform, strength, indication, classification;

  public Med() {
    this("", "", "", "", "", "");
  }

  public Med(String name, String category, String dosageform, String strength, String indication,
      String classification) {
    this.name = name;
    this.category = category;
    this.dosageform = dosageform;
    this.strength = strength;
    this.indication = indication;
    this.classification = classification;
  }

  // public static Med parseCSV(String csv) {
  // String[] arr = Addon.parseCSVLine(csv);
  // return new Med(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
  // }
  public static Med parsMed(String str) {
    String buffer = str.substring(1, str.length() - 1);
    String[] arr = buffer.split("\\|");
    return new Med(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
  }

  @Override
  public int compareTo(Med med) {
    int flag1 = name.compareTo(med.name);
    int flag2 = category.compareTo(med.category);
    int flag3 = dosageform.compareTo(med.dosageform);
    int flag4 = indication.compareTo(med.indication);
    int flag5 = strength.compareTo(med.strength);
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
    return 0;
  }

  public String toString() {
    return String.format("|%s|%s|%s|%s|%s|%s|", name, category, dosageform, strength, indication,
        classification);
  }
}
