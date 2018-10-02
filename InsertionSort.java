public class InsertionSort implements SortingAlgorithm{

	public void sort(int[] a){
		insertion_sort(a);
	}

	public void insertion_sort(int[] a){
		//Work left to right, compare each item to its left and insert the item into its correct position
		for(int i = 1; i< a.length; i++){

			int temp = a[i];
			int k = i-1;
			
			while(k>=0 && a[k] > temp){
				a[k+1] = a[k];
				k--;
			}
			a[k+1] = temp;
		}
	}
}