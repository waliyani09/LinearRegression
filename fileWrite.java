package predictionFinal;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.time.temporal.ChronoUnit;

public class fileWrite  {
	public fileWrite() {
		try {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String csvFile = "test1.csv";
			String output = "output.csv";
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String token = ",";
	        BufferedReader br = new BufferedReader(new FileReader(csvFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
	        String line="";
	        String [] item = new String[4];
	        LocalDate startDate=LocalDate.parse("2010-02-05");
	        line = br.readLine();//This will bring to the first line which is not useful
	        FileRead f1 = new FileRead();
            f1.read();
            LinearRegression l1 = new LinearRegression(f1.Weeklist,f1.salesList);
            LinearRegression l2 = new LinearRegression(f1.holidayWeek,f1.holidaySales);
            System.out.println("For non-holiday:");
            System.out.println(l1.toString());
            //System.out.println(l1.R2());
            //System.out.println("Slope = " + l1.slope() + " Intercept = " + l1.intercept());
            System.out.println("For Holiday:");
            System.out.println(l2.toString());
            bw.write("Day since " + startDate);
            bw.write(token);
            bw.write("Estimated sales");
            bw.write(token);
            bw.write("IS_HOLIDAY");
            bw.write("\n");
            while ((line =br.readLine())!= null) {

                // System.out.println(line.toString());
                item = line.split(",");
                String store = item[0];
                String department = item[1];
                String date = item[2];
               // double sales= Double.parseDouble(item[3]);
                String isTrue = item[3].toLowerCase();
                //System.out.println(isTrue);
                boolean isHoliday = Boolean.valueOf(isTrue);
                Calendar c1 = Calendar.getInstance();
                c1.setTime(sdf.parse(date));
                LocalDate currentDate = LocalDate.parse(date);
                long noOfDaysBetween = ChronoUnit.DAYS.between(startDate, currentDate);
                String day = Long.toString(noOfDaysBetween);
                String prediction = Double.toString(l1.predict((int)noOfDaysBetween));
                String holidayPrediction = Double.toString(l2.predict((int)noOfDaysBetween));
                if (isHoliday==false) {
                	bw.write(day);
                	bw.write(token);
                    bw.write(prediction);
                    bw.write(token);
                    bw.write(isTrue);
                    bw.write("\n");
                } else if (isHoliday==true){
                	bw.write(day);
                	bw.write(token);
                    bw.write(holidayPrediction);
                    bw.write(token);
                    bw.write(isTrue);
                    bw.write("\n");
                }
            }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
