package net.skhu.devdogs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.skhu.devdogs.entity.Member;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    @NotBlank(message = "입력하세요!")
    private String name;

    @NotBlank(message = "입력하세요!")
    @Size(min = 7, message = "7자 이상!")
    private String studentId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "8자~20자")
    private String password;

    @NotBlank(message = "입력해주세요.")
    private String email;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    public MemberDto(Member member) {
        name = member.getName();
        password = member.getPassword();
        email = member.getEmail();
        phoneNumber = member.getPhoneNumber();
        studentId = member.getStudentId();
        joinDate = member.getJoinDate();
    }

    @Builder
    public MemberDto(String name, String studentId, String password, String email, String phoneNumber, LocalDate joinDate) {
        this.name = name;
        this.studentId = studentId;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.joinDate = joinDate;
    }

    public Member toEntity() {
        Member member = Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .phoneNumber(phoneNumber)
                .studentId(studentId)
                .joinDate(joinDate)
                .build();
        return member;
    }

    public void passwordEncoding(String encodingPassword) {
        this.password = encodingPassword;
    }

}
