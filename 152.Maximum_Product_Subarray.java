class Solution {
  public int maxProduct(int[] nums) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int maxProduct = nums[0], minProduct = nums[0], maxRes = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] >= 0) {
        maxProduct = Math.max(nums[i], nums[i] * maxProduct);
        minProduct = Math.min(nums[i], nums[i] * minProduct);
      } else {
        int temp = maxProduct;
        maxProduct = Math.max(nums[i], nums[i] * minProduct);
        minProduct = Math.min(nums[i], nums[i] * temp);
      }

      maxRes = Math.max(maxRes, maxProduct);
    }

    return maxRes;
  }
}
