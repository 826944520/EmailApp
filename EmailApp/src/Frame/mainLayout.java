package Frame;

import Protol.POP3Client;
import Utile.Email;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class mainLayout extends JFrame{
    private static JFrame jf;
    private static JButton jumpToSendButton;
    private static JTable emailListTable;
    public static POP3Client pop3Client;
    private static JButton deleteButton;
    private static JButton readButton;
    private static JTextArea readArea;
    private static JTextField seletField;
    private static ArrayList<Email> emailArrayList;

    public mainLayout(String username, String password){
        jf=new JFrame("i am the new JFrame");
        jf.setLayout(null);
        jf.setBounds(10, 10, 1500, 1000);
        pop3Client = new POP3Client(username, password);
        emailArrayList = pop3Client.getMailList();
        Object[][] rowData = new Object[emailArrayList.size()][4];
        System.out.println(emailArrayList);
        for(int i = 0; i < emailArrayList.size(); i++){
            rowData[i][0] = emailArrayList.get(i).getId();
            rowData[i][1] = emailArrayList.get(i).getTitle();
            rowData[i][2] = emailArrayList.get(i).getFrom();
            rowData[i][3] = emailArrayList.get(i).getDate();
        }
        // 表头（列名）
        Object[] columnNames = {"id", "标题", "发件人", "日期"};
        emailListTable = new JTable(rowData, columnNames);
        emailListTable.setBounds(0,0,1000,500);

        jumpToSendButton = new JButton("发送邮件");
        //发送邮件点击事件
        ActionListener bt1_ls=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                EditForm ml=new EditForm(username, password);//为跳转的界面
            }
        };
        jumpToSendButton.addActionListener(bt1_ls);

        seletField = new JTextField("输入选择的邮件编号");
        seletField.setBounds(0,500,150,50);
        deleteButton = new JButton("删除邮件");
        deleteButton.setBounds(200,550,100,50);
        readButton = new JButton("阅读邮件");
        readButton.setBounds(0,550,100,50);
        readArea = new JTextArea("阅读区域");
        readArea.setBounds(1100,0,500,500);
        readArea.setEditable(false);
        //阅读邮件点击事件
        ActionListener readListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String selected_id = seletField.getText();
                Email email = emailArrayList.get(Integer.parseInt(selected_id) - 1);
                readArea.setText("Date: " + email.getDate() + "\nFrom: " + email.getFrom() + "\nTitle: "+ email.getTitle()
                    + "\n" + email.getContent());
            }
        };
        readButton.addActionListener(readListener);
        //删除邮件点击事件
        ActionListener deleteListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String selected_id = seletField.getText();
                Email email = emailArrayList.get(Integer.parseInt(selected_id) - 1);
                pop3Client.deleteEmail(email);
                emailArrayList = pop3Client.getMailList();
                Object[][] rowData = new Object[emailArrayList.size()][4];
                System.out.println(emailArrayList);
                for(int i = 0; i < emailArrayList.size(); i++){
                    rowData[i][0] = emailArrayList.get(i).getId();
                    rowData[i][1] = emailArrayList.get(i).getTitle();
                    rowData[i][2] = emailArrayList.get(i).getFrom();
                    rowData[i][3] = emailArrayList.get(i).getDate();
                }
                // 表头（列名）
                Object[] columnNames = {"id", "标题", "发件人", "日期"};
                emailListTable = new JTable(rowData, columnNames);
                emailListTable.setBounds(0,0,1000,500);
                jf.add(emailListTable);
            }
        };
        readButton.addActionListener(readListener);

        jf.add(deleteButton);
        jf.add(seletField);
        jf.add(readButton);
        jf.add(readArea);


        jumpToSendButton.setBounds(550, 550, 100, 50);



        jf.add(emailListTable);
        jf.add(jumpToSendButton);

        jf.setVisible(true);
        jf.setLocation(10, 10);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}