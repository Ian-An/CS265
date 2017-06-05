import java.util.Date;

public class prob4 {
	public static void main( String[] args ) {
		int code = Integer.parseInt(args[0]);
		Date date = new Date();
		long milliseconds = date.getTime();
		long seconds = milliseconds/1000;
		int  days = (int)(seconds/(3600*24));
		switch(code) {
			case 0:
			System.out.println("number of milliseconds since January 1,1970:  " + milliseconds);
			break;
			case 1:
			System.out.println("number of seconds since January 1, 1970:  " + milliseconds/1000);
			break;
			case 2:
			System.out.println("number of days since January 1, 1970:  " + days);
			break;
			case 3:
			System.out.println("current Date/Time:  " + date.toString());
			break; 
		}
		
	}
}
