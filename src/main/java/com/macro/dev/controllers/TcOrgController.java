package com.macro.dev.controllers;

import com.google.gson.Gson;
import com.macro.dev.constant.PageConstant;
import com.macro.dev.constant.PageUtil;
import com.macro.dev.constant.PaginatedResult;
import com.macro.dev.constant.ResourceNameConstant;
import com.macro.dev.constant.exception.ResourceNotFoundException;
import com.macro.dev.models.TcOrg;
import com.macro.dev.services.Services;
import com.macro.dev.services.TcOrgService;
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
public class TcOrgController {

    private TcOrgService tcFaqService;

    @Autowired
    public TcOrgController(TcOrgService tcFaqService) {
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
    public Optional<TcOrg> getTcFaqById(@PathVariable String id) {
        return tcFaqService
                .getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> postTcFaq(@RequestParam(value = "data", required = false) String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        TcOrg tcFaq = new TcOrg();
        tcFaq.setOrg_cd(obj.getString("org_cd"));
        tcFaq.setOrg_nm(obj.getString("org_nm"));
        tcFaq.setOrg_nm_eng(obj.getString("org_nm_eng"));
        tcFaq.setOrg_div_cd(obj.getString("org_div_cd"));
        tcFaq.setOrg_lv_cd(obj.getString("org_lv_cd"));
        tcFaq.setUppr_org_cd(obj.getString("uppr_org_cd"));
        tcFaq.setAply_dt(obj.getString("aply_dt"));
        tcFaq.setClse_dt(obj.getString("clse_dt"));
        tcFaq.setUse_yn(obj.getInt("use_yn"));
        tcFaq.setOrg_ord(BigDecimal.valueOf(obj.getInt("org_ord")));
        tcFaq.setTel_no(obj.getString("tel_no"));
        tcFaq.setFax_no(obj.getString("fax_no"));
        tcFaq.setAddr1_cd(obj.getString("addr1_cd"));
        tcFaq.setAddr2_cd(obj.getString("addr2_cd"));
        tcFaq.setAddr_dtl(obj.getString("addr_dtl"));
        tcFaq.setOrg_hdcf_nm(obj.getString("org_hdcf_nm"));
        tcFaq.setReg_id(obj.getString("reg_id"));
        tcFaq.setReg_dtm(obj.getString("reg_dtm"));
        tcFaq.setMod_id(obj.getString("mod_id"));
        tcFaq.setMod_dtm(obj.getString("mod_dtm"));
        tcFaqService.save(tcFaq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tcFaq.getOrg_cd())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(tcFaq);

    }

    @PostMapping("/update")
    public ResponseEntity<?> putTcFaq(@RequestParam(value = "data", required = false) String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        Gson gson = new Gson();
        TcOrg tcFaq = gson.fromJson(jsonString,TcOrg.class);

        assertTcExist(obj.getString("id"));

        tcFaqService.modify(tcFaq.setOrg_cd(obj.getString("id")));

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
