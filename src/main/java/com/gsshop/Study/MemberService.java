package com.gsshop.Study;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(long id);

    void validate(long id);

    void notify(Study study);

    void notify(Member member);
}
