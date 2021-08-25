package LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantInfoPage extends JFrame implements Pages {//Class of Info page
    public JPanel mainPanelOfInfoPage;//Element of GUI
    public JPanel insidePanelOfInfoPage;
    public JLabel Label10;
    public JLabel NameBox;
    public JLabel SurnameBox;
    public JLabel GenderBox;
    public JLabel FacultyBox;
    public JLabel GPABox;
    public JLabel FamilyBox;
    public JLabel PhycixsBox;
    public JLabel FinSituationBox;
    public JLabel ScholarshipBox;
    public JLabel FeeBox;
    public JLabel ExamBox;
    public JLabel MathBox;
    public JLabel EnglishBox;
    public JButton cancelButton;

    public  ApplicantInfoPage(){}//Constructor without parameter

    public ApplicantInfoPage(String title) {//Constructor with parameter, which sets the parameters to GUI page
        super(title);//Sets title of page
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Sets close operation
        this.setContentPane(mainPanelOfInfoPage);//Sets main panel
        this.setLocationRelativeTo(null);//Sets the location relative something
        this.pack();
        cancelButton.addActionListener(new ActionListener() {//Action of cancel button
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage openedLoginPage = new LoginPage("Applicant admission app");
                openedLoginPage.createFrame();//It creates new Login page
                dispose();//close the current page
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
