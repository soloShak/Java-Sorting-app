
public class QuickSort extends SortingAlgorithm {

	public QuickSort() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Code() {
		// TODO Auto-generated method stub
		return "quickSort(arr, beg, end)\r\n" + "1  if (beg < end)\r\n"
				+ "2    pivotIndex = partition(arr,beg, end)\r\n" + "3    quickSort(arr, beg, pivotIndex)\r\n"
				+ "4    quickSort(arr, pivotIndex + 1, end)\r\n" + "\r\n" + "partition(arr, beg, end)\r\n"
				+ "1  set end as pivotIndex\r\n" + "2  pIndex = beg - 1\r\n" + "3  for i = beg to end-1\r\n"
				+ "4  if arr[i] < pivot\r\n" + "5    swap arr[i] and arr[pIndex]\r\n" + "6    pIndex++\r\n"
				+ "7  swap pivot and arr[pIndex+1]\r\n" + "8 return pIndex + 1";
	}

	@Override
	public String Description() {
		// TODO Auto-generated method stub
		return "Like Merge Sort, QuickSort is a Divide and Conquer algorithm. "
				+ "It picks an element as pivot and partitions the given array around the picked pivot."
				+ "The key process in quickSort is partition().";
	}

	// ==============================================
	// Following lines are QuickSort implementation
	// ==============================================
	void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	int partition(int[] arr, int low, int high) {
		int pivot = arr[high];

		int i = (low - 1);

		for (int j = low; j <= high - 1; j++) {

			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	public void Sort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			Sort(arr, low, pi - 1);
			Sort(arr, pi + 1, high);
		}
		setOutput(arr);// setting the sorted array as output variable
	}

}
