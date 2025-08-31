package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import adt.ArrayList;
import adt.List;

/*
 * made by: Heng Hao Zhe
 * 
 * basic file operations
 * no dat file operation provided because you need to implement serializable interface at your own collection adt(not interface but class) or object
 */
public class FileOp {

  public FileOp() {
  }

  public static File openFile(String pathname) {
    return new File(pathname);
  }

  public static void deleteFile(String pathname) {
    deleteFile(new File(pathname));
  }

  public static void deleteFile(File file) {
    if (file.delete()) {
      System.out.println("File deleted: " + file.getName());
    } else {
      System.out.println("Failed to delete file or file does not exist.");
    }
  }

  public static List<String> readTxt(String pathname) {
    return readTxt(new File(pathname));
  }

  public static List<String> readTxt(File file) {
    List<String> result = new ArrayList<>();
    try {
      String str = "";
      BufferedReader br = new BufferedReader(new FileReader(file));
      while ((str = br.readLine()) != null) {
        result.add(str);
      }
      br.close();
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static boolean isNewFile(String pathname) {
    return isNewFile(new File(pathname));
  }

  public static boolean isNewFile(File file) {
    try {
      return file.createNewFile();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean clearFileTxt(String pathname) {
    return clearFileTxt(new File(pathname));
  }

  public static boolean clearFileTxt(File file) {
    try {
      FileWriter fr = new FileWriter(file);
      fr.write("");
      fr.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static void writeTxt(String pathname, String str) {
    writeTxt(new File(pathname), str);
  }

  public static void writeTxt(File file, String str) {
    try {
      FileWriter fr = new FileWriter(file);
      fr.write(str);
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void appendTxt(String pathname, String str) {
    appendTxt(new File(pathname), str);
  }

  public static void appendTxt(File file, String str) {
    try {
      FileWriter fr = new FileWriter(file, true);
      fr.write(str);
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
