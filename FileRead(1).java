package predictionFinal;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.*;
import java.time.temporal.ChronoUnit;


public class FileRead {
    ArrayList<Integer> Weeklist = new ArrayList<>();
    ArrayList<Double> salesList = new ArrayList<>();
    ArrayList<Integer> holidayWeek = new ArrayList<>();
    ArrayList<Double> holidaySales = new ArrayList<>();
    private int firstYear;
    public void read() throws FileNotFoundException {
        String csvFile = "train.csv";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String token = ",";

        BufferedReader br= new BufferedReader(new FileReader(csvFile));;



        String line="";
        String [] item = new String[5];
        LocalDate startDate=LocalDate.parse("2010-02-05");

        {
            try {
                line = br.readLine();//This will bring to the first line which is not useful
                while ((line =br.readLine())!= null) {

                    // System.out.println(line.toString());
                    item = line.split(",");
                    String store = item[0];
                    String department = item[1];
                    String date = item[2];
                    double sales= Double.parseDouble(item[3]);
                    String isTrue = item[4].toLowerCase();
                    //System.out.println(isTrue);
                    boolean isHoliday = Boolean.valueOf(isTrue);
                    //System.out.println(isHoliday);
                    int replacedDate = Integer.valueOf(date.replace("-", ""));
                    // System.out.println(replacedDate);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(sdf.parse(date));
                    /*if(Weeklist.size()==1) {
                        startDate=LocalDate.parse(date);
                    }*/
                    LocalDate currentDate = LocalDate.parse(date);
                    long noOfDaysBetween = ChronoUnit.DAYS.between(startDate, currentDate);
                    //System.out.println(noOfDaysBetween);
                    //int currentYear = c1.get(Calendar.YEAR);
                    //System.out.println(c1.getTime());
                    int weekNumber = c1.get(Calendar.WEEK_OF_YEAR);
                    /*switch (currentYear-firstYear){
                        case 0:
                            break;
                        case 1:
                            weekNumber+=52;
                            break;
                        case 2:
                            weekNumber+=104;
                            break;
                    }*/

                    // System.out.println(weekNumber);
                    if (isHoliday==false) {
                        Weeklist.add((int) noOfDaysBetween);
                        salesList.add(sales);
                    } else if (isHoliday==true){
                        holidayWeek.add((int)noOfDaysBetween);
                        holidaySales.add(sales);
                    }


                }




            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }finally {
                //System.out.println("This is the end of train class.");
            }
        }
    }
public int getFirstYear(){
        return firstYear;
}
}
