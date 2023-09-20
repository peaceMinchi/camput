package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CampDateInfo {
    @Id
    @GeneratedValue
    @Column(name = "campdateinfo_id")
    private Long id;
    private String notWorkingDay;
    private String openDeCl;
    private String openSessions;
    @Builder
    public CampDateInfo(String notWorkingDay, String openDeCl, String openSessions) {
        this.notWorkingDay = notWorkingDay;
        this.openDeCl = openDeCl;
        this.openSessions = openSessions;
    }
}
