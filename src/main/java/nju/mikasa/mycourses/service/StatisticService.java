package nju.mikasa.mycourses.service;

import nju.mikasa.mycourses.entity.ResponseMessage;

public interface StatisticService {
    ResponseMessage teacherStatisticInfo(String teacherId);

    ResponseMessage studentStatisticInfo(String studentId);

    ResponseMessage adminStatisticInfo(String adminId);
}
