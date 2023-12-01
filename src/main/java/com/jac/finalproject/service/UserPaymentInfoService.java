package com.jac.finalproject.service;

import com.jac.finalproject.entity.UserPaymentInfo;

import java.util.List;
import java.util.Optional;

public interface UserPaymentInfoService {

    UserPaymentInfo createUserPaymentInfo(UserPaymentInfo userPaymentInfo);

    Optional<UserPaymentInfo> getUserPaymentInfoById(Long userPaymentInfoId);

    List<UserPaymentInfo> getAllUserPaymentInfos();

    UserPaymentInfo updateUserPaymentInfo(UserPaymentInfo userPaymentInfo);

    void deleteUserPaymentInfo(Long userPaymentInfoId);

    void updateUserPaymentInfoByUserId(UserPaymentInfo updatedUserUserPaymentInfo, Long userId);
}
