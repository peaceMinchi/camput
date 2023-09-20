package camput.repository;

import camput.domain.CampAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampAddressRepository  extends JpaRepository<CampAddress,Long> {

}
