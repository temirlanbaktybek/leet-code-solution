package solution;

import java.util.Arrays;

public class MaximumNumberOfOperationsWithTheSameScoreII {

  public static void main(String[] args) {

    int[] asd = new int[]{9,2,6,9,11,10,10,17,3,9,11,8,9,11,7,13,5,4,16,9,19,17,19,13,13,7,3,2,18,8,4,16,6,1,7,13,9,11,12,9,11,17,13,7,11,6,2,18,6,14,17,3,2,2,14,17,3,5,15,6,14,5,8,12,19,1,10,10,3,17,11,1,17,3,4,2,18,12,8,10,10,12,8,4,16,11,2,12,7,19,1,12,3,17,3,11,8,12,17,3,4,11,9,1,19,10,10,15,1,16,1,18,5,19,1,8,12,11,9,15,5,17,14,2,10,10,14,10,5,9,17,6,14,2,2,11,8,12,8,16,4,11,9,7,13,19,2,18,18,16,15,5,3,17,8,10,10,9,11,13,7,16,2,17,11,9,18,2,7,14,4,9,6,2,12,8,10,10,8,12,8,9,11,7,672,922,419,283,457,13,4,16,8,12,12,8,12,7,13,6,14,18,14,11,16,16,4,6,13,3,3,17,9,11,1,19,19,1,18,2,18,4,6,14,3,17,12,12,8,4,2,19,1,1,12,9,12,8,18,8,12,18,11,9,12,8,17,3,3,11,15,7,13,10,6,1,19,10,10,18,6,3,13,7,13,7,5,15,16,4,12,8,16,4,13,7,11,9,15,2,19,4,19,5,18,2,18,2,14,6,16,4,1,19,16,8,18,2,12,9,17,8,3,17,13,8,18,5,15,9,8,12,15,5,12,8,16,11,9,19,9,3,17,7,13,4,16,15,5,15,6,9,11,18,9,11,11,9,18,13,7,1,19,6,14,10,10,14,9,18,2,3,11,9,12,8,11,9,8,4,16,12,8,12,8,19,14,12,17,8,12,7,1,3,6,14,1,11,9,11,15,12,1,19,6,14,14,18,11};

    int i = maxOperations(asd);

    System.out.println(i);
  }

  public static int maxOperations(int[] nums) {

    if (nums.length <= 3) {
      return 1;
    }

    return maxOperations2(nums, 0, nums.length - 1);
  }

  public static int maxOperations2(int[] nums, int indexX, int indexY) {

    int sumFirstTwo = nums[indexX] + nums[indexX + 1];
    int sumLastTwo = nums[indexY] + nums[indexY - 1];
    int sumFirstAndLast = nums[indexX] + nums[indexY];

    int[][] array2d = new int[nums.length][nums.length];

    cleanArray(array2d);
    int operationCounterFirstTwo = solution1(nums, sumFirstTwo, 1, indexX + 2, indexY, array2d);

    cleanArray(array2d);
    int operationCounterLastTwo = solution1(nums, sumLastTwo, 1, indexX, indexY - 2, array2d);

    cleanArray(array2d);
    int operationCounterFirstAndLast = solution1(nums, sumFirstAndLast, 1, indexX + 1, indexY - 1, array2d);

    int max1 = Math.max(operationCounterFirstTwo, operationCounterLastTwo);
    return Math.max(max1, operationCounterFirstAndLast);

  }

  public static int solution1(int[] nums, int sumEqual, int counter, int indexX, int indexY, int[][] array2d) {

    if (indexX >= indexY) {
      return counter;
    }

    if (array2d[indexX][indexY] != -1) {
      return array2d[indexX][indexY];
    }

    int sumFirstTwo = nums[indexX] + nums[indexX + 1];
    int sumLastTwo = nums[indexY] + nums[indexY - 1];
    int sumFirstAndLast = nums[indexX] + nums[indexY];

    int totalC = 0;
    if (sumEqual == sumFirstAndLast) {
      counter++;
      totalC = solution1(nums, sumEqual, counter, indexX + 1, indexY - 1, array2d);
      int tempTotal = 0;
      if (sumEqual == sumFirstTwo) {
        tempTotal = solution1(nums, sumEqual, counter, indexX + 2, indexY, array2d);
      }
      int tempTotal2 = 0;
      if (sumEqual == sumLastTwo) {
         tempTotal2 = solution1(nums, sumEqual, counter, indexX, indexY - 2, array2d);
      }

      totalC = Math.max(Math.max(tempTotal, tempTotal2), totalC);
    }

    if (totalC != 0) {
      counter = 1;
    }

    int totalA = 0;
    if (sumEqual == sumFirstTwo) {
      counter++;
      totalA = solution1(nums, sumEqual, counter, indexX + 2, indexY, array2d);
    }

    if (totalA != 0) {
      counter = 1;
    }

    int totalB = 0;
    if (sumEqual == sumLastTwo) {
      counter++;
      totalB = solution1(nums, sumEqual, counter, indexX, indexY - 2, array2d);
    }

    if (totalA == 0 && totalB == 0 && totalC == 0) {
      return counter;
    }

    return array2d[indexX][indexY] = Math.max(totalA, Math.max(totalB, totalC));
  }


  public static void cleanArray(int[][] array2d) {
    for (int[] row : array2d) {
      Arrays.fill(row, -1);
    }
  }

}
