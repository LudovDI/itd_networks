package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.PasswordResetTokenData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenData, Long> {
    Optional<PasswordResetTokenData> findByToken(String token);
    Optional<PasswordResetTokenData> findByUser(UserData userData);
}
