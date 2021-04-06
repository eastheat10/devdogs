package net.skhu.devdogs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.skhu.devdogs.entity.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    @NotBlank(message = "이름을 입력하세요")
    private String name;

    @NotBlank(message = "학번을 입력하세요")
    private int studentId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    private LocalDate joinDate;

    public MemberDto(Member member) throws NoSuchAlgorithmException {
        name = member.getName();
        password = member.getPassword();
        email = member.getEmail();
        phoneNumber = member.getPhoneNumber();
        studentId = member.getStudentId();
        joinDate = member.getJoinDate();
    }

    @Builder
    public MemberDto(String name,int studentId, String password,String email, String phoneNumber, LocalDate joinDate) {
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

    public static String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] passByte = password.getBytes(StandardCharsets.UTF_8);
        md.reset();
        byte[] digested = md.digest(passByte);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digested.length; i++) {
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    }
}
