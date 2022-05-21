package com.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NationActivity extends AppCompatActivity {

    //list view hiện trên ui
    private ListView listView;
    //List lưu các nước lấy đc từ json
    private final List<NationInfo> nationList = new LinkedList<>();

    @Override
    //load layout cho Activity ở giai đoạn này, thông qua phương thức setContentView()
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation);

        listView = (ListView) findViewById(R.id.list_view);

        //khởi tạo okHttpClient cho việc lấy dữ liệu JSON
        OkHttpClient client = new OkHttpClient();

        //tạo câu lệnh request thông tin bằng request
        Request request = new Request.Builder().url("http://api.geonames.org/countryInfoJSON?username=btandroid2").build();

        //thực hiện câu lệnh request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //lấy thông tin JSON trả về
                String json = response.body().string();
                try {
                    //tạo đối tương JSON từ chuỗi JSON đã lấy
                    JSONObject object = new JSONObject(json);
                    //trả về dạng mảng do JSON có kiểu geonames [{}, {}]
                    JSONArray jsonArray = object.getJSONArray("geonames");

                    for(int i=0; i<jsonArray.length(); i++){
                        //tạo các đối tượng cho các phần tử trong mảng trên và đưa vào danh sách
                        NationInfo nation =
                                new NationInfo(jsonArray.getJSONObject(i).getString("countryName"),
                                        jsonArray.getJSONObject(i).getString("countryCode"),
                                        jsonArray.getJSONObject(i).getString("population"),
                                        jsonArray.getJSONObject(i).getString("areaInSqKm")
                                        );
                        nationList.add(nation);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //sau khi thêm vào list thì hiển thị lên màn hình theo listview với adapter
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //thực hiện set adapter để hiển thị
                        NationAdapter adapter = new NationAdapter(nationList, NationActivity.this);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });

        // tạo sự kiện cho từng mục trong listview khi nhấp vào
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //thực hiển chuyển activity và gửi thông tin của item vừa bấm sang activity 2
                Intent intent = new Intent(NationActivity.this, NationInfoActivity.class);
                intent.putExtra("Nation", nationList.get(i).toString());
                startActivity(intent);
            }
        });
    }
}