package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TmMacroIndicator;
import com.macro.dev.repository.TmMacroIndicatorRepository;
import com.macro.dev.services.TmMacroIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TmMacroIndicatorServiceImpl implements TmMacroIndicatorService {

    private final TmMacroIndicatorRepository repository;

    // @Autowired
    public TmMacroIndicatorServiceImpl(TmMacroIndicatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TmMacroIndicator> getById(String id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<TmMacroIndicator> getByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return repository.selectByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean save(TmMacroIndicator objectClass) {
        return repository.insert(objectClass) > 0;
    }

    @Override
    @Transactional
    public boolean modify(TmMacroIndicator tcFaq) {
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
