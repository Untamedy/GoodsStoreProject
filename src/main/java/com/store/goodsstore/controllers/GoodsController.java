package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsCounterDto;
import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.services.GoodsCounterService;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.GoodsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@Controller("/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsGroupService groupService;
    @Autowired
    private GoodsCounterService goodsCounterService;

    @PostMapping("/goodslist/page/{groupId}/{page}")
    public ModelAndView getGoodsByGroupId(@PathVariable("groupId") int id,@PathVariable("page") int page) {
        ModelAndView model = new ModelAndView("goodsList");
        PageRequest pageable = PageRequest.of(page-1, 10);
        Page<GoodsDto> goodsPage = goodsService.getPaginatedGoods(id, pageable);
        int totalPage = goodsPage.getTotalPages();
        if(totalPage>0){
            List<Integer> pageNunbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addObject("pageNumber",pageNunbers);
        }        
        model.addObject("activeList", true);
        model.addObject("goodsList", goodsPage.getContent());
        
        return model;
    }

    @PostMapping("/saveGoods")
    public ModelAndView saveGoods(@RequestBody GoodsDto request) {
        GoodsDto response = goodsService.saveGoods(request);
        if (response != null) {
            return new ModelAndView("storePage", HttpStatus.OK);
        }
        return new ModelAndView("addGoodsPage", HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/removeGoods")
    public ModelAndView removeGoods(@RequestBody GoodsDto request) {
        if (goodsService.deleteGoods(request)) {
            return new ModelAndView("storePage", HttpStatus.OK);
        }
        return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/editGoods")
    public ModelAndView editGoods(@RequestBody GoodsDto request) {
        GoodsDto response = goodsService.updateGoods(request);
        if (response != null) {
            return new ModelAndView("groupPage", HttpStatus.OK);
        }
        return new ModelAndView("groupPage", HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/input")
    public ModelAndView addGoodsCount(@RequestBody GoodsDto[] goodsDto) {
        List<GoodsDto> goodsDtoList = goodsCounterService.increaseGoodsQuantity(goodsDto);
        if (goodsDtoList.isEmpty()) {
            return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
        }
        return new ModelAndView("storePage", "list", goodsDtoList);

    }

    @PostMapping("/sale")
    public ModelAndView saleGoods(@RequestBody GoodsDto[] goods) {
        goodsCounterService.decreaseGoodsQuantity(goods);
        return new ModelAndView("groupPage", HttpStatus.OK);
    }

}
