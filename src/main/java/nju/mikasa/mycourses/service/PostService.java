package nju.mikasa.mycourses.service;

import nju.mikasa.mycourses.entity.ResponseMessage;

public interface PostService {
    ResponseMessage post(String userId, String courseId, String title, String text);

    ResponseMessage followup(String userId, String postId, String text);
}
