
public class SelectionSort extends SortingAlgorithm {

	@Override
	public String Code() {
		// TODO Auto-generated method stub
		return "SelectionSort(array A) \r\n"
			+  "1    n = length[A]\r\n"
			+  "2    for j = 1 to n-1\r\n"
			+  "3        do smallest = j\r\n"
			+  "4            for i = j+1 to n\r\n"
			+  "5                do if A[i] < A[smallest]\r\n"
			+  "6                    then smallest = i\r\n"
			+  "7            swap (A[j], A[smallest])";
	}

	@Override
	public String Description() {
		// TODO Auto-generated method stub
		return "A Sorting Algorithm is used to rearrange a given array or list elements according to a comparison operator on the elements. "
				+ "The comparison operator is used to decide the new order of element in the respective data structure.";
	}

	// ===================================================
	// Following lines are SelectionSort implementation
	// ===================================================
	public void Sort(int arr[]) {
		int n = arr.length;
		
		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (arr[j] < arr[min_idx])
					min_idx = j;
			swap(arr, min_idx, i);
		}
		setOutput(arr);
	}
	
	void swap(int arr[], int min_idx, int i) {
		int temp = arr[min_idx];
		arr[min_idx] = arr[i];
		arr[i] = temp;
	}
}
