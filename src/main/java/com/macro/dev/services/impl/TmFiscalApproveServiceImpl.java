package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TmFiscalApprove;
import com.macro.dev.repository.TmFiscalApproveRepository;
import com.macro.dev.services.TmFiscalApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TmFiscalApproveServiceImpl implements TmFiscalApproveService {

    private final TmFiscalApproveRepository repository;

    // @Autowired
    public TmFiscalApproveServiceImpl(TmFiscalApproveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TmFiscalApprove> getById(String id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<TmFiscalApprove> getByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return repository.selectByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean save(TmFiscalApprove objectClass) {
        return repository.insert(objectClass) > 0;
    }

    @Override
    @Transactional
    public boolean modify(TmFiscalApprove tcFaq) {
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
