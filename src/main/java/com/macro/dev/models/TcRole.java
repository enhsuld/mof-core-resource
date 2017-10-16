package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the TC_ROLE database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private String role_id;
	private String mod_dtm;
	private String mod_id;
	private String reg_dtm;
	private String reg_id;
	private String role_desc;
	private String role_nm;
	private String role_nm_eng;
	private String use_yn;
	private List<TcRolePgm> tcRolePgms;
	private List<TcUserRole> tcUserRoles;
}