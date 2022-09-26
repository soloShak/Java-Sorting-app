
public class BubbleSort extends SortingAlgorithm {

	@Override
	public String Code() {
		// TODO Auto-generated method stub
		return "Bubble Sort(arr, size)\r\n" + "1    for i=0 to n-i-1\r\n" + "2        for j=0 to n-i-2\r\n"
				+ "3            if arr[j]>arr[j+1]\r\n" + "4                Swap arr[j] and arr[j+1]";
	}

	@Override
	public String Description() {
		// TODO Auto-generated method stub
		return "Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in the wrong order. "
				+ "This algorithm is not suitable for large data sets as its average and worst case time complexity is quite high.";
	}

	// ==============================================
	// Following lines are BubbleSort implementation
	// ==============================================
	public void Sort(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1])
					swap(arr, i, j);
		setOutput(arr);// setting the sorted array as output variable
	}

	void swap(int[] arr, int i, int j) {
		int temp = arr[j];
		arr[j] = arr[j + 1];
		arr[j + 1] = temp;
	}
}
