package kr.submit.userfeature.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.submit.userfeature.core.error.DuplicateException;
import kr.submit.userfeature.user.domain.service.UserDomainService;
import kr.submit.userfeature.user.dto.UserRequest;
import kr.submit.userfeature.user.dto.UserResponse;
import kr.submit.userfeature.user.application.UserService;
import kr.submit.userfeature.user.domain.code.RoleType;
import kr.submit.userfeature.verify.application.VerifyService;
import kr.submit.userfeature.verify.domain.code.VerifyType;
import kr.submit.userfeature.verify.domain.code.VerifyUsage;
import kr.submit.userfeature.verify.dto.VerifyRequest;
import kr.submit.userfeature.verify.dto.VerifyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "회원가입")
@Slf4j
@RequiredArgsConstructor
@RestController
public class SignUpController {

    private final UserService userService;
    private final UserDomainService userDomainService;
    private final VerifyService verifyService;

    @Operation(summary = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public UserResponse signUp(@Validated(UserView.Create.class) UserRequest userRequest) {
        return userService.create(userRequest.setRoleType(RoleType.USER));
    }

    @Operation(summary = "회원가입 핸드폰 번호 중복확인")
    @GetMapping("/sign-up/duplicate/phone-number/{phoneNumber}")
    public void duplicateByPhoneNumber(@PathVariable String phoneNumber) {
        if(userDomainService.isDuplicateByPhoneNumber(phoneNumber)) throw new DuplicateException("핸드폰번호가 중복됩니다");
    }

    @Operation(summary = "회원가입 이메일 중복확인")
    @GetMapping("/sign-up/duplicate/email/{email}")
    public void duplicateByEmail(@PathVariable String email) {
        if(userDomainService.isDuplicateByEmail(email)) throw new DuplicateException("이메일이 중복됩니다");
    }

    @Operation(summary = "회원가입 핸드폰번호 전송")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/sign-up/send/phone-number/{phoneNumber}")
    public void sendVerifyNumberByPhoneNumber(@PathVariable String phoneNumber) {
        verifyService.sendVerifyNumberByVerifyTypeValue(VerifyRequest.create()
                .setVerifyUsage(VerifyUsage.SIGNUP)
                .setVerifyType(VerifyType.PHONE_NUMBER)
                .setVerifyTypeValue(phoneNumber)
        );
    }

    @Operation(summary = "회원가입 이메일 전송")
    @PostMapping("/sign-up/send/email/{email}")
    public void sendVerifyNumberByEmail(@PathVariable String email) {
        verifyService.sendVerifyNumberByVerifyTypeValue(VerifyRequest.create()
                .setVerifyUsage(VerifyUsage.SIGNUP)
                .setVerifyType(VerifyType.EMAIL)
                .setVerifyTypeValue(email)
        );
    }


    @Operation(summary = "회원가입 핸드폰번호 인증확인")
    @PostMapping("/sign-up/verify/phone-number")
    public void verifyByPhoneNumber(@RequestBody VerifyRequest verifyRequest) {
        verifyService.verifyNumber(verifyRequest
                .setVerifyUsage(VerifyUsage.SIGNUP)
                .setVerifyType(VerifyType.PHONE_NUMBER));
    }

    @Operation(summary = "회원가입 이메일 인증확인")
    @PostMapping("/sign-up/verify/email")
    public void verifyByEmail(@RequestBody VerifyRequest verifyRequest) {
        verifyService.verifyNumber(verifyRequest
                .setVerifyUsage(VerifyUsage.SIGNUP)
                .setVerifyType(VerifyType.EMAIL));
    }

}