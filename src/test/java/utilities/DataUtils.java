package utilities;

import java.util.Random;

public class DataUtils {
    // this method will generate random email
    public static String generateEmail(){
        Random random=new Random();
        int emailId=random.nextInt(100000);
        String email="xyz"+emailId+"@gmail.com";
        return email;
    }
    //this method will generate random number between 1 and provided number
    //. generateRandomNumber(30)
    public static int generateRandomNumber(int range){
        Random random=new Random();
        int randomNum=random.nextInt(range);
        return randomNum;
    }
    //this method will remove $  from string and will convert it do double
    //.remove$AndConvertToDouble("$16.51);
    public static double remove$AndConvertToDouble(String amount){
        amount=amount.substring(1);
        return Double.parseDouble(amount);
    }
}
