package vn.edu.iuh.fit.week_06.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.week_06.models.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
