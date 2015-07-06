package sunny.com.cn.mpchartdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    //柱状图
    @InjectView(R.id.bar_chart)
    BarChart mBarChart;

    //饼图
    @InjectView(R.id.pie_chart)
    PieChart mChart;

    BarChart myBarChart;
    PieChart myPieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        myBarChart = mBarChart;
        myPieChart = mChart;
        initSetting();
        initData();
    }
    private void initSetting() {
        //柱状图
        mBarChart.setDrawYValues(true);

//        mBarChart.setUnit(" %");
        mBarChart.setDescription("");
        mBarChart.setMaxVisibleValueCount(10);
        // disable 3D
        mBarChart.set3DEnabled(false);
        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawBarShadow(false);
        //双击缩放
        mBarChart.setDoubleTapToZoomEnabled(false);
        //触摸
        mBarChart.setTouchEnabled(false);
        //缩放
        mBarChart.setScaleEnabled(false);
        //选中
        mBarChart.setSelected(false);

        mBarChart.setMinimumWidth(40);
        mBarChart.setDrawVerticalGrid(false);
        mBarChart.setDrawHorizontalGrid(true);
        mBarChart.setDrawGridBackground(false);
        XLabels xLabels = mBarChart.getXLabels();
        xLabels.setPosition(XLabels.XLabelPosition.BOTTOM);
        xLabels.setCenterXLabelText(true);
        xLabels.setSpaceBetweenLabels(0);
        mBarChart.setDrawYLabels(true);
        mBarChart.setDrawLegend(false);
        // add a nice and smooth animation
        mBarChart.animateY(2500);
        //饼状图
        mChart.setHoleColor(Color.rgb(235, 235, 235));

        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        mChart.setValueTypeface(tf);
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));

        mChart.setHoleRadius(50f);

        mChart.setDescription("");

        mChart.setDrawYValues(true);
        mChart.setDrawCenterText(true);

        mChart.setDrawHoleEnabled(true);

        mChart.setRotationAngle(0);

        // draws the corresponding description value into the slice
        mChart.setDrawXValues(false);

        // enable rotation of the chart by touch
        mChart.setRotationEnabled(false);

        // display percentage values
        mChart.setUsePercentValues(true);
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart.setOnChartValueSelectedListener(getActivity());
        mChart.setTouchEnabled(false);
    }
    public void initData(){
        //初始化饼状图
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        for(int i=0;i<5;i++) {
            yVals1.add(new Entry((float)(Math.random()*100), i));
            xVals.add("菜单"+i);
        }
        PieDataSet set1 = new PieDataSet(yVals1, "");
        set1.setSliceSpace(3f);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int color:Colors.pieColors){
            colors.add(color);
        }
        set1.setColors(colors);

        PieData data = new PieData(xVals, set1);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);
        mChart.invalidate();
        mChart.setValueTextSize(8);
        mChart.animateXY(1500, 1500);
        mChart.setDrawLegend(true);
//        // mChart.spin(2000, 0, 360);
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        //初始化柱状图
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
        for(int i=0;i<5;i++) {
            yVals3.add(new BarEntry((float)(Math.random()*100), i));
        }
//        if (mfz != null) {
//
//            mBarChart.setYRange(0, Float.parseFloat(mfz), true);
//        }

        BarDataSet set3 = new BarDataSet(yVals3, "Data Set");
        set3.setColors(Colors.barColors);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set3);

        BarData data4 = new BarData(xVals, dataSets);
        mBarChart.setData(data4);
        mBarChart.invalidate();
        mBarChart.notifyDataSetChanged();
    }
}
