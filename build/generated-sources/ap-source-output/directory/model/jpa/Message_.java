package directory.model.jpa;

import directory.model.jpa.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-04T21:58:22")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, Date> createdAt;
    public static volatile SingularAttribute<Message, Integer> idMessage;
    public static volatile SingularAttribute<Message, Boolean> isRead;
    public static volatile SingularAttribute<Message, User> idUserTo;
    public static volatile SingularAttribute<Message, User> idUserFrom;
    public static volatile SingularAttribute<Message, String> content;

}