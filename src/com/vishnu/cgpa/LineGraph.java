package com.vishnu.cgpa;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class LineGraph{

	ArrayList<String> grades;
	double[] y;
	Context ctx;
	public GraphicalView getView(Context context,int id) {
		
		this.ctx=context;
		
		CgpaDatabaseHelper db = new CgpaDatabaseHelper(context);
		grades = db.getFullSavedCgpa(id);
		
		// Our first data
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8}; // x values!
		//double[] y =  { 5.5,6.5,7.5,8.5,9.5,7.5,8.9,7.0 }; // y values!
		y= new double[8];
		
		for(int i=3,j=0;i<11;i++,j++){
			y[j]= Double.valueOf(grades.get(i));
		}
		TimeSeries series = new TimeSeries("GPA"); 
		for( int i = 0; i < x.length; i++)
		{
			//System.out.println("x = "+x[i]+" y= "+y[i]);
			series.add(x[i], y[i]);
		}
		
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
		XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.TRANSPARENT);
		mRenderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
		mRenderer.setAxesColor(Color.BLACK);
		mRenderer.setLabelsColor(Color.BLACK);
		mRenderer.setLabelsTextSize(pixeltodp(5));
		
		mRenderer.setXLabelsAlign(Align.CENTER);
		mRenderer.setXLabelsColor(Color.BLACK);
		mRenderer.setYLabelsAlign(Align.CENTER);
		mRenderer.setYLabelsPadding(5);
		mRenderer.setYLabelsColor(0, Color.BLACK);
		
		mRenderer.setXLabels(8);
		mRenderer.setYLabels(8);
		mRenderer.setChartTitle("Semester VS GPA");
		
		//mRenderer.setMargins(new int[]{5,5,5,5}); 
		mRenderer.setChartTitleTextSize(pixeltodp(20));
		mRenderer.setXTitle("Semester ->");
		mRenderer.setYTitle("GPA/Points");
		mRenderer.setLegendTextSize(pixeltodp(5));
		mRenderer.setXAxisMin(0);
		mRenderer.setXAxisMax(8);
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(10.0);
		mRenderer.setExternalZoomEnabled(false);
		mRenderer.setPanEnabled(false);
		mRenderer.setZoomButtonsVisible(true);
	
		
		// Customization time for line 1!
		renderer.setColor(Color.RED);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		renderer.setShowLegendItem(true);
		renderer.setDisplayChartValues(true);
		renderer.setDisplayChartValuesDistance(1);
		renderer.setChartValuesFormat(new DecimalFormat("#.##"));
		renderer.setChartValuesTextSize(pixeltodp(5));
		renderer.setChartValuesTextAlign(Align.CENTER);
		

		
		return ChartFactory.getLineChartView(context, dataset, mRenderer);
		
		
	}
	private float pixeltodp(float i) {
		// TODO Auto-generated method stub
		DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, metrics);
		
	}

}
