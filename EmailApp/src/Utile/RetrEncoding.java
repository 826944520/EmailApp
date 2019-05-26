package Utile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
解析retr命令传来的邮件内容
 */
public class RetrEncoding {
    public static Email encoding(String content, String id){
        // 创建对日期的正则匹配
        String pattern = "Date(.+?)\\+";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象 a
        Matcher m = r.matcher(content);

        String date = "";
        if (m.find( )) {
            date = m.group(0).substring(5, m.group(0).length() - 2);
        } else {
            date = "unknow date";
        }

        //匹配Encoding和正文
        String encoding = "base64";
        String data = "";
        if(content.contains("quoted-printable")){
            encoding = "quoted-printable";
            String[] datas = content.split(encoding);
            data = datas[datas.length - 1];
            data = data.replaceFirst("<", "##");
            datas = data.split("##");
            data = datas[datas.length - 1].replaceFirst("##", "<");
            data = "<" + data;
            data = "暂不支持此编码";
        }
        else {
            String[] datas = content.split(encoding);
            data = datas[datas.length - 2];
            data = data.split("-")[0];
            data = Base64Utile_cc.DecodeBase64(data);
        }

        //对发件人的匹配
        String[] temp = content.split("From:");
        String from = temp[temp.length - 1];
        from = from.split("<")[1];
        from = from.split(">")[0];

        //对标题的匹配
        temp = content.split("Subject:");
        String title = temp[temp.length - 1];
        if(encoding.equals("quoted-printable")){
            title = title.split("\\?")[3];
            title = title.split("=")[0];
            title = Base64Utile_cc.DecodeBase64(title);
        }
        else {
            title = title.split("\n")[0];
        }


        return new Email(date, id, data, encoding, title, from);
    }
    public static void main(String[] args){
        RetrEncoding.encoding("","");
    }
}
