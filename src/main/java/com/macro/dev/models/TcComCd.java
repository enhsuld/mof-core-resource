package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The persistent class for the TC_COM_CD database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcComCd implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String com_cd;
	private String com_cd_nm;
	private String com_cd_nm_eng;
	private String grp_cd;
	private String mod_dtm;
	private String mod_id;
	private String reg_dtm;
	private String reg_id;
	private String use_yn;
}