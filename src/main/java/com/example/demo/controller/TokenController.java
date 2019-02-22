package com.example.demo.controller;

import com.example.demo.entity.BRD;
import com.example.demo.entity.Token;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.BrdService;
import com.example.demo.service.TokenService;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    BrdService brdService;

    private XLogger xLogger = XLoggerFactory.getXLogger(getClass());


    @GetMapping("/getTokenById")
    public Token getToken(@RequestParam(value = "id") String id){
        xLogger.info("Message : Entered getToken Controller");
        return tokenService.getToken(id);
    }

    @GetMapping("/getTokenNumber")
    public Token getTokenByNumber(@RequestParam(value="token_number") String tokenNumber){
        return tokenService.getTokenByNumber(Long.valueOf(tokenNumber));
    }

    @PostMapping("/createToken")
    public Token createToken(@RequestBody Token token){
        xLogger.info("Message : Entered getToken Controller");
         return tokenService.createToken(token);
    }

    @GetMapping("/getAllTokens")
    public List<Token> getAllNotes() {
        xLogger.info("Message : Entered getToken Controller");
        return tokenRepository.findAll();
    }

    @PutMapping("/updateToken")
    public Token updateToken(@RequestBody Token token){
        xLogger.info("Message : Entered update Token Controller");
        return tokenService.createToken(token);
    }

}



