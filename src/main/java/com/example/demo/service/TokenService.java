package com.example.demo.service;

import com.example.demo.entity.BRD;
import com.example.demo.entity.Token;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.producer.EventInfoPublisher;
import com.example.demo.repository.TokenRepository;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private EventInfoPublisher redisPublisher;

    private XLogger xLogger = XLoggerFactory.getXLogger(getClass());

    @Cacheable(value = "tokens", key = "#token_id")
    public Token getToken(@PathVariable String token_id){
        return tokenRepository.findById(Long.valueOf(token_id)).orElse(null);
    }

    @Cacheable(value = "token", key = "#p0")
    public Token getTokenByNumber( Long tokenNumber){

        Token token = tokenRepository.findByTokenNumber(tokenNumber);
        redisPublisher.publish("Message : getAllAvailableBrdNumber Requested");
        xLogger.info("Message : Requested for AllAvailableBrdNumber");
        return token;
    }

    public List<Token> getTokens() {
        return tokenRepository.findAll();
    }

    @CachePut(value = "tokens", key = "#token.token_id")
    public Token createToken(Token token) {
        if(token == null){
            xLogger.error("Message : The Request Object is Null");
            //throw new NullPointerException("NO Request OBJECT");
            return null;
        }
        xLogger.info("Message : The Request Object is not null and we are saving Object now");
        tokenRepository.save(token);
        return token;
    }

}
