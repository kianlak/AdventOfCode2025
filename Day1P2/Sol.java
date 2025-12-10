package Day1P2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sol {
  public static int normalizeValue(int value) {
    System.out.print(value + " ");

    if (value < 0) {
      value = 100 - ((-1 * value) % 100);
      value = value == 100 ? 0 : value;
    } else if (value > 99) {
      value %= 100;
    }

    System.out.println(value + " ");
    return value;
  }

  public static int secretEntrance(List<String> inputs) {
    int result = 0;
    int currPosition = 50;

    for (String input : inputs) {
      char direction = input.charAt(0);
      int directionAmount = Integer.parseInt(input.substring(1));
      
      if (direction == 'L') currPosition -= directionAmount;
      else if (direction == 'R') currPosition += directionAmount;

      currPosition = normalizeValue(currPosition);

      if (currPosition == 0) result++;
    }

    return result;
  }

  public static void main(String[] args) {
    List<String> inputList = new ArrayList<>();

    String filePath = "input.txt";

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = reader.readLine()) != null) {
        inputList.add(line);
      }
    } catch (IOException error) { error.printStackTrace(); }

    System.out.println(secretEntrance(inputList));
  }
}
