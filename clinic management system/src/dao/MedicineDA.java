package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import adt.ArrayList;
import adt.List;
import entity.Medicine;
import utility.Addon;
import utility.FileOp;

/*
 * made by: Heng Hao Zhe
 */
public class MedicineDA {

  public MedicineDA() {
  }

  public void updateMedicineDat(Medicine medicine, String id) {
    List<Medicine> arr = readMedicineDat();
    for (Medicine elem : arr) {
      if (elem.getId().equals(id)) {
        elem.updateThis(medicine);
      }
    }
    writeMedicineDat(arr);
  }

  public void updateMedicineDat(List<Medicine> arr) {
    writeMedicineDat(arr);
  }

  public void updateMedicineTxt(Medicine medicine, String id) {
    List<Medicine> arr = readMedicineTxt();
    for (Medicine elem : arr) {
      if (elem.getId().equals(id)) {
        elem.updateThis(medicine);
      }
    }
    writeMedicineTxt(arr);
  }

  public void updateMedicineTxt(List<Medicine> arr) {
    writeMedicineTxt(arr);
  }

  public void deleteMedicineDat(Medicine medicine) {
    List<Medicine> arr = readMedicineDat();
    String msg = "Entry not found";
    for (Medicine elem : arr) {
      if (elem.equals(medicine)) {
        arr.remove(elem);
        msg = "Data updated";
      }
    }
    writeMedicineDat(arr);
    Addon.println(msg);
  }

  public void deleteMedicineTxt(Medicine medicine) {
    List<Medicine> arr = readMedicineTxt();
    String msg = "Entry not found";
    for (Medicine elem : arr) {
      if (elem.equals(medicine)) {
        arr.remove(elem);
        msg = "Data updated";
      }
    }
    writeMedicineTxt(arr);
    Addon.println(msg);
  }

  public void newEntryToFileDat(Medicine newEntry) {
    List<Medicine> arr = readMedicineDat();
    arr.add(newEntry);
    writeMedicineDat(arr);
  }

  public void newEntryToFileTxt(Medicine newEntry) {
    FileOp.appendTxt("data/medicine.txt", newEntry + "\n");
  }

  public List<Medicine> readMedicineTxt() {
    List<String> arr = FileOp.readTxt("data/medicine.txt");
    List<Medicine> result = new ArrayList<>();
    for (String str : arr) {
      // result.add(Medicine.parseMedicine(str));
    }
    return result;
  }

  public void writeMedicineTxt(List<Medicine> arr) {
    String buffer = "";
    for (Medicine medicine : arr) {
      buffer += medicine + "\n";
    }
    FileOp.writeTxt("data/medicine.txt", buffer);
  }

  @SuppressWarnings("unchecked")
  public List<Medicine> readMedicineDat() {
    try {
      File file = new File("data/medicine.dat");
      ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
      List<Medicine> arr = (List<Medicine>) (oiStream.readObject());
      oiStream.close();
      return arr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void writeMedicineDat(List<Medicine> arr) {
    File file = new File("data/medicine.dat");
    try {
      ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
      ooStream.writeObject(arr);
      ooStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
