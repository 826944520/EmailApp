import java.io.*;
import java.net.Socket;

public class POP3Client {
    /*
    用户名：xxxx@xx.com
     */
    String username;
    String password;
    public POP3Client(String username, String password){
        this.password = password;
        this.username = username;
    }
    public void getMail(String receiveUsername, String data){
        /*
         *获取用户名，去除@之后的内容
         */
        String username_id = username.split("@")[0];
        try {
            Socket socket=new Socket("pop3.163.com", 110);
            InputStream inputStream = socket.getInputStream();//读取服务器返回信息的流
            InputStreamReader isr = new InputStreamReader(inputStream);//字节解码为字符
            BufferedReader br=new BufferedReader(isr);//字符缓冲

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
            System.out.println(br.readLine());
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        POP3Client s = new POP3Client("kenway9276@163.com", "9276Kenway");
        s.getMail("454730168@qq.com","nihao!!!!!");
    }
}
