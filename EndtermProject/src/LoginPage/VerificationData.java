package LoginPage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerificationData {

    public boolean verifyFields(RegisterPage register) {//checks the fields are they empty
    String username = register.inputUsername.getText();//Store inputted data in variable
    String password = register.inputPassword.getText();
    String name = register.inputName.getText();
    String surname = register.inputSurname.getText();
    String faculty = register.inputFaculty.getText();
    String school_gpa = register.inputGPA.getText();
    String family_members = register.inputNumberOfMembers.getText();
    String scoreOfMath = register.mathTextField.getText();
    String scoreOfPhysics = register.physicsTextField.getText();
    String scoreOfEnglish = register.englishTextField.getText();
    boolean checkGenderButtons = false;
    boolean checkFinSituationButtons = false;

    if (!register.inputGenderButton.isSelected() && !register.inputGenderButton2.isSelected())
        checkGenderButtons = true;//checks buttons

    if (!register.inputFinSituation1.isSelected() && !register.inputFinSituation2.isSelected() && !register.inputFinSituation3.isSelected())//checks buttons
        checkFinSituationButtons = true;

    if (username.equals("") || password.equals("") || name.equals("") || surname.equals("") || faculty.equals("") || school_gpa.equals("") || family_members.equals("") || scoreOfMath.equals("") || scoreOfPhysics.equals("") || scoreOfEnglish.equals("") || checkGenderButtons || checkFinSituationButtons) { //checks all fields are they empty
        JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty fields", 2);
        return false;
    } else return true;
    }

    public boolean checkUsername(String username) throws SQLException {//checks username to existence
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Boolean username_exist = false;

        String SQL = "SELECT * FROM applicant WHERE username=?";

        DatabaseConnection db = DatabaseConnection.getInstance();
        con = db.getConnection();//connects with database
        pstmt = con.prepareStatement(SQL);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();

        try {
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {//If username exists, it will show message
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This username already taken, choose another one", "Username failed", 2);
            }

        } catch (SQLException e) {
            Logger.getLogger(RegisterPage.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DatabaseConnection.closeConnection(con, pstmt, rs);
        }
        return username_exist;
    }
}
