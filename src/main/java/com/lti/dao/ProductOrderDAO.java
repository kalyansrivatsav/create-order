package com.lti.dao;

import com.lti.dto.ProductOrderDTO;
import com.lti.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductOrderDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private QueueService queueService;

    private final String SQL="insert into [order](productid,factoryid,selected_quantity,CreatedDate,status) values (:pid,:fid,:selected_quantity,:createdDate,'INPROCESS')";

    public String insertOrder(ProductOrderDTO pdto){

        Date current_date = new Date();

        Map<String, Object> sqlparms=new HashMap<>();
        sqlparms.put("pid",pdto.getProdId());
        sqlparms.put("fid",pdto.getFactId());
        sqlparms.put("selected_quantity",pdto.getSelectedQuantity());
        sqlparms.put("createdDate",current_date);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValues(sqlparms);

        namedParameterJdbcTemplate.update(SQL, sqlParameterSource, keyHolder);

        queueService.insertMessage(String.valueOf(keyHolder.getKey()));

        return String.valueOf(keyHolder.getKey());
    }
}
