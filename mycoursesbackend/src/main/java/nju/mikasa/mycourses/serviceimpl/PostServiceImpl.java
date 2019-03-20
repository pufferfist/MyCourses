package nju.mikasa.mycourses.serviceimpl;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.entity.StatusMessage;
import nju.mikasa.mycourses.entity.bbs.FollowUp;
import nju.mikasa.mycourses.entity.bbs.Post;
import nju.mikasa.mycourses.entity.course.Course;
import nju.mikasa.mycourses.entity.user.User;
import nju.mikasa.mycourses.repository.FollowUpRepository;
import nju.mikasa.mycourses.repository.PostRepository;
import nju.mikasa.mycourses.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    FollowUpRepository followUpRepository;

    @Override
    public ResponseMessage post(String userId, long courseId, String title, String text) {
        Post post=new Post(new Course(courseId),new User(userId),title,text, Calendar.getInstance());
        postRepository.save(post);
        return StatusMessage.createSuccess;
    }

    @Override
    public ResponseMessage followup(String userId, long postId, String text) {
        FollowUp followUp=new FollowUp(new Post(postId),new User(userId),text,Calendar.getInstance());
        followUpRepository.save(followUp);
        return StatusMessage.createSuccess;
    }

    @Override
    public ResponseMessage getPostList(long courseId) {
        List<Post> postList=postRepository.findByCourse(new Course(courseId));
        return StatusMessage.getSuccess.setData(postList);
    }

    @Override
    public ResponseMessage getFollowUp(long postId) {
        return StatusMessage.getSuccess.setData(followUpRepository.findByPost(new Post(postId)));
    }
}
