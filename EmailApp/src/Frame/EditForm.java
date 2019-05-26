package Frame;

import Protol.POP3Client;
import Protol.SMTPClient;
import Utile.Email;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditForm {
    private static JFrame jf;
    private static JButton sendButton;
    private static JTextField titleField;
    private static JTextField toField;
    private static JTextArea contentArea;
    public static POP3Client pop3Client;
    public static SMTPClient smtpClient;
    public EditForm(String username, String password){
        jf=new JFrame("i am the new JFrame");
        jf.setLayout(null);

        smtpClient = new SMTPClient(username, password);

        titleField = new JTextField("输入标题");
        titleField.setBounds(0,0,200,100);
        toField = new JTextField("输入邮箱");
        toField.setBounds(0,100,200,100);
        contentArea = new JTextArea("输入正文");
        contentArea.setBounds(0,250,500,500);
        sendButton = new JButton("发送邮件");
        sendButton.setBounds(300,0,100,50);
        //发送邮件点击事件
        ActionListener bt1_ls=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {


            }
        };
        sendButton.addActionListener(bt1_ls);




        jf.add(toField);
        jf.add(titleField);
        jf.add(contentArea);
        jf.add(sendButton);
        jf.setVisible(true);
        jf.setLocation(10, 10);
        jf.setBounds(10, 10, 700, 700);
    }
}
