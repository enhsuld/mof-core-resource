package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TmRevenueConfig;
import com.macro.dev.repository.TmRevenueConfigRepository;
import com.macro.dev.services.TmRevenueConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TmRevenueConfigServiceImpl implements TmRevenueConfigService {

    private final TmRevenueConfigRepository repository;

    // @Autowired
    public TmRevenueConfigServiceImpl(TmRevenueConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TmRevenueConfig> getById(String id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<TmRevenueConfig> getByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return repository.selectByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean save(TmRevenueConfig objectClass) {
        return repository.insert(objectClass) > 0;
    }

    @Override
    @Transactional
    public boolean modify(TmRevenueConfig tcFaq) {
        return repository.update(tcFaq) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        return repository.deleteById(id) > 0;
    }

    @Override
    public Integer getTotalPage(Integer perPage, String query) {
        return repository.selectCount(query);
        //return PageUtil.calculateTotalPage(repository.selectCount(), perPage);
    }
}
