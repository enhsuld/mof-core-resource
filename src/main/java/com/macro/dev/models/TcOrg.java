package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the TC_ORG database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcOrg implements Serializable {
	private String org_cd;
	private String addr_dtl;
	private String addr1_cd;
	private String addr2_cd;
	private String aply_dt;
	private String clse_dt;
	private String fax_no;
	private String mod_dtm;
	private String mod_id;
	private String org_div_cd;
	private String org_hdcf_nm;
	private String org_lv_cd;
	private String org_nm;
	private String org_nm_eng;
	private BigDecimal org_ord;
	private String reg_dtm;
	private String reg_id;
	private String tel_no;
	private String uppr_org_cd;
	private int use_yn;
	private List<TcUser> tcUsers;

}