package com.macro.dev.controllers;

import com.google.gson.Gson;
import com.macro.dev.constant.PageConstant;
import com.macro.dev.constant.PageUtil;
import com.macro.dev.constant.PaginatedResult;
import com.macro.dev.constant.ResourceNameConstant;
import com.macro.dev.constant.exception.ResourceNotFoundException;
import com.macro.dev.models.TcRolePgm;
import com.macro.dev.services.Services;
import com.macro.dev.services.TcRolePgmService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/file")
public class TcRolePgmController {

    private TcRolePgmService tcFaqService;

    @Autowired
    public TcRolePgmController(TcRolePgmService tcFaqService) {
        this.tcFaqService = tcFaqService;
    }

    @Autowired
    private Services services;

    @GetMapping("/list")
    public ResponseEntity<?> getTcFaqs(
            @RequestParam(value = "page", required = false) String pageString,
            @RequestParam(value = "data", required = false) String data,
            @RequestParam(value = "pageSize", required = false) String pageSize) throws JSONException {
        String query="";
        if(data!=null){
            query=services.getFilter(data);
        }
        JSONObject obj= new JSONObject(data);
        String multiOrde="";
        String order="";
        if(obj.has("sort")){
            multiOrde=services.getSort(data);
            order="order by "+multiOrde.substring(0,multiOrde.length()-1)+"";
        }
        int page = PageUtil.parsePage(pageString, PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(pageSize, PageConstant.PER_PAGE);
        return ResponseEntity
                .ok(new PaginatedResult()
                        .setData(tcFaqService.getByPage(page, perPage,query,order))
                        .setCurrentPage(page)
                        .setTotal(tcFaqService.getTotalPage(perPage,query)));
    }

    @GetMapping("/{id}")
    public Optional<TcRolePgm> getTcFaqById(@PathVariable String id) {
        return tcFaqService
                .getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> postTcFaq(@RequestParam(value = "data", required = false) String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        TcRolePgm tcFaq = new TcRolePgm();
        tcFaq.setRole_id(obj.getString("role_id"));
        tcFaq.setPgm_id(obj.getString("pgm_id"));
        tcFaq.setInit_yn(obj.getString("init_yn"));
        tcFaq.setIqry_yn(obj.getString("iqry_yn"));
        tcFaq.setSave_yn(obj.getString("save_yn"));
        tcFaq.setDel_yn(obj.getString("del_yn"));
        tcFaq.setExcl_yn(obj.getString("excl_yn"));
        tcFaq.setPrnt_yn(obj.getString("prnt_yn"));
        tcFaq.setHelp_yn(obj.getString("help_yn"));
        tcFaq.setUse_yn(obj.getString("use_yn"));
        tcFaq.setReg_id(obj.getString("reg_id"));
        tcFaq.setReg_dtm(obj.getString("reg_dtm"));
        tcFaq.setMod_id(obj.getString("mod_id"));
        tcFaq.setMod_dtm(obj.getString("mod_dtm"));

        tcFaqService.save(tcFaq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tcFaq.getRole_id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(tcFaq);

    }

    @PostMapping("/update")
    public ResponseEntity<?> putTcFaq(@RequestParam(value = "data", required = false) String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        Gson gson = new Gson();
        TcRolePgm tcFaq = gson.fromJson(jsonString,TcRolePgm.class);

        assertTcExist(obj.getString("id"));

        tcFaqService.modify(tcFaq.setRole_id(obj.getString("id")));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tcFaq);
    }

    @GetMapping("/delete")
    public Boolean deleteTcFaq(HttpServletRequest req) throws JSONException {

        assertTcExist(req.getParameter("id"));
        tcFaqService.deleteById(req.getParameter("id"));

        return true;
    }

    /********************************** HELPER METHOD **********************************/
    private void assertTcExist(String id) {
        tcFaqService
                .getById(id)
                .orElseThrow(() -> new ResourceNotFoundException()
                        .setResourceName(ResourceNameConstant.TcFaq)
                        .setId(id));
    }

}
