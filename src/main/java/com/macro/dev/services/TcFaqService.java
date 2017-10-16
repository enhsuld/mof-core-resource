package com.macro.dev.services;

import com.macro.dev.models.TcFaq;

import java.util.List;
import java.util.Optional;


public interface TcFaqService {

    Optional<TcFaq> getTcFaqById(String id);

    List<TcFaq> getTcFaqsByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean saveTcFaq(TcFaq tcFaq);

    boolean modifyTcFaq(TcFaq tcFaq);

    boolean deleteTcFaqById(String id);

}
