package com.example.wbe04.model.chart.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.wbe04.model.chart.dto.ChartProductPriceDTO;

@Repository
public class ChartProductPriceDAOImpl implements ChartProductPriceDAO {

	
	@Inject
	private SqlSession sqlSession;

	
	private final static Logger logger =LoggerFactory.getLogger(ChartProductPriceDAOImpl.class);
	
	
	private final static String namespace="chart.mapper";
	
	
	
	@Override
	public List<ChartProductPriceDTO> chartProductList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".chartProductList" );
	}





}






