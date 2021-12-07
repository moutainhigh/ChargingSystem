package com.egintra.common.utils.test;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by wumingkai on 2020-3-23.
 */
public class PictureUtil {
    public  final static PictureUtil pictureUtil = new PictureUtil();
    private  PictureUtil(){

    }
    public static  PictureUtil getInstance(){
        return  pictureUtil;
    }
    public  void  createImages(RedeemConfirmationModel redeem,String filePath,String fileName){
        try {
            String[][] tableData2 = new String[8][4];
            tableData2[0][0] = "项目名称";
            tableData2[0][1] = redeem.getProductName();
            tableData2[0][2] = redeem.getProductName();
            tableData2[0][3] = redeem.getProductName();
            //
            tableData2[1][0] = "委托人姓名";
            tableData2[1][1] = redeem.getCustName();
            tableData2[1][2] = "合同编号";
            tableData2[1][3] = redeem.getContractSubBh();
            //
            tableData2[2][0] = "赎回信托单位份数";
            tableData2[2][1] = redeem.getRedeemAmount0();
            tableData2[2][2] = "剩余信托单位份数";
            tableData2[2][3] = redeem.getBenAmount();
            //
            tableData2[3][0] = "信托利益分配账户开户行";
            tableData2[3][1] = redeem.getBankSubName();
            tableData2[3][2] = "信托利益分配账户账号";
            tableData2[3][3] = redeem.getBankACCT();
            //
            tableData2[4][0] = "开放日信托单位净值";
            tableData2[4][1] = redeem.getOpenDateNetWoth();
            tableData2[4][2] = redeem.getOpenDateNetWoth();
            tableData2[4][3] = redeem.getOpenDateNetWoth();
            //
            tableData2[5][0] = "赎回资金（大写）";
            tableData2[5][1] = redeem.getRedeemMoneyCase();
            tableData2[5][2] = "小写";
            tableData2[5][3] = redeem.getRedeemMoney();
            //
            tableData2[6][0] = "赎回费用（大写）";
            tableData2[6][1] = redeem.getFeeMoneyCase();
            tableData2[6][2] = "小写";
            tableData2[6][3] = redeem.getFee();

            //
            tableData2[7][0] = "备 注";
            tableData2[7][1] = "";
            tableData2[7][2] = "";
            tableData2[7][3] = "";

            myGraphicsGeneration(tableData2,redeem.getContractBh(),filePath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void myGraphicsGeneration(String cellsValue[][], String contractBh,String path,String fileName) {
        int kuochong = 200;
        // 字体大小
        int fontTitileSize = 15;
        // 横线的行数
        int totalrow = cellsValue.length+1;
        // 竖线的行数
        int totalcol = 0;
        if (cellsValue[0]  != null) {
            totalcol = cellsValue[0].length;
        }
        // 图片宽度
        int imageWidth = 1792;
        // 行高
        int rowheight = 40;
        // 图片高度
        int imageHeight = totalrow*rowheight+200;
        // 起始高度
        int startHeight = 50;
        // 起始宽度
        int startWidth = 10;
        // 单元格宽度
        int colwidth = (int)((imageWidth-20)/totalcol);
        BufferedImage image = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, imageWidth, imageHeight);
        graphics.setColor(new Color(220,240,240));

        //画横线
        for(int j=0;j<totalrow; j++){
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth,
                    startHeight+(j+1)*rowheight,
                    startWidth+colwidth*totalcol,
                    startHeight+(j+1)*rowheight);
        }
        //画竖线
        for(int k=0;k<totalcol+1;k++){
            if(k==2){
                graphics.setColor(Color.black);
                graphics.drawLine(startWidth+k*colwidth+kuochong, startHeight+rowheight, startWidth+k*colwidth+kuochong, startHeight+rowheight*totalrow);
            }else{
                graphics.setColor(Color.black);
                graphics.drawLine(startWidth+k*colwidth, startHeight+rowheight, startWidth+k*colwidth, startHeight+rowheight*totalrow);
            }
        }
        for(int k=2;k<totalcol;k++){ // 合并单元格 （其实也就是消除掉黑色的线）
            if(k==2){
                graphics.setColor(Color.white);
                graphics.drawLine(startWidth+k*colwidth+kuochong, startHeight+1+rowheight, startWidth+k*colwidth+kuochong, rowheight*3+9);
            }else{
                graphics.setColor(Color.white);
                graphics.drawLine(startWidth+k*colwidth, startHeight+1+rowheight, startWidth+k*colwidth, rowheight*3+9);
            }
        }

        for(int k=2;k<totalcol;k++){// 合并单元格 （其实也就是消除掉黑色的线）
            if(k==2){
                graphics.setColor(Color.white);
                graphics.drawLine(startWidth+k*colwidth+kuochong, startHeight+1+rowheight*5, startWidth+k*colwidth+kuochong, rowheight*7+9);
            }else{
                graphics.setColor(Color.white);
                graphics.drawLine(startWidth+k*colwidth, startHeight+1+rowheight*5, startWidth+k*colwidth, rowheight*7+9);
            }
        }

        for(int k=2;k<totalcol;k++){// 合并单元格 （其实也就是消除掉黑色的线）
            if(k==2){
                graphics.setColor(Color.white);
                graphics.drawLine(startWidth+k*colwidth+kuochong, startHeight+1+rowheight*8, startWidth+k*colwidth+kuochong, rowheight*10+9);
            }else{
                graphics.setColor(Color.white);
                graphics.drawLine(startWidth+k*colwidth, startHeight+1+rowheight*8, startWidth+k*colwidth, rowheight*10+9);
            }
        }

        graphics.setColor(Color.black);
        //画竖线
        /*for(int k=1;k<totalcol+1;k++){
           // graphics.setColor(Color.white);
            graphics.drawLine(startWidth+k*colwidth, startHeight+rowheight, startWidth+k*colwidth, rowheight);
        }*/

        //设置字体
        Font font = new Font("微软雅黑",Font.BOLD,fontTitileSize);
        graphics.setFont(font);

        //写标题
        String title02 = "编号："+contractBh;
        graphics.drawString(title02, startWidth, startHeight+rowheight-10);



        Font fonttile = new Font("微软雅黑",Font.BOLD,18);
        graphics.setFont(fonttile);

        //写标题
        String title03 = "测试测试测试";
        graphics.drawString(title03, 850, 25);

        //写标题
        String title04 = "刺客伍六七哈哈哈哈哈哈";
        graphics.drawString(title04, 790, 60);
        //写入内容
        for(int n=0;n<cellsValue.length;n++){
            for(int h=0;h<cellsValue[n].length;h++){
                font = new Font("微软雅黑",Font.PLAIN,fontTitileSize);
                graphics.setFont(font);
                graphics.setColor(Color.BLACK);
                if(h>0){
                    if(cellsValue[n][h].equals(cellsValue[n][h-1])){

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
        createImage(image, path,fileName);
    }

    /**
     * 将图片保存到指定位置
     * @param image 缓冲文件类
     * @param fileLocation 文件位置
     */
    public static void createImage(BufferedImage image, String fileLocation,String fileName) {
        try {
            File file = new File(fileLocation);
            if(!file.exists()){
                file.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(fileLocation+fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ImageIO.write(image, "jpg", fos);
           /* JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(image);*/
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片转换为string
     * @return
     */
    public static String fileToByteArray(String filePath) throws Exception{
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        File file = new File(filePath);
        BufferedImage bi = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return encoder.encodeBuffer(bytes).trim();
    }

    public static void main(String[] args) {
        RedeemConfirmationModel redeemConfirmationModel = new RedeemConfirmationModel();
        redeemConfirmationModel.setFee("test");
        redeemConfirmationModel.setProductName("进击的巨人");
        redeemConfirmationModel.setRedeemMoney("13集");
        redeemConfirmationModel.setFeeMoneyCase("test");
        redeemConfirmationModel.setRedeemMoneyCase("tets");
        redeemConfirmationModel.setBenAmount("test");
        redeemConfirmationModel.setRedeemAmount0("89789");
        redeemConfirmationModel.setBankACCT("4444444521515657");
        redeemConfirmationModel.setBankSubName("建设银行");
        redeemConfirmationModel.setContractSubBh("10045");
        redeemConfirmationModel.setContractBh("A2001");
        redeemConfirmationModel.setOpenDateNetWoth("1.23");
        redeemConfirmationModel.setCustName("吴小天");
        PictureUtil.getInstance().createImages(redeemConfirmationModel,"D:\\test","test.jpg");
    }

}

