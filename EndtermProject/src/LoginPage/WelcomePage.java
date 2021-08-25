package LoginPage;

import LoginPage.LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame implements Pages {//Class of Welcome page
    private JPanel mainPanelOfWelcomePage;//Element of GUI
    private JPanel insidePanelOfWelcomePage;
    private JButton loginButtonOfWelcomePage;
    private JButton registerButtonOfWelcomePage;

    public WelcomePage(){}//Constructor without parameter

    public WelcomePage(String title) {//Constructor with parameter, which sets the parameters to GUI page
        super(title);//Sets title of page
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Sets close operation
        this.setContentPane(mainPanelOfWelcomePage);//Sets main panel
        this.setLocationRelativeTo(null);//Sets the location relative something
        this.pack();
        loginButtonOfWelcomePage.addActionListener(new ActionListener() {//Action of login button
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage openedLoginPage = new LoginPage("Applicant admission app");
                openedLoginPage.createFrame();//It creates new Login page
                dispose();//close the current page
            }
        }
        );
        registerButtonOfWelcomePage.addActionListener(new ActionListener() {//Action of register button
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPage openedRegisterPage = new RegisterPage("Applicant admission app");
                openedRegisterPage.createFrame();//It creates new Register page
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