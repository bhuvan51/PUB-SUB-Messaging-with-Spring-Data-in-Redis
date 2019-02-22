package com.example.demo.controller;

import com.example.demo.entity.BRD;
import com.example.demo.entity.Token;
import com.example.demo.repository.BrdRepository;
import com.example.demo.service.BrdService;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class BrdController {

    @Autowired
    BrdService brdService;

    @Autowired
    BrdRepository brdRepository;

    private XLogger xLogger = XLoggerFactory.getXLogger(getClass());

    @GetMapping("/brds")
    public List<BRD> getAllBrds(){
      return brdRepository.findAll();
    }

    @GetMapping("/getBrdById")
    public Optional<BRD> getBrdById(@RequestParam(value = "id") long bid){
        xLogger.info("Message : Entered getBrd Controller");
        return brdService.getBrdById(bid);
    }

    @PostMapping("/createBrd")
    public String createBrd(@RequestBody BRD brd){
        xLogger.info("Message : Entered CreateBrd Controller");
        return brdService.createBrd(brd);
    }

    @CachePut(value = "brd", key = "#bid")
    @PutMapping("/updatedBrd")
    public BRD updateBrd(@RequestBody BRD brd){
        BRD brds = brdService.updateBrd(brd);
        xLogger.info("Message : Entered updateBrd Controller");
        return brds;
    }

}
