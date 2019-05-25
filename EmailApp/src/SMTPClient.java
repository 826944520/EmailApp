import java.io.*;
import java.net.Socket;

public class SMTPClient {
    /*
    用户名：xxxx@xx.com
     */
    String username;
    String password;
    public SMTPClient(String username, String password){
        this.password = password;
        this.username = username;
    }
    public void sendMail(String receiveUsername, String data){
        /*
         *对用户名和密码进行Base64编码
         */
        String base64_username = Base64Utile_cc.EncodeBase64(username.getBytes());
        String base64_password = Base64Utile_cc.EncodeBase64(password.getBytes());
        try{
            /*
             *远程连接smtp.163.com服务器的25号端口
             *并定义输入流和输出流(输入流读取服务器返回的信息、输出流向服务器发送相应的信息)
             */
            Socket socket=new Socket("smtp.163.com", 25);
            InputStream inputStream = socket.getInputStream();//读取服务器返回信息的流
            InputStreamReader isr = new InputStreamReader(inputStream);//字节解码为字符
            BufferedReader br=new BufferedReader(isr);//字符缓冲

            OutputStream outputStream = socket.getOutputStream();//向服务器发送相应信息
            PrintWriter pw = new PrintWriter(outputStream, true);//true代表自带flush
            System.out.println(br.readLine());

            /*
             *向服务器发送信息以及返回其相应结果
             */

            //helo
            pw.println("helo user");
            System.out.println(br.readLine());

            //auth login
            pw.println("auth login");
            System.out.println(br.readLine());
            pw.println(base64_username);
            System.out.println(br.readLine());
            pw.println(base64_password);
            System.out.println(br.readLine());

            //Set "mail from" and  "rect to"
            pw.println("mail from:<" + username + ">");
            System.out.println(br.readLine());
            pw.println("rcpt to:<"+ receiveUsername +">");
            System.out.println(br.readLine());

            //Set "data"
            pw.println("data");
            System.out.println(br.readLine());

            //正文主体(包括标题,发送方,接收方,内容,点)
            pw.println("subject:myxulinjie");
            pw.println("from:" + username);
            pw.println("to:" + receiveUsername);
            pw.println("Content-Type: text/plain;charset=\"gb2312\"");//设置编码格式可发送中文内容
            pw.println();
            pw.println(data);
            pw.println(".");
            pw.print("");
            System.out.println(br.readLine());

            /*
             *发送完毕,中断与服务器连接
             */
            pw.println("rset");
            System.out.println(br.readLine());
            pw.println("quit");
            System.out.println(br.readLine());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SMTPClient s = new SMTPClient("kenway9276@163.com", "9276Kenway");
        s.sendMail("454730168@qq.com","nihao!!!!!");
    }
}
