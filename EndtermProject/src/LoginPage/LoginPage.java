package LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame implements Pages {//Class of Login page
    public JPanel insidePanelOfLoginPage;//Element of GUI
    public JLabel Login;
    public JTextField UsernameOfLoginPage;
    public JPasswordField PasswordFieldOfLoginPage;
    public JButton loginButton;
    public JPanel mainPanelOfLoginPage;
    public JButton cancelButton;

    private String username;
    private String password;

    public LoginPage(){}//Constructor without parameter

    public LoginPage(String title) {//Constructor with parameter, which sets the parameters to GUI page
        super(title);//Sets title of page
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Sets close operation
        this.setContentPane(mainPanelOfLoginPage);//Sets main panel
        this.setLocationRelativeTo(null);//Sets the location relative something
        setSize(600, 800);
        this.pack();
    }

    public void loginAccount(LoginPage openedLoginPage) throws SQLException {//It checks the username and password

        username = UsernameOfLoginPage.getText();//Sets the username value
        password = String.valueOf(PasswordFieldOfLoginPage.getPassword());//Sets the password value

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String SQL6 = "SELECT * FROM applicant WHERE username=? AND password=?";

        try {
            DatabaseConnection db = DatabaseConnection.getInstance();//get connection
            con =db.getConnection();
            pstmt = con.prepareStatement(SQL6);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {//Checks the username and password
                ApplicantInfoPage openedInfoPage = new ApplicantInfoPage("Applicant admission app");
                openedInfoPage.createFrame();//If the values are true, it will create new Info page
                GatherData display = new GatherData();
                display.setData(openedInfoPage,username,password);//Outputs the data relates with current user
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username/password", "Login Error", 2);
            }

            DatabaseConnection.closeConnection(con, pstmt, rs);//closes the connection
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void createFrame() {//Creates the page and sets the parameters of page

        this.setBounds(500, 50, 500, 700);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cancelButton.addActionListener(new ActionListener() {//Action of cancel button
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomePage openedWelcomePage = new WelcomePage("Applicant admission app");
                openedWelcomePage.createFrame();//It creates new Welcome page
                dispose();//close the current page
            }
        });
        loginButton.addActionListener(new ActionListener() {//Action of login button
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    loginAccount(LoginPage.this);//it sends the object of Login page to check values
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
