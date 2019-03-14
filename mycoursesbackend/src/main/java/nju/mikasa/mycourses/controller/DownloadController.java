package nju.mikasa.mycourses.controller;

import com.google.gson.Gson;
import nju.mikasa.mycourses.entity.StatusMessage;
import nju.mikasa.mycourses.exception.RollBackException;
import nju.mikasa.mycourses.service.CourseService;
import nju.mikasa.mycourses.serviceimpl.ZipUtil;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;

@Controller
public class DownloadController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletResponse response;

    @PostMapping("downloadAssignment")
    public void downloadAssignment(@RequestBody Map<String, Object> params) throws RollBackException, IOException {
        String path = courseService.downloadAssignment(session.getAttribute("username").toString(),
                Long.parseLong(params.get("assignmentId").toString()));
        if (path == null) {
            noFile();
        } else {
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=assignments.zip");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            ZipUtil.toZip(path, response.getOutputStream(), true);
        }
    }

    @PostMapping("getAssignmentGrade")
    @ResponseBody
    public void getAssignmentGrade(@RequestBody Map<String, Object> params) throws IOException {
        String path = courseService.getAssignmentGrade(session.getAttribute("username").toString(),
                Long.parseLong(params.get("assignmentId").toString()));
        if (path == null) {
            noFile();
        } else {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=assignmentGrade.csv");
            writeStream(path, response.getOutputStream());
        }
    }


    @PostMapping("getCourseGrade")
    @ResponseBody
    public void getCourseGrade(@RequestBody Map<String, Object> params) throws IOException {
        String path = courseService.getCourseGrade(session.getAttribute("username").toString(),
                Long.parseLong(params.get("publishId").toString()));
        if (path == null) {
            noFile();
        } else {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=courseGrade.csv");
            writeStream(path, response.getOutputStream());
        }
    }

    private void writeStream(String path, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        byte[] stream = new byte[fileInputStream.available()];
        fileInputStream.read(stream);
        outputStream.write(stream);
        outputStream.flush();
        outputStream.close();
    }

    private void noFile() throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(StatusMessage.fileNotExist));
        writer.flush();
        writer.close();
    }
}
