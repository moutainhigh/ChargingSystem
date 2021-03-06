package com.egintra.common.utils;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.soap.SOAPConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;

/**
 * webService工具类
 */
@Component
public class WebServiceUtil {

    @Value("${webService.ip}")
    private String IP;

    @Value("${webService.jkxlh}")
    private String JKXLH;

    /**
     * 调用综合平台的接口
     *
     * @param xtlb        系统类别 机动车登记业务：01，驾驶证管理业务：02，事故处理：03，违法处理：04，交警队平台：05，剧毒品业务：06
     * @param jkid        接口标识
     * @param queryXmlDoc 查询条件
     * @return
     */
    public String queryCallWeb(String xtlb, String jkid, String queryXmlDoc) throws Exception {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(IP));
            // 这个也要注意 就是要加
            call.setSOAPActionURI("");
            call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
            // WSDL里面描述的接口名称
            call.setOperationName("queryObjectOut");
            // 接口的参数
            call.addParameter("xtlb", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            // 接口的参数
            call.addParameter("jkxlh", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            // 接口的参数
            call.addParameter("jkid", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            // 接口的参数
            call.addParameter("QueryXmlDoc", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            // 设置返回类型
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            Object resp = (String) call.invoke(new Object[]{xtlb, JKXLH, jkid, queryXmlDoc});
            String result = URLDecoder.decode((String) resp, "utf-8");

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("服务器异常");
        }
    }

    /**
     * 写入综合平台的接口
     *
     * @param xtlb        系统类别  机动车登记业务：01，驾驶证管理业务：02，事故处理：03，违法处理：04，交警队平台：05，剧毒品业务：06
     * @param jkid        接口标识
     * @param queryXmlDoc 查询条件
     * @return
     */
    public String writeCallWeb(String xtlb, String jkid, String queryXmlDoc) {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(IP));
            // 这个也要注意 就是要加
            call.setSOAPActionURI("");
            call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
            // WSDL里面描述的接口名称
            call.setOperationName("writeObjectOut");
            // 接口的参数
            call.addParameter("xtlb", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            // 接口的参数
            call.addParameter("jkxlh", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            // 接口的参数
            call.addParameter("jkid", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            // 接口的参数
            call.addParameter("WriteXmlDoc", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            // 设置返回类型
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            Object resp = (String) call.invoke(new Object[]{xtlb, JKXLH, jkid, queryXmlDoc});
            String result = URLDecoder.decode((String) resp, "utf-8");
            return result;
            // 给方法传递参数，并且调用方法
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
