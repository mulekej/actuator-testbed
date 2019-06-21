package com.ericmulek.actuatortestbed.service


import org.springframework.stereotype.Service

@Service
class MyAuthService {

    boolean authenticate(String token) {
        true
    }

    boolean verifyFeature(String feature) {
        feature == 'SendMessageType3'
    }

    Object getEndpoint(String identifier){
        new Boolean(true)
    }
}
