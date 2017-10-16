package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * The persistent class for the TC_ROLE_PGM database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcRolePgm implements Serializable {
	private static final long serialVersionUID = 1L;

	private TcPgm tcPgm;
	private TcRole tcRole;
	private String role_id;
	private String pgm_id;

	private String del_yn;
	private String save_yn;
	private String excl_yn;
	private String help_yn;
	private String init_yn;
	private String iqry_yn;

	private String mod_dtm;
	private String mod_id;
	private String prnt_yn;
	private String reg_dtm;
	private String reg_id;

	private String use_yn;

}