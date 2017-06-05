public class prob3 {
	public static void main( String[] args) {
		boolean isLeap;
		int year = Integer.parseInt(args[0]);
		if (year % 4 != 0) {
   			  isLeap  = false;
  		} else if (year % 400 == 0) {
  			  isLeap = true;
  		} else if (year % 100 == 0) {
    			isLeap = false;
 		} else {
   			isLeap = true;
  		}
		if ( isLeap == true) {
			System.out.println("leap year!");
		} else {
			System.out.println("not a leap year!");
		}
	}
}
