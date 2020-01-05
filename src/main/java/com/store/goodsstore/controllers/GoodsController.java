package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.EditGoodsDto;
import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.IncomeDocResponseDto;
import com.store.goodsstore.dto.OrderDto;
import com.store.goodsstore.dto.OrganizationDto;
import com.store.goodsstore.entities.Customer;
import com.store.goodsstore.entities.Goods;
import com.store.goodsstore.entities.Order;
import com.store.goodsstore.entities.Organization;
import com.store.goodsstore.services.CustomerService;
import com.store.goodsstore.services.GoodsCounterService;
import com.store.goodsstore.services.GoodsGroupService;
import com.store.goodsstore.services.GoodsService;
import com.store.goodsstore.services.IncomDocService;
import com.store.goodsstore.services.OrderService;
import com.store.goodsstore.services.OrganizationService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.omg.IOP.CodeSets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@Controller

@SessionAttributes(types = OrderDto.class)
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

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrganizationService orgService;

    @ModelAttribute("order")
    public void goodsList(Model model) {
        model.addAttribute("order", new OrderDto());
    }

    @GetMapping("/goodslist/page/{orgcode}/{groupId}/{page}")
    public ModelAndView getGoodsByGroupId(@PathVariable("groupId") int id, @PathVariable("page") int page, @PathVariable("orgcode") String code) {
        ModelAndView model = new ModelAndView("goodsPage");
        PageRequest pageable = PageRequest.of(page - 1, 10);
        Page<GoodsDto> goodsPage = goodsService.getPaginatedGoods(id, pageable);
        int totalPage = goodsPage.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNunbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addObject("pageNumber", pageNunbers);
        }

        List<Customer> customers = customerService.getByOrgCode(code);
        model.addObject("group", id);
        model.addObject("customers", customers);
        model.addObject("activeList", true);
        model.addObject("goodsList", goodsPage.getContent());

        return model;
    }

    @PostMapping("/saveGoods")
    public ModelAndView saveGoods(@ModelAttribute("goods") GoodsDto request) {
        goodsService.saveGoods(request);
        return new ModelAndView("redirect:/goodslist/page/" + request.getGroupId() + "/1");
    }

    @GetMapping("/removeGoods/{groupId}/{goodsCode}")
    public ModelAndView removeGoods(@PathVariable("goodsCode") String code, @PathVariable("groupId") int id) {
        goodsService.deleteGoods(code);
        return new ModelAndView("redirect:/goodslist/page/" + id + "/1");
    }

    @PostMapping("/editGoods")
    public ModelAndView editGoods(@ModelAttribute("goods") EditGoodsDto request) {                       
        goodsService.updateGoods(request);
        return new ModelAndView("redirect:/goodslist/page/" + request.getGroupId() + "/1");
    }

    @PostMapping("/input")
    public ModelAndView addGoodsCount(@ModelAttribute("income") IncomeDocResponseDto incomeDocDto) {
        incomeService.saveIncomeDoc(incomeDocDto);
        return new ModelAndView("redirect:/goodslist/page/" + incomeDocDto.getOrgCode() + "/" + incomeDocDto.getGroupId() + "/1");
    }

    @GetMapping("/addToOrder/{orgCode}/{goodsCode}")
    public ModelAndView addGoodsToOrder(OrderDto order, @PathVariable("orgCode") String orgcode, @PathVariable("goodsCode") String code) {
        Goods goods = goodsService.fingByCode(code);
        order.getGoods().add(goodsService.createGoodsResponse(goods));
        return new ModelAndView("redirect:/goodslist/page/" + orgcode + "/" + goods.getGroup().getId() + "/1");

    }

    @PostMapping("/sale")
    public ModelAndView saleGoods(OrderDto order, @RequestParam("customer") String phone, @RequestParam("orgCode") String orgCode, SessionStatus status) {
        order.setCustomerPhone(phone);
        order.setOrgCode(orgCode);
        OrderDto orderDto = orderService.saveOrder(order);
        goodsCounterService.decreaseGoodsQuantity(order.getGoods());
        ModelAndView model = new ModelAndView("orderPage");
        model.addObject("order", orderDto);
        status.setComplete();
        return model;
    }

}
