package com.egintra.common.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XmlUtils {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "<root>\n" +
                "<head>\n" +
                "<code>1</code>\n" +
                "<message>%E6%95%B0%E6%8D%AE%E4%B8%8B%E8%BD%BD%E6%88%90%E5%8A%9F%EF%BC%81</message>\n" +
                "<rownum>4</rownum>\n" +
                "</head>\n" +
                "<body>\n" +
                "<drv id=\"0\">\n" +
                "  <lsh>2D00613110691</lsh>\n" +
                "  <sfzmhm>370481198810018537</sfzmhm>\n" +
                "  <dabh>370400918919</dabh>\n" +
                "  <xm>%E5%BC%A0%E6%B5%B7%E5%86%9B</xm>\n" +
                "  <ywlx>F</ywlx>\n" +
                "  <ywyy>A</ywyy>\n" +
                "  <kssj>2010-06-13+09%3A22%3A24.0</kssj>\n" +
                "  <jssj>2010-06-13+11%3A29%3A32.0</jssj>\n" +
                "  <ywgw>ABCE</ywgw>\n" +
                "  <kskm>010000</kskm>\n" +
                "  <xygw>O</xygw>\n" +
                "  <glbm>370400000400</glbm>\n" +
                "  <ffbz></ffbz>\n" +
                "  <rkbz></rkbz>\n" +
                "  <hdbz></hdbz>\n" +
                "  <xgzl>I</xgzl>\n" +
                "  <zjcx>C1</zjcx>\n" +
                "  <ywzt>A</ywzt>\n" +
                "  <ywblbm>370400000400</ywblbm>\n" +
                "  <fzjg>%E9%B2%81D</fzjg>\n" +
                "</drv>\n" +
                "<drv id=\"1\">\n" +
                "  <lsh>2190304915227</lsh>\n" +
                "  <sfzmhm>370481198810018537</sfzmhm>\n" +
                "  <dabh>370400918919</dabh>\n" +
                "  <xm>%E5%BC%A0%E6%B5%B7%E5%86%9B</xm>\n" +
                "  <ywlx>C</ywlx>\n" +
                "  <ywyy>A</ywyy>\n" +
                "  <kssj>2019-03-04+15%3A10%3A06.0</kssj>\n" +
                "  <jssj>2019-03-04+15%3A38%3A33.0</jssj>\n" +
                "  <ywgw>AGE</ywgw>\n" +
                "  <kskm>000000</kskm>\n" +
                "  <xygw>O</xygw>\n" +
                "  <glbm>370400000400</glbm>\n" +
                "  <ffbz>3</ffbz>\n" +
                "  <rkbz></rkbz>\n" +
                "  <hdbz></hdbz>\n" +
                "  <xgzl>ABCM</xgzl>\n" +
                "  <zjcx>C1</zjcx>\n" +
                "  <ywzt>A</ywzt>\n" +
                "  <ywblbm>370481000400</ywblbm>\n" +
                "  <fzjg>%E9%B2%81D</fzjg>\n" +
                "</drv>\n" +
                "<drv id=\"2\">\n" +
                "  <lsh>2160324731855</lsh>\n" +
                "  <sfzmhm>370481198810018537</sfzmhm>\n" +
                "  <dabh>370400918919</dabh>\n" +
                "  <xm>%E5%BC%A0%E6%B5%B7%E5%86%9B</xm>\n" +
                "  <ywlx>C</ywlx>\n" +
                "  <ywyy>EF</ywyy>\n" +
                "  <kssj>2016-03-24+16%3A42%3A22.0</kssj>\n" +
                "  <jssj>2016-03-24+17%3A02%3A35.0</jssj>\n" +
                "  <ywgw>AGE</ywgw>\n" +
                "  <kskm>000000</kskm>\n" +
                "  <xygw>O</xygw>\n" +
                "  <glbm>370400000400</glbm>\n" +
                "  <ffbz>3</ffbz>\n" +
                "  <rkbz></rkbz>\n" +
                "  <hdbz></hdbz>\n" +
                "  <xgzl>ABZ</xgzl>\n" +
                "  <zjcx>C1</zjcx>\n" +
                "  <ywzt>A</ywzt>\n" +
                "  <ywblbm>370481000400</ywblbm>\n" +
                "  <fzjg>%E9%B2%81D</fzjg>\n" +
                "</drv>\n" +
                "<drv id=\"3\">\n" +
                "  <lsh>2130319517247</lsh>\n" +
                "  <sfzmhm>370481198810018537</sfzmhm>\n" +
                "  <dabh>370400918919</dabh>\n" +
                "  <xm>%E5%BC%A0%E6%B5%B7%E5%86%9B</xm>\n" +
                "  <ywlx>C</ywlx>\n" +
                "  <ywyy>A</ywyy>\n" +
                "  <kssj>2013-03-19+16%3A08%3A10.0</kssj>\n" +
                "  <jssj>2013-03-19+16%3A56%3A20.0</jssj>\n" +
                "  <ywgw>AGE</ywgw>\n" +
                "  <kskm>000000</kskm>\n" +
                "  <xygw>O</xygw>\n" +
                "  <glbm>370400000400</glbm>\n" +
                "  <ffbz>3</ffbz>\n" +
                "  <rkbz></rkbz>\n" +
                "  <hdbz></hdbz>\n" +
                "  <xgzl>BC</xgzl>\n" +
                "  <zjcx>C1</zjcx>\n" +
                "  <ywzt>A</ywzt>\n" +
                "  <ywblbm>370481000400</ywblbm>\n" +
                "  <fzjg>%E9%B2%81D</fzjg>\n" +
                "</drv>\n" +
                "</body>\n" +
                "</root>";
        XmlUtils test = new XmlUtils();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        List<Map<String, String>> list1 = test.parseXml2List(xml, list);
        List<Map<String, String>> mapList = removeRepeatMapByKey(list1, "lsh");
        System.out.println("resMapList===" + mapList);
//        String resultCode = (String)map.get("formNo");
//        System.out.println("resultCode===="+resultCode);
//        String handleDeptId = (String)map.get("detail.handleDeptId");
//        System.out.println("handleDeptId===="+handleDeptId);
    }

    /**
     * 将xml转换为Map。 支持xml标签多层嵌套，并以"."分隔多级标签（不包括根节点）。 不支持XML标签重复时的情况
     *
     * @param xml
     * @param map
     * @return
     */
    public static Map<String, String> parseXml2Map(String xml, Map<String, String> map) {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new StringReader(xml));
            Element root = doc.getRootElement();
            String path = "";
            if (map.containsKey(root.getName().trim())) {
                path = map.get(root.getName().trim());
                map.remove(root.getName().trim());
            }
            for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                if (element.isTextOnly()) {
                    if (path.length() > 0) {
                        map.put(path + element.getName().trim(), element.getTextTrim());
                    } else {
                        map.put(element.getName().trim(), element.getTextTrim());
                    }
                } else {
                    map.put(element.getName().trim(), path + element.getName().trim() + ".");
                    parseXml2Map(element.asXML(), map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 解析多个返回的数据
     *
     * @param xml
     * @param list
     * @return
     */
    public static List<Map<String, String>> parseXml2List(String xml, List<Map<String, String>> list) {
        try {
            Map<String, String> map = new HashMap<>();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new StringReader(xml));
            Element root = doc.getRootElement();
            String path = "";
            if (map.containsKey(root.getName().trim())) {
                path = map.get(root.getName().trim());
                map.remove(root.getName().trim());
            }
            for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                if (element.isTextOnly()) {
                    if (path.length() > 0) {
                        map.put(path + element.getName().trim(), element.getTextTrim());
                        list.add(map);
                    } else {
                        map.put(element.getName().trim(), element.getTextTrim());
                        list.add(map);
                    }

                } else {
                    map.put(element.getName().trim(), path + element.getName().trim() + ".");
                    list.add(map);
                    parseXml2List(element.asXML(), list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 根据map中的某个key 去除List中重复的map
     *
     * @param list
     * @param mapKey
     * @return
     * @author shijing
     */
    public static List<Map<String, String>> removeRepeatMapByKey(List<Map<String, String>>
                                                                         list, String mapKey) {

        //把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
        List<Map<String, String>> listMap = new ArrayList<>();
        Map<String, Map> msp = new HashMap<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            Map map = list.get(i);
            String id = (String) map.get(mapKey);
            map.remove(mapKey);
            msp.put(id, map);
        }
        //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
        Set<String> mspKey = msp.keySet();
        for (String key : mspKey) {
            Map newMap = msp.get(key);
            newMap.put(mapKey, key);
            listMap.add(newMap);
        }

        return listMap;
    }
}
