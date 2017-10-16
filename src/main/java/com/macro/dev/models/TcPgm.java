package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * The persistent class for the TC_PGM database table.
 * 
 */

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcPgm implements Serializable {

	private String pgm_id;
	private String pgm_desc;

	private String pgm_nm;
	private String pgm_nm_eng;
	private String parent_id;
	private String call_url;
	private String ui_icon;
	private int update_type;
	private int order_id;

	private String reg_dtm;
	private String reg_id;

	private String help_doc_id;
	private String mod_dtm;
	private String mod_id;

	private List<TcRolePgm> tcRolePgms;
	private List<TcPgm> children;
}