package camput.repository;

import camput.domain.CampPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampPriceRepository extends JpaRepository<CampPrice,Long> {
}
