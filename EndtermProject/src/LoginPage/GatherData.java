package LoginPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GatherData {

    public void setData(ApplicantInfoPage openedInfoPage, String username, String password) {//Sets data to panel
        int applicant_id;
        try {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            String SQL = "SELECT applicant_id FROM applicant WHERE username=? AND password=?";

            DatabaseConnection db = DatabaseConnection.getInstance();
            con =db.getConnection();
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            rs.next();
            applicant_id = rs.getInt("applicant_id");//determines applicant id according to username and password


            String SQL1 = "SELECT name,surname,gender,faculty FROM applicant WHERE applicant_id=?";

            pstmt = con.prepareStatement(SQL1);
            pstmt.setInt(1, applicant_id);
            rs = pstmt.executeQuery();
            rs.next();
            openedInfoPage.NameBox.setText(rs.getString("name"));
            openedInfoPage.SurnameBox.setText(rs.getString("surname"));
            openedInfoPage.GenderBox.setText(rs.getString("gender"));
            openedInfoPage.FacultyBox.setText(rs.getString("faculty"));


            String SQL2 = "SELECT member_of_family,finantial_situation FROM social_passport WHERE social_passport_id=?";

            pstmt = con.prepareStatement(SQL2);
            pstmt.setInt(1, applicant_id);
            rs = pstmt.executeQuery();
            rs.next();
            openedInfoPage.FamilyBox.setText(rs.getString("member_of_family"));
            openedInfoPage.FinSituationBox.setText(rs.getString("finantial_situation"));


            String SQL3 = "SELECT scholarship,school_gpa FROM scholarship WHERE scholarship_id=?";

            pstmt = con.prepareStatement(SQL3);
            pstmt.setInt(1, applicant_id);
            rs = pstmt.executeQuery();
            rs.next();
            openedInfoPage.ScholarshipBox.setText(rs.getString("scholarship") + "%");
            openedInfoPage.GPABox.setText(rs.getString("school_gpa"));


            String SQL4 = "SELECT total_fee FROM tuition_fee WHERE tuition_fee_id=?";

            pstmt = con.prepareStatement(SQL4);
            pstmt.setInt(1, applicant_id);
            rs = pstmt.executeQuery();
            rs.next();
            openedInfoPage.FeeBox.setText(rs.getString("total_fee") + "tg");


            String SQL5 = "SELECT math_score,physic_score,english_score,exam_status FROM entrance_exam WHERE entrance_exam_id=?";

            pstmt = con.prepareStatement(SQL5);
            pstmt.setInt(1, applicant_id);
            rs = pstmt.executeQuery();
            rs.next();
            openedInfoPage.MathBox.setText(rs.getString("math_score"));
            openedInfoPage.PhycixsBox.setText(rs.getString("physic_score"));
            openedInfoPage.EnglishBox.setText(rs.getString("english_score"));
            openedInfoPage.ExamBox.setText(rs.getString("exam_status"));

            DatabaseConnection.closeConnection(con, pstmt, rs);//closes connection
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
