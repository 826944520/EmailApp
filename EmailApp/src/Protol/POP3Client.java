package Protol;

import Utile.Email;
import Utile.RetrEncoding;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class POP3Client {
    /*
    用户名：xxxx@xx.com
     */
    private String username;
    private String password;
    private Socket socket;
    private Scanner socketReader;
    private PrintWriter socketWriter;
    private Matcher m;

    public POP3Client(String username, String password){
        this.password = password;
        this.username = username;
    }
    public ArrayList<Email> getMailList(){
        /*
         *获取用户名，去除@之后的内容
         */
        String username_id = username.split("@")[0];
        //把邮件存进List
        ArrayList<Email> emailList = new ArrayList<>();

        try {
            Socket socket = new Socket("pop3.163.com", 110);
            InputStream inputStream = socket.getInputStream();//读取服务器返回信息的流
            InputStreamReader isr = new InputStreamReader(inputStream);//字节解码为字符
            BufferedReader br = new BufferedReader(isr);//字符缓冲

            OutputStream outputStream = socket.getOutputStream();//向服务器发送相应信息
            PrintWriter pw = new PrintWriter(outputStream, true);//true代表自带flush
            System.out.println(br.readLine());

            /*
             *向服务器发送信息以及返回其相应结果
             */

            //user
            pw.println("user " + username_id);
            System.out.println(br.readLine());

            //pass
            pw.println("pass " + password);
            System.out.println(br.readLine());

            //stat
            pw.println("stat");

            //

            //循环所有的邮件
            String stat = br.readLine();
            String emailNum_str = stat.split(" ")[1];
            int emailNum = Integer.parseInt(emailNum_str);
            for(int i = 1; i <= emailNum; i++){
                //retr
                pw.println("retr " + i);
                String content = "";
                String temp = br.readLine();
                while(!temp.equals(".")){
                    content = content + temp + "\n";
                    temp = br.readLine();
                }
                System.out.println(content);

                Email e = RetrEncoding.encoding(content, String.valueOf(i));
                emailList.add(e);
            }
            //quit
            pw.println("quit");

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return emailList;
    }

    public boolean deleteEmail(Email email){
        String username_id = username.split("@")[0];
        try {
            Socket socket = new Socket("pop3.163.com", 110);
            InputStream inputStream = socket.getInputStream();//读取服务器返回信息的流
            InputStreamReader isr = new InputStreamReader(inputStream);//字节解码为字符
            BufferedReader br = new BufferedReader(isr);//字符缓冲

            OutputStream outputStream = socket.getOutputStream();//向服务器发送相应信息
            PrintWriter pw = new PrintWriter(outputStream, true);//true代表自带flush
            System.out.println(br.readLine());

            /*
             *向服务器发送信息以及返回其相应结果
             */

            //user
            pw.println("user " + username_id);
            System.out.println(br.readLine());

            //pass
            pw.println("pass " + password);
            System.out.println(br.readLine());

            //dele
            String sentence = "dele " + email.getId();
            pw.println(sentence);
            System.out.println(sentence);
            System.out.println(br.readLine());
            //quit
            pw.println("quit");
            System.out.println(br.readLine());



        }
        catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }
    public static void main(String[] args){
        POP3Client s = new POP3Client("kenway9276@163.com", "9276Kenway");
        //s.deleteEmail(new Email("1","1","1","1","1","1"));

    }
}
