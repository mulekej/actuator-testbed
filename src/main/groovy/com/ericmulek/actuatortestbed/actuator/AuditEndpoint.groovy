package com.ericmulek.actuatortestbed.actuator

import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.stereotype.Component

@Component
@Endpoint(id = 'myAuditEndpoint')
class AuditEndpoint {

    //see InMemoryAuditEventRepository for example simple cache
    @ReadOperation
    Map getAuditEvents() {
        [messageId: 'A MessageId I searched for', senderIp: '127.0.0.1', senderName: 'localhost']
    }
}
