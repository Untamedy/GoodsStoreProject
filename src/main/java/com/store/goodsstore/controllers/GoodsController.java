package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.IncomeDocDto;
import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.services.GoodsCounterService;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.GoodsService;
import com.store.goodsstore.services.IncomDocService;
import com.store.goodsstore.services.OrderService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@Controller
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsGroupService groupService;
    @Autowired
    private GoodsCounterService goodsCounterService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private IncomDocService incomeService;

    @GetMapping("/goodslist/page/{groupId}/{page}")
    public ModelAndView getGoodsByGroupId(@PathVariable("groupId") int id, @PathVariable("page") int page) {
        ModelAndView model = new ModelAndView("goodsPage");
        PageRequest pageable = PageRequest.of(page - 1, 10);
        Page<GoodsDto> goodsPage = goodsService.getPaginatedGoods(id, pageable);
        int totalPage = goodsPage.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNunbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addObject("pageNumber", pageNunbers);
        }
        model.addObject("group", id);
        model.addObject("activeList", true);
        model.addObject("goodsList", goodsPage.getContent());

        return model;
    }

    @PostMapping("/saveGoods")
    public ModelAndView saveGoods(@ModelAttribute("goods") GoodsDto request) {
        goodsService.saveGoods(request);
        return new ModelAndView("redirect:goodsPage");
    }

    @GetMapping("/removeGoods/{groupId}/{goodsCode}")
    public ModelAndView removeGoods(@PathVariable("goodsCode") String code, @PathVariable("groupId") int id) {
        goodsService.deleteGoods(code);
        return new ModelAndView("redirect:/goodslist/page/" + id + "/1");
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
    public ModelAndView addGoodsCount(@ModelAttribute IncomeDocDto incomeDto) {
        goodsCounterService.increaseGoodsQuantity(incomeDto.getGoods());
        return new ModelAndView("storePage", "incomeDoc", incomeService.saveIncomeDoc(incomeDto));

    }

    @PostMapping("/sale")
    public ModelAndView saleGoods(@ModelAttribute OrderDto orderDto) {
        List<GoodsDto> goods = goodsCounterService.decreaseGoodsQuantity(orderDto);
        if (!goods.isEmpty()) {
            return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
        }
        return new ModelAndView("storePage", HttpStatus.OK);
    }

}
