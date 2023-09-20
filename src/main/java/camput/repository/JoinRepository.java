package camput.repository;

import camput.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<Member,Long> {
}
