package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the TC_USER database table.
 * 
 */

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long user_id;
	private String addr_dtl;
	private String addr1_cd;
	private String addr2_cd;
	private String cell_no;
	private String email;
	private String fax_no;
	private String mod_dtm;
	private String mod_id;
	private String pw_mod_dtm;
	private String reg_dtm;
	private String reg_id;
	private String tel_no;
	private String use_bgn_dt;
	private String use_end_dt;
	private int use_yn;
	private String user_nm;
	private String user_nm_eng;
	private String org_cd;
	private String user_pw;
	private TcOrg tcOrg;
	private List<TcUserAudit> tcUserAudits;
	private List<TcUserLog> tcUserLogs;
	private List<TcUserRole> tcUserRoles;
}