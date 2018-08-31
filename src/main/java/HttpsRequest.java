import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

public class HttpsRequest {

    public static String httpsRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer = null;
        try {
            //创建SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = {new MyX509TrustManager()};
            //初始化
            sslContext.init(null,tm,new java.security.SecureRandom());
            //获取SSLScoketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLScoketFactory
            connection.setSSLSocketFactory(ssf);
            connection.connect();
            //往服务端写内容
            if(null!=outputStr){
                OutputStream os = connection.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            //读取服务端返回的内容
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine())!= null){
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

//    //public static void main(String[] args) {
//        System.out.println(httpsRequest("https://www.baidu.com","get",null));
//    }
}
