package Day1.Part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sol {
  public static int normalizeValue(int value) {
    if (value < 0) {
      value = 100 - ((-1 * value) % 100);
      value = value == 100 ? 0 : value;
    } else if (value > 99) {
      value %= 100;
    }

    return value;
  }

  public static int secretEntrance(List<String> inputs) {
    int result = 0;
    int extraClicks = 0;
    int currPosition = 50;
    int preNormalized;
    
    for (String input : inputs) {
      char direction = input.charAt(0);
      int directionAmount = Integer.parseInt(input.substring(1));
      
      boolean startedZeroFlag = false;

      if (currPosition == 0) startedZeroFlag = true;
      
      if (direction == 'L') currPosition -= directionAmount;
      else if (direction == 'R') currPosition += directionAmount;

      preNormalized = currPosition;   
      currPosition = normalizeValue(currPosition);

      if (preNormalized < 0 || preNormalized > 99) {
        if      (startedZeroFlag && directionAmount >= 100 && currPosition == 0)  extraClicks += directionAmount / 100 - 1;
        else if (startedZeroFlag && directionAmount >= 100 && currPosition != 0)  extraClicks += directionAmount / 100;
        else if (!startedZeroFlag && directionAmount >= 100 && currPosition == 0) extraClicks += directionAmount / 100;
        else if (!startedZeroFlag && directionAmount >= 100 && currPosition != 0) extraClicks += directionAmount / 100;
        else if (!startedZeroFlag && directionAmount < 100 && currPosition != 0) extraClicks++;
      }

      if (currPosition == 0) result++;
    }

    return result + extraClicks;
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
