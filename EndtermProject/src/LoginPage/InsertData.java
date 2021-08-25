package LoginPage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertData {//Class that works with data to insert to database
    public void insertData(RegisterPage register) throws SQLException {

        String username = register.inputUsername.getText();//Stores inputted data in variable
        String password = register.inputPassword.getText();
        String name = register.inputName.getText();
        String surname = register.inputSurname.getText();
        String faculty = register.inputFaculty.getText();
        int school_gpa = Integer.parseInt(register.inputGPA.getText());
        int family_members = Integer.parseInt(register.inputNumberOfMembers.getText());
        int scoreOfMath = Integer.parseInt(register.mathTextField.getText());
        int scoreOfPhysics = Integer.parseInt(register.physicsTextField.getText());
        int scoreOfEnglish = Integer.parseInt(register.englishTextField.getText());
        List<Integer> scoresOfExam = new ArrayList<Integer>();//Store all scores of exam in one list
        scoresOfExam.add(scoreOfMath);
        scoresOfExam.add(scoreOfPhysics);
        scoresOfExam.add(scoreOfEnglish);
        String gender = "Female";
        String finSituation = "Low";

        if (register.inputGenderButton2.isSelected()) {//Compares the radio buttons
            gender = "Male";
        }
        if (register.inputFinSituation1.isSelected()) {//Compares the radio buttons
            finSituation = "High";
        } else if (register.inputFinSituation2.isSelected()) {
            finSituation = "Medium";
        }


        int scholarship = Calculations.calculateScholarship(school_gpa, family_members, finSituation);//calculates the scholarship
        int tuition_fee = Calculations.calculateTuitionFee(scholarship);//calculates the tuition fee
        String examStatus = Calculations.calculateExamStatus(scoresOfExam);//calculates the exam status


        VerificationData verObject = new VerificationData();
        if (verObject.verifyFields(register)) {//checks empty fields
            if (!verObject.checkUsername(username)) {//checks username to existence
                try {
                    Connection con = null;
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;

                    String SQL1 = "INSERT INTO social_passport(member_of_family,finantial_situation) VALUES (?,?)";
                    String SQL2 = "INSERT INTO scholarship(scholarship,school_gpa) VALUES (?,?)";
                    String SQL3 = "INSERT INTO tuition_fee(total_fee) VALUES (?)";
                    String SQL4 = "INSERT INTO entrance_exam(math_score,physic_score,english_score,exam_status) VALUES (?,?,?,?)";
                    String SQL5 = "INSERT INTO applicant(name,surname,gender,faculty,username,password) VALUES (?,?,?,?,?,?)";

                    DatabaseConnection db = DatabaseConnection.getInstance();//connects with database
                    con =db.getConnection();
                    pstmt = con.prepareStatement(SQL1);
                    pstmt.setInt(1, family_members);
                    pstmt.setString(2, finSituation);
                    pstmt.executeUpdate();

                    pstmt = con.prepareStatement(SQL2);
                    pstmt.setInt(1, scholarship);
                    pstmt.setInt(2, school_gpa);
                    pstmt.executeUpdate();

                    pstmt = con.prepareStatement(SQL3);
                    pstmt.setInt(1, tuition_fee);
                    pstmt.executeUpdate();

                    pstmt = con.prepareStatement(SQL4);
                    pstmt.setInt(1, scoreOfMath);
                    pstmt.setInt(2, scoreOfPhysics);
                    pstmt.setInt(3, scoreOfEnglish);
                    pstmt.setString(4, examStatus);
                    pstmt.executeUpdate();

                    pstmt = con.prepareStatement(SQL5);
                    pstmt.setString(1, name);
                    pstmt.setString(2, surname);
                    pstmt.setString(3, gender);
                    pstmt.setString(4, faculty);
                    pstmt.setString(5, username);
                    pstmt.setString(6, password);

                    if (pstmt.executeUpdate() != 0) {//if the data inserted correctly, it will show message
                        register.dispose();
                        WelcomePage openedWelcomePage = new WelcomePage("Applicant admission app");
                        openedWelcomePage.createFrame();
                        JOptionPane.showMessageDialog(null, "Your account has been created!");

                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Check your box");
                    }

                    DatabaseConnection.closeConnection(con, pstmt, rs);//close connection
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
