package com.ssxt.weather;

import java.io.Serializable;

import com.ssxt.common.entity.WeatherEntity;

@SuppressWarnings("serial")
public class WeatherHelper   implements Serializable{
	public static WeatherEntity dailyToEntity(WeatherDaily from,WeatherEntity to){
		to.setShowTime(from.getDate());
		to.setSr(from.getAstro()==null?null:from.getAstro().getSr());
		to.setSs(from.getAstro()==null?null:from.getAstro().getSs());
		to.setHum(from.getHum());
		to.setPcpn(from.getPcpn());
		to.setPop(from.getPop());
		to.setPres(from.getPres());
		to.setWindDeg(from.getAstro()==null?null:from.getWind().getDeg());
		to.setWindDir(from.getAstro()==null?null:from.getWind().getDir());
		to.setWindSc(from.getWind()==null?null:from.getWind().getSc());
		to.setWindSpd(from.getWind()==null?null:from.getWind().getSpd());
		to.setDayCode(from.getCond()==null?null:from.getCond().getCode_d());
		to.setNightCode(from.getCond()==null?null:from.getCond().getCode_n());
		to.setDayTxt(from.getCond()==null?null:from.getCond().getTxt_d());
		to.setNightTxt(from.getCond()==null?null:from.getCond().getTxt_n());
		to.setTmpMax(from.getTmp()==null?null:from.getTmp().getMax());
		to.setTmpMin(from.getTmp()==null?null:from.getTmp().getMin());


		return to;
		
	}

	public static WeatherEntity basicToEntity(WeatherBasic from,WeatherEntity to){
		
//		to.setSr(from.getAstro()==null?null:from.getAstro().getSr());
//		to.setSs(from.getAstro()==null?null:from.getAstro().getSs());
		to.setHum(from.getHum());
		to.setPcpn(from.getPcpn());
//		to.setPop(from.getPop());
		to.setPres(from.getPres());
//		to.setWindDeg(from.getAstro()==null?null:from.getWind().getDeg());
//		to.setWindDir(from.getAstro()==null?null:from.getWind().getDir());
		to.setWindSc(from.getWind()==null?null:from.getWind().getSc());
		to.setWindSpd(from.getWind()==null?null:from.getWind().getSpd());
//		to.setDayCode(from.getCond()==null?null:from.getCond().getCode_d());
//		to.setNightCode(from.getCond()==null?null:from.getCond().getCode_n());
//		to.setDayTxt(from.getCond()==null?null:from.getCond().getTxt_d());
//		to.setNightTxt(from.getCond()==null?null:from.getCond().getTxt_n());
//		to.setTmpMax(from.getTmp()==null?null:from.getTmp().getMax());
//		to.setTmpMin(from.getTmp()==null?null:from.getTmp().getMin());


		return to;
		
	}

}