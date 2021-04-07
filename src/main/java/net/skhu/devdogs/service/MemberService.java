package net.skhu.devdogs.service;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.entity.Member;
import net.skhu.devdogs.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
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
        memberDto.passwordEncoding(memberDto.getPassword());
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
    public boolean login(HttpSession session, MemberDto dto) throws NoSuchAlgorithmException {
        Member member = memberRepository.findByStudentId(dto.getStudentId());
        // MemberDto dto = new MemberDto(member);
        if (member == null) {
            return false;
        }
        if (member.getPassword().equals(dto.getPassword())) {
            session.setAttribute("member", dto);
            return true;
        }
        return false;
    }

    public MemberDto loginCheck(MemberDto memberDto) throws Exception {
        Member findMember = memberRepository.findByStudentId(memberDto.getStudentId());
        memberDto.passwordEncoding(memberDto.getPassword());

        if (findMember != null) {
            MemberDto findMemberDto = new MemberDto(findMember);
            if (memberDto.getPassword().equals(findMemberDto.getPassword())) {
                return findMemberDto;
            }
        }
        return null;
    }

    // 로그아웃
    public void logout(HttpSession session) {
        session.removeAttribute("member");
    }

    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

    public Member findById(Long id) {
        Member member = memberRepository.findById(id).get();
        return member;
    }

    public Member findByStudentId(String studentId) {
        Member member = memberRepository.findByStudentId(studentId);
        return member;
    }

    public Member findByName(String name) {
        Member member = memberRepository.findByName(name);
        return member;
    }

    @Transactional
    public void updateMember(MemberDto memberDto, Long id) throws NoSuchAlgorithmException {
        Member member = memberRepository.findById(id).get();
        Member updateMember = memberDto.toEntity();
        member.memberUpdate(updateMember.getPassword(), updateMember.getEmail(), updateMember.getPhoneNumber());
    }

    public int userIdCheck(String studentId) {

        int count = memberRepository.countByStudentId(studentId);

        return count;
    }
}
