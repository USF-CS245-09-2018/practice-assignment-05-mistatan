public class MergeSort implements SortingAlgorithm{

	public void sort(int[] a){
		merge_sort(a,0,a.length-1);
	}
	public void merge_sort(int[] a, int left, int right){

		if(left < right){
			int mid = (left + right)/2;
			merge_sort(a, left, mid);
			merge_sort(a, mid+1, right);
			merge(a, left, mid, right);
		}
	}
	public void merge(int[] arr, int left, int middle, int right){
		int a1_size = middle + 1 - left;
		int a2_size = right - middle;

		//Create new temp sub arrays
		int[] left_ar = new int[a1_size];
		int[] right_ar = new int[a2_size];

		//copy over units
		for(int i = 0; i < a1_size; i++){
			left_ar[i] = arr[left+i];
		}
		for(int k = 0; k < a2_size; k++){
			right_ar[k] = arr[middle+1+k];
		}

		int left_ar_index = 0; 
		int right_ar_index = 0;
		int k = left;

		//Compare and merge in order
		while(left_ar_index < a1_size && right_ar_index < a2_size){
			if(left_ar[left_ar_index] <= right_ar[right_ar_index]){
				arr[k] = left_ar[left_ar_index];
				left_ar_index++;
			}else{
				arr[k] = right_ar[right_ar_index];
				right_ar_index++;
			}
			k++;
		}

		//Copy the rest of the bits if the arrays are uneven in length 
		while(left_ar_index < a1_size){
			arr[k++] = left_ar[left_ar_index++];
		}
		while(right_ar_index < a2_size){
			arr[k++] = right_ar[right_ar_index++];
		}

	}
}