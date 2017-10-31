package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TcUserAudit;
import com.macro.dev.repository.TcUserAuditRepository;
import com.macro.dev.services.TcUserAuditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TcUserAuditServiceImpl implements TcUserAuditService {


    private final TcUserAuditRepository faqRepository;

   // @Autowired
    public TcUserAuditServiceImpl(TcUserAuditRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public Optional<TcUserAudit> getById(String id) {
        return Optional.ofNullable(faqRepository.findById(id));
    }

    @Override
    public List<TcUserAudit> getByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return faqRepository.selectByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean save(TcUserAudit tcFaq) {
        return faqRepository.insert(tcFaq) > 0;
    }

    @Override
    @Transactional
    public boolean modify(TcUserAudit tcFaq) {
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
