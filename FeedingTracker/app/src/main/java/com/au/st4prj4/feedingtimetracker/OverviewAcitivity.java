package com.au.st4prj4.feedingtimetracker;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.icu.util.LocaleData;
import android.os.Build;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.widget.TextView;

//Code source: https://getridbug.com/android/plot-data-value-on-timeline-axis-in-bar-chart-using-mpandroidchart/

@RequiresApi(api = Build.VERSION_CODES.O)
public class OverviewAcitivity extends AppCompatActivity {
    //initialize variable
    BarChart mBarChart;
    ArrayList<Feedings> input;
    TextView breastMilk,bottleMilk;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        //Assign variable
        mBarChart = findViewById(R.id.barChart);
        breastMilk= findViewById(R.id.numberOfBreastTxt);
        bottleMilk = findViewById(R.id.offeredBottleTxt);

        breastMilk.setText(String.format(getResources().getString(R.string.offeredBreast)+ " 5.33 ml"));
        bottleMilk.setText(String.format(getResources().getString(R.string.OfferedBottleText)+" 10.67 ml"));
        //Initialize array list
        ArrayList<BarEntry> barEntries = new ArrayList<>();


        input = new ArrayList<Feedings>(Arrays.asList(
                new Feedings(15,"breast",LocalDate.of(2022, 05, 15)),
                new Feedings(13,"bottle",LocalDate.of(2022,05,16)),
                new Feedings(12,"breast",LocalDate.of(2022, 05, 17)),
                new Feedings(18,"bottle",LocalDate.of(2022,05,18)),
                new Feedings(13,"breast",LocalDate.of(2022, 05, 19)),
                new Feedings(16,"bottle",LocalDate.of(2022,05,20))
                ));

        showDateBars();
    }

    private void showDateBars()
    {
        //input Y data (Months Data - 12 Values)

        ArrayList<Double> valuesList = new ArrayList<Double>();
        final ArrayList<LocalDate> xAxisLabel = new ArrayList<>();

        for (int i = 0; i < input.size(); ++i)
        {
                valuesList.add(input.get(i).Milk);
                xAxisLabel.add(input.get(i).date);
        }
        xAxisLabel.add(LocalDate.now());

        //prepare Bar Entries
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valuesList.size(); i++) {
            BarEntry barEntry = new BarEntry(i + 1, valuesList.get(i).floatValue()); //start always from x=1 for the first bar
            entries.add(barEntry);
        }
        
        //initialize xAxis
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(14);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAxisMinimum(0 + 0.5f); //to center the bars inside the vertical grid lines we need + 0.5 step
        xAxis.setAxisMaximum(entries.size() + 0.5f); //to center the bars inside the vertical grid lines we need + 0.5 step
        xAxis.setLabelCount(xAxisLabel.size(), true); //draw x labels for 13 vertical grid lines
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setXOffset(0f); //labels x offset in dps
        xAxis.setYOffset(0f); //labels y offset in dps
        xAxis.setLabelRotationAngle(-45);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int valueinput = (int)value;
                return xAxisLabel.get(valueinput).toString();
            }
        });

        //initialize Y-Right-Axis
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setTextSize(14);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setAxisLineColor(Color.BLACK);
        rightAxis.setDrawGridLines(true);
        rightAxis.setGranularity(1f);
        rightAxis.setGranularityEnabled(true);
        rightAxis.setAxisMinimum(0);
        rightAxis.setAxisMaximum(10f); //todo: der er noget galt her
        rightAxis.setLabelCount(4, true); //draw y labels (Y-Values) for 4 horizontal grid lines starting from 0 to 6000f (step=2000)
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        //initialize Y-Left-Axis
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setLabelCount(0, true);
        leftAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "ml";
            }
        });

        //set the BarDataSet
        BarDataSet barDataSet = new BarDataSet(entries, "Date");
        //barDataSet.setColor(Color.BLUE);
        barDataSet.setFormSize(15f);
        barDataSet.setDrawValues(false);
        barDataSet.setValueTextSize(12f);

        //set the BarData to chart
        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.5f);
        mBarChart.setData(data);
        mBarChart.setScaleEnabled(false);
        mBarChart.getLegend().setEnabled(false);
        mBarChart.setDrawBarShadow(false);
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawGridBackground(true);
        mBarChart.invalidate();
    }
}