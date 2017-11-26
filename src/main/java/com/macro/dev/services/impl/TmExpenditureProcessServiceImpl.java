package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TmExpenditureProcess;
import com.macro.dev.repository.TmExpenditureProcessRepository;
import com.macro.dev.services.TmExpenditureProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TmExpenditureProcessServiceImpl implements TmExpenditureProcessService {

    private final TmExpenditureProcessRepository repository;

    // @Autowired
    public TmExpenditureProcessServiceImpl(TmExpenditureProcessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TmExpenditureProcess> getById(String id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<TmExpenditureProcess> getByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return repository.selectByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean save(TmExpenditureProcess objectClass) {
        return repository.insert(objectClass) > 0;
    }

    @Override
    @Transactional
    public boolean modify(TmExpenditureProcess tcFaq) {
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
