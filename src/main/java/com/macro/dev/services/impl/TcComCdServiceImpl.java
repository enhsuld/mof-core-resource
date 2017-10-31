package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TcComCd;
import com.macro.dev.repository.TcComCdRepository;
import com.macro.dev.services.TcComCdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TcComCdServiceImpl implements TcComCdService {


    private final TcComCdRepository faqRepository;

   // @Autowired
    public TcComCdServiceImpl(TcComCdRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public Optional<TcComCd> getById(String id) {
        return Optional.ofNullable(faqRepository.findById(id));
    }

    @Override
    public List<TcComCd> getByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return faqRepository.selectByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean save(TcComCd tcFaq) {
        return faqRepository.insert(tcFaq) > 0;
    }

    @Override
    @Transactional
    public boolean modify(TcComCd tcFaq) {
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
