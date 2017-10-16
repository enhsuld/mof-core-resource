package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the TC_ATT_FILE database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcAttFile implements Serializable {
	private static final long serialVersionUID = 1L;

	private String att_id;
	private String att_nm;
	private String att_path;
	private BigDecimal att_seq;
	private String del_yn;
	private BigDecimal file_sz;
	private String reg_dtm;
	private String reg_id;
	private String rmk;
}