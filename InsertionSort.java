public class InsertionSort extends SortingAlgorithm {

	public InsertionSort() {
		super();
	}
	
	@Override
	public String Code() {
		// TODO Auto-generated method stub
		return "INSERTION-SORT (A)\r\n" + "1  for j <- 2 to length[A]\r\n" + "2       do key <- A[j]\r\n"
				+ "3         Insert A[j] into the sorted sequence A[1..j-1].\r\n" + "4        i <- j - 1\r\n"
				+ "5        while i > 0 and A[i] > key\r\n" + "6           do A[i + 1] <- A[i]\r\n"
				+ "7              i <- i - 1\r\n" + "8        A[i + 1] <- key";
	}

	@Override
	public String Description() {
		// TODO Auto-generated method stub
		return "Insertion sort is a simple sorting algorithm that works similar to the way you sort playing cards in your hands. "
				+ "The array is virtually split into a sorted and an unsorted part. "
				+ "Values from the unsorted part are picked and placed at the correct position in the sorted part.";
	}

	// ==============================================
	// Following lines are insertionSort implementation
	// ==============================================
	public void Sort(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;

			while ((j >= 0) && (arr[j] > key)) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
		setOutput(arr);// setting the sorted array as output variable
	}
	
}
