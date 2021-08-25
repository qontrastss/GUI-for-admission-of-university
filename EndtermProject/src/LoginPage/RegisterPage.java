package LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterPage extends JFrame implements Pages {//Class of Register page
    public JPanel insidePanelOfRegisterPage;//Element of GUI
    public JLabel LoginLabel;
    public JTextField inputUsername;
    public JTextField inputName;
    public JTextField inputSurname;
    public JTextField inputPassword;
    public JRadioButton inputGenderButton2;
    public JRadioButton inputGenderButton;
    public JTextField inputFaculty;
    public JTextField inputGPA;
    public JTextField inputNumberOfMembers;
    public JTextField mathTextField;
    public JTextField englishTextField;
    public JTextField physicsTextField;
    public JRadioButton inputFinSituation1;
    public JRadioButton inputFinSituation2;
    public JRadioButton inputFinSituation3;
    public JLabel Label10;
    public JButton registerButton;
    public JPanel mainPanelOfRegisterPage;
    public JButton cancelButton;

    public RegisterPage(){}//Constructor without parameter

    public RegisterPage(String title) {//Constructor with parameter, which sets the parameters to GUI page
        super(title);//Sets title of page
        this.setContentPane(mainPanelOfRegisterPage);//Sets main panel
        this.setLocationRelativeTo(null);//Sets the location relative something
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Sets close operation
        cancelButton.addActionListener(new ActionListener() {//Action of cancel button
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomePage openedWelcomePage = new WelcomePage("Applicant admission app");
                openedWelcomePage.createFrame();//It creates new Welcome page
                dispose();//close the current page
            }
        });
        registerButton.addActionListener(new ActionListener() {//Action of register button
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InsertData insObject = new InsertData();
                    insObject.insertData(RegisterPage.this);//It sends the object with values to insert data to database
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    @Override
    public void createFrame() {//Creates the page and sets the parameters of page
        this.setBounds(500, 50, 500, 700);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
