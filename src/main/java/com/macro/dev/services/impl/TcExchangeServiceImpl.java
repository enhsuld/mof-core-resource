package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TcExchange;
import com.macro.dev.repository.TcExchangeRepository;
import com.macro.dev.services.TcExchangeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TcExchangeServiceImpl implements TcExchangeService {


    private final TcExchangeRepository faqRepository;

   // @Autowired
    public TcExchangeServiceImpl(TcExchangeRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public Optional<TcExchange> getById(String id) {
        return Optional.ofNullable(faqRepository.findById(id));
    }

    @Override
    public List<TcExchange> getByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return faqRepository.selectByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean save(TcExchange tcFaq) {
        return faqRepository.insert(tcFaq) > 0;
    }

    @Override
    @Transactional
    public boolean modify(TcExchange tcFaq) {
        return faqRepository.update(tcFaq) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        return faqRepository.deleteById(id) > 0;
    }

    @Override
    public Integer getTotalPage(Integer perPage, String query) {
        return faqRepository.selectCount(query);
        //return PageUtil.calculateTotalPage(faqRepository.selectCount(), perPage);
    }
}
