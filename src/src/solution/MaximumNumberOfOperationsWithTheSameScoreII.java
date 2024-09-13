package solution;

import java.util.Arrays;

public class MaximumNumberOfOperationsWithTheSameScoreII {

  public static void main(String[] args) {

    int i = maxOperations(new int[]{4, 2, 1, 1, 4});

    System.out.println(i);
  }

  public static int maxOperations(int[] nums) {

    if (nums.length <= 3) {
      return 1;
    }

    return maxOperations2(nums, 0, 0);
  }

  public static int maxOperations2(int[] nums, int operationCounter, int sumEqualTo) {

    if (nums.length <= 1) {
      return operationCounter;
    }

    if (nums.length == 2) {
      if (sumEqualTo == maxOperationFirstAndLastSum(nums)) {
        operationCounter++;
      }
      return operationCounter;
    }

    int sumFirstTwo = maxOperationFirstTwoSum(nums);
    int sumLastTwo = maxOperationLastTwoSum(nums);
    int counter = operationCounter;


    if (sumFirstTwo == sumLastTwo) {
      counter += 2;
      return maxOperations2(Arrays.stream(nums).skip(2).limit(nums.length - 4).toArray(), counter, sumFirstTwo);
    }

    {
      int[] numsMinusFirstTwo = Arrays.stream(nums).skip(2).toArray();

      int sumMinusFirstTwo__FirstAndLastTwoSum = maxOperationFirstAndLastSum(numsMinusFirstTwo);

      if (sumFirstTwo == sumMinusFirstTwo__FirstAndLastTwoSum) {
        counter += 2;
        return maxOperations2(Arrays.stream(nums).skip(3).limit(nums.length - 4).toArray(), counter, sumFirstTwo);
      }

    }

    {
      int[] numsMinusFirstTwo = Arrays.stream(nums).limit(nums.length - 3).toArray();

      int sumMinusFirstTwo__FirstAndLastTwoSum = maxOperationFirstAndLastSum(numsMinusFirstTwo);

      if (sumLastTwo == sumMinusFirstTwo__FirstAndLastTwoSum) {
        counter += 2;
        return maxOperations2(Arrays.stream(nums).skip(1).limit(nums.length - 4).toArray(), counter, sumLastTwo);
      }
    }

    return counter;
  }

  public static int maxOperationFirstTwoSum(int[] nums) {
    return nums[0] + nums[1];
  }

  public static int maxOperationLastTwoSum(int[] nums) {
    return nums[nums.length - 1] + nums[nums.length - 2];
  }

  public static int maxOperationFirstAndLastSum(int[] nums) {
    return nums[0] + nums[nums.length - 1];
  }

}
