package com.macro.dev.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TmMacroIndicator implements Serializable {
    private static final long serialVersionUID = 1L;
    private String miId;
    private String comCd;
    private String unitMst;
    private String useYn;
    private String regId;
    private String regDtm;
    private String modId;
    private String modDtm;
}
