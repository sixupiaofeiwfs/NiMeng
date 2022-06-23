package com.nimeng.bean;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/30 9:08
 * <p>
 * Changes (from 2022/6/30)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/30 : Create ComprehensiveDataBean.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ComprehensiveDataBean {
    private float reading;
    private float correctionValue;//修正值
    private float actualValue;//实际值
    private float readingOfTestedInstrument;//被检仪器读数
    private float temIndicationError;//温度示值误差
    private float dewPointReading;//露点读数
    private float standardCorrectionValue;//标准器修正值
    private float standardActualValue;//标准器实际值
    private float actualRelativeHumidity;//实际相对湿度
    private float wetBulbTemperature;//湿球温度
    private float relativeHumidity;//相对湿度
    private float indicationError;//示值误差


    public float getReading() {
        return reading;
    }

    public void setReading(float reading) {
        this.reading = reading;
    }

    public float getCorrectionValue() {
        return correctionValue;
    }

    public void setCorrectionValue(float correctionValue) {
        this.correctionValue = correctionValue;
    }

    public float getActualValue() {
        return actualValue;
    }

    public void setActualValue(float actualValue) {
        this.actualValue = actualValue;
    }

    public float getReadingOfTestedInstrument() {
        return readingOfTestedInstrument;
    }

    public void setReadingOfTestedInstrument(float readingOfTestedInstrument) {
        this.readingOfTestedInstrument = readingOfTestedInstrument;
    }

    public float getDewPointReading() {
        return dewPointReading;
    }

    public void setDewPointReading(float dewPointReading) {
        this.dewPointReading = dewPointReading;
    }

    public float getStandardCorrectionValue() {
        return standardCorrectionValue;
    }

    public void setStandardCorrectionValue(float standardCorrectionValue) {
        this.standardCorrectionValue = standardCorrectionValue;
    }

    public float getStandardActualValue() {
        return standardActualValue;
    }

    public void setStandardActualValue(float standardActualValue) {
        this.standardActualValue = standardActualValue;
    }

    public float getActualRelativeHumidity() {
        return actualRelativeHumidity;
    }

    public void setActualRelativeHumidity(float actualRelativeHumidity) {
        this.actualRelativeHumidity = actualRelativeHumidity;
    }


    public float getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(float relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public float getIndicationError() {
        return indicationError;
    }

    public void setIndicationError(float indicationError) {
        this.indicationError = indicationError;
    }

    public float getWetBulbTemperature() {
        return wetBulbTemperature;
    }

    public void setWetBulbTemperature(float wetBulbTemperature) {
        this.wetBulbTemperature = wetBulbTemperature;
    }

    public float getTemIndicationError() {
        return temIndicationError;
    }

    public void setTemIndicationError(float temIndicationError) {
        this.temIndicationError = temIndicationError;
    }

    public ComprehensiveDataBean() {
    }

    public ComprehensiveDataBean(float reading, float correctionValue, float actualValue, float readingOfTestedInstrument, float temIndicationError, float dewPointReading, float standardCorrectionValue, float standardActualValue, float actualRelativeHumidity, float wetBulbTemperature, float relativeHumidity, float indicationError) {
        this.reading = reading;
        this.correctionValue = correctionValue;
        this.actualValue = actualValue;
        this.readingOfTestedInstrument = readingOfTestedInstrument;
        this.temIndicationError = temIndicationError;
        this.dewPointReading = dewPointReading;
        this.standardCorrectionValue = standardCorrectionValue;
        this.standardActualValue = standardActualValue;
        this.actualRelativeHumidity = actualRelativeHumidity;
        this.wetBulbTemperature = wetBulbTemperature;
        this.relativeHumidity = relativeHumidity;
        this.indicationError = indicationError;
    }
}
