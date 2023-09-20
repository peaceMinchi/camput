package camput.Service;


import camput.Dto.MemberJoinDto;

//@Service
//@RequiredArgsConstructor
public interface JoinService {

    void join(MemberJoinDto memberJoinDto);
    void validateDuplicateMember(MemberJoinDto memberJoinDto);

    public String memberLoginCheck(String memberEmail);

}
