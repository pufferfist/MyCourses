package nju.mikasa.mycourses.entity;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
    public static String getStaticPath(){
        String path= null;
        try {
            path = ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ").replace('/', '\\');
            path=path.substring(1);
            path+="/CourseFiles";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static Calendar getCalendar(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        Date date;
        try {
            date = format.parse(time);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static boolean saveFile(MultipartFile file,String filePath){
        if(file.isEmpty()){
            return false;
        }
        File desFile=new File(filePath);
        if(!desFile.getParentFile().exists()){
            desFile.mkdirs();
        }
        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            System.out.println("文件复制失败");
            return false;
        }
        return true;
    }
}
