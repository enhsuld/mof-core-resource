package com.macro.dev.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.macro.dev.constant.DatatableInput;
import com.macro.dev.repository.mybatis.CrudMapper;
import com.macro.dev.repository.mybatis.TcPgmMapper;
import com.macro.dev.repository.mybatis.TcUserMapper;
import com.macro.dev.models.TcPgm;
import com.macro.dev.models.TcUser;
import com.macro.dev.services.DatatableService;
import com.macro.dev.services.Services;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


@RestController
@RequestMapping("/api/core")
public class CoreController {

    private CrudMapper crudMapper;
    private TcUserMapper usersMapper;
    private TcPgmMapper pgmMapper;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DatatableService datatableService;

    @Autowired
    private Services services;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CoreController(CrudMapper crudMapper, TcUserMapper usersMapper, TcPgmMapper pgmMapper) {
        this.crudMapper=crudMapper;
        this.usersMapper=usersMapper;
        this.pgmMapper=pgmMapper;
    }

    @RequestMapping(value = "/list/{domain}", method= RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String customers(@PathVariable String domain, @RequestParam(required = false, value = "data") String request, @RequestParam(required = false, value = "key") String col, @RequestParam(required = false, value = "model") String model, HttpServletRequest req) throws HttpRequestMethodNotSupportedException, UnsupportedEncodingException, JSONException, JsonProcessingException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        Long count=(long) 0;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
                String query="";
                if(request!=null){
                    query=services.getFilter(request);
                }
                System.out.println("select * from "+domain+" "+query+"");
                count= crudMapper.getListCount("select COUNT(*) from "+domain+" "+query+"");
                JSONObject obj= new JSONObject(request);

                String multiOrde="";
                String dir="";
                JSONArray sort=null;
                if(obj.has("sort")){
                    sort=obj.getJSONArray("sort");
                }
                String order="";
                if(obj.has("sort")){
                    multiOrde=services.getSort(request);
                    if(multiOrde.length()==0){
                        order=" t."+col+" desc";
                    }
                    else{
                        order=" "+multiOrde.substring(0,multiOrde.length()-1)+"";
                    }
                }


                DatatableInput inp = new DatatableInput();
             //   inp.setColumns(columns);
               // inp.setSearch(query);
                inp.setStart(obj.getInt("skip"));
                inp.setLength(obj.getInt("page")*obj.getInt("pageSize"));
                inp.setOrder(order);
                inp.setCustom(query);
                inp.setService(domain);
                List<HashMap<String, Object>> dt= datatableService.getFromDatatable(inp);

                List<Map<String, Object>> nrs = new ArrayList<>();
                for(Map<String, Object> oldMap:dt){
                    Map<String, Object> newMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
                    for ( String key : oldMap.keySet() ) {
                        newMap.put(key.toLowerCase(),oldMap.get(key));
                    }
                    nrs.add(newMap);
                }

                JSONArray arr= new JSONArray(nrs);
                JSONObject robj= new JSONObject();
                robj.put("total",count);
                robj.put("data",arr);
                return robj.toString();
        }
        return null;
    }


    @RequestMapping(value = "/{action}/{domain}", method= RequestMethod.POST)
    public @ResponseBody String update(@RequestBody String jsonStr, @RequestParam(required = false, value = "data") String request,@RequestParam(required = false, value = "key") String key, @RequestParam(required = false, value = "table") String table, @PathVariable String action, @PathVariable String domain) throws JSONException,ClassCastException{

        try{
            Class<?> classtoConvert;
            JSONObject obj=new JSONObject(request);
            classtoConvert=Class.forName(domain);
            Gson gson = new Gson();
            Object object = gson.fromJson(obj.toString(),classtoConvert);

            System.out.println("!!!!!"+object.toString());
            if(action.equals("update")){

                if(!obj.has("models")){
                    String[] s = request.substring(1,request.length()-1).split(",");
                    final Map<String, String> m = new HashMap<>();

                    final Map<String, String> cm = new HashMap<>();

                    Object someObject = object;
                    for (Field field : someObject.getClass().getDeclaredFields()) {
                        field.setAccessible(true); // You might want to set modifier to public first.
                        Object value = field.get(someObject);
                        if (value != null) {
                            boolean isNumeric = value.toString().trim().chars().allMatch( Character::isDigit );
                            if(isNumeric){
                                cm.put(field.getName(), String.valueOf(value));
                            }
                            else{
                                cm.put(field.getName(), "'"+value.toString().trim()+"'");
                            }
                        }
                    }


                    System.out.println("sda"+cm.toString().substring(1,cm.toString().length()-1));

                  /*  for(String pair : s)                        //iterate over the pairs
                    {
                        System.out.println(pair);
                        String[] entry = pair.split(":");
                        if(!entry[1].equalsIgnoreCase("null") && !entry[0].trim().substring(1,entry[0].trim().length()-1).equalsIgnoreCase("rn")){
                            String val="'"+entry[1].trim()+"'";

                            boolean isNumeric = entry[1].trim().chars().allMatch( Character::isDigit );
                            if(isNumeric){
                                m.put(entry[0].trim().substring(1,entry[0].trim().length()-1), entry[1].trim());
                            }
                            else{
                                m.put(entry[0].trim().substring(1,entry[0].trim().length()-1), "'"+entry[1].trim().substring(1,entry[1].trim().length()-1)+"'");
                            }

                        }
                    }*/

                    crudMapper.updateObject(table,cm.toString().substring(1,cm.toString().length()-1),key,obj.getInt(key));
                }

                else{
                    JSONArray rs=(JSONArray) obj.get("models");
                    System.out.println("rs obj "+rs);
                    for(int i=0;i<rs.length();i++){
                        String str=rs.get(i).toString();
                        JSONObject batchobj= new JSONObject(str);
                        Object bobj = gson.fromJson(batchobj.toString(),classtoConvert);
                        int upid=batchobj.getInt("id");
                      //  dao.PeaceCrud(bobj, domainName, "update", (long) upid, 0, 0, null);
                    }

                }
            }
            else if(action.equals("delete")){
                crudMapper.deleteObject(table,key,obj.getLong(key));
            }
            else if(action.equals("create")){
                final Map<String, String> cm = new HashMap<>();
                String columns="";
                String values="";
                Object someObject = object;
                for (Field field : someObject.getClass().getDeclaredFields()) {
                    field.setAccessible(true); // You might want to set modifier to public first.
                    System.out.println(field.getType().getTypeName());
                    Object value = field.get(someObject);
                    if (value != null) {
                        if(value.toString().length()>0){
                            columns=columns+", "+field.getName();

                            boolean isNumeric = value.toString().trim().chars().allMatch( Character::isDigit );
                            if(isNumeric){
                                values=values+", "+value.toString();
                            }
                            else{
                                if (value instanceof Boolean){
                                    values=values+", "+"'"+value.toString().trim()+"'";
                                }
                                else{
                                    values=values+", "+"'"+value.toString().trim()+"'";
                                }


                            }
                        }
                    }
                }
                String seq=table+"_seq";
                crudMapper.createObject(table,columns.substring(1),values.substring(1),key,seq.toUpperCase());
            }
            return "true";
        }
        catch(Exception  e){
            e.printStackTrace();
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/parentmenus", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public String parentmenu(HttpServletRequest req) throws ClassNotFoundException, JSONException {
       // List<Map<String, String>> rel= crudMapper.getListObject("select * from TC_PGM c where c.parent_id is null order by c.pgm_id asc");
        JSONArray arr = new JSONArray();

        List<TcPgm> rs = pgmMapper.findAll();
        if(rs.size()>0){
            for(int i=0;i<rs.size();i++){
                JSONObject obj=new JSONObject();
                obj.put("id",rs.get(i).getPgm_id());
                obj.put("title",rs.get(i).getPgm_nm());
                obj.put("value",rs.get(i).getPgm_id());
                obj.put("level",1);
                arr.put(obj);
                if(rs.get(i).getChildren().size()>0){
                    List<TcPgm> chi= rs.get(i).getChildren();

                    JSONArray childlevel2 = new JSONArray();

                    for(int j=0;j<rs.get(i).getChildren().size();j++){
                        JSONObject child=new JSONObject();
                        child.put("title", chi.get(j).getPgm_nm());
                        child.put("value", chi.get(j).getPgm_id());
                        child.put("id",  chi.get(j).getPgm_id());
                        child.put("parent_id", chi.get(j).getParent_id());
                        child.put("level", 2);
                        arr.put(child);
                        JSONArray childlevel3 = new JSONArray();

                        if(chi.get(j).getChildren().size()>0){
                            List<TcPgm> cha= (List<TcPgm>) chi.get(j).getChildren();
                            for(int c=0;c<cha.size();c++){
                                JSONObject child3=new JSONObject();
                                child3.put("title", cha.get(c).getPgm_nm());
                                child3.put("value", cha.get(c).getPgm_id());
                                child3.put("id", cha.get(c).getPgm_id());
                                child3.put("parent_id", cha.get(c).getParent_id());
                                child3.put("level", 3);
                                arr.put(child3);
                            }
                        }

                    }
                }
            }
        }
        JSONObject wmap = new JSONObject();
        wmap.put("options", arr);

        return wmap.toString();
    }



    @RequestMapping(value="/user/add/{id}", method=RequestMethod.PUT)
    public @ResponseBody String ajaxuser(@RequestBody String jsonString,@PathVariable Long id,OAuth2Authentication auth) throws JSONException,  UnsupportedEncodingException {
        String resultDecoded = java.net.URLDecoder.decode(jsonString, "UTF-8");
        JSONObject resp= new JSONObject();
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        JSONObject obj= new JSONObject(resultDecoded);
        JSONObject result= new JSONObject();


        if (!accessToken.isExpired()) {
            HashMap<String, String> savedUser=crudMapper.getObjectByPath("tc_user","user_nm", obj.getString("user_nm"));
            if(obj.getLong("user_id")==0 && savedUser.size()==0){

                TcUser norg= new TcUser();
                if(obj.has("email")){
                    norg.setEmail(obj.getString("email"));
                }
          /*  if(obj.has("familyname")){
                norg.setFamilyname(obj.getString("familyname"));
            }
            if(obj.has("givenname")){
                norg.setGivenname(obj.getString("givenname"));
            }*/
                if(obj.has("cell_no")){
                    norg.setCell_no(obj.getString("cell_no"));
                }
                if(obj.has("org_cd")){
                    norg.setOrg_cd(obj.getString("org_cd"));
                }

                norg.setUser_nm(obj.getString("user_nm"));
                norg.setUser_pw(passwordEncoder.encode(obj.getString("user_pw")));

                norg.setUse_yn(1);

                usersMapper.insertUser(norg);

                HashMap<String, String> newUser=crudMapper.getObjectByPath("tc_user","user_nm", obj.getString("user_nm"));

                JSONArray arr= obj.getJSONArray("roles");

                for(int a=0;a<arr.length();a++){
                    String vals=newUser.get("USER_ID")+","+arr.getLong(a);
                    crudMapper.createCrObject("tc_user_role","user_id,role_id",vals);
                }
                result.put("re", 0);
            }
            else{
                TcUser norg=usersMapper.findById(id);
                if(obj.has("email")){
                    norg.setEmail(obj.getString("email"));
                }
                if(obj.has("cell_no")){
                    norg.setCell_no(obj.getString("cell_no"));
                }
                if(obj.has("org_cd")){
                    norg.setOrg_cd(obj.getString("org_cd"));
                }

                norg.setUser_nm(obj.getString("user_nm"));
                norg.setUser_pw(passwordEncoder.encode(obj.getString("user_pw")));

                norg.setUse_yn(1);

                usersMapper.updateUser(norg);


                if(obj.has("roles")){
                    crudMapper.deleteObject("tc_user_role","user_id",id);
                    JSONArray arr= obj.getJSONArray("roles");
                    if(arr.length()>0){
                        for(int a=0;a<arr.length();a++){
                            String vals=id+","+arr.getLong(a);
                            crudMapper.createCrObject("tc_user_role","user_id,role_id",vals);
                        }
                    }
                }

                result.put("re", 1);
            }

        }


        return result.toString();

    }
}
