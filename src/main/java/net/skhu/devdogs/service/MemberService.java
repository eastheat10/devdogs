package net.skhu.devdogs.service;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.entity.Member;
import net.skhu.devdogs.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 존재 여부 체크
    public boolean joinCheck(MemberDto dto) {
        Member findMember = memberRepository.findByStudentId(dto.getStudentId());
        if (findMember == null) {
            return true;
        } else {
            return false;
        }
    }

    // 회원가입
    @Transactional
    public Long join(MemberDto memberDto) throws NoSuchAlgorithmException {
        memberDto.passwordEncoding(encrypt(memberDto.getPassword()));
        Member member = memberDto.toEntity();
        Member findMember = memberRepository.findByStudentId(member.getStudentId());
        if (findMember == null) {
            memberRepository.save(member);
            return member.getId();
        } else{
            return null;
        }
    }

    // 로그인
    public MemberDto login(MemberDto memberDto) throws Exception {
        Member findMember = memberRepository.findByStudentId(memberDto.getStudentId());
        memberDto.passwordEncoding(encrypt(memberDto.getPassword()));

        if (findMember != null) {
            MemberDto findMemberDto = new MemberDto(findMember);
            if (memberDto.getPassword().equals(findMemberDto.getPassword())) {
                return findMemberDto;
            }
        }
        return null;
    }

    public List<MemberDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (Member member : members) {
            MemberDto dto = new MemberDto(member);
            memberDtoList.add(dto);
        }
        return memberDtoList;
    }

    public MemberDto findById(Long id) {
        Member member = memberRepository.findById(id).get();
        MemberDto memberDto = new MemberDto(member);
        return memberDto;
    }

    public MemberDto findByStudentId(String studentId) {
        Member member = memberRepository.findByStudentId(studentId);
        MemberDto memberDto = new MemberDto(member);
        return memberDto;
    }

    public MemberDto findByName(String name) {
        Member member = memberRepository.findByName(name);
        MemberDto memberDto = new MemberDto(member);
        return memberDto;
    }

    @Transactional
    public void updateMember(MemberDto memberDto, Long id) throws NoSuchAlgorithmException {
        Member member = memberRepository.findById(id).get();
        Member updateMember = memberDto.toEntity();
        member.memberUpdate(updateMember.getPassword(), updateMember.getEmail(), updateMember.getPhoneNumber());
    }

    public int studentIdCheck(String studentId) {
        if(studentId.length() < 7){
            return 1;
        }
        int count = memberRepository.countByStudentId(studentId);

        return count;
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
