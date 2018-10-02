public class QuickSort implements SortingAlgorithm{

	public void sort(int[] a){
		quick_sort(a,0,a.length -1);
	}
	public void quick_sort(int[] arr, int low, int high){
		if(low < high){
			int partition_index = partition(arr,low,high);
			quick_sort(arr, low, partition_index-1);
			quick_sort(arr, partition_index+1, high);
		}
	}
	public int partition(int[] arr, int low, int high){
		int pivot = low;//can use low, high, middle
		int lower = low + 1;
		int upper = high;

		while(lower < upper){
			while(lower <= upper && arr[lower] <= arr[pivot]){
				//Keep incrementing towards pivot until value is greater than pivot
				lower++;
			}
			while(upper >= lower && arr[upper] >= arr[pivot]){
				//Keep decremeting upper index until value is less than pivot
				upper--;
			}
			if(lower < upper){
				swap(arr,lower,upper);//Swap the two values that were found
				lower++;
				upper--;
			}
		}
		//Swap the pivot into final position
		swap(arr,upper,pivot);
		return upper;
	}
	public void swap(int[] arr, int a, int b){
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}