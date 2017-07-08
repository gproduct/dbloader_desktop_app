import java.util.Arrays;

public class debug {
	public static void log(String x){
		/*
		 *  log(String x)
		 *  
		 *  A shorter way to print something to the console
		 *  
		 *  @parameter
		 *  String x - print x to the terminal
		 */
		System.out.println(x);
	}
	public static void log_array(String[] x){
		/*
		 *  log_array(String[] x)
		 * 
		 *  Shorter way of logging an array
		 * 
		 *  @parameter
		 *  String[] x - printing x array to console
		 */
		System.out.println(Arrays.toString(x));
	}
}
