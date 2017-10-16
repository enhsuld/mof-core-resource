package com.macro.dev.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macro.dev.repository.mybatis.CrudMapper;
import com.macro.dev.repository.mybatis.TcPgmMapper;
import com.macro.dev.repository.mybatis.TcUserMapper;
import com.macro.dev.models.TcUser;
import com.macro.dev.models.TcUserRole;
import com.macro.dev.services.Services;
import com.macro.dev.services.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private TokenStore tokenStore;

    private TcPgmMapper pgmMapper;

    private TcUserMapper usersMapper;

    private CrudMapper crudMapper;


    public UserController(TcPgmMapper pgmMapper, TcUserMapper usersMapper, CrudMapper crudMapper) {
        this.pgmMapper = pgmMapper;
        this.usersMapper=usersMapper;
        this.crudMapper=crudMapper;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private Services services;

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(value="/core/rjson/{id}/{path}",method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String rjson(OAuth2Authentication auth, @PathVariable long id, @PathVariable String path){
        try{
            if(auth.isAuthenticated()){
                final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
                final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
                System.out.println(accessToken.getAdditionalInformation());
                JSONObject userObject=new JSONObject(accessToken.getAdditionalInformation());
                List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
                System.out.println(auth.getAuthorities());

                for(Object item :auth.getAuthorities()){
                    String rolename = item.toString();
                    System.out.println(rolename);
                    if("ROLE_SUPER".equals(rolename)){
                        ObjectMapper mapper = new ObjectMapper();
                        Map<String,Object> wmap=new HashMap<String, Object>();
                        wmap.put("rcreate", 1);
                        wmap.put("rupdate", 1);
                        wmap.put("rdelete", 1);
                        wmap.put("rread", 1);
                        wmap.put("rexport", 1);
                        wmap.put("ruptype", 1);
                        return mapper.writeValueAsString(wmap);
                    }
                    else{
                        TcUser loguser=userService.selectUserByUsername(userObject.getString("username"));
                        HashMap<String, String> lm= crudMapper.getObjectByPath("tc_pgm","call_url",path);
                        System.out.println("^^^^"+lm);
                        if(lm!=null){
                            JSONObject object = new JSONObject(lm);
                            List<TcUserRole> us=loguser.getTcUserRoles();
                            System.out.println("!!!"+us.size());
                            ObjectMapper mapper = new ObjectMapper();
                            Map<String,Object> wmap=new HashMap<String, Object>();
                            System.out.println("lm"+lm);
                            JSONObject obj = new JSONObject();

                            obj.put("rcreate", 0);
                            obj.put("rupdate", 0);
                            obj.put("rdelete", 0);
                            obj.put("rread", 0);
                            obj.put("rexport", 0);
                            obj.put("ruptype", object.getInt("UPDATE_TYPE"));

                            for(int u=0;u<us.size();u++){
                                System.out.println("@@"+us.get(u).getTcRole().getTcRolePgms());
                                List<Map<String, String>> rs= crudMapper.getListObject("select * from tc_role_pgm t where t.role_id='"+us.get(u).getTcRole().getRole_id()+"' and t.pgm_id='"+object.getString("PGM_ID")+"'");
                                if(rs.size()>0){
                                    JSONObject pgm=new JSONObject(rs.get(0));
                                    System.out.println("bandi"+rs.get(0));

                                    if(pgm.getInt("SAVE_YN")!=0){
                                        obj.remove("rcreate");
                                        obj.put("rcreate", pgm.getInt("SAVE_YN"));
                                    }
                                    if(pgm.getInt("IQRY_YN")!=0){
                                        obj.remove("rupdate");
                                        obj.put("rupdate", pgm.getInt("IQRY_YN"));
                                    }
                                    if(pgm.getInt("DEL_YN")!=0){
                                        obj.remove("rdelete");
                                        obj.put("rdelete", pgm.getInt("DEL_YN"));
                                    }
                                    if(pgm.getInt("PRNT_YN")!=0){
                                        obj.remove("rread");
                                        obj.put("rread", pgm.getInt("PRNT_YN"));
                                    }
                                    if(pgm.getInt("EXCL_YN")!=0){
                                        obj.remove("rexport");
                                        obj.put("rexport", pgm.getInt("EXCL_YN"));
                                    }
                                }
                            }
                            return obj.toString();
                        }

                    }
                }



           /* while(itr.hasNext()){
                String rolename = itr.next().toString();

                //String returnS=dao.loginedUserViewAuthority(userDetail, id);
                if("ROLE_SUPER".equals(rolename)){
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String,Object> wmap=new HashMap<String, Object>();
                    wmap.put("rcreate", 1);
                    wmap.put("rupdate", 1);
                    wmap.put("rdelete", 1);
                    wmap.put("rread", 1);
                    wmap.put("rexport", 1);
                    result.add(wmap);
                    return mapper.writeValueAsString(wmap);
                }
                else{
                    LutUser loguser=(LutUser) dao.getHQLResult("from LutUser t where t.id='"+id+"'", "current");
                    LutMenu lm=(LutMenu) dao.getHQLResult("from LutMenu t where t.stateurl='"+path+"'", "current");

                    List<LnkUserrole> us=loguser.getLnkUserroles();
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String,Object> wmap=new HashMap<String, Object>();

                    JSONObject obj = new JSONObject();

                    obj.put("rcreate", 0);
                    obj.put("rupdate", 0);
                    obj.put("rdelete", 0);
                    obj.put("rread", 0);
                    obj.put("rexport", 0);

                    for(int u=0;u<us.size();u++){
                        System.out.println("@@"+us.get(u).getRoleid());
                        List<LnkMenurole> rs=(List<LnkMenurole>) dao.getHQLResult("from LnkMenurole t where t.roleid='"+us.get(u).getRoleid()+"' and t.menuid="+lm.getId()+"", "list");
                        if(rs.size()>0){
                            if(rs.get(0).getRcreate()!=0){
                                obj.remove("rcreate");
                                obj.put("rcreate", rs.get(0).getRcreate());
                            }
                            if(rs.get(0).getRupdate()!=0){
                                obj.remove("rupdate");
                                obj.put("rupdate", rs.get(0).getRupdate());
                            }
                            if(rs.get(0).getRdelete()!=0){
                                obj.remove("rdelete");
                                obj.put("rdelete", rs.get(0).getRdelete());
                            }
                            if(rs.get(0).getRread()!=0){
                                obj.remove("rread");
                                obj.put("rread", rs.get(0).getRread());
                            }
                            if(rs.get(0).getRexport()!=0){
                                obj.remove("rexport");
                                obj.put("rexport", rs.get(0).getRexport());
                            }
                        }
                    }
                    return obj.toString();

                   return "true";
                }
            }*/


            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return path;

    }


    @RequestMapping(value = "/core/user/roles/{id}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public @ResponseBody String roles(@PathVariable int id) {
        try{

            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            JSONArray arr=new JSONArray();

            Collection<Map<String, String>> mobj= crudMapper.getListObject("select * from tc_user_role c where user_id="+id+"");
            for(Map<String, String> value:mobj){
                JSONObject curr = new JSONObject(value);
                arr.put(curr.getString("ROLE_ID"));
            }

            return arr.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value="/core/roles/{domain}/path/{id}",method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public String read(@PathVariable String domain,  @PathVariable String path,@PathVariable String id) throws ClassNotFoundException, JSONException{
        try {
            HashMap<String, String> rel=crudMapper.getObjectByPath(domain,path,id);
        /*    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            for(int i=0;i<rel.size();i++){
                Map<String,Object> wmap=new HashMap<String, Object>();
                wmap.put("menuid", rel.get(i).getLutMenu().getId());
                wmap.put("roleid", rel.get(i).getLutRole().getId());
                wmap.put("create", rel.get(i).getRcreate());
                wmap.put("read", rel.get(i).getRread());
                wmap.put("update", rel.get(i).getRupdate());
                wmap.put("delete", rel.get(i).getRdelete());
                wmap.put("export", rel.get(i).getRexport());
                result.add(wmap);
            }*/
            org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
            return mapper.writeValueAsString(rel);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/core/resource/{domain}", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public @ResponseBody String tree(@PathVariable String domain) {
        try{

            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            JSONArray arr=new JSONArray();

            if(domain.equalsIgnoreCase("LutMenu")){
                Collection<Map<String, String>> mobj= crudMapper.getListObject("select * from TC_PGM c order by c.pgm_id asc");
                for(Map<String, String> value:mobj){
                    JSONObject curr = new JSONObject(value);
                    JSONObject obj=new JSONObject();
                    obj.put("value", curr.getString("PGM_ID"));
                    obj.put("text", curr.getString("PGM_NM"));
                    arr.put(obj);
                }
            }
            if(domain.equalsIgnoreCase("LutRole")){
                Collection<Map<String, String>> mobj= crudMapper.getListObject("select * from TC_role c order by c.role_id asc");
                for(Map<String, String> value:mobj){
                    JSONObject curr = new JSONObject(value);
                    JSONObject obj=new JSONObject();
                    obj.put("value", curr.getString("ROLE_ID"));
                    obj.put("text", curr.getString("ROLE_NM"));
                    arr.put(obj);
                }
            }
            if(domain.equalsIgnoreCase("LutCmmOrganization")){
                Collection<Map<String, String>> mobj= crudMapper.getListObject("select * from TC_org c order by c.org_cd asc");
                for(Map<String, String> value:mobj){
                    JSONObject curr = new JSONObject(value);
                    JSONObject obj=new JSONObject();
                    obj.put("value", curr.getString("ORG_CD"));
                    obj.put("text", curr.getString("ORG_NM"));
                    arr.put(obj);
                }
            }
            return arr.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/users/extra")
    @ResponseBody
    public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        System.out.println(accessToken);
        return accessToken.getAdditionalInformation();
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(value="/mjson",method= RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String mjson(OAuth2Authentication auth){
        try{
            final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
            final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
            List<TcUser> us=userService.selectAllUsers();
            JSONObject result = new JSONObject();
            TcUser loguser= null;
            if (!accessToken.isExpired()) {
                UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                Long userid = 0L;
                String roles="";
                boolean rolesuper=false;
                loguser=userService.selectUserByUsername(userDetail.getUsername());

                if(userDetail.getAuthorities().toString().contains("ROLE_SUPER")){
                    rolesuper=true;
                }
                else{
                    userid=loguser.getUser_id();
                }
                if(rolesuper){
                    result=services.getMjson(roles,true);
                }
                else{
                    System.out.println(loguser.getTcUserRoles().size());
                    for(int i=0;i<loguser.getTcUserRoles().size();i++){
                        roles=roles+","+loguser.getTcUserRoles().get(i).getTcRole().getRole_id();
                    }
                    System.out.println("@@"+roles);
                    result=services.getMjson(roles,false);
                }
            }

            return result.toString();

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
