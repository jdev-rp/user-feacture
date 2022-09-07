package kr.submit.userfeature.user.repository;

import kr.submit.userfeature.core.jpa.template.PagingRepositoryTemplate;
import kr.submit.userfeature.user.domain.entity.UserEntity;
import kr.submit.userfeature.user.dto.UserQuery;
import kr.submit.userfeature.user.dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, PagingRepositoryTemplate<UserResponse, UserQuery> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query("update UserEntity set password = :password, updatedBy = ?#{principal?.userId}, updatedAt = current_timestamp where userId = :userId")
    Optional<UserEntity> changePassword(@Param("userId") Long userId, @Param("password") String password);

}