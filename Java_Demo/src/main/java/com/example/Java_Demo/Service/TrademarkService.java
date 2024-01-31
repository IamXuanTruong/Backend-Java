package com.example.Java_Demo.Service;

import com.example.Java_Demo.Models.Trademark;
import com.example.Java_Demo.Repository.TrademarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrademarkService {
    private final TrademarkRepository trademarkRepository;
    @Autowired
    public TrademarkService(TrademarkRepository trademarkRepository){
        this.trademarkRepository = trademarkRepository;
    }
    public List<Trademark> getAllTrademark(){
        return trademarkRepository.findAll();
    }
    public Trademark createTrandmark(Trademark trademark){
        Trademark trademark1 = new Trademark();
        trademark1.setTrademard_name(trademark.getTrademard_name());
        return trademarkRepository.save(trademark1);
    }
    public Optional<Trademark> getTrademarkid(Long id){
        return trademarkRepository.findById(id);
    }
    public void delateTrademark(Long id){
        trademarkRepository.deleteById(id);
    }
}
