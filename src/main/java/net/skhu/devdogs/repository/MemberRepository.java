package net.skhu.devdogs.repository;

import net.skhu.devdogs.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByStudentId(int studentId);

    public Member findByName(String name);

}
