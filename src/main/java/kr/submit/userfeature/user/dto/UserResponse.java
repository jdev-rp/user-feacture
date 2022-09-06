package kr.submit.userfeature.user.dto;

import com.fasterxml.jackson.annotation.JsonView;
import kr.submit.userfeature.user.domain.code.RoleType;
import kr.submit.userfeature.user.domain.entity.UserEntity;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@Builder(access = AccessLevel.PACKAGE)
public class UserResponse {

    @JsonView({UserView.List.class, UserView.MyInfo.class})
    private final Long userId;
    @JsonView({UserView.List.class, UserView.MyInfo.class})
    private final String nickName;
    @JsonView({UserView.List.class, UserView.MyInfo.class})
    private final String name;
    @JsonView({UserView.List.class, UserView.MyInfo.class})
    private final String email;
    @JsonView({UserView.List.class, UserView.MyInfo.class})
    private final String phoneNumber;
    @JsonView(UserView.List.class)
    private final RoleType roleType;

    private final boolean enabled;

    public static UserResponse fromEntity(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getUserId())
                .nickName(userEntity.getNickname())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .roleType(userEntity.getRoleType())
                .build();
    }
}