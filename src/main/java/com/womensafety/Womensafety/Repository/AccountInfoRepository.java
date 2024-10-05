package com.womensafety.Womensafety.Repository;

import com.womensafety.Womensafety.Model.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {
    Optional<AccountInfo> findByUserId(Long userId);
}
