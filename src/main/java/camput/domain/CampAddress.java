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
public class CampAddress {

    @Id
    @GeneratedValue
    @Column(name = "campaddress_id")
    private Long id;
    private String simpleAddr;
    private String detailAddr;
    private String zipeCode;
    private String doNm;
    private String sigunNum;

    @Builder
    public CampAddress(String simpleAddr, String detailAddr, String zipeCode, String doNm, String sigunNum) {
        this.simpleAddr = simpleAddr;
        this.detailAddr = detailAddr;
        this.zipeCode = zipeCode;
        this.doNm = doNm;
        this.sigunNum = sigunNum;
    }
}
