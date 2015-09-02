package directory.model.jpa;

import directory.model.jpa.Post;
import directory.model.jpa.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-04T21:58:22")
@StaticMetamodel(NewsGroup.class)
public class NewsGroup_ { 

    public static volatile SingularAttribute<NewsGroup, Integer> idNewsgroup;
    public static volatile CollectionAttribute<NewsGroup, Post> postCollection;
    public static volatile CollectionAttribute<NewsGroup, User> userCollection;
    public static volatile SingularAttribute<NewsGroup, String> name;

}