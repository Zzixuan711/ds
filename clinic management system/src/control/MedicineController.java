package control;

import adt.List;
import utility.Addon;
import utility.FileOp;

public class MedicineController {

  public MedicineController() {
  }

  public void showMainMenu() {
    Addon.println("");
  }

  public void printMenu(int index) {
    String menu = "";
    List<String> list = FileOp.readTxt("data/menu.txt");
    stop: {
      for (int i = 0; i < list.size(); i++) {
        String curLine = list.get(i);
        String[] words = curLine.split(",");
        if (Integer.parseInt(words[0]) != index) {
          i += Integer.parseInt(words[1]);
          continue;
        }
        for (int j = i + 1, count = 0; j < list.size() && count < Integer.parseInt(words[1]) + 1; j++) {
          menu += list.get(j).trim() + "\n";
        }
        break stop;
      }
    }
    Addon.printf("%s", menu);
  }

}
