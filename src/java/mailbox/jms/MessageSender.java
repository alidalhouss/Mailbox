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

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */

@Stateless
public class MessageSender {
    
    @Resource(mappedName = "jms/JMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "jms/myQueue")
    Queue queue;
    
    public void sendMessage(String message) {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            textMessage = session.createTextMessage();
            textMessage.setObjectProperty("user", "ali");
            textMessage.setText(message);
            messageProducer.send(textMessage);
            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            System.out.println("ERROR JMS: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
}
