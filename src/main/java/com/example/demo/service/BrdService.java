package com.example.demo.service;

import com.example.demo.entity.BRD;
import com.example.demo.entity.Token;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.producer.EventInfoPublisher;
import com.example.demo.repository.BrdRepository;
import com.example.demo.repository.TokenRepository;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrdService {

    @Autowired
    BrdRepository brdRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private EventInfoPublisher redisPublisher;

    private XLogger xLogger = XLoggerFactory.getXLogger(getClass());

    public List<BRD> getAllBrd() {
        return brdRepository.findAll();
    }


    public Optional<BRD> getBrdById(Long bid) {
        if (!brdRepository.existsById(bid)) {
            throw new ResourceNotFoundException("Brd with id ", " not found");
        }
        return brdRepository.findById(bid);
    }

    public String createBrd(BRD brd) {
        if(brd == null){
            xLogger.error("Message : The Requested Object is Null");
            //throw new NullPointerException("NO Request OBJECT");
            return "The Requested Object is null, So didnt save anything";
        }
        xLogger.info("Message : The Request Object is not null and we are saving Object now");
        brdRepository.save(brd);
        return "The Object is Saved Successfully";
    }

    public BRD updateBrd(BRD brd){
        if(brd == null){
            xLogger.error("Message : The Request Object is Null");
            //throw new NullPointerException("NO Request OBJECT");
            return null;
        }
         brdRepository.save(brd);
        redisPublisher.publish("Message : updateBrdNumber Requested");
        xLogger.info("Message : Updated BRD Record");
        return brd;
    }


}