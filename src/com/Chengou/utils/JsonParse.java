package com.Chengou.utils;

import com.Chengou.Entity.CenterData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//解析json数据的辅助类
public class JsonParse {
    private String jsonString; //存储要解析的json数据

    //存储centerData对象
    private CenterData[] centerData;

    public JsonParse(String jsonString){
        this.jsonString = jsonString;
        ObjectMapper objectMapper = new ObjectMapper();//使用objectMapper来解析该字符串

        try{
            JsonNode rootNode = objectMapper.readTree(jsonString); //将json字符串转换为JsonNode对象
            //根节点
            JsonNode dataNode = rootNode.path("data");
            //获取data节点下的diff节点
            JsonNode diffNode = dataNode.path("diff");
            //创建一个CenterData对象数组
            centerData = new CenterData[diffNode.size()];
            //获取diif下的每一个节点映射成CenterData对象
            for(int i = 0; i < diffNode.size(); i++){
                centerData[i] = objectMapper.readValue(diffNode.get(i).toString(), CenterData.class);
                //readValue方法被用于将diffNode.get(i)返回的json子节点转换为CenterData对象
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public CenterData[] getCenterData() {
        return centerData;
    }
    
}
