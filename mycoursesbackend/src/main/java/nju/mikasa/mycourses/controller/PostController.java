package nju.mikasa.mycourses.controller;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class PostController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PostService postService;

    @PostMapping("post")
    public ResponseMessage post(@RequestBody Map<String, Object> params) {
        return postService.post(request.getSession().getAttribute("username").toString(),
                Long.parseLong(params.get("courseId").toString()),
                params.get("title").toString(), params.get("text").toString());
    }

    @PostMapping("followup")
    public ResponseMessage followup(@RequestBody Map<String, Object> params) {
        return postService.followup(request.getSession().getAttribute("username").toString(),
                Long.parseLong(params.get("postId").toString()), params.get("text").toString());
    }

    @PostMapping("getPostList")
    public ResponseMessage getPostList(@RequestBody Map<String, Object> params) {
        return postService.getPostList(Long.parseLong(params.get("courseId").toString()));
    }

    @PostMapping("getFollowUp")
    public ResponseMessage getFollowUp(@RequestBody Map<String, Object> params) {
        return postService.getFollowUp(Long.parseLong(params.get("postId").toString()));
    }
}
