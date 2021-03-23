package com.gsshop.Study;

import java.util.Optional;

public interface RcmdService {
    Optional<Member> findById(long id);
}
