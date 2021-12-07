package com.egintra.common.utils.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author: shafei
 * @Date: 2020-04-15 10:30
 * @Version: V1.0
 * @Description:
 */
public class PrintTest {
    protected static final String DIAN_MING = "伊晨Bayby童装工厂";
    protected static final String SHOU_HUO_REN = "大飞家";
    protected static final String SHOU_HUO_REN_BEI_ZHU = "无";
    protected static final String MAIJIA_DIZHI = "A区三街01114号";
    protected static final String MAIJIA_DH = "18660665245";
    protected static final String MAIJIA_WX = "yichenbaby0";

    public static void main(String[] args) {
        OrderVo orderVo = new OrderVo();
        orderVo.setBuyer("大飞家");
        orderVo.setRemark("无");
        orderVo.setSeller(DIAN_MING);
        orderVo.setAddress(MAIJIA_DIZHI);
        orderVo.setIphone(MAIJIA_DH);
        orderVo.setWxno(MAIJIA_WX);
//      orderVo.setTotalMoney(0);

        List<ProductVo> products = new ArrayList<ProductVo>();
        ProductVo productVo = new ProductVo();
        productVo.setName("忍者短袖（红）");
        productVo.setPrice(26.00);
        productVo.setAmount(8);
        productVo.setSize("100M");

        ProductVo productVo2 = new ProductVo();
        productVo2.setName("忍者短袖（蓝）");
        productVo2.setPrice(26.00);
        productVo2.setAmount(4);
        productVo2.setSize("103M");
        products.add(productVo);
        products.add(productVo2);
        new PrintTest().printDefault(orderVo, products);
    }

    private void printDefault(final OrderVo orderVo, final List<ProductVo> products ) {
        if (PrinterJob.lookupPrintServices().length > 0) {
            /*
             * 打印格式
             */
            PageFormat pageFormat = new PageFormat();
            // 设置打印起点从左上角开始，从左到右，从上到下打印
            pageFormat.setOrientation(PageFormat.PORTRAIT);
            /*
             * 打印页面格式设置
             */
            Paper paper = new Paper();
            // 设置打印宽度（固定，和具体的打印机有关）和高度（跟实际打印内容的多少有关）
            paper.setSize(140, 450);
            // 设置打印区域 打印起点坐标、打印的宽度和高度
            paper.setImageableArea(0, 0, 135, 450);
//          paper.setImageableArea(0, 0, 135, 450);
            pageFormat.setPaper(paper);
            // 创建打印文档
            Book book = new Book();
            book.append(new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat,
                                 int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return NO_SUCH_PAGE;
                    }
                    Graphics2D graphics2D = (Graphics2D) graphics;
                    Font font = new Font("宋体", Font.PLAIN, 5);
                    graphics2D.setFont(font);
                    drawString(graphics2D, "//////////////////////////////",
                            10, 17, 119, 8);
                    font = new Font("宋体", Font.PLAIN, 7);
                    graphics2D.setFont(font);
                    int yIndex = 30;
                    int lineHeight = 10;
                    int lineWidth = 120;
                    Color defaultColor = graphics2D.getColor();
                    Color grey = new Color(145, 145, 145);
                    // 收货信息
                    // yIndex = drawString(graphics2D,
                    // "发件人："+DIAN_MING+"（微信：yichenbaby0）", 10, yIndex,
                    // lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "收货人：" + orderVo.getBuyer(), 10,
                            yIndex, lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "收货人备注信息："
                                    + orderVo.getRemark(), 10, yIndex + lineHeight,
                            lineWidth, lineHeight);
                    // 收货信息边框
                    Stroke stroke = new BasicStroke(0.5f, BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_BEVEL, 0, new float[] { 4, 4 }, 0);
                    graphics2D.setStroke(stroke);
                    graphics2D.drawRect(5, 10, 129, yIndex);
                    // 商品名称
                    lineWidth = 129;
                    lineHeight = 8;
                    graphics2D.setFont(new Font("宋体", Font.BOLD, 8));
                    graphics2D.setColor(defaultColor);
                    yIndex = drawString(graphics2D, DIAN_MING, 5, yIndex
                            + lineHeight + 20, lineWidth, 12);
                    graphics2D.setFont(new Font("宋体", Font.PLAIN, 6));
                    graphics2D.setColor(grey);
//                  yIndex = drawString(graphics2D, "操作员：金豆        "
//                          + getCurrDate(), 5, yIndex + lineHeight + 2,
//                          lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "时间："
                                    + getCurrDate(), 5, yIndex + lineHeight + 2,
                            lineWidth, lineHeight);
                    // yIndex = drawString(graphics2D, "日期："+getCurrDate(), 5 +
                    // lineWidth/2, yIndex, lineWidth, lineHeight);
                    // yIndex = drawString(graphics2D, "日期："+getCurrDate(), 5 +
                    // lineWidth/2, yIndex, lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "商品名", 5, yIndex
                            + lineHeight * 2 - 5, lineWidth, lineHeight);
                    // yIndex = drawString(graphics2D, "尺码", (lineWidth/10)*4,
                    // yIndex, lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "单价", (lineWidth / 10) * 8,
                            yIndex, lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "数量",
                            (lineWidth / 10) * 10, yIndex, lineWidth,
                            lineHeight);

                    //总合计价格
                    int total = 0;
                    double totalPrice = 0;
                    for (ProductVo productVo : products) {
                        graphics2D.setFont(new Font("宋体", Font.PLAIN, 7));
                        //商品名称
                        yIndex = drawString(graphics2D, productVo.getName(), 5,
                                yIndex + 15, (lineWidth / 10) * 7, 10);
                        graphics2D.setFont(new Font("宋体", Font.PLAIN, 6));
                        graphics2D.setColor(grey);
//                      yIndex = drawString(graphics2D, "尺码:" + "100M", 5,
//                              yIndex + 11, lineWidth, lineHeight);// 尺码
                        //单价
                        yIndex = drawString(graphics2D, productVo.getPrice()+"",
                                (lineWidth / 10) * 8, yIndex, lineWidth,
                                lineHeight);
                        //数量
                        yIndex = drawString(graphics2D, productVo.getAmount()+"",
                                (lineWidth / 10) * 10, yIndex, lineWidth,
                                lineHeight);
                        graphics2D.setFont(new Font("宋体", Font.PLAIN, 7));
                        yIndex = yIndex + 2;
                        graphics2D.drawLine(5, yIndex, 5 + lineWidth, yIndex);

                        total=total+productVo.getAmount();
                        totalPrice=totalPrice+(productVo.getPrice()*productVo.getAmount());
                    }
                    graphics2D.setColor(defaultColor);
                    // yIndex = drawString(graphics2D, "会员名称：小清新", 5, yIndex +
                    // lineHeight * 2, lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "总  数："+total, 5, yIndex
                            + lineHeight, lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "总  计："+totalPrice, 5, yIndex
                            + lineHeight, lineWidth, lineHeight);
//                  yIndex = drawString(graphics2D, "收  款：100.00", 5, yIndex
//                          + lineHeight, lineWidth, lineHeight);
//                  yIndex = drawString(graphics2D, "找  零：44.70", 5, yIndex
//                          + lineHeight, lineWidth, lineHeight);
                    graphics2D.setFont(new Font("宋体", Font.PLAIN, 6));
                    graphics2D.setColor(grey);
//                  yIndex = drawString(graphics2D, "微信："+orderVo.getWxno(), 5, yIndex
//                          + lineHeight * 2, lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "微信："+MAIJIA_WX, 5, yIndex
                            + lineHeight * 2, lineWidth, lineHeight);
//                  yIndex = drawString(graphics2D, "地址：" + orderVo.getAddress(), 5,
//                          yIndex + lineHeight, lineWidth, lineHeight);
                    yIndex = drawString(graphics2D, "地址：" + MAIJIA_DIZHI, 5,
                            yIndex + lineHeight, lineWidth, lineHeight);
                    yIndex = yIndex + 20;
                    graphics2D.drawLine(0, yIndex, 140, yIndex);
                    return PAGE_EXISTS;
                }

                private String getCurrDate() {
                    Date currDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    return sdf.format(currDate);
                }
            }, pageFormat);
            // 获取默认打印机
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPageable(book);
            try {
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
                System.out.println("打印异常");
            }
        } else {
            System.out.println("没法发现打印机服务");
        }

    }


    /**
     * 字符串输出
     *
     * @param graphics2D
     *            画笔
     * @param text
     *            打印文本
     * @param x
     *            打印起点 x 坐标
     * @param y
     *            打印起点 y 坐标
     * @param lineWidth
     *            行宽
     * @param lineHeight
     *            行高
     * @return 返回终点 y 坐标
     */
    private static int drawString(Graphics2D graphics2D, String text, int x,
                                  int y, int lineWidth, int lineHeight) {
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        if (fontMetrics.stringWidth(text) < lineWidth) {
            graphics2D.drawString(text, x, y);
            return y;
        } else {
            char[] chars = text.toCharArray();
            int charsWidth = 0;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < chars.length; i++) {
                if ((charsWidth + fontMetrics.charWidth(chars[i])) > lineWidth) {
                    graphics2D.drawString(sb.toString(), x, y);
                    sb.setLength(0);
                    y = y + lineHeight;
                    charsWidth = fontMetrics.charWidth(chars[i]);
                    sb.append(chars[i]);
                } else {
                    charsWidth = charsWidth + fontMetrics.charWidth(chars[i]);
                    sb.append(chars[i]);
                }
            }
            if (sb.length() > 0) {
                graphics2D.drawString(sb.toString(), x, y);
                y = y + lineHeight;
            }
            return y - lineHeight;
        }
    }
}