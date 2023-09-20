package camput.Service;

import camput.Dto.MemberJoinDto;

public interface MemberUpdateService {

    MemberJoinDto view(String loginId);
    String update(MemberJoinDto memberJoinDto);
}
