/*
 * Copyright (C) 2015 Ali DALHOUSS <ali.dalhouss@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mailbox.jms;

import java.util.Enumeration;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */

@Stateless
public class MessageReceiverSync {
    
    @Resource(mappedName = "jms/JMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "jms/myQueue")
    Queue myQueue;
    
    private String message;
    
    public String receiveMessage() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer queueBrowser = session.createConsumer(myQueue, "username = 'ali'");
            String enumeration = queueBrowser.getMessageSelector();
            int count=0;
            
            System.out.println(enumeration);
            session.close();
            connection.close();
        } catch (JMSException e) {
            System.out.println("JMS ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "";
    }
    
}
