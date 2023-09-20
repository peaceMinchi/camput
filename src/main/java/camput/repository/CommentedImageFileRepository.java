package camput.repository;

import camput.domain.CommentedImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentedImageFileRepository extends JpaRepository<CommentedImageFile,Long> {
}
