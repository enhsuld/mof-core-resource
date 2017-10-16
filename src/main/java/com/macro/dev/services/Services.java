package com.macro.dev.services;

import com.macro.dev.repository.mybatis.CrudMapper;
import com.macro.dev.repository.mybatis.TcPgmMapper;
import com.macro.dev.repository.mybatis.UsersMapper;
import com.macro.dev.models.TcPgm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class Services {


    private TcPgmMapper pgmMapper;

    private UsersMapper usersMapper;

	private CrudMapper crudMapper;

    public Services(TcPgmMapper pgmMapper,UsersMapper usersMapper,CrudMapper crudMapper) {
        this.pgmMapper = pgmMapper;
        this.usersMapper=usersMapper;
		this.crudMapper=crudMapper;
    }

	@Cacheable(value="customers", key="#roles")
	public JSONObject getMjson(String roles, boolean superuser) throws JSONException{
		System.out.println("Service processing...");
	
		JSONObject robj = new JSONObject();
		JSONArray result = new JSONArray();
		Collection<Object> roleset=null;
		if(superuser){
			List<TcPgm> rs = pgmMapper.findAll();
            if(rs.size()>0){
	    		for(int i=0;i<rs.size();i++){
	        		JSONObject obj=new JSONObject();
	        		obj.put("id",rs.get(i).getPgm_id());
					obj.put("title",rs.get(i).getPgm_nm());
					obj.put("icon",rs.get(i).getUi_icon());
					obj.put("create","1");
					obj.put("update","1");
					obj.put("delete","1");
					obj.put("export","1");
					obj.put("uptype","rs.get(i).getUpdate_type()");


	        		JSONArray childs = new JSONArray();

	        		if(rs.get(i).getChildren().size()>0){
	        			List<TcPgm> chi= (List<TcPgm>) rs.get(i).getChildren();

	        			for(int j=0;j<rs.get(i).getChildren().size();j++){
	        				JSONObject child=new JSONObject();
			        		child.put("title", chi.get(j).getPgm_nm());
							child.put("create", 1);
							child.put("update", 1);
							child.put("delete", 1);
							child.put("export", 1);
							child.put("uptype", chi.get(j).getUpdate_type());
			        		//child.put("link", chi.get(j).getStateurl());
			        		childs.put(child);

							JSONArray childlevel3 = new JSONArray();

			        		if(chi.get(j).getChildren().size()>0){
								List<TcPgm> cha= (List<TcPgm>) chi.get(j).getChildren();
	            				for(int c=0;c<cha.size();c++){
	            					Map<String,Object> child3=new HashMap<String, Object>();
									System.out.println("###"+cha.get(c).getPgm_nm());
									child3.put("title", cha.get(c).getPgm_nm());
	            					child3.put("link", cha.get(c).getCall_url());
									child3.put("create", 1);
									child3.put("update", 1);
									child3.put("delete", 1);
									child3.put("export", 1);
									child3.put("uptype", cha.get(c).getUpdate_type());
	            					childlevel3.put(child3);
	                			}
	            			}
			        		else{
			        			child.put("link", chi.get(j).getCall_url());
			        		}
			        		child.put("submenu", childlevel3);
	        			}

	        		}
	        		else{
	        			obj.put("link", rs.get(i).getCall_url());
	        		}
					obj.put("submenu", childs);
	        		result.put(obj);
	        	}
	    	}
    		robj.put("mjson", result);
		}else{

			System.out.println(roles.substring(1,roles.length()));
			Collection<Map<String, String>>  mobj= crudMapper.getListObject("select c.pgm_id, c.pgm_nm, c.parent_id, c.call_url, c.ui_icon, t.DEL_YN, t.SAVE_YN, t.EXCL_YN, t.init_yn, t.IQRY_YN, c.update_type from TC_PGM c, TC_ROLE_PGM t where t.role_id in ("+roles.substring(1,roles.length())+") and c.pgm_id=t.pgm_id order by c.order_id asc");

			for (Map<String,String> value : mobj) {
				JSONObject curr = new JSONObject(value);
				JSONObject wmap=new JSONObject();
				if(curr.getInt("parent_id")==0){
					int inp=0;
					for(int i=0;i<result.length();i++){
						JSONObject it = (JSONObject) result.get(i);
						if(it.getString("id").equals(curr.getString("PGM_ID"))){
							inp=inp+1;
						}
					}

					wmap.put("id", curr.getString("PGM_ID"));
					wmap.put("title", curr.getString("PGM_NM"));
					if(curr.has("UI_ICON")){
						wmap.put("icon", curr.getString("UI_ICON"));
					}

					wmap.put("create", curr.getString("SAVE_YN"));
					wmap.put("update", curr.getString("IQRY_YN"));
					wmap.put("delete", curr.getString("DEL_YN"));
					wmap.put("export", curr.getString("EXCL_YN"));
					wmap.put("uptype", curr.getString("UPDATE_TYPE"));

					JSONArray childs = new JSONArray();
					int count=0;
					for (Map<String,String> valueSecond : mobj) {
						JSONObject fcurr = new JSONObject(valueSecond);
						if(curr.getString("PGM_ID").equalsIgnoreCase(fcurr.getString("parent_id"))){
							JSONObject fchild=new JSONObject();
							count=count+1;
							fchild.put("title", fcurr.getString("PGM_NM"));
							fchild.put("id", fcurr.getString("PGM_ID"));
							fchild.put("create", fcurr.getString("SAVE_YN"));
							fchild.put("update", fcurr.getString("IQRY_YN"));
							fchild.put("delete", fcurr.getString("DEL_YN"));
							fchild.put("export", fcurr.getString("EXCL_YN"));
							fchild.put("uptype", fcurr.getString("UPDATE_TYPE"));
							int chicount=0;
							for(int i=0;i<childs.length();i++){
								JSONObject it = (JSONObject) childs.get(i);
								if(it.getString("title").equalsIgnoreCase(fcurr.getString("PGM_NM"))){
									chicount=chicount+1;
								}
							}

							JSONArray tchilds = new JSONArray();
							int tcount=0;
							for (Map<String,String> valueThird : mobj) {
								JSONObject tcurr = new JSONObject(valueThird);
								if(fcurr.getString("PGM_ID").equalsIgnoreCase(tcurr.getString("parent_id"))){
									JSONObject tchild=new JSONObject();
									tcount=tcount+1;
									tchild.put("title", tcurr.getString("PGM_NM"));
									if(tcurr.has("CALL_URL")){
										tchild.put("link",  tcurr.getString("CALL_URL"));
									}
									tchild.put("id", tcurr.getString("PGM_ID"));
									tchild.put("create", tcurr.getString("SAVE_YN"));
									tchild.put("update", tcurr.getString("IQRY_YN"));
									tchild.put("delete",tcurr.getString("DEL_YN"));
									tchild.put("export",tcurr.getString("EXCL_YN"));
									tchild.put("uptype", tcurr.getString("UPDATE_TYPE"));
									tchilds.put(tchild);
								}
							}

							fchild.put("submenu",  tchilds);
							if(tcount==0 && fcurr.has("CALL_URL")){
								fchild.put("link",  fcurr.getString("CALL_URL"));
							}
							if(chicount==0){
								childs.put(fchild);
							}
						}
					}

					wmap.put("submenu", childs);
					if(childs.length()==0){
						if(curr.has("CALL_URL")){
							wmap.put("link", curr.getString("CALL_URL"));
						}
					}

					if(inp==0){
						result.put(wmap);
					}
				}
				else{
					if(curr.has("CALL_URL")) {
						wmap.put("link", curr.getString("CALL_URL"));
					}
				}
			}

    		robj.put("mjson", result);
		}
		
		return robj;
	}

	public String getSort(String request) throws JSONException{
		String field="";
		String order="";
		String multiOrde="";
		String dir="";
		JSONObject obj= new JSONObject(request);
		JSONArray arr= obj.getJSONArray("sort");
		for(int i=0; i<arr.length();i++){
			String str=arr.get(i).toString();
			JSONObject srt= new JSONObject(str);
			if(srt.isNull("field")){
				field="";
			}
			else{
				field=srt.getString("field");
				multiOrde=multiOrde+ " "+ field;

			}
			if(srt.isNull("dir")){
				dir="";
			}
			else{
				dir=srt.getString("dir");
				multiOrde=multiOrde + " " +dir + ",";
			}
		}
		return multiOrde;
	}

	public String getFilter(String request) throws JSONException{

		JSONObject filter=null;
		String flquery="";
		JSONObject req= new JSONObject(request);
		String operator="";
		String value="";
		if(req.has("filter")){
			if(!req.isNull("filter")){
				if(req.getJSONObject("filter").has("logic")){
					filter=req.getJSONObject("filter");
				}
			}
		}
		String filterfield="";
		if(filter!=null){

			JSONObject fltr= filter;

			String logic=fltr.getString("logic");

			JSONArray arr= fltr.getJSONArray("filters");
			for(int i=0; i<arr.length();i++){
				String str=arr.get(i).toString();
				JSONObject srt= new JSONObject(str);
				if(srt.isNull("field")){
					filterfield="";
				}
				else{
					filterfield=srt.getString("field");
				}
				if(srt.isNull("operator")){
					operator="";
				}
				else{
					operator=srt.getString("operator");
				}
				if(srt.isNull("value")){
					value="";
				}
				else{
					value=String.valueOf(srt.get("value")).toLowerCase();
				}
				if(i>0){
					switch(operator){
						case "startswith":flquery=flquery+  " "+logic+" lower("+filterfield+ ") LIKE '"+value+"%'"; break;
						case "contains":flquery=flquery+  " "+logic+" lower("+filterfield+ ") LIKE '%"+value+"%'"; break;
						case "doesnotcontain":flquery=flquery+  " "+logic+" lower("+filterfield+ ") NOT LIKE '%"+value+"%'"; break;
						case "endswith":flquery=flquery+  " "+logic+" lower("+filterfield+ ") LIKE '%"+value+"'"; break;
						case "neq":flquery=flquery+  " "+logic+" lower("+filterfield+ ") != '"+value+"'"; break;
						case "eq":flquery=flquery+  " "+logic+" lower("+filterfield+ ") = '"+value+"'"; break;
						case "gte":flquery=flquery+  " "+logic+" lower("+filterfield+ ") >="+value+""; break;
					}
				}else{
					switch(operator){
						case "startswith":flquery=" Where lower("+filterfield+ ") LIKE '"+value+"%'"; break;
						case "contains":flquery=" Where lower("+filterfield+ ") LIKE '%"+value+"%'"; break;
						case "doesnotcontain":flquery=" Where lower("+filterfield+ ") NOT LIKE '%"+value+"%'"; break;
						case "endswith":flquery=" Where lower("+filterfield+ ") LIKE '%"+value+"'"; break;
						case "neq":flquery=" Where lower("+filterfield+ ") != '"+value+"'"; break;
						case "eq":flquery=" Where lower("+filterfield+ ") = '"+value+"'"; break;
						case "gte":flquery=" Where lower("+filterfield+ ") >= '"+value+"'"; break;
					}
				}
			}
		}
		return flquery;
	}

	@CacheEvict(value = "customers", key = "#id")
	public void evict(long id){
	}
}
