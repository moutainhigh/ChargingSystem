package com.egintra.common.utils.bank;

import com.alibaba.fastjson.JSONObject;
import com.egintra.common.utils.DateUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        PayDto payDto = new PayDto();
        payDto.setMchno("105000086510414");
        payDto.setMerjnlno("2D00613110691K000999");
        payDto.setOrderno("2D00613110691K000236");
        String timestart = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        payDto.setTimestart("");
        Date afterDate = new Date((new Date().getTime() + 60000 * 5));
        String timeexpire = DateUtils.formatDate(afterDate, "yyyy-MM-dd HH:mm:ss");
        payDto.setTimeexpire("");
        payDto.setTranamt(0.01);
        payDto.setCurrency("156");
        payDto.setFronturl("http://192.168.19.8:90/egintra-fee/callBack/callBack");
        payDto.setNotifyurl("http://192.168.19.8:90/egintra-fee/callBack/callBack");
        payDto.setRisklevel("01");
        payDto.setMernote("");
        payDto.setSubject("");
        payDto.setMchabbr("");
        payDto.setSubmchno("");
        payDto.setSubmchnm("");
        payDto.setSubmchabbr("");
        payDto.setTermid("01");
        try {
            //拼成json
//            String signData = BankAutographUtils.getSignData(payDto, "IFOPF001", "105000086510414", "中信银行");
//            // 加密
//            String encryptBusiness = BankAutographUtils.encryptBusiness(signData);
//            //验签
//            String sign = BankAutographUtils.getSignatureSecrets(signData);
//            JSONObject busijson = new JSONObject();
//            busijson.put("encryptBody", encryptBusiness);
//            busijson.put("sign", sign);
//            System.out.println(busijson);
//            String encode = URLEncoder.encode(busijson.toJSONString(), "UTF-8");
//            String url = "https://ifop.citicbank.com/pay?dataType=" + encode;
//            String result = HttpClientUtil.doGet(url);
//            System.out.println(result);
            String signData = BankAutographUtils.getSignData(payDto, "IFOPF001", "105000086510414", "安之畅收费系统");
            String encryptBusiness = BankAutographUtils.encryptBusiness(signData);
//            //验签
            String sign = BankAutographUtils.getSignatureSecrets(signData);
            sign=java.net.URLEncoder.encode(sign, "UTF-8");
            JSONObject busijson = new JSONObject();
            busijson.put("sign", sign);
            busijson.put("encryptBody", encryptBusiness);
//            Map<String,String> stringStringMap=new HashMap<>();
//            stringStringMap.put("sign",sign);
//            stringStringMap.put("encryptBody",encryptBusiness);
//            String urlstr = "https://ifop.citicbank.com/pay";
//            String result=HttpUtil.doPost(urlstr,stringStringMap);
//            System.out.println(result);
            System.out.println(busijson);
            String urlstr = "https://ifop.citicbank.com/pay";
            String business = busijson.toJSONString();
            URL url = new URL(urlstr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(business.getBytes("UTF-8"));
            os.flush();
            os.close();
            System.out.println("国安1");
            InputStream is = conn.getInputStream();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, "utf-8"));
            String respstr = null;
            StringBuilder sbr = new StringBuilder();
            while ((respstr = br.readLine()) != null) {
                sbr.append(respstr);
            }
            br.close();
            is.close();
            System.out.println("国安2");
            String responString = sbr.toString();
            System.out.println(responString);
            JSONObject jsonObject = JSONObject.parseObject(responString);

//            String respbusiness = jsonObject.getString("encryptBody");
//            String signStr = jsonObject.getString("sign");
//            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String getJsonstr() {
        JSONObject json = new JSONObject();
        json.put("TIMESTART", "");
        json.put("MERJNLNO", "");
        json.put("ORDERNO", "");
        json.put("TRANAMT", 0.1);
        json.put("FRONTURL", "www.baidu.com");
        json.put("NOTIFYURL", "http://192.168.19.8:90/egintra-fee/callBack/callBack");
        json.put("RISKLEVEL", "01");
        json.put("SUBJECT", "");
        json.put("MERNOTE", "支付");
        json.put("TIMEEXPIRE", "");
        json.put("ACCNO", "");
        json.put("MCHABBR", "");
        json.put("MOBILEEDITABLE", "");
        json.put("ACCNOEDITABLE", "");
        json.put("CURRENCY", "156");
        json.put("MOBILE", "");
        json.put("TERMID", "01");
        json.put("MCHNO", "105000086510414");
        json.put("OPENTRANSCODE", "IFOPF001");
        json.put("OPENMERCODE", "");
        json.put("OPENBUSITYPE", "105000086510414");
        json.put("OPENMERNAME", "银行测试");
        json.put("OPENVER", "1.0.0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        json.put("OPENLAUNCHDATE", sdf.format(new Date()));
        SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
        json.put("OPENLAUNCHTIME", sdf1.format(new Date()));
        json.put("OPENMERFLOWID", "2D00613110691K000236");
        String jsonstr = json.toJSONString();
        return jsonstr;
    }
}
