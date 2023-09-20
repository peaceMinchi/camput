package camput.repository;

import camput.domain.CampImageFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampImageFileRepository  extends JpaRepository<CampImageFiles,Long> {
}
