package directory.model.jpa;

import directory.model.jpa.NewsGroup;
import directory.model.jpa.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-04T21:58:22")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, NewsGroup> idNewsgroup;
    public static volatile SingularAttribute<Post, Date> createdAt;
    public static volatile SingularAttribute<Post, Boolean> isRead;
    public static volatile SingularAttribute<Post, User> idUserTo;
    public static volatile SingularAttribute<Post, User> idUserFrom;
    public static volatile SingularAttribute<Post, String> content;
    public static volatile SingularAttribute<Post, Integer> idPost;

}