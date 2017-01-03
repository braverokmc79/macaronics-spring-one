package com.example.wbe04.service.chart;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.wbe04.model.chart.dao.ChartProductPriceDAO;
import com.example.wbe04.model.chart.dto.ChartProductPriceDTO;


@Service
public class ChartProductPriceServiceImpl implements ChartProductPriceService {

	
	@Inject
	private ChartProductPriceDAO  chartProductPriceService;
	
	
	private static final Logger logger =LoggerFactory.getLogger(ChartProductPriceServiceImpl.class);
	
	
	@Override
	public List<ChartProductPriceDTO> chartProductList() {
		// TODO Auto-generated method stub
		return chartProductPriceService.chartProductList();
	}

}







