import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Sorting {
	
	public static void bubbleSort(int[] input) {
		
		for(int i = 0; i<input.length; i++) {
			for(int j = 0; j<input.length -1 - i; j++) {
				if(input[j]>input[j+1]) {
					swap(input, j, j+1);
				}
			}
		}
		
	}
	
	public static void mergeSort(int[] input) {
		recurMerge(input, 0, input.length-1);
	}
	
	public static void parallelMergeSort(int[] input, int numThreads) {
		ForkJoinPool pool = new ForkJoinPool(numThreads);
		ForkJoinTask<Void> job = pool.submit(new MergeSortTask(input, 0, input.length-1));
		job.join();
	}
	
	private static void recurMerge(int[] input, int lo, int hi) {
		if(lo == hi) { //Base case, one element is always sorted
			return;
		} else {
			int mid = (lo+hi) / 2;
			recurMerge(input, lo, mid);
			recurMerge(input, mid+1, hi);
			merge(input, lo, mid, hi);
		}
	}
	
	/**
	 * Merges two subarrays in input within ranges lo - mid and mid - hi
	 */
	protected static void merge(int[] input, int lo, int mid, int hi) {
		int left = lo;
		int right = mid+1;
		
		while(left<=mid && right<=hi) {
			if(input[left] <= input[right]) {
				left++;
			} else {
				int temp = input[right];
				int index = right;
				while(index != left) {
					input[index] = input[--index];
				}
				
				input[index] = temp;
				left++;
				mid++;
				right++;	
			}
		}
	}
	
	/**
	 * Swaps keys in a given array at two given locations
	 */
	public static void swap(int[] input, int x, int y) {
		int temp = input[x];
		input[x] = input[y];
		input[y] = temp;
	}
	
}

class MergeSortTask extends RecursiveAction {

	final int[] input;
	final int lo;
	final int hi;
	
	public MergeSortTask(int[] input, int lo, int hi) {
		this.input = input;
		this.lo = lo;
		this.hi = hi;
	}
	
	@Override
	protected void compute() {
		if(lo == hi) {
			return;
		} else {
			int mid = (lo+hi) / 2;
			invokeAll(new MergeSortTask(input, lo, mid), new MergeSortTask(input, mid+1, hi));
			Sorting.merge(input, lo, mid, hi);
		}
	}
	
}
