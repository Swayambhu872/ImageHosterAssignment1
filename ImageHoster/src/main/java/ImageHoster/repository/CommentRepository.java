package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;
    //The method receives the comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public List<Comment> createComment(Comment newComment, Integer imageId) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        List<Comment> comments=getAllCommentsByImageId(imageId);
        comments.add(newComment);
        return comments;
    }
    //The method creates an instance of EntityManager
    //Executes JPQL query to fetches the images from the database of given imageId
    //Returns a single result as images fetched from the database
    // Another JPQL query fetch all the comments related to that query
    public List<Comment> getAllCommentsByImageId(Integer imageId) {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Image> typedQuery = entityManager.createQuery("SELECT i from Image i where i.id =:imageId", Image.class).setParameter("imageId", imageId);
        Image image = typedQuery.getSingleResult();
        TypedQuery<Comment> typedQuery1 = entityManager.createQuery("SELECT c from Comment c where c.image=:image ", Comment.class).setParameter("image",image);
        List<Comment> list = typedQuery1.getResultList();

        return list;
    }
}
