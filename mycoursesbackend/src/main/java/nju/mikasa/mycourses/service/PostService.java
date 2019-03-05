package nju.mikasa.mycourses.service;

import nju.mikasa.mycourses.entity.ResponseMessage;

public interface PostService {
    ResponseMessage post(String userId, long courseId, String title, String text);

    ResponseMessage followup(String userId, long postId, String text);

    ResponseMessage getPostList(long courseId);

    ResponseMessage getPost(long postId);
}
