package utility;

import adt.ArrayList;
import adt.List;

/*
 * made by: Heng Hao Zhe
 * 
 * simple functions
 */
public class Addon {
  public static void println(Object obj) {
    System.out.println(obj);
  }

  public static void printf(String format, Object... obj) {
    System.out.printf(format, obj);
  }

  public static <T> void printArr(List<T> arr) {
    String str = "[";
    for (T t : arr) {
      str += t + ",";
    }
    if (arr.size() > 0)
      str = str.substring(0, str.length() - 1);
    str += "]";
    println(str);
  }

  public static void printArr(Object[] arr) {
    String str = "[";
    for (int i = 0; i < arr.length; i++) {
      str += arr[i] + ",";
    }
    if (arr.length > 0)
      str = str.substring(0, str.length() - 1);
    str += "]";
    println(str);
  }

  // to avoid parsing the wrong comma
  public static String[] parseCSVLine(String str) {
    List<String> ans = new ArrayList<>();
    boolean isString = false;
    String buffer = "";
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (isString) {
        if (c == '"') {
          isString = false;
        }
        buffer += c;
      } else {
        if (c == '"') {
          isString = true;
        } else if (c == ',') {
          ans.add(buffer);
          buffer="";
          continue;
        }
        buffer += c;
      }
    }
    ans.add(buffer);
    return ans.toArray(new String[0]);
  }

  // todo
  // return
  public static int binSearch() {
    return 0;
  }
}
