class Solution {
    public int minimumAverageDifference(int[] nums) {
        long numsSum = 0;
        for (int num : nums) {
            numsSum += num;
        }
        long minAverageDiff = Long.MAX_VALUE;
        long sumFromFront = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            sumFromFront += nums[i];
            int numbersRight = i == nums.length - 1 ? 1 : nums.length - i - 1;
            long averageDiff =
                    Math.abs(sumFromFront / (i + 1) - (numsSum - sumFromFront) / numbersRight);
            if (minAverageDiff > averageDiff) {
                minAverageDiff = averageDiff;
                index = i;
            }
            if (averageDiff == 0) {
                break;
            }
        }
        return index;
    }
}
