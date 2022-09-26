
public class HeapSort extends SortingAlgorithm {

	@Override
	public String Code() {
		// TODO Auto-generated method stub
		return "Heapsort(A) {\r\n" + "1    BuildHeap(A)\r\n" + "2    for i <- length(A) downto 2 \r\n"
				+ "3       exchange A[1] <-> A[i]\r\n" + "4       heapsize <- heapsize -1\r\n"
				+ "5       Heapify(A, 1)\r\n" + "6  }\r\n" + "\r\n" + "Heapify(A, i) {\r\n" + "7    le <- left(i)\r\n"
				+ "8    ri <- right(i)\r\n" + "9    if (le<=heapsize) and (A[le]>A[i])\r\n"
				+ "10       largest <- le\r\n" + "11   else\r\n" + "12      largest <- i \r\n"
				+ "13   if (ri<=heapsize) and (A[ri]>A[largest])\r\n" + "14      largest <- ri\r\n"
				+ "15   if (largest != i) \r\n" + "16      exchange A[i] <-> A[largest]\r\n"
				+ "17      Heapify(A, largest)\r\n" + "18 }";
	}

	@Override
	public String Description() {
		// TODO Auto-generated method stub
		return "Heap sort is a comparison-based sorting technique based on Binary Heap data structure. "
				+ "It is similar to selection sort where we first find the minimum element and place the minimum element at the beginning. "
				+ "We repeat the same process for the remaining elements.";
	}

	// ==============================================
	// Following lines are HeapSort implementation
	// ==============================================
	public void Sort(int arr[]) {
		int n = arr.length;

		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);
		for (int i = n - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0);
		}
		setOutput(arr);// setting the sorted array as output variable
	}

	void heapify(int arr[], int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if (l < n && arr[l] > arr[largest])
			largest = l;

		if (r < n && arr[r] > arr[largest])
			largest = r;

		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			heapify(arr, n, largest);
		}
	}
}
