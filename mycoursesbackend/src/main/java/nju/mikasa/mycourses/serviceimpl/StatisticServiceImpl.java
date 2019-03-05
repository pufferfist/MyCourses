package nju.mikasa.mycourses.serviceimpl;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.service.StatisticService;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Override
    public ResponseMessage teacherStatisticInfo(String teacherId) {
        return null;
    }

    @Override
    public ResponseMessage studentStatisticInfo(String studentId) {
        return null;
    }

    @Override
    public ResponseMessage adminStatisticInfo(String adminId) {
        return null;
    }
}
