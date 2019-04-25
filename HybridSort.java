/**
 * Hybrid Sorting Algorithm is a combination of merge sort and insertion sort.
 * The algorithm operates by recursively splitting the array into sub-arrays
 * until they are less than or equal to the run size (64 for best results.)
 * Those sub-arrays are then sorted using insertion sort as it's the best
 * in-place sorting algorithm and operates faster than selection sort on small
 * arrays. The early termination feature of insertion sort also beats out bubble
 * sort (when no swaps are made.) After the sub-arrays are sorted, they are
 * merge back together using the merge step of merge sort.
 *
 * @author Michael Tan
 *
 */
public class HybridSort implements SortingAlgorithm {

	/**
	 * Limit for how large a sub array can be. Powers of 2: 16,32,64,128 seem to
	 * work best
	 */
	private static final int RUNSIZE = 64;

	@Override
	public void sort(int[] a) {
		hybrid_sort(a, 0, a.length - 1, RUNSIZE);
	}

	/**
	 * A recursive sorting algorithm that divides an array into sub-arrays less that
	 * or equal to the run-size, sorts the sub-arrays using insertion sort, then
	 * merges them back together with the merge step from merge sort.
	 *
	 * @param arr     - Array to sort
	 * @param left    - Left Index
	 * @param right   - Right Index
	 * @param runsize - Limiting size for sub arrays
	 */
	public void hybrid_sort(int[] arr, int left, int right, int runsize) {
		if ((right - left) > runsize) {
			int mid = left + (right - left) / 2;

			// Recursively sub divide the array until sub-array sizes are less than or equal
			// to run-size
			hybrid_sort(arr, left, mid, runsize);
			hybrid_sort(arr, mid + 1, right, runsize);

			// Sub arrays are then merges back together in the fashion of (run1 + run2) +
			// (run3 + run4)
			merge(arr, left, mid, right);
		} else {
			// Insertion sort allows for efficient in-place sorting of sub arrays
			insertion_sort(arr, left, right);
		}
	}

	/**
	 * A slightly modified version of Insertion Sort which accepts start and end
	 * positions
	 *
	 * @param a     - Array
	 * @param start - starting index
	 * @param end   - ending index
	 */
	public void insertion_sort(int[] a, int start, int end) {
		for (int i = start + 1; i <= end; i++) {
			int temp = a[i];
			int k = i - 1;
			// Instead of k >= 0, did k >= start
			while (k >= start && temp < a[k]) {
				a[k + 1] = a[k];
				--k;
			}
			a[k + 1] = temp;
		}
	}

	/**
	 * Merge step from merge sort
	 *
	 * @param arr    - Array to merge
	 * @param left   - left pointer
	 * @param middle - middle pointer
	 * @param right  - right pointer
	 */
	public void merge(int[] arr, int left, int middle, int right) {
		int a1_size = middle + 1 - left;
		int a2_size = right - middle;

		// Create new temp sub arrays
		int[] left_ar = new int[a1_size];
		int[] right_ar = new int[a2_size];

		// copy over units
		for (int i = 0; i < a1_size; i++) {
			left_ar[i] = arr[left + i];
		}
		for (int k = 0; k < a2_size; k++) {
			right_ar[k] = arr[middle + 1 + k];
		}

		int left_ar_index = 0;
		int right_ar_index = 0;
		int k = left;

		// Compare and merge in order
		while (left_ar_index < a1_size && right_ar_index < a2_size) {
			if (left_ar[left_ar_index] <= right_ar[right_ar_index]) {
				arr[k] = left_ar[left_ar_index];
				left_ar_index++;
			} else {
				arr[k] = right_ar[right_ar_index];
				right_ar_index++;
			}
			k++;
		}

		// Copy the rest of the bits if the arrays are uneven in length
		while (left_ar_index < a1_size) {
			arr[k++] = left_ar[left_ar_index++];
		}
		while (right_ar_index < a2_size) {
			arr[k++] = right_ar[right_ar_index++];
		}
	}

}