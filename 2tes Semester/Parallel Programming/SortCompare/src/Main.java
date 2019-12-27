import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {
		
		FileWriter write = new FileWriter("SortComparison.txt");
		PrintWriter print_ln = new PrintWriter(write);
		
		print_ln.printf("Test ran on %d cores \n \n", Runtime.getRuntime().availableProcessors());
		
		for(int i = 0, j = 10; i<17; i++, j*=2) {
			
			print_ln.print("----------------------------\n");
			
			print_ln.print("Array of size: " + j + "\n \n");
			
			int[] input = randArray(j);
			StopWatch watch = new StopWatch();
			
			if(i <= 14) {
				int[] arr1 = Arrays.copyOf(input, input.length);
				watch.Reset();
				Sorting.bubbleSort(arr1);
				print_ln.printf("BubbleSort took %d ms \n", watch.Stop());
			} else {
				print_ln.print("BubbleSort omitted! \n");
			}
			
			int[] arr2 = Arrays.copyOf(input, input.length);
			watch.Reset();
			Sorting.mergeSort(arr2);
			print_ln.printf("Sequencial mergesort took %d ms \n", watch.Stop());
			
			int[] arr3 = Arrays.copyOf(input, input.length);
			watch.Reset();
			Sorting.parallelMergeSort(arr3, Runtime.getRuntime().availableProcessors());
			print_ln.printf("Parallel mergesort took %d ms \n", watch.Stop());
			
			System.out.println(i+1);
		}
		
		print_ln.close();

	}
	
	public static int[] randArray(int size) {
		Random random = new Random();
		int[] out = new int[size];
		
		for(int i = 0; i<size; i++) {
			out[i] = random.nextInt(2*size);
		}
		
		return out;
	}
}

class StopWatch {
	long initTime;
	
	public StopWatch() {
		this.initTime = System.currentTimeMillis();
	}
	
	public long Stop() {
		return System.currentTimeMillis() - initTime;
	}
	
	public void Reset() {
		initTime = System.currentTimeMillis();
	}
}
