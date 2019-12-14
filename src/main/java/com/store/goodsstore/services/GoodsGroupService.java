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

    public GoodsGroupDto editGroup(String newName, String oldName,String storeCode) {
        Store store = storeService.getByCode(storeCode);
        GoodsGroup group = repository.findByNameAndStoreId(oldName, store.getId());
        if(group!=null){
          group.setName(newName); 
           repository.save(group);
            return createDto(group);
        }  
       throw new RuntimeException("Group with name" + oldName+ " not found in store "+ store.getName());
    }

    public GoodsGroup getGroupByname(String groupName) {
        return repository.findByName(groupName);
    }

    public GoodsGroupDto createDto(GoodsGroup group) {
        GoodsGroupDto groupDto = new GoodsGroupDto();
        groupDto.setId(group.getId());
        groupDto.setName(group.getName());   
        groupDto.setStoreCode(group.getStore().getCode());        
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

    public List<GoodsGroupDto> getAllGroupByStore(int storeId) {
      List<GoodsGroupDto> dto= repository.findByStoreId(storeId).stream().map((tmp)->{
          return createDto(tmp);
      }).collect(Collectors.toList());
      return dto;
    }

    List<GoodsGroup> getAllGroup() {
        return repository.findAll();
    }

    public GoodsGroup getById(int groupId) {
       return repository.findById(groupId).get();
    }

}
