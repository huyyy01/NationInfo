package com.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NationInfoActivity extends AppCompatActivity {

    private NationInfo info;
    private ImageView flagImage, mapImage;
    private TextView nationName, area, population;

    @Override
    //load layout và gán các giá trị thông tin
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation_info);

        mapImage = (ImageView) findViewById(R.id.map_image);
        flagImage = (ImageView) findViewById(R.id.flag_image);
        nationName = (TextView) findViewById(R.id.country_name);
        area = (TextView) findViewById(R.id.area);
        population = (TextView) findViewById(R.id.population);

        //Sử dụng Bundle để xác định intent chỉ định các quốc gia hiển thị thông tin
        Bundle extras = getIntent().getExtras();
        String nationExtras = extras.getString("Nation");
        try{
            //dữ liệu đc gửi là 1 chuỗi có kiểu json nên phải đưa về đối tượng json
            JSONObject object = new JSONObject(nationExtras);
            //gán các giá trị hiển thị với từng mục tương ứng
            setTitle(object.getString("countryName"));
            //gán và hiển thị hình ảnh bằng thư viện picasso
            Picasso.get().load(object.getString("mapShape")).into(mapImage);
            Picasso.get().load(object.getString("countryCode")).into(flagImage);
            //hiển thị các thông tin còn lại
            nationName.setText(object.getString("countryName"));
            area.setText(object.getString("areaInSqKm"));
            population.setText(object.getString("population"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}