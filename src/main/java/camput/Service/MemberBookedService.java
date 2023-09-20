package camput.Service;

import camput.domain.MemberBooked;

public interface MemberBookedService {
    MemberBooked makeMemberReservation(Long campBookedId, String memberId);
}
