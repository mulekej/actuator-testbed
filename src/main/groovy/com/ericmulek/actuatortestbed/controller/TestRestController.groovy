package com.ericmulek.actuatortestbed.controller

import com.ericmulek.actuatortestbed.annotation.CustomAuditTrail
import com.ericmulek.actuatortestbed.annotation.VerifyFeature
import com.ericmulek.actuatortestbed.domain.Person
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class TestRestController {

    @GetMapping('path1')
    @VerifyFeature(feature = 'SendMessageType3')
    @CustomAuditTrail
    String testEndpoint1() {
        log.info('testEndpoint1')
        'Hello Rest World'
    }

    @PostMapping('path2')
    @VerifyFeature(feature = 'SendMessageType3')
    @CustomAuditTrail
    String acceptPerson(@RequestBody Person person) {
        log.info("Step 5: In Controller. $person.firstName - $person.lastName")
        'My Response String'
    }
}
