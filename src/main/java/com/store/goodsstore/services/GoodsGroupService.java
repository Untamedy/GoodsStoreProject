package com.store.goodsstore.services;

import com.store.goodsstore.dto.GoodsGroupDto;
import com.store.goodsstore.entities.GoodsGroup;
import com.store.goodsstore.repository.GroupRepository;
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
    
    public GoodsGroupDto saveGroup(String name){
        if(!repository.exsistsByName(name)){
          return createDto(repository.save(createGroup(name)));
        }  
        return null;
    }

    public List<GoodsGroupDto> getAllgroup() {
        List<GoodsGroup> groups = repository.findAll();
        List<GoodsGroupDto> groupsDto = groups.stream().map(tmp -> {
          GoodsGroupDto dto = createDto(tmp);
          return dto;
        }).collect(Collectors.toList());        
        return groupsDto;
    }

    public GoodsGroupDto getGroupByname(String groupName, int id) {
        return createDto(repository.findByNameAndStoreId(groupName, id));
    }

    public GoodsGroupDto createDto(GoodsGroup group) {
        return new GoodsGroupDto(group.getName());        
    }
    
    public GoodsGroup createGroup(String name){
        return new GoodsGroup(name);               
    }
    
    public boolean removeGroup(GoodsGroupDto groupDto){
        GoodsGroup group = repository.findByName(groupDto.getName());        
        if(group!=null){
            if(goodsService.findByGroupId(group.getId()).isEmpty()){
                repository.delete(group);
                return true;
            }
        }
        return false;       
    }

    public List<GoodsGroupDto> getAll() {
        return repository.findAll().stream().map(tmp->{
            return createDto(tmp);
        }).collect(Collectors.toList());
                
    }

}
