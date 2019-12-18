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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public GoodsGroupDto saveGroup(GoodsGroupDto dto) {
        return createDto(repository.save(createGroup(dto)));
    }

    @Transactional
    public GoodsGroupDto editGroup(String newName, String oldName, String storeCode) {
        Store store = storeService.getByCode(storeCode);
        GoodsGroup oldgroup = repository.findByNameAndStoreId(oldName, store.getId());

        if (oldgroup != null) {
            GoodsGroup newgroup = repository.findByNameAndStoreId(newName, store.getId());
            if (newgroup == null) {
                oldgroup.setName(newName);
                repository.save(oldgroup);
                return createDto(oldgroup);
            }
            throw new RuntimeException("Group with name" + newName + " is already exists in store " + store.getName());

        }
        throw new RuntimeException("Group with name" + oldName + " not found in store " + store.getName());
    }

    @Transactional(readOnly = true)
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
        throw new AlreadyExistsException("Group with name " + group.getName() + " is alredy exists");

    }

    @Transactional
    public boolean removeGroup(String name, String code) {
        Store store = storeService.getByCode(code);
        GoodsGroup group = repository.findByNameAndStoreId(name, store.getId());
        if (group != null) {
            if (goodsService.getByGroupId(group.getId()).isEmpty()) {
                repository.delete(group);
                return true;
            }
            throw new RuntimeException("Can't removed group, maybe group contain goods");
        }
        throw new RuntimeException("Can't removed group");
    }

    @Transactional(readOnly = true)
    public List<GoodsGroupDto> getAllGroupByStore(int storeId) {
        List<GoodsGroupDto> dto = repository.findByStoreId(storeId).stream().map((tmp) -> {
            return createDto(tmp);
        }).collect(Collectors.toList());
        return dto;
    }

    @Transactional(readOnly = true)
    List<GoodsGroup> getAllGroup() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public GoodsGroup getById(int groupId) {
        return repository.findById(groupId).get();
    }

}
