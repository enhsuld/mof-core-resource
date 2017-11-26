package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TcFaq;
import com.macro.dev.repository.TcFaqRepository;
import com.macro.dev.services.TcFaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TcFaqServiceImpl implements TcFaqService {

    private final TcFaqRepository faqRepository;

   // @Autowired
    public TcFaqServiceImpl(TcFaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public Optional<TcFaq> getTcFaqById(String id) {
        return Optional.ofNullable(faqRepository.findById(id));
    }

    @Override
    public List<TcFaq> getTcFaqsByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return faqRepository.selectFaqByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean saveTcFaq(TcFaq tcFaq) {
        return faqRepository.insert(tcFaq) > 0;
    }

    @Override
    @Transactional
    public boolean modifyTcFaq(TcFaq tcFaq) {
        return faqRepository.update(tcFaq) > 0;
    }

    @Override
    @Transactional
    public boolean deleteTcFaqById(String id) {
        return faqRepository.deleteById(id) > 0;
    }

    @Override
    public Integer getTotalPage(Integer perPage, String query) {
        return faqRepository.selectCount(query);
        //return PageUtil.calculateTotalPage(faqRepository.selectCount(), perPage);
    }
}