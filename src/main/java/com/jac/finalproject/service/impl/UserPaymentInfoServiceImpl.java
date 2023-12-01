package com.jac.finalproject.service.impl;

import com.jac.finalproject.entity.UserPaymentInfo;
import com.jac.finalproject.repository.UserPaymentInfoRepository;
import com.jac.finalproject.service.UserPaymentInfoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPaymentInfoServiceImpl implements UserPaymentInfoService {

    private final UserPaymentInfoRepository userPaymentInfoRepository;

    @Autowired
    public UserPaymentInfoServiceImpl(UserPaymentInfoRepository userPaymentInfoRepository) {
        this.userPaymentInfoRepository = userPaymentInfoRepository;
    }

    @Override
    public UserPaymentInfo createUserPaymentInfo(UserPaymentInfo userPaymentInfo) {
        return userPaymentInfoRepository.save(userPaymentInfo);
    }

    @Override
    public Optional<UserPaymentInfo> getUserPaymentInfoById(Long userPaymentInfoId) {
        return userPaymentInfoRepository.findById(userPaymentInfoId);
    }

    @Override
    public List<UserPaymentInfo> getAllUserPaymentInfos() {
        return userPaymentInfoRepository.findAll();
    }

    @Override
    public UserPaymentInfo updateUserPaymentInfo(UserPaymentInfo userPaymentInfo) {
        return userPaymentInfoRepository.save(userPaymentInfo);
    }

    @Override
    public void deleteUserPaymentInfo(Long userPaymentInfoId) {
        userPaymentInfoRepository.deleteById(userPaymentInfoId);
    }

    @Override
    @Transactional
    public void updateUserPaymentInfoByUserId(UserPaymentInfo updatedUserUserPaymentInfo, Long userId) {
        userPaymentInfoRepository.updateUserPaymentInfoByUserId(updatedUserUserPaymentInfo.getCreditCardNumber(),updatedUserUserPaymentInfo.getCvvCode(),updatedUserUserPaymentInfo.getExpirationDate(),userId);
    }
}
