package com.example.Java_Demo.Controlller;

import com.example.Java_Demo.Models.Trademark;
import com.example.Java_Demo.Repository.TrademarkRepository;
import com.example.Java_Demo.Service.TrademarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trademark")
public class TrademarkController {
    private final TrademarkRepository trademarkRepository;
    private final TrademarkService trademarkService;
    @GetMapping
    public ResponseEntity<List<Trademark>>getallTrademark(){
        List<Trademark> trademarks = trademarkService.getAllTrademark();
        return new ResponseEntity<>(trademarks, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createTrademark(@RequestBody Trademark trademark){
        trademarkService.createTrandmark(trademark);
        return ResponseEntity.ok().body("+1");
    }
    @GetMapping("/detail/{id}")
    public Optional<Trademark> getTrademarkid(@PathVariable Long id){
        return trademarkRepository.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrademark(@PathVariable Long id){
        if (trademarkRepository.existsById(id)){
            trademarkRepository.deleteById(id);
            return ResponseEntity.ok().body("ok");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("err");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Trademark>updateTrademark(@RequestBody Trademark trademark,@PathVariable Long id){
        Trademark trademark1 = trademarkRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found"));
        trademark1.setTrademard_name(trademark.getTrademard_name());
        Trademark updateTrademark = trademarkRepository.save(trademark1);
        return ResponseEntity.ok(updateTrademark);
    }
}
