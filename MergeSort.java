
public class MergeSort extends SortingAlgorithm {

	@Override
	public String Code() {
		// TODO Auto-generated method stub
		return "MergeSort(arr, left, right):\r\n" + "1    if left > right \r\n" + "2        return\r\n"
				+ "3    mid = (left+right)/2\r\n" + "4    mergeSort(arr, left, mid)\r\n"
				+ "5    mergeSort(arr, mid+1, right)\r\n" + "6    merge(arr, left, mid, right)\r\n" + "end";
	}

	@Override
	public String Description() {
		// TODO Auto-generated method stub
		return "Merge Sort is a Divide and Conquer algorithm. "
				+ "It divides the input array into two halves, calls itself for the two halves, and then it merges the two sorted halves. "
				+ "The merge() function is used for merging two halves.";
	}

	// ==============================================
	// Following lines are mergeSort implementation
	// ==============================================
	public void Merge(int arr[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		int L[] = new int[n1];
		int R[] = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		int i = 0, j = 0;
		int k = l;
		
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++; k++;
		}
		while (j < n2) {
			arr[k] = R[j];
			j++; k++;
		}
	}

	public void Sort(int arr[], int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;
			Sort(arr, l, m);
			Sort(arr, m + 1, r);
			Merge(arr, l, m, r);
		}
		setOutput(arr);// setting the sorted array as output variable
	}

}
