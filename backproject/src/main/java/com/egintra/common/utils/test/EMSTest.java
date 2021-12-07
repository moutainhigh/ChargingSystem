package com.egintra.common.utils.test;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.dom4j.DocumentException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class EMSTest implements Printable {

    private String[] value = null;// 所要打印的数据{ "001", "002", "003"};
    private int[][] position = null;// 每个数据在图片中的坐标 { { 10, 50 }, { 30, 70 }, { 50, 90 }};

    public static void main(String[] args) throws DocumentException {


        printReport();


    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
// TODO Auto-generated method stub

        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }


        String tableData1[][] = {{"8月31日","累计用户数","目标值","完成进度","时间进度", "进度差异"}, {"掌厅客户端（户）","469281","1500000","31.2%","33.6%", "-2.4%"}};
        String[][] tableData2 = {{"项目编码","项目名称","标准(元)","单位","数量","金额(元)"},
                {"00-8790-3201","机动车驾驶证","10.00","","1","10.00"},
                {"00-8790-32022","汽车场地驾驶技能考试费","240.00","","1","240.00"},
                {"00-8790-32024","安全文明考试费用","40.00","","1","40.00"},
                {"00-8790-32023","汽车道路技能考试费","200.00","","1","200.00"},
                {"00-8790-3201","道路法规相关知识考试费用","80.00","","1","80.00"},
                {"金额合计(大写：)","469281","","","",""}};
        String[] textArray=new String[]{"山东省非税收通用票据","交款人","执收单位编码","年","月","日","No.A","校验码","执收单位","复核人","经办人"};
        myGraphicsGeneration(tableData2,g,textArray);
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < value.length; i++) {
            String str = value[i];
            int[] temp = position[i];
            int x = temp[0];
            int y = temp[1];
// 设置打印字体（字体名称、样式和点大小）
            Font font = new Font("新宋体", Font.PLAIN, 9);
            g2d.setFont(font); //设置字体
            g2d.drawString(str, x, y);
        }
        return Printable.PAGE_EXISTS;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public int[][] getPosition() {
        return position;
    }

    public void setPosition(int[][] position) {
        this.position = position;
    }

    public static void printReport() {
        PrinterJob pj = PrinterJob.getPrinterJob();//创建一个打印任务
        PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
        Paper paper = pf.getPaper();
// 设置页面高和宽，A4纸为595,842
        double pageWidth = 595;
        double pageHeight = 810;
        paper.setSize(pageWidth, pageHeight);
        paper.setImageableArea(0, 0, pageWidth, pageHeight);
//        pf.setOrientation(PageFormat.LANDSCAPE); //设置打印方向，LANDSCAPE为横向，打印方向默认为纵向
        pf.setPaper(paper);
        EMSTest printTest = new EMSTest();
        printTest.setValue(new String[]{""});
        printTest.setPosition(new int[][]{{230, 400}});
        pj.setPrintable(printTest, pf);
        if (pj.printDialog()) { //弹出打印对话框，打印对话框，用户可以通过它改变各种选项，例如：设置打印副本数目，页面方向，或者目标打印机。
            try {
                pj.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * 生成图片
     * @param cellsValue 以二维数组形式存放 表格里面的值
     */
    public static void myGraphicsGeneration(String cellsValue[][], Graphics graphics,String[] textArray) {
        int kuochong = 200;
        int kuochong1 = 600;
        // 字体大小
        int fontTitileSize = 10;
        // 横线的行数
        int totalrow = cellsValue.length+1;
        // 竖线的行数
        int totalcol = 0;
        if (cellsValue[0] != null) {
            totalcol = cellsValue[0].length;
        }
        int totalcol1 = 0;
        if (cellsValue[6] != null) {
            totalcol1 = cellsValue[6].length;
        }
        // 图片宽度
        int imageWidth = 512;
        // 行高
        int rowheight = 40;
        // 图片高度
        int imageHeight = totalrow*rowheight+50;
        // 起始高度
        int startHeight = 35;
        // 起始宽度
        int startWidth = 10;
        // 单元格宽度
        int colwidth = (int)((imageWidth-20)/totalcol);
        BufferedImage image = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_RGB);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, imageWidth, imageHeight);
        graphics.setColor(new Color(220,240,240));

        //画横线
        for(int j=0;j<totalrow; j++){
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth, startHeight+(j+1)*rowheight, startWidth+colwidth*totalcol, startHeight+(j+1)*rowheight);
        }
        //画竖线
        for(int k=0;k<totalcol+1;k++){
            if(k==2){
                graphics.setColor(Color.black);
                graphics.drawLine(startWidth+k*colwidth+kuochong, startHeight+rowheight, startWidth+k*colwidth+kuochong, startHeight+rowheight*totalrow);
            } else{
                graphics.setColor(Color.black);
                graphics.drawLine(startWidth+k*colwidth, startHeight+rowheight, startWidth+k*colwidth, startHeight+rowheight*totalrow);
            }

        }

        //设置字体
        Font font = new Font("微软雅黑",Font.PLAIN,fontTitileSize);
        graphics.setFont(font);
        //写标题
        String title = "山东省非税收通用票据:";
        for(String str:textArray){
            if("山东省非税收通用票据".equals(str)){
                font = new Font("微软雅黑",Font.BOLD,15);
                graphics.setFont(font);
                graphics.drawString("山 东 省 非 税 收 通 用 票 据",180,25);
            }else if("交款人".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("交款人:",10,50);
                graphics.drawString("张三",45,50);
            }else if("执收单位编码".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("执收单位编码:", 10, 70);
                graphics.drawString("202108260000001", 75, 70);
            }else if("年".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("2021", 210, 70);
                graphics.drawString("年", 235, 70);
            }else if("月".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("8", 250, 70);
                graphics.drawString("月", 260, 70);
            }else if("日".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("26", 270, 70);
                graphics.drawString("日", 285, 70);
            }else if("No.A".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("No.A:",400,50);
            }else if("校验码".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("校验码:",400,70);

            }else if ("执收单位".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("执收单位:",10,380);
            }else if ("复核人".equals(str)){
                font = new Font("微软雅黑",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("执收单位:",210,380);
            }else if ("经办人".equals(str)){
                font = new Font("复核人",Font.PLAIN,10);
                graphics.setFont(font);
                graphics.drawString("经办人:",400,380);
            }
        }
        //写入内容
        for(int n=0;n<cellsValue.length;n++){
            for(int h=0;h<cellsValue[n].length;h++){
                font = new Font("微软雅黑",Font.PLAIN,fontTitileSize);
                graphics.setFont(font);
                graphics.setColor(Color.BLACK);
                if(h>0){
                    if(cellsValue[n][h].equals(cellsValue[n][h-1])){
                        graphics.drawString(cellsValue[n][h], startWidth+colwidth*h+5, startHeight+rowheight*(n+2)-10);
                    }else{
                        if(h==2){
                            graphics.drawString(cellsValue[n][h], startWidth+colwidth*h+5+kuochong, startHeight+rowheight*(n+2)-10);
                        }else{
                            graphics.drawString(cellsValue[n][h], startWidth+colwidth*h+5, startHeight+rowheight*(n+2)-10);
                        }
                    }
                }else{
                    graphics.drawString(cellsValue[n][h], startWidth+colwidth*h+5, startHeight+rowheight*(n+2)-10);

                }
            }
        }
        // 保存图片
//        createImage(image, path);
    }

    /**
     * 将图片保存到指定位置
     * @param image 缓冲文件类
     * @param fileLocation 文件位置
     */
    public void createImage(BufferedImage image, String fileLocation) {
        try {
            FileOutputStream fos = new FileOutputStream(fileLocation);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(image);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}