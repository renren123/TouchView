package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView labelView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        labelView = (TextView)findViewById(R.id.event_label);
        TextView touchView = (TextView)findViewById(R.id.touch_area);
        final TextView historyView = (TextView) findViewById(R.id.history_label);

        touchView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action)
                {
                    case(MotionEvent.ACTION_DOWN) :
                        Display("ACTION_DOWN",event);
                        break;
                    case (MotionEvent.ACTION_UP):
                        int historySize = ProcessHistory(event);
                        historyView.setText("历史数据量："+historySize);
                        Display("ACTION_UP",event);
                        break;
                    case (MotionEvent.ACTION_MOVE):
                        Display("ACTION_MOVE",event);
                        break;

                }
                return true;
            }
        });
    }
    private  void Display(String eventType,MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();
        float pressure = event.getPressure();
        float size = event.getSize();
        int RawX = (int) event.getRawX();
        int RawY = (int) event.getRawY();
        String msg = "";
        msg +="事件类型："+eventType+"\n";
        msg+="相对坐标："+String.valueOf(x)+","+String.valueOf(y)+"\n";
        msg+="绝对坐标："+String.valueOf(RawX)+","+String.valueOf(RawY)+"\n";
        msg+="触点压力："+String.valueOf(pressure)+",";
        msg+="触点尺寸："+String.valueOf(size)+"\n";
        labelView.setText(msg);
    }
    private int ProcessHistory(MotionEvent event)
    {
        int historySize = event.getHistorySize();
        for(int i = 0;i<historySize;i++)
        {
            long time = event.getHistoricalEventTime(i);
            float pressure = event.getHistoricalPressure(i);
            float x= event.getHistoricalX(i);
            float y = event.getHistoricalY(i);
            float size = event.getHistoricalSize(i);

        }
        return  historySize;
    }
}
