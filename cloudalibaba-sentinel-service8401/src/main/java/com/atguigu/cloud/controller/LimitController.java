package com.atguigu.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LimitController {

    @GetMapping(value = "/cheems/1")
    public String cheems() {
        return "sentinelResourco(*￣▽￣*)ブ";
    }

    @GetMapping(value = "/cheemsKing")
    @SentinelResource(value = "cheemsKing", blockHandler = "cheemsKingHandler")
    public String cheemsKing() {
        return "cheemsKing:sentinelResourco(*￣▽￣*)ブ";
    }

    public String cheemsKingHandler(BlockException blockException) {
        return "限流了,cheemsKing,请回吧!!!/(ㄒoㄒ)/~~";
    }

    @GetMapping(value = "/cheemsQueen/{id}")
    @SentinelResource(value = "cheemsQueen", blockHandler = "cheemsQueenHandler", fallback = "cheemsQueenFallback")
    public String cheemsQueen(@PathVariable("id") String id){
        if (id.equalsIgnoreCase("No1")){
            throw new RuntimeException("你太美丽了!");
        }
        return "欢迎你的到来,my unique!!! Queen o(*￣▽￣*)ブ" + id;
    }

    public String cheemsQueenHandler(@PathVariable("id") String id, BlockException blockException){
        return "我很抱歉,皇后,请您稍等一下!" + id;
    }

    public String cheemsQueenFallback(@PathVariable("id") String id, Throwable e){
        return "这是最后的馈赠了!" + id + e.getMessage();
    }

    @GetMapping(value = "/chemmsKids")
    @SentinelResource(value = "cheemsKids",blockHandler = "cheemsKidsHandler")
    public String cheemsKids(@RequestParam(value = "kid1",required = false) String kid1,
                             @RequestParam(value = "kie2",required = false) String kid2){
        return "你们好呀o(*￣▽￣*)ブ,have a good time!";
    }
    public String cheemsKidsHandler(String kid1, String kid2, BlockException blockException){
        return "快跑,它是人类!!!";
    }

    @GetMapping(value = "/cheems")
    public String jemms(){
        return "cheems 测试 sentinel 授权规则";
    }

}
