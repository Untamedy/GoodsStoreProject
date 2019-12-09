package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsDto;
import com.store.goodsstore.dto.GoodsGroupDto;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.entities.Store;
import com.store.goodsstore.exceptions.AlreadyExistsException;
import com.store.goodsstore.repository.GroupRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author YBolshakova
 */
@Service
public class GoodsGroupService {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private StoreService storeService;
    
    @Autowired
    private UserService userService;

    public GoodsGroupDto saveGroup(GoodsGroupDto dto) {        
            return createDto(repository.save(createGroup(dto)));
    }

    public GoodsGroupDto editGroup(String newName, String oldName) {
        GoodsGroup group = repository.findByName(oldName);
        group.setName(newName);
        repository.save(group);
        return createDto(group);
    }

    public GoodsGroup getGroupByname(String groupName) {
        return repository.findByName(groupName);
    }

    public GoodsGroupDto createDto(GoodsGroup group) {
        GoodsGroupDto groupDto = new GoodsGroupDto();
        groupDto.setName(group.getName());
        //List<GoodsDto> goodsDto = new ArrayList<>();
       // if (!group.getGoods().isEmpty()) {
       //     goodsDto = group.getGoods().stream().map((tmp) -> {
       //         return goodsService.createGoodsResponse(tmp);
        //    }).collect(Collectors.toList());
      //  }
       // groupDto.setGoods(goodsDto);
        return groupDto;
    }

    public GoodsGroup createGroup(GoodsGroupDto dto) {
        GoodsGroup group = repository.findByNameAndStoreId(dto.getName(), storeService.getByCode(dto.getStoreCode()).getId());
        if (group == null) {
            group = new GoodsGroup();
            group.setName(dto.getName());
            Store store = storeService.getByCode(dto.getStoreCode());
            group.setStore(store);
            return group;
        } 
        throw new AlreadyExistsException("Group with name " + group.getName() +" is alredy exists");
       
    }

   /* public boolean removeGroup(String name) {
        GoodsGroup group = repository.findByName(name);
        if (group != null) {
            if (group.getGoods().isEmpty()) {
                repository.delete(group);
                return true;
            }
        }
        return false;
    }*/

    public List<GoodsGroupDto> getAllGroupDto(String username) {
       Store store = userService.getByName(username).getOrg().getStore();
        return repository.findAll().stream().map(tmp -> {
            return createDto(tmp);
        }).collect(Collectors.toList());

    }

    List<GoodsGroup> getAllGroup() {
        return repository.findAll();
    }

}
