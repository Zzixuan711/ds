package control;

import java.util.Scanner;

import adt.ArrayList;
import adt.ArraySet;
import adt.Set;
import adt.List;
import dao.MedicineDA;
import entity.Med;
import entity.Medicine;
import utility.Addon;
import utility.FileOp;
import utility.Sorting;

public class Driver {
  public static void main(String[] args) {
    
    // ignore all this
    // List<String> list1 = FileOp.readTxt("data/med.txt");
    // ArrayList<Med> list2 = new ArrayList<>();
    // for (String str : list1) {
    //   list2.add(Med.parsMed(str));
    // }
    // ArrayList<Medicine> list3 = new ArrayList<>(list2.size());
    // for (Med med : list2) {
    //   list3.add(new Medicine("", med.name, "", med.category, med.dosageform, med.strength, med.indication,
    //       med.classification, 0, 0));
    // }

    // Sorting.binInsertSort(list3);
    // // Sorting.quickSort(list2);
    // String ans = "";
    // for (Medicine medicine : list3) {
    //   ans += medicine + "\n";
    // }
    // FileOp.writeTxt("data/medicine.txt", ans);
  }

}
