package predictionFinal;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class mainFunction {
    public static void main(String[] args) throws FileNotFoundException {
    	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");  
    	Date date = new Date(System.currentTimeMillis());  
    	 
        System.out.println("Process started at:");
        System.out.println(formatter.format(date));
        Calendar.getInstance().toString();
    	fileWrite f1 = new fileWrite();
    	System.out.println("Process ended at:");
    	System.out.println(formatter.format(date));
        
//        System.out.print(Calendar.getInstance().getTime().toString());


    }
}
