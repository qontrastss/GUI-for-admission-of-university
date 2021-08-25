package LoginPage;

import java.util.ArrayList;
import java.util.List;

public class Calculations {

    public static int calculateScholarship(int school_gpa,int numOfFamilyMembers,String finantial_situation){//Calculates inserted data according to family members, financial situation and school gpa

        if(school_gpa>4) {//the statements determines the scholarship value
            if (numOfFamilyMembers >= 4){
                if(finantial_situation.equals("Low")){
                    return 80;
                }
                else if(finantial_situation.equals("Medium")){
                    return 60;
                }
                else if(finantial_situation.equals("High")){
                    return 30;
                }
            }
            else {
                if(finantial_situation.equals("Low")){
                    return 60;
                }
                else if(finantial_situation.equals("Medium")){
                    return 30;
                }
                else if(finantial_situation.equals("High")){
                    return 10;
                }
            }
        }
        else {
            if (numOfFamilyMembers >= 4){
                if(finantial_situation.equals("Low")){
                    return 50;
                }
                else if(finantial_situation.equals("Medium")){
                    return 30;
                }
                else if(finantial_situation.equals("High")){
                    return 10;
                }
            }
            else {
                if(finantial_situation.equals("Low")){
                    return 40;
                }
                else if(finantial_situation.equals("Medium")){
                    return 10;
                }
                else if(finantial_situation.equals("High")){
                    return 0;
                }
            }
        }
        return 0;
    }

    public static int calculateTuitionFee(int scholarship){//Calculates the tuition fee according to scholarship
        int tuitionFee=1000000;
        return tuitionFee-(tuitionFee*scholarship/100);
    }

    public static String calculateExamStatus(List<Integer> scoresOfExam){//Determines exam status according to scores of exam
        boolean check=false;
        for(int score : scoresOfExam){
        if(score<25){
            return "Failed";
        }
    }
        return "Passed";
    }

}
