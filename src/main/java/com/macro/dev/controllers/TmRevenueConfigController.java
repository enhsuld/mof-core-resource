package com.macro.dev.controllers;

import com.google.gson.Gson;
import com.macro.dev.constant.PageConstant;
import com.macro.dev.constant.PageUtil;
import com.macro.dev.constant.PaginatedResult;
import com.macro.dev.constant.ResourceNameConstant;
import com.macro.dev.constant.exception.ResourceNotFoundException;
import com.macro.dev.models.TmRevenueConfig;
import com.macro.dev.services.Services;
import com.macro.dev.services.TmRevenueConfigService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/tm_revenue_config")
public class TmRevenueConfigController {

    private TmRevenueConfigService service;

    @Autowired
    public TmRevenueConfigController(TmRevenueConfigService service) {
        this.service = service;
    }

    @Autowired
    private Services services;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
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
                        .setData(service.getByPage(page, perPage,query,order))
                        .setCurrentPage(page)
                        .setTotal(service.getTotalPage(perPage,query)));
    }

    @GetMapping("/{id}")
    public Optional<TmRevenueConfig> getById(@PathVariable String id) {
        return service
                .getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> post(@RequestParam(value = "data", required = false) String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        TmRevenueConfig objectClass = new TmRevenueConfig();
        objectClass.setComCd(obj.getString("comCd"));
        objectClass.setComCd1(obj.getString("comCd1"));
        objectClass.setBtPt(obj.getInt("btPt"));
        objectClass.setUseYn(obj.getString("useYn"));
        objectClass.setRegId(obj.getString("regId"));
        objectClass.setRegDtm(obj.getString("regDtm"));
        objectClass.setModId(obj.getString("modId"));
        objectClass.setModDtm(obj.getString("modDtm"));
        service.save(objectClass);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(objectClass.getRbiId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(objectClass);

    }

    @PostMapping("/update")
    public ResponseEntity<?> put(@RequestParam(value = "data", required = false) String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        Gson gson = new Gson();
        TmRevenueConfig objectClass = gson.fromJson(jsonString,TmRevenueConfig.class);

        assertExist(obj.getString("id"));

        service.modify(objectClass.setRbiId(obj.getString("id")));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(objectClass);
    }

    @GetMapping("/delete")
    public Boolean delete(HttpServletRequest req) throws JSONException {

        assertExist(req.getParameter("id"));
        service.deleteById(req.getParameter("id"));

        return true;
    }

    /********************************** HELPER METHOD **********************************/
    private void assertExist(String id) {
        service
                .getById(id)
                .orElseThrow(() -> new ResourceNotFoundException()
                        .setResourceName(ResourceNameConstant.TmRevenueConfig)
                        .setId(id));
    }

}

