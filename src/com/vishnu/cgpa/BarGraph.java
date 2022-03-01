package com.vishnu.cgpa;

import java.text.DecimalFormat;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class BarGraph {
	Context ctx;

	public GraphicalView getView(Context context, float [] y) 
	{	
		this.ctx=context;
		// Bar 1
		int [] x= {1,2,3,4,5,6,7,8};
		//double [] y = { 5.5, 6.5, 7.0, 8.1, 9.0, 10, 5, 8 };
		CategorySeries series = new CategorySeries("Sem/GPA");
		for (int i = 0; i < y.length; i++) {
			series.add("Bar " + x[i], y[i]);
		}
		
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		

		// This is how the "Graph" itself will look like
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.setChartTitle("Semester VS GPA Comparison");
		mRenderer.setChartTitleTextSize(pixeltodp(10));
		mRenderer.setXTitle("Semester ->");
		mRenderer.setYTitle("GPA");
		mRenderer.setLabelsTextSize(10);
		
		mRenderer.setBarSpacing(-0.75);
		mRenderer.setBarWidth(15);
		
		mRenderer.setXLabels(8);
		mRenderer.setYLabels(8);
		mRenderer.setXAxisMin(0);
		mRenderer.setXAxisMax(8);
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(10.0);
		mRenderer.setAxesColor(Color.BLACK);
		mRenderer.setLabelsColor(Color.BLACK);
		
	    mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.TRANSPARENT);
		mRenderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
		
		
		mRenderer.setPanEnabled(false);
		mRenderer.setZoomButtonsVisible(true);
		
		mRenderer.setLabelsTextSize(pixeltodp(5));
		mRenderer.setXLabelsAlign(Align.CENTER);
		mRenderer.setXLabelsColor(Color.BLACK);
		mRenderer.setYLabelsAlign(Align.CENTER);
	
		mRenderer.setYLabelsPadding(5);
		mRenderer.setYLabelsColor(0, Color.BLACK);
		mRenderer.setLegendTextSize(pixeltodp(5));
		
		
	    // Customize bar 1
		XYSeriesRenderer renderer = new XYSeriesRenderer();
	    renderer.setDisplayChartValues(true);
	    renderer.setChartValuesSpacing((float) 0.5);
	    renderer.setChartValuesFormat(new DecimalFormat("#.##"));
	    renderer.setChartValuesTextSize(pixeltodp(5));
	    renderer.setChartValuesTextAlign(Align.CENTER);
	    renderer.setChartValuesSpacing(pixeltodp(2));
	    mRenderer.addSeriesRenderer(renderer);
	    
	    
	    
		return ChartFactory.getBarChartView(context, dataset,mRenderer, Type.DEFAULT);
	}
	
	private float pixeltodp(float i) {
		// TODO Auto-generated method stub
		DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, metrics);
		
	}
}
