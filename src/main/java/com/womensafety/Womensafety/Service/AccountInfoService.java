package com.womensafety.Womensafety.Service;

import com.womensafety.Womensafety.Model.AccountInfo;
import com.womensafety.Womensafety.Repository.AccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountInfoService {
    @Autowired
    private AccountInfoRepository accountInfoRepository;

    public AccountInfo getAccountInfo(Long usersId) {
        return accountInfoRepository.findByUserId(usersId).orElse(null);
    }

    public AccountInfo saveAccountInfo(AccountInfo accountInfo) {
        return accountInfoRepository.save(accountInfo);
    }
}
