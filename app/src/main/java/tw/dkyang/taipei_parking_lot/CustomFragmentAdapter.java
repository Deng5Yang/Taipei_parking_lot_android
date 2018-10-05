package tw.dkyang.taipei_parking_lot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomFragmentAdapter extends FragmentPagerAdapter{
    String mTitle1,mTitle2;

    public CustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDataOne(String msg){
        mTitle1 = msg;
        notifyDataSetChanged();
    }

    public void setDataTwo(String msg){
        mTitle2 = msg;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int pos) {
        if (pos == 0){
            return new CreateFragment().newInstance(pos,mTitle1);
        }else{
            return new CreateFragment().newInstance(pos,mTitle2);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
