
package com.maxrocky.vesta.application.util;//import org.apache.commons.io.IOUtils;

import com.maxrocky.vesta.application.ChargePaymentServiceImpl;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by liuwei on 2016/1/18.
 */

public class SendRequest implements Runnable {


    private String requestUrl;

    private String params;

    private int num;


    private int count = 0;

    public SendRequest(String requestUrl, String params, int num,int count){
        this.requestUrl = requestUrl;
        this.params = params;
        this.num = num;
        this.count = count;
    }
    @Override
    public void run() {


         sendUrlRequest(requestUrl,params);

    }

    public  String sendUrlRequest(String requestUrl,String reqBody){


        ChargePaymentServiceImpl.dataBean dataBean = ChargePaymentServiceImpl.dataBeanMap.get(String.valueOf(count));
        System.out.println("进入了线程方法了");

        synchronized (ChargePaymentServiceImpl.class){
            ChargePaymentServiceImpl.dataBeanMap.get(String.valueOf(count)).threadStartCount ++;
        }


        System.out.println("----sendUrlRequest start ------"+System.currentTimeMillis());
        long startTime = System.currentTimeMillis();
        long lastThreadEndTime = System.currentTimeMillis();
        String tempStr = "";
        try {
            String json = reqBody;
            reqBody = java.net.URLEncoder.encode(reqBody,"utf-8");
            byte[] sendData = ("checkCode=1&strJson="+reqBody).getBytes("UTF-8");

            //请求地址
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(100 * 1000);//设置超时的时间
            conn.setDoInput(true);
            conn.setDoOutput(true);//如果通过post提交数据，必须设置允许对外输出数据
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(sendData.length));
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            System.out.println(json);
            out.write(sendData);//写入请求的字符串
            out.flush();
            out.close();

            System.out.println("----sendUrlRequest end ------"+System.currentTimeMillis());
            //请求返回的状态

            byte[] resultByte = new byte[1024*30];
            conn.getInputStream().read(resultByte);


            System.out.println("=========================   start   ============================");


            String s = String.format("第【%s】次调用的第【%s】个线程",count,num);
            System.out.println();
            System.out.println(s + "            "+reqBody);
            System.out.println();
            String result = new String(resultByte,"utf-8");
            System.out.println(s + "            "+result);
            System.out.println("=========================   end   ============================");
            if(conn.getResponseCode() ==200) {
                System.out.println(IOUtils.toString(conn.getInputStream()));
                synchronized (ChargePaymentServiceImpl.class){

                    if(result.split("success").length>1){
                        dataBean.success ++ ;

                        long endTime=System.currentTimeMillis(); //获取结束时间
                        dataBean.totalTime = dataBean.totalTime + (endTime-startTime);
                        dataBean.totalSuccess++;

                        System.out.println("程序成功数量  "+ dataBean.success);
                        System.out.println("程序["+num+"]运行时间： "+(endTime-startTime)+"ms");

                        Map<String,Long> resultMap = new HashMap<String,Long>();
                        resultMap.put("totalTime",(endTime-dataBean.threadStart));
                        if(dataBean.success == 0){
                            resultMap.put("avgTime",0L);
                        }else{
                            resultMap.put("avgTime",dataBean.totalTime/dataBean.success);
                        }
                        dataBean.resultList.add(resultMap);
                    }




                }


            } else {

                System.out.println("no++");
                System.out.println(conn.getResponseCode());
                System.out.println(IOUtils.toString(conn.getInputStream()));
            }
        } catch (IOException e) {

            e.printStackTrace();
        }





        return tempStr;
    }


    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }





}

