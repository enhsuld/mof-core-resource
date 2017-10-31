package com.macro.dev.services.impl;

import com.macro.dev.constant.PageUtil;
import com.macro.dev.models.TcUserRole;
import com.macro.dev.repository.TcUserRoleRepository;
import com.macro.dev.services.TcUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TcUserRoleServiceImpl implements TcUserRoleService {


    private final TcUserRoleRepository faqRepository;

   // @Autowired
    public TcUserRoleServiceImpl(TcUserRoleRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public Optional<TcUserRole> getById(String id) {
        return Optional.ofNullable(faqRepository.findById(id));
    }

    @Override
    public List<TcUserRole> getByPage(Integer page, Integer perPage, String query, String order) {
        Integer offset = PageUtil.calculateOffset(page, perPage);
        return faqRepository.selectByPage(offset, perPage*page, query, order);
    }

    @Override
    @Transactional
    public boolean save(TcUserRole tcFaq) {
        return faqRepository.insert(tcFaq) > 0;
    }

    @Override
    @Transactional
    public boolean modify(TcUserRole tcFaq) {
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
