package aurora.timer.client.service;

import aurora.timer.client.ServerURL;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by hao on 17-1-30.
 */
public class TimerYeah {
//    private String id;
    public boolean isStop;
    private static Logger logger = Logger.getLogger("timer");

//    @Override
//    public void run() {
//        this.isStop = true;
//        logger.info("开始计时");
//        while (isStop) {
//            addTime(this.id);
//            long sleepTime = 5 * 60 * 1000;
//            try {
//                Thread.sleep(sleepTime);
//                logger.fine("是的，我正在计时");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "后台计时崩了\n", "提示", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        logger.info("后台计时结束");
//    }
//
//    public TimerYeah() {
//        this("0");
//    }

//    public TimerYeah(String id) {
//        this.id = id;
//    }

    public static boolean addTime(String id) {
        boolean flag = false;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(ServerURL.TIMER + "?id=" + id);
            System.out.println(ServerURL.TIMER);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String req = null;
            req = reader.readLine();

            if ("true".equals(req)) {
                flag = true;
                logger.info("上传时间");
            } else {
                logger.warning("加时返回错误信息：" + req);
                JOptionPane.showMessageDialog(null, "上传时间失败。。请重启试试。。\n", "提示", JOptionPane.ERROR_MESSAGE);
            }

            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "上传时间函数异常。。\n", "提示", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }
}
