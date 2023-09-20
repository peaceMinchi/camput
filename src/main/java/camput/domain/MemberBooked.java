package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberBooked {
        @Id
        @GeneratedValue
        @Column(name = "memberBooked_id")
        private Long id;
        private LocalDateTime bookedDay;
        private LocalDate mStartDay;
        private LocalDate mEndDay;
        private String bookedCampName;
        private String bookedCampAddress;
        private String bookedCampImageUrl;
        @ManyToOne(fetch = FetchType.LAZY)
        private Member member;
        @Builder
        public MemberBooked(LocalDateTime bookedDay, LocalDate mStartDay, LocalDate mEndDay, String bookedCampName, String bookedCampAddress, String bookedCampImageUrl, Member member) {
                this.bookedDay = bookedDay;
                this.mStartDay = mStartDay;
                this.mEndDay = mEndDay;
                this.bookedCampName = bookedCampName;
                this.bookedCampAddress = bookedCampAddress;
                this.bookedCampImageUrl = bookedCampImageUrl;
                this.member = member;
        }
}

