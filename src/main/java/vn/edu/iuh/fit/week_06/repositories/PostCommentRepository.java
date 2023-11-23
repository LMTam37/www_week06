package vn.edu.iuh.fit.week_06.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.week_06.models.PostComment;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findPostCommentByPost_IdAndParentComment_Id(Long postId, Long parrentCommentId);

    List<PostComment> findByPost_IdAndParentComment_Id(Long postId, Long parrentPostCommentId);

}
