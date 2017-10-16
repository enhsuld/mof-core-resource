package com.macro.dev.repository.mybatis;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.StringTypeHandler;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vaio on 10/8/2017.
 */
public class EmptyStringTypeHandler extends StringTypeHandler {

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return unnulledString(super.getResult(rs, columnName));
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return unnulledString(super.getResult(rs, columnIndex));
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return unnulledString(super.getResult(cs, columnIndex));
    }

    private String unnulledString(String value) {
        return StringUtils.defaultString(value, "0");
    }

}