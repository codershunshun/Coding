class Solution {
  public int findKthLargest(int[] nums, int k) {
    return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
  }

  private int quickSelect(int[] nums, int start, int end, int k) {
    if (start == end) {
      return nums[start];
    }

    int position = partition(nums, start, end);

    if (position + 1 == k) {
      return nums[position];
    } else if (position + 1 < k) {
      return quickSelect(nums, position + 1, end, k);
    } else {
      return quickSelect(nums, start, position - 1, k);
    }
  }

  private int partition(int[] nums, int start, int end) {
    int left = start;
    int right = end;
    int pivot = nums[left];

    while (left < right) {
      while (left < right && nums[right] >= pivot) {
        right--;
      }
      nums[left] = nums[right];

      while (left < right && nums[left] <= pivot) {
        left++;
      }
      nums[right] = nums[left];
    }
    nums[left] = pivot;
    return left;
  }
}
