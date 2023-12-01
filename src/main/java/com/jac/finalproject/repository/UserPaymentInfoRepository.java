package com.jac.finalproject.repository;

import com.jac.finalproject.entity.UserPaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfo, Long> {

    @Modifying
    @Query("UPDATE UserPaymentInfo upi SET upi.creditCardNumber = :creditCardNumber, upi.cvvCode = :cvvCode, upi.expirationDate = :expirationDate WHERE upi.user.id = :userId")
    void updateUserPaymentInfoByUserId(@Param("creditCardNumber") String creditCardNumber,@Param("cvvCode") String cvvCode, @Param("expirationDate") String expirationDate,@Param("userId") Long userId);
}
