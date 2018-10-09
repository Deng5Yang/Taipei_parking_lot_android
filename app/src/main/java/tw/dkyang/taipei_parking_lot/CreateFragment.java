package tw.dkyang.taipei_parking_lot;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CreateFragment extends Fragment {

    private int showType;
    private String content;
    private Context context;

    public static CreateFragment newInstance(int showType,String content) {

        Bundle args = new Bundle();
        args.putInt("showType",showType);
        args.putString("content",content);
        CreateFragment fragment = new CreateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //帶入參數
        showType = getArguments().getInt("showType");
        content = getArguments().getString("content");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("checkpoint","onCreateView");
        if ("0".equals(String.valueOf(showType))){  //0=第一頁
            return LayoutInflater.from(context).inflate(R.layout.fragment_list,null);
        }else if ("1".equals(String.valueOf(showType))){    //1=第一頁
            return LayoutInflater.from(context).inflate(R.layout.fragment_map,null);
        }else{
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Log.d("checkpoint","onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        if(showType ==0){   //第一頁
            TextView message = getView().findViewById(R.id.item1);
            message.setText(content);
        }else if (showType ==1){    //第二頁
            TextView message = getView().findViewById(R.id.item2);
            message.setText(content);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

}
