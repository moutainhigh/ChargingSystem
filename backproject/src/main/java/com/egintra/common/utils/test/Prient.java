package com.egintra.common.utils.test;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
/**
 * @Author: shafei
 * @Date: 2020-04-15 10:19
 * @Version: V1.0
 * @Description:
 */
public class Prient implements Printable {
    @Override
    public int print(Graphics g, PageFormat pf, int page)
            throws PrinterException {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("Default", Font.PLAIN, 14));
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g.drawString("等位排单号", 90, 10);
        g.drawString("------------------------------------------------------",
                10, 25);
        g.drawString("手机号码：***", 10, 40);
        g.drawString("就餐人数：3", 10, 55);
        g.drawString("领号日期：2013-08-09 12:00:00", 10, 70);
        g.drawString("------------------------------------------------------",
                10, 85);
        g.setFont(new Font("Default", Font.PLAIN, 25));
        g.drawString("中桌9号", 10, 100);
        g.setFont(new Font("Default", Font.PLAIN, 14));
        g.drawString("您之前还有" + 5 + "桌客人在等待", 10, 115);
        g.drawString("------------------------------------------------------",
                10, 130);
        g.drawString("*打印时间:2013-08-09 12:00:00*", 10, 145);
        g.drawString("*注意迎宾叫号，过号请到迎宾台*", 10, 160);
        g.drawString("*提前短信通知可能存在延时，仅供参考*", 10, 180);
        g.drawString("*最终解释权归本店所有*", 10, 195);
        g.drawString("店名：餐厅", 10, 210);

        return PAGE_EXISTS;
    }

    public static void main(String[] args) {

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Prient());
        try {
            job.print();
        } catch (PrinterException e) {
            System.out.println("================打印出现异常");
        }
    }
}