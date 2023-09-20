package camput.repository;

import camput.domain.CampLiked;
import camput.domain.Camput;
import camput.domain.MemberLiked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampLikedRepository extends JpaRepository<CampLiked,Long> {
    CampLiked findByMemberLikedAndCamput(MemberLiked memberLiked, Camput camp);
    void deleteById(Long id);
}
