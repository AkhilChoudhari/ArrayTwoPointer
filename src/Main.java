import java.util.Arrays;

/*
*//*
 Problem:
 Given a sorted array of integers, find two numbers such that they add up to a specific target.
 Return their 1-based indices.

 Brute Force:
 Try all pairs using two loops → O(n^2)

 Optimal Two Pointer Approach:
 Since array is sorted:
 - If sum is too small → move left pointer right
 - If sum is too large → move right pointer left
 We eliminate impossible regions safely each step.

 Time Complexity: O(n)
 Space Complexity: O(1)
*//*
class Main {

    public static int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // Return 1-based indices as required
                return new int[]{left + 1, right + 1};
            }
            else if (sum < target) {
                // Sum too small → increase it
                left++;
            }
            else {
                // Sum too large → decrease it
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println("Two Sum Sorted:");
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{1,2,3,4,6}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{}, 5)));
        System.out.println(Arrays.toString(twoSum(null, 5)));
    }
} */


/*

import java.util.*;

*/
/*
 Problem:
 Find all unique triplets in the array that sum to zero.

 Brute Force:
 Use 3 nested loops → O(n^3)

 Optimal Approach:
 1. Sort array.
 2. Fix one element.
 3. Use two pointers for remaining part.
 4. Skip duplicates carefully.

 Time Complexity:
 Sorting → O(n log n)
 Two pointer for each element → O(n^2)
 Total → O(n^2)

 Space Complexity:
 O(1) extra space (excluding output)
*//*

class main {

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate base elements
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate left values
                    while (left < right && nums[left] == nums[left + 1]) left++;

                    // Skip duplicate right values
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                }
                else if (sum < 0) {
                    left++; // Need larger sum
                }
                else {
                    right--; // Need smaller sum
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("3Sum:");
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(threeSum(new int[]{0,0,0}));
        System.out.println(threeSum(new int[]{}));
        System.out.println(threeSum(null));
    }
}
*/

/*
*//*
 Problem:
 Given elevation map, compute trapped rain water.

 Brute Force:
 For each index find max left and max right → O(n^2)

 Optimal:
 Two pointers with leftMax and rightMax.
 Process smaller boundary first.

 Time Complexity: O(n)
 Space Complexity: O(1)
*//*
class Main {

    public static int trap(int[] height) {
        if (height == null || height.length < 3) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {

            if (height[left] < height[right]) {

                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }

                left++;
            }
            else {

                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }

                right--;
            }
        }

        return water;
    }

    public static void main(String[] args) {
        System.out.println("Trapping Rain Water:");
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[]{4,2,0,3,2,5}));
        System.out.println(trap(new int[]{}));
        System.out.println(trap(null));
    }
}*/


/*
*//*
 Problem:
 Remove duplicates from sorted array in-place.

 Brute Force:
 Use extra array → O(n) space
 Or use set but with O(n) space--not expected in interview

 Optimal:
 Slow & fast pointer.
 Fast scans, slow writes unique elements.

 Time: O(n)
 Space: O(1)
*//*
class Main {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }

        return slow + 1;
    }

    public static void main(String[] args) {
        System.out.println("Remove Duplicates:");
        int[] arr = {1,1,2};
        System.out.println(removeDuplicates(arr));
        System.out.println(removeDuplicates(new int[]{}));
        System.out.println(removeDuplicates(null));
    }
}*/




/*
import java.util.Arrays;

*/
/*
 Problem:
 Given sorted array, return sorted squares.

 Brute Force:
 Square then sort → O(n log n)

 Optimal:
 since the array is sorted, Largest square comes from either end.
 Fill result from back.

 Time: O(n)
 Space: O(n)
*//*

class Main {

    public static int[] sortedSquares(int[] nums) {
        if (nums == null) return new int[]{};

        int n = nums.length;
        int[] result = new int[n];

        int left = 0, right = n - 1;
        int index = n - 1;

        while (left <= right) {

            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];

            if (leftSq > rightSq) {
                result[index--] = leftSq;
                left++;
            } else {
                result[index--] = rightSq;
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Sorted Squares:");
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4,-1,0,3,10})));
        System.out.println(Arrays.toString(sortedSquares(new int[]{})));
        System.out.println(Arrays.toString(sortedSquares(null)));
    }
}
*/


/*
 Problem:
 Merge two sorted arrays nums1 and nums2 into nums1 in-place.

 nums1:
 - Has total size m + n
 - First m elements are valid
 - Last n positions are empty (placeholders)

 nums2:
 - Has n valid sorted elements

 Example:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6], n = 3

 Result:
 nums1 = [1,2,2,3,5,6]

 ---------------------------------------------------------

 Brute Force:
 Copy nums2 into nums1 and sort.
 Time: O((m+n) log(m+n))

 Optimal Approach (Two Pointer):
 - Compare elements from the END of both arrays.
 - Place the larger element at the back of nums1.
 - Move pointers backward.
 - Fill from back to avoid overwriting unprocessed values.

 Time Complexity: O(m + n)
 Space Complexity: O(1)
*/

/*class Main {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // Pointer to last valid element in nums1
        int i = m - 1;

        // Pointer to last element in nums2
        int j = n - 1;

        // Pointer to last position in nums1 (total capacity)
        int k = m + n - 1;

        *//*
         Why fill from back?

         If we fill from front:
         - We might overwrite values in nums1
           that we haven't compared yet.

         Filling from back ensures:
         - We only overwrite empty positions.
        *//*

        *//*
         We loop while nums2 still has elements.

         Why only check (j >= 0)?
         - If nums1 elements remain (i >= 0),
           they are already correctly placed.
         - But if nums2 elements remain,
           they MUST be copied.
        *//*
        while (j >= 0) {

            *//*
             IMPORTANT:
             Always check (i >= 0) before accessing nums1[i].

             If i < 0:
             - nums1 original elements are exhausted.
             - We must copy remaining nums2 elements.

             Forgetting this condition can cause
             ArrayIndexOutOfBoundsException.
            *//*
            if (i >= 0 && nums1[i] > nums2[j]) {

                // nums1[i] is larger → place it at back
                nums1[k] = nums1[i];
                i--;
                k--; //  // Move the fill pointer backward

            } else {

                // nums2[j] is larger OR nums1 exhausted
                nums1[k] = nums2[j];
                j--;
                k--;   // Move the fill pointer backward
            }
        }
    }

    public static void main(String[] args) {
        Main solution = new Main();
        int[] nums1A = {1,2,3,0,0,0};
        int[] nums2A = {2,5,6};
        solution.merge(nums1A, 3, nums2A, 3);
        System.out.println("Normal Case 1:");
        System.out.println(Arrays.toString(nums1A));
        System.out.println();

        // -------------------------
        // Normal Test Case 2
        // -------------------------
        int[] nums1B = {4,5,6,0,0,0};
        int[] nums2B = {1,2,3};
        solution.merge(nums1B, 3, nums2B, 3);
        System.out.println("Normal Case 2:");
        System.out.println(Arrays.toString(nums1B));
        System.out.println();
    }
    /*
 Problem:
 Given an array containing only 0s, 1s, and 2s,
 sort it in-place in one pass.

 0 → Red
 1 → White
 2 → Blue

 Constraints:
 - One pass
 - O(1) extra space
 - In-place

 --------------------------------------------------------

 Brute Force:
 Count number of 0s, 1s, and 2s in first pass,
 overwrite array in second pass.
 Time: O(n)
 Space: O(1)
 But requires two passes.

 --------------------------------------------------------

 Optimal Approach: Dutch National Flag Algorithm

 Maintain four regions:

 [ 0 zone | 1 zone | unknown | 2 zone ]

 Invariant at any time:

 0 .. low-1       → all 0s
 low .. mid-1     → all 1s
 mid .. high      → unprocessed elements
 high+1 .. end    → all 2s

 We shrink the "unknown" region until mid > high.

 Time Complexity: O(n)
 Space Complexity: O(1)
*/

class Solution {

    /*
        public void sortColors(int[] nums) {
     /*
        if 2 passes are allowed, we can keep:
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        //if interviewer allows two passes
        Single pass → count frequencies
        Second pass → overwrite array

        Time: O(n)
        Space: O(1) (technically constant)

        what if interveiwer asks to solve it in one pass, take 2nd solution
     */

    /* int count0 = 0;
     int count1 = 0;
     int count2 = 0;

     for(int i=0; i<nums.length; i++){
        if(nums[i] == 0){
            count0++;
        } else if(nums[i] == 1){
            count1++;
        } else {
            count2++;
        }
     }

     for(int i=0;i<nums.length; i++){
        if(count0 > 0) {
            nums[i] = 0;
            count0--;

        } else if(count1 > 0) {
            nums[i] = 1;
            count1--;

        } else if(count2 > 0){
            nums[i] = 2;
            count2--;

        }
     }

    } */


/*    public void sortColors(int[] nums) {

        // Edge case: null or trivial array
        if (nums == null || nums.length <= 1) return;

        int low = 0;                  // boundary for 0s
        int mid = 0;                  // current element being examined
        int high = nums.length - 1;   // boundary for 2s

        *//*
         Initially:
         - No 0s confirmed
         - No 2s confirmed
         - Entire array is unknown region
        *//*

        while (mid <= high) {

            *//*
             Case 1: nums[mid] == 0

             This element belongs to the 0-zone.
             Swap it with element at low pointer.

             After swap:
             - Element at index low is guaranteed correct (0).
             - The swapped value at mid must be 1
               (because low..mid-1 is all 1s by invariant).

             So we safely increment both low and mid.
            *//*
            if (nums[mid] == 0) {

                swap(nums, low, mid);

                low++;   // expand 0 zone
                mid++;   // move to next unknown element
            }

            *//*
             Case 2: nums[mid] == 1

             1 belongs in the middle zone.
             It is already in correct region.

             Just move mid forward.
            *//*
            else if (nums[mid] == 1) {

                mid++;
            }

            *//*
             Case 3: nums[mid] == 2

             This element belongs in the 2-zone.
             Swap it with element at high pointer.

             IMPORTANT:
             We DO NOT increment mid here.

             Why?
             Because the element swapped from high
             is unprocessed and must be examined next.
            *//*
            else { // nums[mid] == 2

                swap(nums, mid, high);

                high--;  // expand 2 zone

                // mid not incremented here intentionally
            }
        }
    }

    // Helper method to swap two elements
    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }*/


    /*
 Problem:
 Given people weights and boat weight limit,
 return minimum number of boats needed.
 Each boat can carry at most 2 people.

brute force:
        //brute force: to try all pairs.
        //this is too slow and exponential complexity

 optimal Approach:
 1. Sort the array.
 2. Use two pointers (lightest & heaviest).
 3. Try pairing them greedily.

 Time Complexity: O(n log n) due to sorting
 Space Complexity: O(1)
*/

    /*class Main {
        public int numRescueBoats(int[] people, int limit) {
            //brute force: to try all pairs.
            //this is too slow and exponential complexity

            //this is two pointer greedy problem
            //2-ptr greedy almost always need sorting first to make elimination easy

            //try with two pointer
            //deceptively easy
            //bc of sorting the time: o(nlogn), space: o(1)
            Arrays.sort(people); //sort first

            int left = 0; //lightest
            int right = people.length - 1; //heaviest
            int boats = 0;

            while (left <= right){
                if(people[left] + people[right] <= limit) {
                    left++;
                    right--;
                    boats++;
                } else {
                    right--;
                    boats++;
                }
            }
            return boats;
        }
    }
*/
}







