package Day1.Part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sol {
  final static int STARTING_POSITION = 50;
  final static int DIAL_SIZE = 100;

  private static int parseRotation(String s) {
    int multiplicator = s.charAt(0) == 'R' ? 1 : -1;
    return multiplicator * Integer.parseInt(s.substring(1));
  }

  private static int calculateZerosPassed(int position, int rotation) {
    if (rotation > 0) {
      return position / DIAL_SIZE;
    } else {
      int zerosPassed = (DIAL_SIZE - position) / DIAL_SIZE;

      boolean startedAtZero = position - rotation == 0;
      if (startedAtZero) {
        zerosPassed--;
      }

      return zerosPassed;
    }
  }

  public static int secretEntrance(List<String> inputs) {
    int position = STARTING_POSITION;
    int zeroCount = 0;

    for (String line : inputs) {
      int rotation = parseRotation(line);
      position += rotation;
      zeroCount += calculateZerosPassed(position, rotation);
      position = Math.floorMod(position, DIAL_SIZE);
    }

    return zeroCount;
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
