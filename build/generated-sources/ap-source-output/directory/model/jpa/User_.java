package directory.model.jpa;

import directory.model.jpa.Message;
import directory.model.jpa.NewsGroup;
import directory.model.jpa.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-04T21:58:22")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Boolean> readRight;
    public static volatile SingularAttribute<User, NewsGroup> idNewsgroup;
    public static volatile SingularAttribute<User, Integer> idUser;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Integer> role;
    public static volatile SingularAttribute<User, String> mailbox;
    public static volatile CollectionAttribute<User, Post> postCollection;
    public static volatile SingularAttribute<User, Boolean> writeRight;
    public static volatile CollectionAttribute<User, Message> messageCollection;
    public static volatile CollectionAttribute<User, Message> messageCollection1;
    public static volatile CollectionAttribute<User, Post> postCollection1;
    public static volatile SingularAttribute<User, String> username;

}