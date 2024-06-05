package com.Chengou;
import com.Chengou.DAO.stockDAO;
import com.Chengou.DAO.Impl.stockDAOImpl;
import com.Chengou.Entity.CenterData;
import com.Chengou.utils.JsonParse;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class spider {


    String base_url = "https://push2.eastmoney.com/api/qt/clist/get?cb=jQuery112308192246411802031_1687766236352&fid=f62&po=1&pz=50&np=1&fltt=2&invt=2&ut=b2884a393a59ad64002292a3e90d46a5&fs=m%3A0%2Bt%3A6%2Bf%3A!2%2Cm%3A0%2Bt%3A13%2Bf%3A!2%2Cm%3A0%2Bt%3A80%2Bf%3A!2%2Cm%3A1%2Bt%3A2%2Bf%3A!2%2Cm%3A1%2Bt%3A23%2Bf%3A!2%2Cm%3A0%2Bt%3A7%2Bf%3A!2%2Cm%3A1%2Bt%3A3%2Bf%3A!2&fields=f12%2Cf14%2Cf2%2Cf3%2Cf62%2Cf184%2Cf66%2Cf69%2Cf72%2Cf75%2Cf78%2Cf81%2Cf84%2Cf87%2Cf204%2Cf205%2Cf124%2Cf1%2Cf13&pn=";
    //po:是第几个数据开始
    //pz:每页显示多少条数据
    //pn:第几页
    public void crawl(int pn){

        String url = base_url + pn;
        //创建一个已配置好的默认CloseableHttpClient实例，用于发送http请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //构建GET请求对象
        HttpGet httpGet = new HttpGet(url);
        //设置请求头的User-Agent字段将爬虫伪装成用户
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.51" );
        //用于在后续代码中对http响应进行处理和关闭
        CloseableHttpResponse response=null;
        JsonParse jsonParse = null;
        try {
            //发送请求, execute 方法已经被废弃， 可以使用 doExecute 方法
            response = httpClient.execute(httpGet);
            //从响应中获取响应实体
            HttpEntity entity = response.getEntity();
            //响应实体转换为字符串格式
            String jsonString = EntityUtils.toString(entity, "utf-8");
            System.out.println(jsonString);
            Pattern p = Pattern.compile("(jQuery.*\\(|\\).*)");
            Matcher m = p.matcher(jsonString);
            jsonString = m.replaceAll("");

            jsonParse = new JsonParse(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将centerData对象转换存入数据库
        if(jsonParse == null) return;
        CenterData[] centerData = jsonParse.getCenterData();
        stockDAO stockDAO = new stockDAOImpl();
        stockDAO.insertAll(centerData);

    }
    
    public static void main(String[] args)  {
        
        spider spider = new spider();
        for(int i = 1; i <= 20; i++){
            spider.crawl(i);
        }
        
    }
}
